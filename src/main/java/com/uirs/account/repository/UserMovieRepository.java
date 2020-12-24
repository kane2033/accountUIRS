package com.uirs.account.repository;

import com.uirs.account.entity.User;
import com.uirs.account.entity.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMovieRepository extends JpaRepository<UserMovie, Long> {

    //@Query("SELECT userMovies FROM User.userMovies userMovies WHERE userMovies.status = 1")
    List<UserMovie> getAllByUser(User user);

    UserMovie getByIdAndUser(Long userMovieId, User user);
}
