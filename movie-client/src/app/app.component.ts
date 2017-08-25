import { Component } from '@angular/core';
import { Movie } from "./movie";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  movies = movies;
}

// Todo move to service
const movies: Movie[] = [{
  title: "Rogue One",
  rating: 4,
  description: "Part eight in this epic universe...",
  genres: ["Action", "Adventure", "Sci-Fi"],
  year: "2016",
  runTime: "133 min",
  poster: "https://images-na.ssl-images-amazon.com/images/M/MV5BMjEwMzMxODIzOV5BMl5BanBnXkFtZTgwNzg3OTAzMDI@._V1_SX300.jpg"
}, {
  title: "Shawshank Redemption",
  rating: 5,
  description: "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
  genres: ["Crime", "Drama"],
  year: "1994",
  runTime: "142 min",
  poster: "https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1_SX300.jpg"
}, {
  title: "Funny Games",
  rating: 3.5,
  description: "Two psychopathic young men take a family hostage in their cabin..",
  genres: ["Crime", "Drama", "Horror"],
  year: "2007",
  runTime: "111 min",
  poster: "https://images-na.ssl-images-amazon.com/images/M/MV5BMTg4OTExNTYzMV5BMl5BanBnXkFtZTcwOTg1MDU1MQ@@._V1_SX300.jpg"
}, {
  title: "A Clockwork Orange",
  rating: 5,
  description: "In the future, a sadistic gang leader is imprisoned and volunteers for a conduct-aversion experiment, but it doesn't go as planned.",
  genres: ["Crime", "Drama", "Sci-Fi"],
  year: "1971",
  runTime: "136 min",
  poster: "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY3MjM1Mzc4N15BMl5BanBnXkFtZTgwODM0NzAxMDE@._V1_SX300.jpg"
}];
