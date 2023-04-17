package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.comment.Comment;
import com.insearchofknowledge.bugTracker.project.Project;
import com.insearchofknowledge.bugTracker.ticket.Ticket;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(generator = "developer-generic-generator")
    @GenericGenerator(
            name = "developer-generic-generator",
            parameters = @Parameter(name = "prefix", value = "DEV"),
            strategy = "com.insearchofknowledge.bugTracker.generics.CustomIdGenerator"
    )
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String role;  // Admin, Project Leader, Developer

    @OneToMany(mappedBy = "author")
    private List<Ticket> ticketsCreated = new ArrayList<>();

    @ManyToMany(mappedBy = "devsAssigned")
    private List<Ticket> assignedTickets;

    @ManyToMany(mappedBy = "assignedTeam")
    private List<Project> projects;

    @OneToMany(mappedBy = "commentAuthor")
    private List<Comment> comments;
}
