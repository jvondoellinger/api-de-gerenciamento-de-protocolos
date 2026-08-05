import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Card } from '../../components/card/card';
import { ITicketCardService, TICKET_CARD_SERVICE } from '../../features/find-ticket-cards-service/ITicketCardService';
import { TicketDetails } from '../../models/api.models';

@Component({
  selector: 'app-card-grid',
  standalone: true,
  imports: [CommonModule, Card],
  templateUrl: './card-grid.html',
})
export class CardGrid {
  items: TicketDetails[] = [];

  constructor(
    @Inject(TICKET_CARD_SERVICE) private service: ITicketCardService,
    private router: Router,
  ) {
    this.service.fetchTickets().then((tickets) => (this.items = tickets));
  }

  viewDetail(id: string) {
    this.router.navigate(['/ticket', id]);
  }
}
