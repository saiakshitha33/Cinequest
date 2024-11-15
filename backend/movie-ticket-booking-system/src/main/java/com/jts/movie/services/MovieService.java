package com.jts.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.jts.movie.convertor.MovieConvertor;
import com.jts.movie.entities.Movie;
import com.jts.movie.exceptions.MovieAlreadyExist;
import com.jts.movie.repositories.MovieRepository;
import com.jts.movie.request.MovieRequest;
import java.util.List;
@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public String addMovie(MovieRequest movieRequest) {
		Movie movieByName = movieRepository.findByMovieName(movieRequest.getMovieName());
		
		if (movieByName != null && movieByName.getLanguage().equals(movieRequest.getLanguage())) {
			throw new MovieAlreadyExist();
		}
		
		Movie movie = MovieConvertor.movieDtoToMovie(movieRequest);
		
		movieRepository.save(movie);
		return "The movie has been added successfully";
		// Method to get all movies
	}
	// Method to get all movies
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	// Fetch a movie by ID
	public Movie getMovieById(Integer id) {
		Optional<Movie> movie = movieRepository.findById(id);
		return movie.orElse(null);  // Return the movie if found, else null
	}

}