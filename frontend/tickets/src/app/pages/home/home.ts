import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../../services/api.service';
import { TicketDetails } from '../../models/api.models';
import { Card } from '../../components/card/card';
import { Navbar } from '../../components/navbar/navbar';
import { CreateTicketModal } from '../../components/create-ticket-modal/create-ticket-modal';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, Card, Navbar],
  templateUrl: './home.html',
})
export class Home implements OnInit {
  tickets: TicketDetails[] = [];
  page = 0;
  size = 12;
  totalPages = 0;
  loading = false;
  error = '';
  search = '';

  constructor(
    private api: ApiService,
    private router: Router,
    private modal: NgbModal,
  ) {}

  ngOnInit() {
    this.load();
  }

  load() {
    this.loading = true;
    this.error = '';
    this.api.getTicketsPaginated(this.page, this.size).subscribe({
      next: (res) => {
        this.tickets = res.items;
        this.totalPages = res.totalPages;
        this.loading = false;
      },
      error: () => {
        this.error = 'Não foi possível carregar os tickets.';
        this.loading = false;
      },
    });
  }

  goTo(page: number) {
    if (page < 0 || page >= this.totalPages) return;
    this.page = page;
    this.load();
  }

  openCreateTicket() {
    const ref = this.modal.open(CreateTicketModal, { centered: true, size: 'lg' });
    ref.result.then(
      (result) => { if (result === 'created') this.load(); },
      () => {}
    );
  }

  viewDetail(id: string) {
    this.router.navigate(['/ticket', id]);
  }

  get pages(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i);
  }
}
