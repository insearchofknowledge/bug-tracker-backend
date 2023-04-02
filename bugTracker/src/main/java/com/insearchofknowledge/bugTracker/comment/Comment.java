package com.insearchofknowledge.bugTracker.comment;

import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.project.Project;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Date;

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
    private String content;
    private Date datePosted;

    @ManyToOne
    @JoinColumn(name = "developerId")
    private Developer commentAuthor;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

}
