package com.fitness.activityservice.Service;


import com.fitness.activityservice.Dto.ActivityRequest;
import com.fitness.activityservice.Dto.ActivityResponse;
import com.fitness.activityservice.Module.Activity;
import com.fitness.activityservice.Repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RequiredArgsConstructor
@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final  UserValidationService userValidationService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    public @Nullable ActivityResponse trackActivity(ActivityRequest request) {

        boolean isValidUser = userValidationService.validateUser(request.getUserId());
        if (!isValidUser) {
            throw new RuntimeException("Invalid User: " + request.getUserId());
        }
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);
        } catch(Exception e) {
            log.error("Failed to publish activity to RabbitMQ : ", e);
        }
        return  mapToResponse(activity);
    }
    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }
    public @Nullable List<ActivityResponse> getUserActivities(String userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public @Nullable ActivityResponse getActivityById(String activityId) {
        return activityRepository.findById(activityId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + activityId));
    }
}
