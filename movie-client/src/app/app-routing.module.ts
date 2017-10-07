import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import { MoviesComponent } from "./movies/movies.component";
import { MovieComponent } from "./movie/movie.component";
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";

const appRoutes = [
  { path: 'movies', component: MoviesComponent },
  { path: 'movies/:id', component: MovieComponent },
  { path: '',
    redirectTo: '/movies',
    pathMatch: 'full'
  },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {}
