import { Component } from '@angular/core';

@Component({
  selector: 'app-client-search-form',
  templateUrl: './client-search-form.component.html',
  styleUrls: ['./client-search-form.component.css']
})
export class ClientSearchFormComponent {
  dataQuality= ["Complete", "Partial", "Client Did Not Know", "Client Refused", "Data Not Collected"]
  monthsDays=[
    {month: 'January',
     days: 30},
     {month: 'February',
     days: 29},
     {month: 'March',
     days: 31},
     {month: 'April',
     days: 30},
     {month: 'May',
     days: 31},
     {month: 'June',
     days: 30},
     {month: 'July',
     days: 31},
     {month: 'August',
     days: 31},
     {month: 'September',
     days: 30},
     {month: 'October',
     days: 31},
     {month: 'November',
     days: 30},
     {month: 'December',
     days: 31},
  ]

  days: number[]=[];
  days29=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29];
  days30=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30];
  days31=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31];


  months31Days=["January", "March", "May", "July", "August", "October", "December"];
  months30Days=["April", "June", "September", "November"];

  monthSelected(event: Event){
    const monthSelected = (event.target as HTMLSelectElement).value
    if(this.months31Days.includes(monthSelected)){
      this.days = this.days31;
    }
    if(this.months30Days.includes(monthSelected)){
      this.days=this.days30;
    }
    if(monthSelected==='February'){
      this.days=this.days29;
    }
  }
}
