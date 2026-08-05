import { Injectable } from '@angular/core';
import { ITicketCardService } from './ITicketCardService';
import { TicketDetails } from '../../models/api.models';

@Injectable({ providedIn: 'root' })
export class TicketCardsMockService implements ITicketCardService {
  async fetchTickets(): Promise<TicketDetails[]> {
    return [
      {
        id: '00000000-0000-0000-0000-000000000001',
        ticketNumber: 'MOCK-001',
        title: 'Ticket de exemplo (mock)',
        queue: { id: 'q1', area: 'TI', subarea: 'Suporte', createdAt: '', createdBy: '', updatedAt: '', lastUpdatedBy: '' },
        mentions: [],
        status: 'PENDING',
        deadline: new Date(Date.now() + 86400000 * 3).toISOString(),
        openedBy: '00000000-0000-0000-0000-000000000001',
        openedOn: new Date().toISOString(),
        lastUpdatedBy: '00000000-0000-0000-0000-000000000001',
        lastUpdatedOn: new Date().toISOString(),
      },
    ];
  }
}
