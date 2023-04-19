package com.insearchofknowledge.bugTracker.comment;

import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.ticket.Ticket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(generator = "comment-generic-generator")
    @GenericGenerator(
            name = "comment-generic-generator",
            parameters = @Parameter(name = "prefix", value= "COM"),
            strategy = "com.insearchofknowledge.bugTracker.generics.CustomIdGenerator"
    )
    private String id;
    @NotNull(message = "Can't be null (Entity)")
    @NotBlank(message = "Can't be blank (Entity)")
    private String content;
    private LocalDateTime datePosted;
    private Boolean wasEdited;

    @ManyToOne
    @JoinColumn(name = "developerId")
    private Developer commentAuthor;

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;
}
