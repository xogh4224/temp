package com.gyungjo.manage.repository;

import com.gyungjo.manage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {
}
