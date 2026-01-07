package com.fitness.aiservice.Controller;

import com.fitness.aiservice.Model.Recomendation;
import com.fitness.aiservice.Service.RecomendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecomendationController {
    private final RecomendationService recomendationService;
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recomendation>> getUserRecomendation(@PathVariable String userId){
        return ResponseEntity.ok(recomendationService.getUserRecomendation(userId));
    }
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recomendation> getActivityRecomendation(@PathVariable String activityId){
        return  ResponseEntity.ok(recomendationService.getActivityRecomendation(activityId));
    }
}
