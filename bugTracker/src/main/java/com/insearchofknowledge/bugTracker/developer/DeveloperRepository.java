package com.insearchofknowledge.bugTracker.developer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, String> {

    // I want to avoid multiple queries for all the ids in the iterable
    // I also want to ensure an all or nothing guarantee in the service layer
    @Query("SELECT dev FROM Developer dev WHERE dev.id IN :ids")
    List<Developer> findAllByIds(List<String> ids);
}
