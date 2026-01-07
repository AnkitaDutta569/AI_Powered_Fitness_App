package com.example.activity.Repository;

import com.example.activity.Model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,String> {
    List<Activity> findByUserId(String userId);

    Activity save(Activity activity);
}
