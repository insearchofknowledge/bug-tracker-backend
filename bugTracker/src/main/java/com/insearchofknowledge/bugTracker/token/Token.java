package com.insearchofknowledge.bugTracker.token;

import com.insearchofknowledge.bugTracker.developer.Developer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(generator = "token-generic-generator")
    @GenericGenerator(
            name = "token-generic-generator",
            parameters = @Parameter(name = "prefix", value = "TKN"),
            strategy = "com.insearchofknowledge.bugTracker.generics.CustomIdGenerator"
    )
    private String id;

    @Column(unique = true)
    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;
    private boolean isRevoked;
    private boolean isExpired;

    @ManyToOne
    @JoinColumn(name = "developerId")
    private Developer developer;
}
