import { Component, OnInit } from '@angular/core';
import { Movie } from "../movie";
import { MovieService } from "./movie.service";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {
  private movies: Movie[];

  constructor(public movieService: MovieService) {
  }

  ngOnInit() {
   this.movieService.movies.subscribe(movies => {
     this.movies = movies;
   });
  }
}
