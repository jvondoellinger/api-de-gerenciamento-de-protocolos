import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-add-mention-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="modal-header">
      <h5 class="modal-title fw-bold">Adicionar Menção</h5>
      <button class="btn-close" (click)="activeModal.dismiss()"></button>
    </div>
    <div class="modal-body">
      @if (error) { <div class="alert alert-danger py-2">{{ error }}</div> }
      <div class="mb-3">
        <label class="form-label fw-semibold">UUID do usuário <span class="text-danger">*</span></label>
        <input
          type="text"
          class="form-control font-monospace"
          [(ngModel)]="userId"
          placeholder="xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
        />
        <small class="text-muted">Informe o ID do usuário a ser mencionado.</small>
      </div>
    </div>
    <div class="modal-footer">
      <button class="btn btn-outline-secondary" (click)="activeModal.dismiss()">Cancelar</button>
      <button class="btn btn-primary fw-semibold" (click)="submit()" [disabled]="!userId.trim() || loading">
        @if (loading) { <span class="spinner-border spinner-border-sm me-1"></span> }
        Mencionar
      </button>
    </div>
  `,
})
export class AddMentionModal {
  @Input() ticketId!: string;
  userId = '';
  loading = false;
  error = '';

  constructor(public activeModal: NgbActiveModal, private api: ApiService) {}

  submit() {
    if (!this.userId.trim()) return;
    this.loading = true;
    this.api.addMention(this.ticketId, { userId: this.userId.trim(), ticketId: this.ticketId }).subscribe({
      next: () => this.activeModal.close('added'),
      error: (err) => { this.loading = false; this.error = err?.error ?? 'Erro ao mencionar usuário.'; },
    });
  }
}
