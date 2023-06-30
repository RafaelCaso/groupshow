package com.groupshow.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupshow.artwork.painting.Painting;
import com.groupshow.artwork.performance.Performance;
import com.groupshow.artwork.photograph.Photograph;
import com.groupshow.artwork.song.Song;
import com.groupshow.artwork.video.Video;
import com.groupshow.artwork.writing.Writing;
import com.groupshow.token.Token;
import com.groupshow.token.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_level")
    private GradeLevel gradeLevel;

    @Column(length = 100)
    private String major;

    @Column(length = 100)
    private String minor;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @PrePersist
    public void prePersistCreatedOn() {
        createdOn = LocalDateTime.now();
    }

    @Column(name = "registration_token", unique = true, length = 36, nullable = false)
    private String registrationToken;

	@Column(name = "is_account_activated", nullable = false)
	private Boolean isAccountActivated;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Token> tokens;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Painting> paintings;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Performance> performances;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Photograph> photographs;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Song> songs;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Video> videos;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Writing> writings;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return tokens.stream()
                .anyMatch(token -> token.getTokenType().equals(TokenType.REFRESH)
                        && !token.getIsExpired());
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
        return isAccountActivated;
    }
}
