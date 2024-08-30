package com.rentcar.api.config;

import com.rentcar.api.auth.LoginRepository;
import com.rentcar.api.auth.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserInitializer {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initAdminUser() {
        return args -> {
            if (!loginRepository.existsByUsername("admin")) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRole("ADMIN");

                loginRepository.save(admin);

                System.out.println("Usuário admin criado com sucesso!");
            } else {
                System.out.println("Usuário admin já existe.");
            }
        };
    }
}