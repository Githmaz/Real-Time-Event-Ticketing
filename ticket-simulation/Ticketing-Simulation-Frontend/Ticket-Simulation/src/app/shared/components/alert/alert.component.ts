import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { AlertService } from '../../../services/alert/alert.service';

@Component({
  selector: 'app-alert',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './alert.component.html',
  styleUrl: './alert.component.css'
})
export class AlertComponent {
  message: string | null = null;
  type: string | null = null;
  visible = false;

  constructor(private alertService: AlertService) {}

  ngOnInit(): void {
    this.alertService.alertState$.subscribe((alert) => {
      if (alert) {
        this.message = alert.message;
        this.type = alert.type;
        this.visible = true;
      } else {
        this.visible = false;
      }
    });
  }
}
