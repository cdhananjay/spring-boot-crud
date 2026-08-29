package cdhananjay.spring_boot_crud.service;

import cdhananjay.spring_boot_crud.entity.User;
import cdhananjay.spring_boot_crud.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void createUser(User user){
        // validations
        // ...
        userRepository.saveUser(user);
    }
}
