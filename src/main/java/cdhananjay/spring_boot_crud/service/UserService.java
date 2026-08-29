package cdhananjay.spring_boot_crud.service;

import cdhananjay.spring_boot_crud.entity.User;
import cdhananjay.spring_boot_crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void createUser(User user){
        // validations
        // ...
        userRepository.save(user);
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user){
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isEmpty()) return null;
        userRepository.save(user);
        return user;
    }

    public boolean deleteUser(Long id) {
        if(!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }
}
