import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, map } from 'rxjs';
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

  /**
   * O backend retorna Content-Type: application/octet-stream mesmo para JSON.
   * Usamos responseType: 'text' para receber o corpo bruto e parseamos manualmente.
   */
  private get<T>(url: string, params?: HttpParams): Observable<T> {
    return this.http
      .get(url, { responseType: 'text', params })
      .pipe(map((body) => JSON.parse(body) as T));
  }

  private post<T>(url: string, body: unknown): Observable<T> {
    return this.http
      .post(url, body, { responseType: 'text' })
      .pipe(map((res) => (res ? (JSON.parse(res) as T) : (null as T))));
  }

  private patch<T>(url: string, body: unknown): Observable<T> {
    return this.http
      .patch(url, body, { responseType: 'text' })
      .pipe(map((res) => (res ? (JSON.parse(res) as T) : (null as T))));
  }

  private delete<T>(url: string): Observable<T> {
    return this.http
      .delete(url, { responseType: 'text' })
      .pipe(map((res) => (res ? (JSON.parse(res) as T) : (null as T))));
  }

  // ── Tickets ────────────────────────────────────────────────────────────────

  // GET /api/ticket?page=0&size=12
  getTicketsPaginated(page: number, size: number): Observable<Pagination<TicketDetails>> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.get<Pagination<TicketDetails>>(`${this.base}/api/ticket`, params);
  }

  // GET /api/ticket/{id}
  getTicketById(id: string): Observable<TicketDetails> {
    return this.get<TicketDetails>(`${this.base}/api/ticket/${id}`);
  }

  // GET /api/ticket/number?number=XXX
  getTicketByNumber(number: string): Observable<TicketDetails> {
    const params = new HttpParams().set('number', number);
    return this.get<TicketDetails>(`${this.base}/api/ticket/number`, params);
  }

  // GET /api/ticket/author/{authorId}?page=0&size=12
  getTicketsByAuthor(authorId: string, page: number, size: number): Observable<Pagination<TicketDetails>> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.get<Pagination<TicketDetails>>(`${this.base}/api/ticket/author/${authorId}`, params);
  }

  // POST /api/ticket  { title, queueId, deadline }
  createTicket(req: CreateTicketRequest): Observable<void> {
    return this.post<void>(`${this.base}/api/ticket`, req);
  }

  // PATCH /api/ticket/api/ticket/close  { ticketId }
  closeTicket(ticketId: string): Observable<void> {
    return this.patch<void>(`${this.base}/api/ticket/api/ticket/close`, { ticketId });
  }

  // PATCH /api/ticket/add/interaction  { text, ticketId }
  addInteraction(req: AddInteractionRequest): Observable<void> {
    return this.patch<void>(`${this.base}/api/ticket/add/interaction`, req);
  }

  // PATCH /api/ticket  { userId, ticketId }
  addMention(req: AddMentionRequest): Observable<void> {
    return this.patch<void>(`${this.base}/api/ticket`, req);
  }

  // DELETE /api/ticket/{ticketId}/mention/{mentionId}
  removeMention(ticketId: string, mentionId: string): Observable<void> {
    return this.delete<void>(`${this.base}/api/ticket/${ticketId}/mention/${mentionId}`);
  }

  // PATCH /api/ticket/change/queue  { ticketId, queueId }
  changeQueue(req: ChangeQueueRequest): Observable<void> {
    return this.patch<void>(`${this.base}/api/ticket/change/queue`, req);
  }

  // ── Queues ─────────────────────────────────────────────────────────────────

  // GET /api/ticket/queue/pagination?page=0&size=20
  getQueuesPaginated(page: number, size: number): Observable<Pagination<QueueDetails>> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.get<Pagination<QueueDetails>>(`${this.base}/api/ticket/queue/pagination`, params);
  }

  // GET /api/ticket/queue/{id}
  getQueueById(id: string): Observable<QueueDetails> {
    return this.get<QueueDetails>(`${this.base}/api/ticket/queue/${id}`);
  }

  // GET /api/ticket/queue/area/{area}
  getQueuesByArea(area: string): Observable<QueueDetails[]> {
    return this.get<QueueDetails[]>(`${this.base}/api/ticket/queue/area/${area}`);
  }

  // POST /api/queue  { area, subarea }
  createQueue(req: CreateQueueRequest): Observable<void> {
    return this.post<void>(`${this.base}/api/queue`, req);
  }

  // DELETE /api/queue/{id}
  deleteQueue(id: string): Observable<void> {
    return this.delete<void>(`${this.base}/api/queue/${id}`);
  }

  // PATCH /api/queue/area  { id, area }
  changeQueueArea(id: string, req: ChangeQueueAreaRequest): Observable<void> {
    return this.patch<void>(`${this.base}/api/queue/area`, { id, ...req });
  }

  // PATCH /api/queue  { id, subarea }
  changeQueueSubarea(id: string, req: ChangeQueueSubareaRequest): Observable<void> {
    return this.patch<void>(`${this.base}/api/queue`, { id, ...req });
  }
}
