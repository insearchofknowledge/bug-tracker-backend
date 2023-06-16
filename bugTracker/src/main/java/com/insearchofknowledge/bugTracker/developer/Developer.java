package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.comment.Comment;
import com.insearchofknowledge.bugTracker.project.Project;
import com.insearchofknowledge.bugTracker.ticket.Ticket;
import com.insearchofknowledge.bugTracker.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "developers")
public class Developer implements UserDetails {

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

    @Enumerated(EnumType.STRING)
    private Role role;  // Admin, Project Leader, Developer

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Ticket> ticketsCreated = new ArrayList<>();

    @ManyToMany(mappedBy = "devsAssigned", fetch = FetchType.LAZY)
    private List<Ticket> assignedTickets;

    @ManyToMany(mappedBy = "assignedTeam", fetch = FetchType.LAZY)
    private List<Project> projects;

    @OneToMany(mappedBy = "commentAuthor", fetch = FetchType.LAZY)
    private List<Comment> comments;

    // Security:
    @OneToMany(mappedBy = "developer")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
