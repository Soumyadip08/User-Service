package com.lcwd.user.service.controller;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {     //for converting jason format @ReqBody
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user
    @GetMapping("/{userId}")      // its call two services hotel servces and rating services
    @CircuitBreaker(name = "ratingHotelBreaker" , fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // craeting FALLBACK method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId , Exception ex){
        log.info("Fall back becuase service is down: " , ex.getMessage());
       User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created because some service is down")
                .userId("12345")
                .build();
        return new ResponseEntity<>(user , HttpStatus.OK);

    }

    //all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser () {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
