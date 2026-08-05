import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../../services/api.service';
import { TicketDetails } from '../../models/api.models';
import { Navbar } from '../../components/navbar/navbar';
import { StatusBadgePipe } from '../../pipes/status-badge.pipe';
import { AddInteractionModal } from '../../components/add-interaction-modal/add-interaction-modal';
import { AddMentionModal } from '../../components/add-mention-modal/add-mention-modal';
import { ChangeQueueModal } from '../../components/change-queue-modal/change-queue-modal';
import { CreateTicketModal } from '../../components/create-ticket-modal/create-ticket-modal';

@Component({
  selector: 'app-ticket-detail',
  standalone: true,
  imports: [CommonModule, Navbar, StatusBadgePipe],
  templateUrl: './ticket-detail.html',
})
export class TicketDetail implements OnInit {
  ticket: TicketDetails | null = null;
  loading = false;
  error = '';
  closeLoading = false;
  closeError = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private api: ApiService,
    private modal: NgbModal,
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.load(id);
  }

  load(id: string) {
    this.loading = true;
    this.error = '';
    this.api.getTicketById(id).subscribe({
      next: (t) => { this.ticket = t; this.loading = false; },
      error: () => { this.error = 'Ticket não encontrado.'; this.loading = false; },
    });
  }

  reload() {
    if (this.ticket) this.load(this.ticket.id);
  }

  closeTicket() {
    if (!this.ticket) return;
    if (!confirm('Confirma o fechamento do ticket?')) return;
    this.closeLoading = true;
    this.api.closeTicket(this.ticket.id).subscribe({
      next: () => { this.closeLoading = false; this.reload(); },
      error: (err) => { this.closeLoading = false; this.closeError = err?.error ?? 'Erro ao fechar ticket.'; },
    });
  }

  openAddInteraction() {
    if (!this.ticket) return;
    const ref = this.modal.open(AddInteractionModal, { centered: true, size: 'lg' });
    ref.componentInstance.ticketId = this.ticket.id;
    ref.result.then((r) => { if (r === 'added') this.reload(); }, () => {});
  }

  openAddMention() {
    if (!this.ticket) return;
    const ref = this.modal.open(AddMentionModal, { centered: true });
    ref.componentInstance.ticketId = this.ticket.id;
    ref.result.then((r) => { if (r === 'added') this.reload(); }, () => {});
  }

  openChangeQueue() {
    if (!this.ticket) return;
    const ref = this.modal.open(ChangeQueueModal, { centered: true });
    ref.componentInstance.ticketId = this.ticket.id;
    ref.componentInstance.currentQueueId = this.ticket.queue.id;
    ref.result.then((r) => { if (r === 'changed') this.reload(); }, () => {});
  }

  removeMention(mentionId: string) {
    if (!this.ticket) return;
    if (!confirm('Remover esta menção?')) return;
    this.api.removeMention(this.ticket.id, mentionId).subscribe({
      next: () => this.reload(),
      error: () => alert('Erro ao remover menção.'),
    });
  }

  openCreateTicket() {
    this.modal.open(CreateTicketModal, { centered: true, size: 'lg' });
  }

  get isClosed(): boolean {
    return this.ticket?.status === 'CLOSED' || this.ticket?.status === 'CANCELED';
  }

  get deadlinePassed(): boolean {
    return !!this.ticket && new Date(this.ticket.deadline) < new Date();
  }
}
