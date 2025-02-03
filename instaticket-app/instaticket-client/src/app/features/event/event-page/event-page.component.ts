import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-event-page',
  standalone: true,
  imports: [],
  templateUrl: './event-page.component.html',
  styleUrl: './event-page.component.css'
})
export class EventPageComponent {

  constructor(private readonly route:ActivatedRoute){}

  ngOnInit():void{
    this.route.params.subscribe(params => {
      console.log(params['eventId']);
    })
  }

 fetchEventDetails(eventId : string){
  
 }

}
