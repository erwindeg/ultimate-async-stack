import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable } from 'rxjs/observable';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/filter';
import 'rxjs/add/observable/dom/webSocket';
import { Movie } from '../movie';
import { FormControl } from '@angular/forms';

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
      .filter(searchTerm => searchTerm.length >= 3)
      .debounceTime(300)
      .distinctUntilChanged()
      .subscribe(searchTerm => this.search(searchTerm));

    this.socket$
      .filter(movie => movie.hasOwnProperty('_id'))
      .subscribe(
        movie => this.movies.push(movie as Movie),
        err => console.log('error:', err),
        () => console.log('complete')
      );
  }

  ngOnDestroy(): void {
    this.socket$.unsubscribe();
  }

  search(searchTerm: string) {
    this.movies = [];
    this.socket$.next(
      JSON.stringify({
        action: 'search',
        body: searchTerm
      })
    );
  }
}
