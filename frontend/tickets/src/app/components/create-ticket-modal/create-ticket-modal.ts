import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../../services/api.service';
import { QueueDetails } from '../../models/api.models';

@Component({
  selector: 'app-create-ticket-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-ticket-modal.html',
})
export class CreateTicketModal implements OnInit {
  title = '';
  queueId = '';
  deadline = '';
  queues: QueueDetails[] = [];
  loading = false;
  error = '';

  constructor(
    public activeModal: NgbActiveModal,
    private api: ApiService
  ) {}

  ngOnInit() {
    this.api.getQueuesPaginated(0, 100).subscribe({
      next: (res) => (this.queues = res.items),
      error: () => (this.error = 'Não foi possível carregar as filas.'),
    });
  }

  get valid(): boolean {
    return this.title.trim().length > 0 && this.queueId.length > 0 && this.deadline.length > 0;
  }

  submit() {
    if (!this.valid) return;
    this.loading = true;
    this.error = '';

    this.api.createTicket({
      title: this.title.trim(),
      queueId: this.queueId,
      deadline: new Date(this.deadline).toISOString(),
    }).subscribe({
      next: () => this.activeModal.close('created'),
      error: (err) => {
        this.loading = false;
        this.error = err?.error ?? 'Erro ao criar ticket.';
      },
    });
  }
}
