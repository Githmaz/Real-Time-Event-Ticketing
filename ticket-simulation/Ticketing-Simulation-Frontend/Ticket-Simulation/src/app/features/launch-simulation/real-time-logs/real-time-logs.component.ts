import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../../../services/webSocket/web-socket.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-real-time-logs',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './real-time-logs.component.html',
  styleUrl: './real-time-logs.component.css'
})
export class RealTimeLogsComponent implements OnInit {
  logData : string[] = [];
  constructor(private webSocketService:WebSocketService){
    
  }
  ngOnInit(): void {
    this.webSocketService.client.onConnect = () => {
      console.log('WebSocket connection established in Angular!');
    };
    this.webSocketService.message$.subscribe((message) => {
      this.logData.push(message);
    });
  }
  
}
