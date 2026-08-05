import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { ITicketCardService } from './ITicketCardService';
import { TicketDetails, Pagination } from '../../models/api.models';
import { Environment } from '../../enviroment/enviroment';

@Injectable({ providedIn: 'root' })
export class TicketCardsService implements ITicketCardService {
  private url = `${Environment.apiUrl}/api/ticket`;

  constructor(private http: HttpClient) {}

  async fetchTickets(): Promise<TicketDetails[]> {
    const res = await firstValueFrom(
      this.http.get<Pagination<TicketDetails>>(this.url, { params: { page: 0, size: 20 } })
    );
    return res.items;
  }
}
