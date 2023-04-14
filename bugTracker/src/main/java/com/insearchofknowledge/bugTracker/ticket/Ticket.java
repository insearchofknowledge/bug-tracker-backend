package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.comment.Comment;
import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.project.Project;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TicketPriority;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TicketStatus;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TypeOfTicket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(generator = "ticket-generic-generator")
    @GenericGenerator(
            name = "ticket-generic-generator",
            parameters = @Parameter(name = "prefix", value = "TCT"),
            strategy = "com.insearchofknowledge.bugTracker.generics.CustomIdGenerator"
    )
    private String id;
    @NotNull(message = "Title required")
    @NotBlank
    private String title;
    @NotNull(message = "Description required")
    @NotBlank
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime lastDateModified;
    @Enumerated(EnumType.STRING)
    private TypeOfTicket typeOfTicket;
    @Enumerated(EnumType.STRING)
    private TicketPriority ticketPriority;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToOne
    @JoinColumn(name = "authorId")
    @NotNull
    private Developer author;

    @ManyToMany
            @JoinTable(
                    name = "DevsAssignedToTicket",
                    joinColumns = @JoinColumn(name = "ticketId"),
                    inverseJoinColumns = @JoinColumn(name = "developerId")
            )
    private List<Developer> devsAssigned;

    @OneToMany(mappedBy = "ticket")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;
}
