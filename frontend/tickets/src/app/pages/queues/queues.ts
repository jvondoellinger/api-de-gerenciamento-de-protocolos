import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../../services/api.service';
import { QueueDetails } from '../../models/api.models';
import { Navbar } from '../../components/navbar/navbar';
import { CreateQueueModal } from '../../components/create-queue-modal/create-queue-modal';
import { CreateTicketModal } from '../../components/create-ticket-modal/create-ticket-modal';

@Component({
  selector: 'app-queues',
  standalone: true,
  imports: [CommonModule, FormsModule, Navbar],
  templateUrl: './queues.html',
})
export class Queues implements OnInit {
  queues: QueueDetails[] = [];
  page = 0;
  size = 20;
  totalPages = 0;
  loading = false;
  error = '';

  // edição inline
  editingId: string | null = null;
  editArea = '';
  editSubarea = '';
  editLoading = false;
  editError = '';

  constructor(private api: ApiService, private modal: NgbModal) {}

  ngOnInit() {
    this.load();
  }

  load() {
    this.loading = true;
    this.error = '';
    this.api.getQueuesPaginated(this.page, this.size).subscribe({
      next: (res) => { this.queues = res.items; this.totalPages = res.totalPages; this.loading = false; },
      error: () => { this.error = 'Não foi possível carregar as filas.'; this.loading = false; },
    });
  }

  goTo(page: number) {
    if (page < 0 || page >= this.totalPages) return;
    this.page = page;
    this.load();
  }

  openCreateQueue() {
    const ref = this.modal.open(CreateQueueModal, { centered: true });
    ref.result.then((r) => { if (r === 'created') this.load(); }, () => {});
  }

  openCreateTicket() {
    this.modal.open(CreateTicketModal, { centered: true, size: 'lg' });
  }

  startEdit(q: QueueDetails) {
    this.editingId = q.id;
    this.editArea = q.area;
    this.editSubarea = q.subarea;
    this.editError = '';
  }

  cancelEdit() {
    this.editingId = null;
    this.editError = '';
  }

  saveEdit(q: QueueDetails) {
    this.editLoading = true;
    this.editError = '';
    const areaChanged = this.editArea.trim() !== q.area;
    const subareaChanged = this.editSubarea.trim() !== q.subarea;

    const calls: Promise<void>[] = [];

    if (areaChanged) {
      calls.push(
        new Promise((res, rej) =>
          this.api.changeQueueArea(q.id, { area: this.editArea.trim() }).subscribe({ next: () => res(), error: rej })
        )
      );
    }
    if (subareaChanged) {
      calls.push(
        new Promise((res, rej) =>
          this.api.changeQueueSubarea(q.id, { subarea: this.editSubarea.trim() }).subscribe({ next: () => res(), error: rej })
        )
      );
    }

    Promise.all(calls)
      .then(() => { this.editLoading = false; this.editingId = null; this.load(); })
      .catch(() => { this.editLoading = false; this.editError = 'Erro ao salvar alterações.'; });
  }

  deleteQueue(id: string, area: string) {
    if (!confirm(`Remover a fila "${area}"?`)) return;
    this.api.deleteQueue(id).subscribe({
      next: () => this.load(),
      error: () => alert('Não foi possível remover a fila.'),
    });
  }

  get pages(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i);
  }
}
