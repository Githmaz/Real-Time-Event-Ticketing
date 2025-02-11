import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-payment-form',
  standalone: true,
  imports: [],
  templateUrl: './payment-form.component.html',
  styleUrl: './payment-form.component.css'
})
export class PaymentFormComponent {
  @Input() subtotal: number = 0;
  @Input() discount: number = 0;
  @Input() tax: number = 0;
  @Input() total: number = 0;
}
