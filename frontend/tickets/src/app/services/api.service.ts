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

  // GET /api/ticket?page=0&size=12
  getTicketsPaginated(page: number, size: number): Observable<Pagination<TicketDetails>> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<Pagination<TicketDetails>>(`${this.base}/api/ticket`, { params });
  }

  // GET /api/ticket/{id}
  getTicketById(id: string): Observable<TicketDetails> {
    return this.http.get<TicketDetails>(`${this.base}/api/ticket/${id}`);
  }

  // GET /api/ticket/number?number=XXX
  getTicketByNumber(number: string): Observable<TicketDetails> {
    const params = new HttpParams().set('number', number);
    return this.http.get<TicketDetails>(`${this.base}/api/ticket/number`, { params });
  }

  // GET /api/ticket/author/{authorId}?page=0&size=12
  getTicketsByAuthor(authorId: string, page: number, size: number): Observable<Pagination<TicketDetails>> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<Pagination<TicketDetails>>(`${this.base}/api/ticket/author/${authorId}`, { params });
  }

  // POST /api/ticket  { title, queueId, deadline }
  createTicket(req: CreateTicketRequest): Observable<void> {
    return this.http.post<void>(`${this.base}/api/ticket`, req);
  }

  // PATCH /api/ticket/api/ticket/close  { ticketId }
  // (o CloseTicketEndpoint tem @PatchMapping("/api/ticket/close") dentro de @RequestMapping("/api/ticket"))
  closeTicket(ticketId: string): Observable<void> {
    return this.http.patch<void>(`${this.base}/api/ticket/api/ticket/close`, { ticketId });
  }

  // PATCH /api/ticket/add/interaction  { text, ticketId }
  addInteraction(req: AddInteractionRequest): Observable<void> {
    return this.http.patch<void>(`${this.base}/api/ticket/add/interaction`, req);
  }

  // PATCH /api/ticket  { userId, ticketId }
  addMention(req: AddMentionRequest): Observable<void> {
    return this.http.patch<void>(`${this.base}/api/ticket`, req);
  }

  // DELETE /api/ticket/{ticketId}/mention/{mentionId}
  removeMention(ticketId: string, mentionId: string): Observable<void> {
    return this.http.delete<void>(`${this.base}/api/ticket/${ticketId}/mention/${mentionId}`);
  }

  // PATCH /api/ticket/change/queue  { ticketId, queueId }
  changeQueue(req: ChangeQueueRequest): Observable<void> {
    return this.http.patch<void>(`${this.base}/api/ticket/change/queue`, req);
  }

  // ── Queues ─────────────────────────────────────────────────────────────────

  // GET /api/ticket?page=0&size=20  (FindTicketByPagination e FindQueueByPagination
  // usam o mesmo /api/ticket — não há endpoint de queue paginado com path /api/queue)
  // Nota: CreateQueue, ChangeArea, ChangeSubarea ficam em /api/queue
  getQueuesPaginated(page: number, size: number): Observable<Pagination<QueueDetails>> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<Pagination<QueueDetails>>(`${this.base}/api/ticket/queue/pagination`, { params });
  }

  // GET /api/ticket/queue/{id}  (sem endpoint próprio mapeado — usa /api/ticket/{id} por ora)
  getQueueById(id: string): Observable<QueueDetails> {
    return this.http.get<QueueDetails>(`${this.base}/api/ticket/queue/${id}`);
  }

  // GET /api/ticket/queue/area/{area}
  getQueuesByArea(area: string): Observable<QueueDetails[]> {
    return this.http.get<QueueDetails[]>(`${this.base}/api/ticket/queue/area/${area}`);
  }

  // POST /api/queue  { area, subarea }
  createQueue(req: CreateQueueRequest): Observable<void> {
    return this.http.post<void>(`${this.base}/api/queue`, req);
  }

  // DELETE /api/queue/{id}  (sem endpoint próprio mapeado — a ser implementado)
  deleteQueue(id: string): Observable<void> {
    return this.http.delete<void>(`${this.base}/api/queue/${id}`);
  }

  // PATCH /api/queue/area  { id, area }
  changeQueueArea(id: string, req: ChangeQueueAreaRequest): Observable<void> {
    return this.http.patch<void>(`${this.base}/api/queue/area`, { id, ...req });
  }

  // PATCH /api/queue  { id, subarea }
  changeQueueSubarea(id: string, req: ChangeQueueSubareaRequest): Observable<void> {
    return this.http.patch<void>(`${this.base}/api/queue`, { id, ...req });
  }
}
