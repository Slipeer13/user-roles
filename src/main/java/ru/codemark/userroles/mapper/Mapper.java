package ru.codemark.userroles.mapper;

import org.springframework.stereotype.Component;
import ru.codemark.userroles.entity.User;

@Component
public class Mapper {

    public UserDTO getUserDTOFromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }
}
