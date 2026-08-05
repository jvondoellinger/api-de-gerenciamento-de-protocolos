import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-add-interaction-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="modal-header">
      <h5 class="modal-title fw-bold">Adicionar Interação</h5>
      <button class="btn-close" (click)="activeModal.dismiss()"></button>
    </div>
    <div class="modal-body">
      @if (error) { <div class="alert alert-danger py-2">{{ error }}</div> }
      <div class="mb-3">
        <label class="form-label fw-semibold">Texto <span class="text-danger">*</span></label>
        <textarea class="form-control" rows="4" [(ngModel)]="text" placeholder="Descreva a interação..."></textarea>
      </div>
    </div>
    <div class="modal-footer">
      <button class="btn btn-outline-secondary" (click)="activeModal.dismiss()">Cancelar</button>
      <button class="btn btn-primary fw-semibold" (click)="submit()" [disabled]="!text.trim() || loading">
        @if (loading) { <span class="spinner-border spinner-border-sm me-1"></span> }
        Adicionar
      </button>
    </div>
  `,
})
export class AddInteractionModal {
  @Input() ticketId!: string;
  text = '';
  loading = false;
  error = '';

  constructor(public activeModal: NgbActiveModal, private api: ApiService) {}

  submit() {
    if (!this.text.trim()) return;
    this.loading = true;
    this.api.addInteraction(this.ticketId, { text: this.text.trim(), ticketId: this.ticketId }).subscribe({
      next: () => this.activeModal.close('added'),
      error: (err) => { this.loading = false; this.error = err?.error ?? 'Erro ao adicionar interação.'; },
    });
  }
}
