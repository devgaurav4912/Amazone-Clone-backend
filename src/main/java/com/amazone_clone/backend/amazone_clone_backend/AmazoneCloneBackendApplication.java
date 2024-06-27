package com.amazone_clone.backend.amazone_clone_backend;

import com.amazone_clone.backend.amazone_clone_backend.entity.Role;
import com.amazone_clone.backend.amazone_clone_backend.entity.User;
import com.amazone_clone.backend.amazone_clone_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AmazoneCloneBackendApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AmazoneCloneBackendApplication.class, args);

        System.out.println("Application is Started.....");

    }

	public void run(String... arg){

		User adminAccount = userRepository.findByRole(Role.ADMIN);

		if(adminAccount == null){
			User user = new User();

			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setLastName("adminLast");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}

}
