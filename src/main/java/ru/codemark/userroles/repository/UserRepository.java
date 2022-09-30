package ru.codemark.userroles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.codemark.userroles.entity.User;


public interface UserRepository extends JpaRepository<User, String> {

}
