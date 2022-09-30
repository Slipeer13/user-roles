package ru.codemark.userroles.service;

import ru.codemark.userroles.UserRolesApplication;
import ru.codemark.userroles.entity.User;
import ru.codemark.userroles.mapper.Mapper;
import ru.codemark.userroles.mapper.UserDTO;
import ru.codemark.userroles.repository.UserRepository;

import javax.jws.WebService;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebService(
		serviceName = "UserService",
		portName = "UserPort",
		targetNamespace = "http://ru.codemark.userrole/",
		endpointInterface = "ru.codemark.userroles.service.UserService")
public class UserServiceImpl implements UserService {
	private static final Validator validator;
	private static final Mapper mapper;
	static {
		validator = Validation.buildDefaultValidatorFactory().usingContext().getValidator();
		mapper = new Mapper();
	}

	@Override
	public List<UserDTO> findAllUsers() {
		return getUserRepository().findAll().stream().map(mapper::getUserDTOFromUser)
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO findUserByLogin(String login) {
		 User user = getUserRepository().findById(login)
				.orElseThrow(()-> new EntityNotFoundException(String.format("Нет пользователя с логином %s", login)));
		 UserDTO userDTO = mapper.getUserDTOFromUser(user);
		 userDTO.setRoles(user.getRoles());
		 return userDTO;
	}

	@Override
	public User deleteUserByLogin(String login) {
		User user = getUserRepository().findById(login)
				.orElseThrow(()-> new EntityNotFoundException(String.format("Нет пользователя с логином %s", login)));
		getUserRepository().deleteById(login);
		return user;
	}

	@Override
	public Response saveUser(User user) {
		Response response = new Response();
		List<String> errors = getValidationList(user);
		if(errors.size() == 0 && !getUserRepository().existsById(user.getLogin())) {
			getUserRepository().save(user);
			response.setSuccess(true);
		} else {
			response.setSuccess(false);
			if (errors.size() > 0) {
				response.setErrors(errors);
			} else {
				response.setErrors(Collections.singletonList(String.format("пользователь с логином %s уже есть"
						, user.getLogin())));
			}
		}
		return response;
	}

	@Override
	public Response updateUser(String oldLogin, User newUser) {
		User user = deleteUserByLogin(oldLogin);
		Response response = saveUser(newUser);
		if(!response.getSuccess()) {
			saveUser(user);
		}
		return response;
	}

	private UserRepository getUserRepository() {
		return UserRolesApplication.getUserRepository();
	}

	private <T> List<String> getValidationList(T t) {
		Set<ConstraintViolation<T>> validatesUser = validator.validate(t);
		return validatesUser.stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());
	}


}
