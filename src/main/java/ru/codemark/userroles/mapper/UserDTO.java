package ru.codemark.userroles.mapper;

import lombok.Data;
import ru.codemark.userroles.entity.Role;

import java.util.List;

@Data
public class UserDTO {
    private String name;
    private String login;
    private List<Role> roles;
}
