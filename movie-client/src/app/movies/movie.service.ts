import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Movie } from "../movie";

@Injectable()
export class MovieService {

  constructor(private http: HttpClient) {
  }

  get movies(): Observable<Movie[]> {
    return this.http.get('http://localhost:8080/api/movies');
  }
}
