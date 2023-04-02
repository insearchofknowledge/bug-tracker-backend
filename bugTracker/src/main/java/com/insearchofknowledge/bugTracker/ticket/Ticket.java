package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.project.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Date;
import java.util.Set;

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
    private String title;
    private String description;
    private Date dateCreated;
    private Date lastDateModified;
    @Enumerated(EnumType.STRING)
    private TypeOfTicket typeOfTicket;
    @Enumerated(EnumType.STRING)
    private TicketPriority ticketPriority;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Developer author;

    @ManyToMany
            @JoinTable(
                    name = "DevsAssignedToTicket",
                    joinColumns = @JoinColumn(name = "ticketId"),
                    inverseJoinColumns = @JoinColumn(name = "developerId")
            )
    private Set<Developer> devsAssigned;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;
}
