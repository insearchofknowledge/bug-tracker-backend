package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.comment.Comment;
import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.ticket.Ticket;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(generator = "project-generic-generator")
    @GenericGenerator(
            name = "project-generic-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "PRO"),
            strategy = "com.insearchofknowledge.bugTracker.generics.CustomIdGenerator"
    )
    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date deadline;

    @OneToMany(mappedBy =  "project")
    private Set<Ticket> tickets;

    @ManyToMany
    @JoinTable(
            name = "developersOnTeamForProject",
            joinColumns = @JoinColumn(name = "projectId"),
            inverseJoinColumns = @JoinColumn(name = "developerId")
    )
    private List<Developer> assignedTeam;

    @OneToMany(mappedBy = "project")
    private List<Comment> comments;
}
