package com.uirs.account.controller;


import com.uirs.account.entity.Movie;
import com.uirs.account.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/movies/")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "")
    public ResponseEntity<Movie> getMovieById(@RequestParam Long id) {
        return new ResponseEntity<>(movieService.getById(id), HttpStatus.OK);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }
}
