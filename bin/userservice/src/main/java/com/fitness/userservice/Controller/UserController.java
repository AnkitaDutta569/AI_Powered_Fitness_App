package com.fitness.userservice.Controller;

import com.fitness.userservice.Dto.RegisterRequest;
import com.fitness.userservice.Dto.UserResponse;
import com.fitness.userservice.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/{UserId}")

    public  ResponseEntity<UserResponse> getUserProfile ( @PathVariable("userId") String UserId){
        return ResponseEntity.ok(userService.getUserProfile(UserId));
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> reqister( @Valid @RequestBody RegisterRequest request){
      return  ResponseEntity.ok(userService.register(request)  );
    }
    @GetMapping("/{userId}/validate")
    public  ResponseEntity<Boolean> validateUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.existByUserId(userId));
    }

}
