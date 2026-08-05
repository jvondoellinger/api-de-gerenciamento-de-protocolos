import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../../services/api.service';
import { QueueDetails } from '../../models/api.models';

@Component({
  selector: 'app-change-queue-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="modal-header">
      <h5 class="modal-title fw-bold">Mudar Fila</h5>
      <button class="btn-close" (click)="activeModal.dismiss()"></button>
    </div>
    <div class="modal-body">
      @if (error) { <div class="alert alert-danger py-2">{{ error }}</div> }
      <div class="mb-3">
        <label class="form-label fw-semibold">Nova fila <span class="text-danger">*</span></label>
        <select class="form-select" [(ngModel)]="newQueueId">
          <option value="">Selecione uma fila...</option>
          @for (q of queues; track q.id) {
            <option [value]="q.id" [disabled]="q.id === currentQueueId">
              {{ q.area }} / {{ q.subarea }}{{ q.id === currentQueueId ? ' (atual)' : '' }}
            </option>
          }
        </select>
      </div>
    </div>
    <div class="modal-footer">
      <button class="btn btn-outline-secondary" (click)="activeModal.dismiss()">Cancelar</button>
      <button class="btn btn-primary fw-semibold" (click)="submit()" [disabled]="!newQueueId || loading">
        @if (loading) { <span class="spinner-border spinner-border-sm me-1"></span> }
        Mover
      </button>
    </div>
  `,
})
export class ChangeQueueModal implements OnInit {
  @Input() ticketId!: string;
  @Input() currentQueueId!: string;
  queues: QueueDetails[] = [];
  newQueueId = '';
  loading = false;
  error = '';

  constructor(public activeModal: NgbActiveModal, private api: ApiService) {}

  ngOnInit() {
    this.api.getQueuesPaginated(0, 100).subscribe({
      next: (res) => (this.queues = res.items),
      error: () => (this.error = 'Não foi possível carregar as filas.'),
    });
  }

  submit() {
    if (!this.newQueueId) return;
    this.loading = true;
    this.api.changeQueue({ ticketId: this.ticketId, queueId: this.newQueueId }).subscribe({
      next: () => this.activeModal.close('changed'),
      error: (err) => { this.loading = false; this.error = err?.error ?? 'Erro ao mudar fila.'; },
    });
  }
}
