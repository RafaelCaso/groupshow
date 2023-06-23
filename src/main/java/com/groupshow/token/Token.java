package com.groupshow.token;

import com.groupshow.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Integer tokenID;

    @Column(unique = true)
    private String jwt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TokenType tokenType;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @PrePersist
    public void prePersistCreatedOn() {
        createdOn = LocalDateTime.now();
    }

    @Column(name = "expires_on")
    private LocalDateTime expiresOn;

    @Column(name = "is_expired", nullable = false)
    private Boolean isExpired;

    @Column(name = "is_revoked", nullable = false)
    private Boolean isRevoked;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
