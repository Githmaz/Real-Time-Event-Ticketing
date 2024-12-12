import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private alertSubject = new BehaviorSubject<{ message: string; type: string } | null>(null);

  // Observable for alert data
  alertState$ = this.alertSubject.asObservable();

  // Show alert
  showAlert(message: string, type: 'success' | 'info' | 'error' | 'warning' | 'dark', duration: number = 3000): void {
    this.alertSubject.next({ message, type });

    // Automatically dismiss the alert after the duration
    setTimeout(() => {
      this.dismissAlert();
    }, duration);
  }

  // Dismiss alert
  dismissAlert(): void {
    this.alertSubject.next(null);
  }
}
