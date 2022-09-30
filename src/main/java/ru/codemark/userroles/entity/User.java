package ru.codemark.userroles.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class User {

    @Size(min = 2, message = "имя должно быть длиннее 1 символа")
    private String name;

    @Id
    @Size(min = 2, message = "login должен быть длиннее 1 символа")
    private String login;

    @Pattern(regexp = ".*([A-Z]+).*(\\d+).*|.*(\\d+).*([A-Z]+).*"
            , message = "password должен содержать букву в заглавном регистре и цифру")
    private String password;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return login != null && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
