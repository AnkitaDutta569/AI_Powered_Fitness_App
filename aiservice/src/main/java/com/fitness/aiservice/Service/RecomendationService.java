package com.fitness.aiservice.Service;

import com.fitness.aiservice.Model.Recomendation;
import com.fitness.aiservice.Repository.RecomendationRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecomendationService {
    private final RecomendationRepository repository;

    public @Nullable List<Recomendation> getUserRecomendation(String userId) {
        return repository.findByUserId(userId);
    }

    public @Nullable  Recomendation getActivityRecomendation(String activityId) {
        return  repository.findByActivityId(activityId)
                .orElseThrow(()->new RuntimeException("No activity Id found for "+activityId));
    }
}
