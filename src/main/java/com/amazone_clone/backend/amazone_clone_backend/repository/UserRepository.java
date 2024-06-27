package com.amazone_clone.backend.amazone_clone_backend.repository;

import com.amazone_clone.backend.amazone_clone_backend.entity.Role;
import com.amazone_clone.backend.amazone_clone_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    User findByRole(Role role);

}
