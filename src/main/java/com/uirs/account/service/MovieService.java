package com.uirs.account.service;

import com.uirs.account.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    Movie getById(Long id);
}
