package com.uirs.account.service;

import com.uirs.account.entity.User;
import com.uirs.account.entity.UserMovie;

import java.util.List;

public interface UserMovieService {

    List<UserMovie> getAllByUser(User user);

    UserMovie getByIdAndUser(Long userMovieId, User user);

    UserMovie add(UserMovie movie);

    List<UserMovie> update(List<UserMovie> movies);

    void deleteById(Long Id);
}
