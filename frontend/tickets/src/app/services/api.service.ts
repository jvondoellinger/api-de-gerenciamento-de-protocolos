import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Environment } from '../enviroment/enviroment';
import {
  AddInteractionRequest,
  AddMentionRequest,
  ChangeQueueAreaRequest,
  ChangeQueueRequest,
  ChangeQueueSubareaRequest,
  CreateQueueRequest,
  CreateTicketRequest,
  Pagination,
  QueueDetails,
  TicketDetails,
} from '../models/api.models';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private base = Environment.apiUrl;

  constructor(private http: HttpClient) {}

  // ── Tickets ────────────────────────────────────────────────────────────────

  getTicketsPaginated(page: number, size: number): Observable<Pagination<TicketDetails>> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<Pagination<TicketDetails>>(`${this.base}/api/ticket`, { params });
  }

  getTicketById(id: string): Observable<TicketDetails> {
    return this.http.get<TicketDetails>(`${this.base}/api/ticket/${id}`);
  }

  getTicketByNumber(number: string): Observable<TicketDetails> {
    return this.http.get<TicketDetails>(`${this.base}/api/ticket/number/${number}`);
  }

  getTicketsByAuthor(authorId: string, page: number, size: number): Observable<Pagination<TicketDetails>> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<Pagination<TicketDetails>>(`${this.base}/api/ticket/author/${authorId}`, { params });
  }

  createTicket(req: CreateTicketRequest): Observable<void> {
    return this.http.post<void>(`${this.base}/api/ticket`, req);
  }

  closeTicket(id: string): Observable<void> {
    return this.http.patch<void>(`${this.base}/api/ticket/${id}/close`, {});
  }

  addInteraction(id: string, req: AddInteractionRequest): Observable<void> {
    return this.http.post<void>(`${this.base}/api/ticket/${id}/interaction`, req);
  }

  addMention(id: string, req: AddMentionRequest): Observable<void> {
    return this.http.post<void>(`${this.base}/api/ticket/${id}/mention`, req);
  }

  removeMention(ticketId: string, mentionId: string): Observable<void> {
    return this.http.delete<void>(`${this.base}/api/ticket/${ticketId}/mention/${mentionId}`);
  }

  changeQueue(id: string, req: ChangeQueueRequest): Observable<void> {
    return this.http.patch<void>(`${this.base}/api/ticket/${id}/queue`, req);
  }

  // ── Queues ─────────────────────────────────────────────────────────────────

  getQueuesPaginated(page: number, size: number): Observable<Pagination<QueueDetails>> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<Pagination<QueueDetails>>(`${this.base}/api/ticket/queue`, { params });
  }

  getQueueById(id: string): Observable<QueueDetails> {
    return this.http.get<QueueDetails>(`${this.base}/api/ticket/queue/${id}`);
  }

  getQueuesByArea(area: string): Observable<QueueDetails[]> {
    return this.http.get<QueueDetails[]>(`${this.base}/api/ticket/queue/area/${area}`);
  }

  createQueue(req: CreateQueueRequest): Observable<void> {
    return this.http.post<void>(`${this.base}/api/ticket/queue`, req);
  }

  deleteQueue(id: string): Observable<void> {
    return this.http.delete<void>(`${this.base}/api/ticket/queue/${id}`);
  }

  changeQueueArea(id: string, req: ChangeQueueAreaRequest): Observable<void> {
    return this.http.patch<void>(`${this.base}/api/ticket/queue/${id}/area`, req);
  }

  changeQueueSubarea(id: string, req: ChangeQueueSubareaRequest): Observable<void> {
    return this.http.patch<void>(`${this.base}/api/ticket/queue/${id}/subarea`, req);
  }
}
