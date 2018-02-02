import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from '../movie';
import { FormControl } from "@angular/forms";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, OnDestroy {
  public movies: Movie[];
  private searchTerm = new FormControl();
  private socket$ = Observable.webSocket('ws://localhost:8080');

  constructor() {
    this.movies = [];
  }

  ngOnInit() {
    this.searchTerm.valueChanges
      .debounceTime(300)
      .distinctUntilChanged()
      .filter(searchTerm => searchTerm.length >= 3)
      .subscribe(searchTerm => this.search(searchTerm));

    this.socket$.subscribe(movie => {
        if (movie.hasOwnProperty('_id')) {
          this.movies.push(<Movie>movie);
        }
      },
      err => console.log("error:", err),
      () => console.log("complete")
    );
  }

  ngOnDestroy(): void {
    this.socket$.unsubscribe();
  }

  search(searchTerm: string) {
    this.movies = [];
    this.socket$.next(JSON.stringify({
      action: 'search',
      body: searchTerm
    }));
  }
}
