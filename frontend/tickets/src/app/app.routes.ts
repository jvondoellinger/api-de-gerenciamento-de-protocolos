import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./pages/home/home').then((m) => m.Home),
  },
  {
    path: 'ticket/:id',
    loadComponent: () => import('./pages/ticket-detail/ticket-detail').then((m) => m.TicketDetail),
  },
  {
    path: 'queues',
    loadComponent: () => import('./pages/queues/queues').then((m) => m.Queues),
  },
  {
    path: '**',
    redirectTo: '',
  },
];
