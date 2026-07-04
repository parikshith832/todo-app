package com.todoapp.service;

import com.todoapp.dto.RegisterDto;
import com.todoapp.entity.User;

public interface UserService {

    User register(RegisterDto registerDto);

    User findByUsernameOrEmail(String usernameOrEmail);
}
