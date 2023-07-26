package com.example.jsfspringbootjwt.service;
import com.example.jsfspringbootjwt.model.User;
import com.example.jsfspringbootjwt.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegisterService {

    private final UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerNewUser(User user){
        userRepository.save(user);
    }
}
