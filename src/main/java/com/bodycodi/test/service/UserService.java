package com.bodycodi.test.service;

import com.bodycodi.test.dto.UserDto;
import com.bodycodi.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public int insert(UserDto user) {
        return userRepository.insert(user);
    }

    public UserDto findUser(String username) {
        return userRepository.findUser(username);
    }


}
