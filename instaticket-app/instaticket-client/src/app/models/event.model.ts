
export interface Events {
  id: number;               // Maps from `id`
  eventId: string;          // Maps from `eventId`
  eventName: string;        // Maps from `eventName`
  eventDateTime: string;    // Maps from `eventDateTime` (ISO format)
  eventCreatedDate: string; // Maps from `eventCreatedDate`
  location: string;         // Maps from `location`
  vendorId: string;         // Maps from `vendorId`
  ticketPackages: any | null; // Maps from `ticketPackages` (flexible type)
  eventImage?: string;      // Optional: Default or provided event image
}