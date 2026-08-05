import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-create-queue-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="modal-header">
      <h5 class="modal-title fw-bold">Nova Fila</h5>
      <button class="btn-close" (click)="activeModal.dismiss()"></button>
    </div>
    <div class="modal-body">
      @if (error) { <div class="alert alert-danger py-2">{{ error }}</div> }
      <div class="mb-3">
        <label class="form-label fw-semibold">Área <span class="text-danger">*</span></label>
        <input type="text" class="form-control" [(ngModel)]="area" placeholder="Ex: Infraestrutura" />
      </div>
      <div class="mb-3">
        <label class="form-label fw-semibold">Subárea <span class="text-danger">*</span></label>
        <input type="text" class="form-control" [(ngModel)]="subarea" placeholder="Ex: Redes" />
      </div>
    </div>
    <div class="modal-footer">
      <button class="btn btn-outline-secondary" (click)="activeModal.dismiss()">Cancelar</button>
      <button class="btn btn-primary fw-semibold" (click)="submit()" [disabled]="!area.trim() || !subarea.trim() || loading">
        @if (loading) { <span class="spinner-border spinner-border-sm me-1"></span> }
        Criar fila
      </button>
    </div>
  `,
})
export class CreateQueueModal {
  area = '';
  subarea = '';
  loading = false;
  error = '';

  constructor(public activeModal: NgbActiveModal, private api: ApiService) {}

  submit() {
    if (!this.area.trim() || !this.subarea.trim()) return;
    this.loading = true;
    this.api.createQueue({ area: this.area.trim(), subarea: this.subarea.trim() }).subscribe({
      next: () => this.activeModal.close('created'),
      error: (err) => { this.loading = false; this.error = err?.error ?? 'Erro ao criar fila.'; },
    });
  }
}
