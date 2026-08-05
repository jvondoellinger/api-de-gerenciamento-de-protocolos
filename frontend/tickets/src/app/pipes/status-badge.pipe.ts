import { Pipe, PipeTransform } from '@angular/core';
import { TicketStatus } from '../models/api.models';

export interface StatusBadge {
  label: string;
  css: string;
}

const STATUS_MAP: Record<TicketStatus, StatusBadge> = {
  PENDING:      { label: 'Pendente',       css: 'bg-secondary' },
  VALIDATING:   { label: 'Em análise',     css: 'bg-primary' },
  COMPLETED:    { label: 'Concluído',      css: 'bg-success' },
  INCOMPLETE:   { label: 'Incompleto',     css: 'bg-warning text-dark' },
  RETRYING:     { label: 'Retentativa',    css: 'bg-info text-dark' },
  UNPRODUCTIVE: { label: 'Improdutivo',    css: 'bg-danger' },
  CANCELED:     { label: 'Cancelado',      css: 'bg-dark' },
  PRIORITIZED:  { label: 'Prioritário',    css: 'bg-warning text-dark' },
  CLOSED:       { label: 'Fechado',        css: 'bg-secondary' },
};

@Pipe({ name: 'statusBadge', standalone: true })
export class StatusBadgePipe implements PipeTransform {
  transform(status: TicketStatus): StatusBadge {
    return STATUS_MAP[status] ?? { label: status, css: 'bg-secondary' };
  }
}
