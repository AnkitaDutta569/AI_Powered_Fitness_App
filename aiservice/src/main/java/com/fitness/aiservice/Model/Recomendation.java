package com.fitness.aiservice.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
@Data
@Document(collection = "recomendation")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recomendation {
    @Id
    private  String id;
    private String userId;
    private String activityId;
    private String activityType;
    private String recomendation;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;
    private LocalDateTime createdAt;
}
