package ru.codemark.userroles.mapper;

import ru.codemark.userroles.entity.User;

public class Mapper {

    public UserDTO getUserDTOFromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }
}
