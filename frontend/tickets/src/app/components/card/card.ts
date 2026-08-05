import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TicketDetails } from '../../models/api.models';
import { StatusBadgePipe } from '../../pipes/status-badge.pipe';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [CommonModule, RouterModule, StatusBadgePipe],
  templateUrl: './card.html',
})
export class Card {
  @Input({ required: true }) ticket!: TicketDetails;
  @Output() viewDetail = new EventEmitter<string>();

  get durationDays(): number {
    return Math.ceil(
      (Date.now() - new Date(this.ticket.openedOn).getTime()) / 86_400_000
    );
  }

  get deadlinePassed(): boolean {
    return new Date(this.ticket.deadline) < new Date();
  }
}
