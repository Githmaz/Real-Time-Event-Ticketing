import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-alert-message',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './alert-message.component.html',
  styleUrl: './alert-message.component.css'
})
export class AlertMessageComponent {
  @Input({ required: true }) type: 'info' | 'error' | 'success' | 'warning' | 'dark' = 'info'; // Default: 'info'
  @Input({ required: true }) message: string = 'This is a default alert message!'; // Default message
  @Input() duration: number = 5000; // Default duration (5 seconds)


  get alertClass(): string {
    const typeClasses: { [key: string]: string } = {
      info: 'text-blue-800 border-blue-300 bg-blue-50 dark:bg-gray-800 dark:text-blue-400 dark:border-blue-800',
      error: 'text-red-800 border-red-300 bg-red-50 dark:bg-gray-800 dark:text-red-400 dark:border-red-800',
      success: 'text-green-800 border-green-300 bg-green-50 dark:bg-gray-800 dark:text-green-400 dark:border-green-800',
      warning: 'text-yellow-800 border-yellow-300 bg-yellow-50 dark:bg-gray-800 dark:text-yellow-300 dark:border-yellow-800',
      dark: 'text-gray-800 border-gray-300 bg-gray-50 dark:bg-gray-800 dark:text-gray-300 dark:border-gray-600'
    };
    return typeClasses[this.type] || typeClasses['info'];
  }


}
