export default class CreateTicketRequest {
  constructor(
    public title: string,
    public queueId: string,
    public deadline: string,
  ) {}
}
