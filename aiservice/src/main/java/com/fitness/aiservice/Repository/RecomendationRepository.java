package com.fitness.aiservice.Repository;

import com.fitness.aiservice.Model.Recomendation;
import org.jspecify.annotations.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecomendationRepository extends MongoRepository<Recomendation,String> {
    @Nullable List<Recomendation> findByUserId(String userId);

    @Nullable
    Optional<Recomendation> findByActivityId(String activityId);
}
