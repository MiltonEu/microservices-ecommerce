package com.eustache.user;

import com.eustache.user.model.Admin;
import com.eustache.user.model.Role;
import com.eustache.user.model.abstraction.User;
import com.eustache.user.repository.RoleRepository;
import com.eustache.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
@EnableEurekaClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);    }

    @Bean
    public CommandLineRunner start(UserRepository userRepository, RoleRepository roleRepository){
        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(null,"ADMIN"));
        return args -> {
            User admin = new Admin(null, "milton.test@test.com", "test",roles, false,  "Milton", "Eustache" );
          userRepository.save(admin);
        };
    }
}
