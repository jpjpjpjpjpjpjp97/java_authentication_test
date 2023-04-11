package com.spring.authenticationtest.preload;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.spring.authenticationtest.model.Role;
import com.spring.authenticationtest.model.User;
import com.spring.authenticationtest.repository.RoleRepository;
import com.spring.authenticationtest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Set;

@Configuration
class LoadData {
    private static final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            Role userClient = new Role("USER");
            Role standardClient = new Role("StandardClient");
            Set<Role> userRoleSet = new java.util.HashSet<>(Collections.emptySet());
            userRoleSet.add(standardClient);
            userRoleSet.add(userClient);
            log.info("Preloading Roles...");
            roleRepository.save(userClient);
            roleRepository.save(standardClient);
            roleRepository.save(new Role("Administrator"));
            log.info("Preloading Users...");
            User user1 = new User("jdiaz", passwordEncoder.encode("12345password"), userRoleSet);
            User user2 = new User("ilopez", passwordEncoder.encode("testpassword"), userRoleSet);
            userRepository.save(user1);
            userRepository.save(user2);
        };
    }
}