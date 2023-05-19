package com.insearchofknowledge.bugTracker.project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {

    // For fetching projects a given developer is part of
    List<Project> findByAssignedTeamId(String developerId);
}
