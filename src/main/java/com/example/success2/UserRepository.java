package com.example.success2;
import org.springframework.data.repository.CrudRepository;

import com.example.success2.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}