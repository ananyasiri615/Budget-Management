package com.bcc.repository;

import com.bcc.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    // You can define custom queries here if needed
}

