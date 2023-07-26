package com.example.jsfspringbootjwt.service;
import com.example.jsfspringbootjwt.model.User;
import com.example.jsfspringbootjwt.repository.UserRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserListService {
    private final UserRepository userRepository;

    public UserListService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUserList(){
        return userRepository.findAll();
    }
}
