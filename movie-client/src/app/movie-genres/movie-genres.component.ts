import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-movie-genres',
  templateUrl: './movie-genres.component.html',
  styleUrls: ['./movie-genres.component.css']
})
export class MovieGenresComponent implements OnInit {
  @Input() genres: string[];

  constructor() { }

  ngOnInit() {
  }

}
