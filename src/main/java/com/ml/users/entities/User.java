package com.ml.users.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ml.opinions.entities.Opinion;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@EntityListeners({AuditingEntityListener.class, UserListener.class})
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private final Set<Opinion> opinions = new HashSet<>();

    @Column(nullable = false, updatable = false)
    @NotNull
    @CreatedDate
    private Instant creationDate;

    @Deprecated
    public User() {
    }

    public User(@NotBlank @Email String login, @NotBlank @Min(6) String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return Objects.nonNull(id) ? Objects.equals(id, user.id) : Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }

}
