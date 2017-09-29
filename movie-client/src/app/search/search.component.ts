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

  constructor() {
    this.movies = [];
  }

  ngOnInit() {
    this.searchTerm$
      .filter(term => term.length >= 3)
      .debounceTime(300)
      .distinctUntilChanged()
      .subscribe(term => this.search(term));
  }

  searchChanged(term: string) {
    this.searchTerm$.next(term);
  }

  search(term: string) {
    this.movies = [];

    const socket = Observable.webSocket('ws://localhost:8080');
    socket.subscribe(data => {
      if (data['_id']) {
        this.movies.push(<Movie>data);
      }
    });
    socket.next(JSON.stringify({
      action: 'search',
      body: term
    }));
  }
}
