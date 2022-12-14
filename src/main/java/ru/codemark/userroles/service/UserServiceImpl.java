package ru.codemark.userroles.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.codemark.userroles.entity.User;
import ru.codemark.userroles.mapper.Mapper;
import ru.codemark.userroles.mapper.UserDTO;
import ru.codemark.userroles.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	@Lazy
	private Validator validator;
	@Autowired
	private  Mapper mapper;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserDTO> findAllUsers() {
		return userRepository.findAll().stream().map(mapper::getUserDTOFromUser)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findUserByLogin(String login) {
		 User user = userRepository.findById(login)
				.orElseThrow(()-> new EntityNotFoundException(String.format("Нет пользователя с логином %s", login)));
		 UserDTO userDTO = mapper.getUserDTOFromUser(user);
		 Hibernate.initialize(user.getRoles());
		 userDTO.setRoles(user.getRoles());
		 return userDTO;
	}

	@Override
	public User deleteUserByLogin(String login) {
		User user = userRepository.findById(login)
				.orElseThrow(()-> new EntityNotFoundException(String.format("Нет пользователя с логином %s", login)));
		userRepository.deleteById(login);
		return user;
	}

	@Override
	public Response saveUser(User user) {
		Response response = new Response();
		List<String> errors = getErrorsList(user);
		if(errors.size() == 0 && !userRepository.existsById(user.getLogin())) {
			userRepository.save(user);
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
	public Response updateUser(String oldLogin, User userNewData) {
		User user = deleteUserByLogin(oldLogin);
		Response response = saveUser(userNewData);
		if(!response.getSuccess()) {
			saveUser(user);
		}
		return response;
	}

	private List<String> getErrorsList(User user) {
		return validator.validate(user).stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());
	}


}
