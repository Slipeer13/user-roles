package ru.codemark.userroles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.codemark.userroles.repository.UserRepository;

@SpringBootApplication
public class UserRolesApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(UserRolesApplication.class, args);
    }

    public static UserRepository getUserRepository() {
        return context.getBean(UserRepository.class);
    }

}
