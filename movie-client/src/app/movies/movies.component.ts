import { Component, OnInit } from '@angular/core';
import { Movie } from "../movie";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {
  private movies: Movie[];

  constructor() {
  }

  ngOnInit() {
  }
}
