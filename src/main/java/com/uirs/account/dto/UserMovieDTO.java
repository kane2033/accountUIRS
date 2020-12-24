package com.uirs.account.dto;

import com.uirs.account.entity.Movie;
import com.uirs.account.entity.MovieStatus;
import com.uirs.account.entity.User;
import com.uirs.account.entity.UserMovie;
import lombok.Data;

@Data
public class UserMovieDTO {

    private String username;
    private Movie movie;
    private String movieStatus;
    private int rating;

    public UserMovie fromDTOtoUserMovie(User user) {
        UserMovie userMovie = new UserMovie();
        userMovie.setUser(user);
        userMovie.setMovie(movie);
        userMovie.setMovieStatus(MovieStatus.valueOf(movieStatus));
        userMovie.setRating(rating);
        return userMovie;
    }
}
