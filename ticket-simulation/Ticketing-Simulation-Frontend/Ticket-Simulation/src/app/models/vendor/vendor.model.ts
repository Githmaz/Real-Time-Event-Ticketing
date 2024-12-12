export class Vendor {
     vendorId: string;        // Unique vendor identifier
     userName: string;      // Vendor name
     totalTickets: number;    // Total tickets sold
     ticketPrice: number;     // Ticket price
  
    constructor(id: number) {
      this.vendorId = `V${id}`;
      this.userName = `Vendor-${this.vendorId}`;
      this.ticketPrice = 100;
      this.totalTickets = 1;
    }
  
    // Getters and Setters
    public getVendorId(): string {
      return this.vendorId;
    }
  
    public getVendorName(): string {
      return this.userName;
    }
  
    public setVendorName(name: string): void {
      this.userName = name;
    }
  
    public getTotalTickets(): number {
      return this.totalTickets;
    }
  
    public setTotalTickets(tickets: number): void {
      this.totalTickets = tickets;
    }
  
    public getTicketPrice(): number {
      return this.ticketPrice;
    }
  
    public setTicketPrice(price: number): void {
      this.ticketPrice = price;
    }
  }