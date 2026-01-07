package com.fitness.aiservice.Service;

import com.fitness.aiservice.Model.Activity;
import com.fitness.aiservice.Model.Recomendation;
import com.fitness.aiservice.Repository.RecomendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListner {
    private final ActivityAiService aiService;
    private final RecomendationRepository recommendationRepository;
    @RabbitListener(queues = "activity.queue")

    public void processActivity(Activity activity) {
        log.info("Received activity for processing: {}", activity.getId());
//        log.info("Generated Recommendation: {}", aiService.Recommendation(activity));
        Recomendation recommendation = aiService.Recommendation(activity);
        recommendationRepository.save(recommendation);
    }
    }

