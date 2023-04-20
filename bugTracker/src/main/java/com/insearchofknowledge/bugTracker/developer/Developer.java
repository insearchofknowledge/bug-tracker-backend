package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.comment.Comment;
import com.insearchofknowledge.bugTracker.project.Project;
import com.insearchofknowledge.bugTracker.ticket.Ticket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @NotNull
    @NotBlank
    private String firstName;
    private String lastName;
    private String phone;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String email;
    private String password;
    private String role;  // Admin, Project Leader, Developer

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Ticket> ticketsCreated = new ArrayList<>();

    @ManyToMany(mappedBy = "devsAssigned", fetch = FetchType.LAZY)
    private List<Ticket> assignedTickets;

    @ManyToMany(mappedBy = "assignedTeam", fetch = FetchType.LAZY)
    private List<Project> projects;

    @OneToMany(mappedBy = "commentAuthor", fetch = FetchType.LAZY)
    private List<Comment> comments;
}
