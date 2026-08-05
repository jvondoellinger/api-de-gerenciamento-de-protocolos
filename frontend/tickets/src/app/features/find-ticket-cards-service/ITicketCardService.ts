import { InjectionToken } from '@angular/core';
import { TicketDetails } from '../../models/api.models';

export interface ITicketCardService {
  fetchTickets(): Promise<TicketDetails[]>;
}

export const TICKET_CARD_SERVICE = new InjectionToken<ITicketCardService>('TICKET_CARD_SERVICE');
