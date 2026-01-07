package com.example.activity.Controller;

import com.example.activity.Dto.ActivityRequest;
import com.example.activity.Dto.ActivityResponse;
import  com.example.activity.Service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {

    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request){
//        if (userId != null) {
//            request.setUserId(userId);
//        }
        return ResponseEntity.ok(activityService.trackActivity(request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader("X-User-ID") String userId){
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }


//    @GetMapping("/{activityId}")
//    public ResponseEntity<ActivityResponse> getActivity(@PathVariable String activityId){
//        return ResponseEntity.ok(activityService.getActivityById(activityId));
//    }
}
