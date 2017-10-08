import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Movie } from '../movie';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  public movies: Movie[];
  private searchTerm$ = new Subject<string>();
  private socket = Observable.webSocket('ws://localhost:8080');

  constructor() {
    this.movies = [];
  }

  ngOnInit() {
    this.searchTerm$
      .filter(term => term.length >= 3)
      .debounceTime(300)
      .distinctUntilChanged()
      .subscribe(term => this.search(term));

    this.socket.subscribe(movie => {
      if (movie.hasOwnProperty('_id')) {
        this.movies.push(<Movie>movie);
      }
    });
  }

  searchChanged(term: string) {
    this.searchTerm$.next(term);
  }

  search(term: string) {
    this.movies = [];
    this.socket.next(JSON.stringify({
      action: 'search',
      body: term
    }));
  }
}
