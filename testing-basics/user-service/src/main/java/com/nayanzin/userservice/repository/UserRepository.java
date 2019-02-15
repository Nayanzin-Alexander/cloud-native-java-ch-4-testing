package com.nayanzin.userservice.repository;

import com.nayanzin.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(@Param("username") String name);
}
