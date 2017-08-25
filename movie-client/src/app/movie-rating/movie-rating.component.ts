import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-movie-rating',
  templateUrl: './movie-rating.component.html',
  styleUrls: ['./movie-rating.component.css']
})
export class MovieRatingComponent implements OnInit {
  @Input() rating: number;
  points: number[];

  constructor() { }

  ngOnInit() {
    this.points = [];
    for (let i: number = 0; i < 5; i++) {
      if(i + 0.5 === this.rating) {
        this.points.push(1);
      } else if(i < this.rating) {
        this.points.push(2);
      } else {
        this.points.push(0);
      }
    }
  }

}
