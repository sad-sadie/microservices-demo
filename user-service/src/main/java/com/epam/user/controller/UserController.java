package com.epam.user.controller;

import com.epam.user.VO.ResponseTemplateVO;
import com.epam.user.entity.UserEntity;
import com.epam.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public UserEntity saveUser(@RequestBody UserEntity userEntity) {
        log.info("Inside saveUser of UserController");
        return userService.saveUser(userEntity);
    }

    @GetMapping("/{id}")
    //@CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "fallBack")
   // @Retry(name = "USER-SERVICE")
    //@RateLimiter(name = "USER-SERVICE")
    public ResponseEntity<ResponseTemplateVO> getUserWithDepartment(@PathVariable("id") long userId) {
        log.info("Inside getUserWithDepartment of UserController");
        return new ResponseEntity<>(userService.getUserWithDeparment(userId), HttpStatus.OK);
    }

    public ResponseEntity<Object> fallBack(long id, Exception e) {
        return new ResponseEntity<>("User service is down", HttpStatus.FORBIDDEN);
    }
}
