package com.groupshow.token;

import com.groupshow.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Integer tokenID;

    private String token;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TokenType tokenType;

    @Column(name = "is_expired", nullable = false)
    private Boolean isExpired;

    @Column(name = "is_revoked", nullable = false)
    private Boolean isRevoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
