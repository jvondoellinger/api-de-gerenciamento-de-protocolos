// ─── Queue ──────────────────────────────────────────────────────────────────
export interface QueueDetails {
  id: string;
  area: string;
  subarea: string;
  createdAt: string;
  createdBy: string;
  updatedAt: string;
  lastUpdatedBy: string;
}

// ─── Mention ─────────────────────────────────────────────────────────────────
export interface Mention {
  id: string;
  userId: string;
  mentionedById: string;
  mentionedAt: string;
  ticketId: string;
}

// ─── Ticket ──────────────────────────────────────────────────────────────────
export type TicketStatus =
  | 'PENDING'
  | 'VALIDATING'
  | 'COMPLETED'
  | 'INCOMPLETE'
  | 'RETRYING'
  | 'UNPRODUCTIVE'
  | 'CANCELED'
  | 'PRIORITIZED'
  | 'CLOSED';

export interface TicketDetails {
  id: string;
  ticketNumber: string;
  title: string;
  queue: QueueDetails;
  mentions: Mention[];
  status: TicketStatus;
  deadline: string;
  openedBy: string;
  openedOn: string;
  lastUpdatedBy: string;
  lastUpdatedOn: string;
}

// ─── Pagination ──────────────────────────────────────────────────────────────
export interface Pagination<T> {
  items: T[];
  page: number;
  size: number;
  totalPages: number;
}

// ─── Requests ────────────────────────────────────────────────────────────────
export interface CreateTicketRequest {
  title: string;
  queueId: string;
  deadline: string; // ISO 8601
}

export interface CreateQueueRequest {
  area: string;
  subarea: string;
}

export interface AddInteractionRequest {
  text: string;
  ticketId: string;
}

export interface AddMentionRequest {
  userId: string;
  ticketId: string;
}

export interface ChangeQueueRequest {
  ticketId: string;
  queueId: string;
}

export interface ChangeQueueAreaRequest {
  area: string;
}

export interface ChangeQueueSubareaRequest {
  subarea: string;
}
