package ru.codemark.userroles.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.codemark.userroles.entity.User;
import ru.codemark.userroles.mapper.UserDTO;
import ru.codemark.userroles.service.Response;
import ru.codemark.userroles.service.UserService;

import javax.jws.WebService;
import java.util.List;

@WebService(
        serviceName = "UserService",
        portName = "UserPort",
        targetNamespace = "http://ru.codemark.userrole/",
        endpointInterface = "ru.codemark.userroles.service.UserService")
public class UserEndpoint extends SpringBeanAutowiringSupport {

    @Autowired
    private UserService userService;

    public List<UserDTO> findAllUsers() {
        return userService.findAllUsers();
    }


    public UserDTO findUserByLogin(String login) {
        return userService.findUserByLogin(login);
    }


    public User deleteUserByLogin(String login) {
        return userService.deleteUserByLogin(login);
    }


    public Response saveUser(User user) {
        return userService.saveUser(user);
    }


    public Response updateUser(String oldLogin, User userNewData) {
        return userService.updateUser(oldLogin, userNewData);
    }
}
