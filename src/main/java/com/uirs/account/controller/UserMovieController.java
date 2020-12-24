package com.uirs.account.controller;


import com.uirs.account.dto.UserMovieDTO;
import com.uirs.account.entity.User;
import com.uirs.account.entity.UserMovie;
import com.uirs.account.service.UserMovieService;
import com.uirs.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user-movies/")
public class UserMovieController {
    private final UserMovieService movieService;
    private final UserService userService;

    @Autowired
    public UserMovieController(UserMovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    @GetMapping(path = "all")
    //@Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public ResponseEntity<List<UserMovie>> getAllByUsername(@RequestParam String username) {
        User user = userService.findByUsername(username);
        // надо сделать dto
        return new ResponseEntity<>(movieService.getAllByUser(user), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<UserMovie> getByIdAndUsername(@RequestParam Long id, @RequestParam String username) {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(movieService.getByIdAndUser(id, user), HttpStatus.OK);
    }

    @PostMapping(path = "add", consumes = {"application/json"})
    public ResponseEntity<String> addUserMovie(@RequestBody UserMovieDTO requestDTO) {
        String username = requestDTO.getUsername();
        User user = userService.findByUsername(username);

        UserMovie userMovie = requestDTO.fromDTOtoUserMovie(user);
        movieService.add(userMovie);

        return ResponseEntity.ok("Фильм успешно добавлен в ваш список, " + username);
    }

    @PostMapping(path = "update", consumes = {"application/json"})
    public ResponseEntity<String> updateUserMovies(@RequestBody List<UserMovieDTO> requestDTO) {
        String username = requestDTO.get(0).getUsername();
        User user = userService.findByUsername(username);

        List<UserMovie> userMovies = new ArrayList<>();
        for (UserMovieDTO dto : requestDTO) {
            userMovies.add(dto.fromDTOtoUserMovie(user));
        }

        movieService.update(userMovies);

        return ResponseEntity.ok("Список ваших фильмов успешно обновлен, " + username);
    }

    @PostMapping(path = "delete", consumes = {"application/json"})
    public ResponseEntity<String> deleteUserMovieById(@RequestParam Long id) {
        movieService.deleteById(id);
        return ResponseEntity.ok("Фильм успешно удален");
    }
}
