package ru.codemark.userroles.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.codemark.userroles.entity.Role;

import java.util.List;

@Component
@Data
public class UserDTO {
    private String name;
    private String login;
    private String password;
    private List<Role> roles;
}
