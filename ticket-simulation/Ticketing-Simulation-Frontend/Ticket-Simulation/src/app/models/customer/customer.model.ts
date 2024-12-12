export class Customer {
     id: string;
     userName: string;           // Unique customer identifier
     isVip: boolean;               // Whether the customer is a VIP
     numberOfTickets: number;      // Number of tickets the customer holds
  
    constructor(id:number) {
      this.id = "C" + (id+1); 
      this.userName = "Customer-"+this.id;
      this.isVip = false;
      this.numberOfTickets = 1;
    }

    public getCustomerId():string{
      return this.id;
    }
    public getCustomerName(): string {
      return this.userName;
    }
  
    public setCustomerName(name: string): void {
      this.userName = name;
    }
 
  
    public getIsVip(): boolean {
      return this.isVip;
    }
  
    public setIsVip(isVIP: boolean): void {
      this.isVip = isVIP;
    }
  
    public getNumberOfTickets(): number {
      return this.numberOfTickets;
    }
  
    public setNumberOfTickets(numberOfTickets: number): void {
      this.numberOfTickets = numberOfTickets;
    }
  

  }