package ru.codemark.userroles.service;

import ru.codemark.userroles.entity.User;
import ru.codemark.userroles.mapper.UserDTO;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;
import java.util.List;


@WebService(targetNamespace = "http://codemark.ru.userroles/", name = "UserService")
public interface UserService {

	@WebResult(name = "user")
	@ResponseWrapper(
			localName = "findAllUsersResponse",
			targetNamespace = "http://codemark.ru.userroles/",
			className = "ru.codemark.userroles.service.UserService.findAllResponse")
	List<UserDTO> findAllUsers();

	@WebResult(name = "user")
	@ResponseWrapper(
			localName = "findUserResponse",
			targetNamespace = "http://codemark.ru.userroles/",
			className = "ru.codemark.userroles.service.UserService.findUserResponse")
	UserDTO findUserByLogin(@WebParam(name = "login") String login);

	@WebResult(name = "delete_user")
	@ResponseWrapper(
			localName = "deleteUserResponse",
			targetNamespace = "http://codemark.ru.userroles/",
			className = "ru.codemark.userroles.service.UserService.deleteUserResponse")
	User deleteUserByLogin(@WebParam(name = "login") String login);

	@WebResult(name = "save_user")
	@ResponseWrapper(
			localName = "saveUserResponse",
			targetNamespace = "http://codemark.ru.userroles/",
			className = "ru.codemark.userroles.service.UserService.saveUserResponse")
	Response saveUser(@WebParam(name = "user") User user);

	@WebResult(name = "update_user")
	@ResponseWrapper(
			localName = "updateUserResponse",
			targetNamespace = "http://codemark.ru.userroles/",
			className = "ru.codemark.userroles.service.UserService.updateUserResponse")
	Response updateUser(@WebParam(name = "oldLogin") String login, @WebParam(name = "newDataUser") User user);


}
