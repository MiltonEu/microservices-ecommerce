package com.eustache.user.controller;

import com.eustache.model.Response;
import com.eustache.user.exception.UserNotFoundException;
import com.eustache.user.model.Admin;
import com.eustache.user.model.abstraction.User;
import com.eustache.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/listUsers")
    public ResponseEntity<Response> findAllUsers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("users", userService.findAll()))
                        .message("Users retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/findOne/{userId}")
    public ResponseEntity<Response> findUserById(@PathVariable("userId") Integer userId) throws UserNotFoundException {
        Optional<User> optionalUser = userService.findUserById(userId);
        if(optionalUser.isPresent()){
            return ResponseEntity.ok(Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("user",optionalUser))
                    .message("User retrieved")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value()).build());
        }
        throw new UserNotFoundException("Requested user not found");
    }

    @PostMapping("/saveUser")
    public ResponseEntity<Response> saveAdmin(@RequestBody User userToSave){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/saveUser")
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).body(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("admin", userService.saveUser(userToSave)))
                        .message("Admin saved")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value()).build()
        );
    }
}
