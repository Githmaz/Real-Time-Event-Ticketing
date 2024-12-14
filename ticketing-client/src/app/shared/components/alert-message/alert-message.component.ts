import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-alert-message',
  standalone: true,
  imports: [],
  templateUrl: './alert-message.component.html',
  styleUrl: './alert-message.component.css'
})
export class AlertMessageComponent {
    @Input() title: string = 'Are you sure?'; // Default title
    @Input() message: string = 'Do you really want to continue? This process cannot be undone'; // Default message
    @Input() cancelLabel: string = 'Cancel'; // Default cancel button label
    @Input() confirmLabel: string = 'Confirm'; // Default confirm button label
}
