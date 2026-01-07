package com.fitness.userservice.Service;

import com.fitness.userservice.Dto.RegisterRequest;
import com.fitness.userservice.Dto.UserResponse;
import com.fitness.userservice.Model.User;
import com.fitness.userservice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
 private UserRepository repository;

 public UserResponse register(RegisterRequest request){
     if(repository.existsByEmail(request.getEmail())){
         throw new RuntimeException("Email already Exists");
     };
     User user = new User();
     user.setEmail(request.getEmail());
     user.setFirstName(request.getFirstName());
     user.setLastName(request.getLastName());
     user.setPassword(request.getPassword());

     User Saveduser = repository.save(user);
     UserResponse response = new UserResponse();

     response.setId(Saveduser.getId());
     response.setEmail(Saveduser.getEmail());
     response.setPassword(Saveduser.getPassword());
     response.setFirstName(Saveduser.getFirstName());
     response.setLastName(Saveduser.getLastName());
     response.setCreationTime(Saveduser.getCreationTime());
     response.setUpdationTime(Saveduser.getUpdationTime());
     return response;

 }



    public @Nullable Boolean existByUserId(String userId) {
     return repository.existsById(userId);
    }

    public @Nullable UserResponse getUserProfile(String userId) {
     User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setCreationTime(user.getCreationTime());
        response.setUpdationTime(user.getUpdationTime());

        return  response;
    }
}
