import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private readonly alertSubject = new BehaviorSubject<{ type: string; message: string } | null>(null);
  alert$ = this.alertSubject.asObservable();

  get alert() {
    return this.alertSubject.value;
  }

  showAlert(type: 'info' | 'error' | 'success' | 'warning' | 'dark', message: string, duration: number = 5000): void {
    this.alertSubject.next({ type, message });
    setTimeout(() => this.clearAlert(), duration);
  }

  clearAlert(): void {
    this.alertSubject.next(null);
  }
}
