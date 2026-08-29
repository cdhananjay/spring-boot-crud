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
        user.setDeleted(false);
        userRepository.save(user);
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.findByIdAndDeletedIsFalse(id);
        return user.orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAllAndFindByDeletedIsFalse();
    }

    public User updateUser(User user){
        Optional<User> existingUser = userRepository.findByIdAndDeletedIsFalse(user.getId());
        if (existingUser.isEmpty()) return null;
        user.setDeleted(false);
        userRepository.save(user);
        return user;
    }

    public boolean deleteUser(Long id) {
        if(!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }

    public boolean softDeleteUser(Long id) {
        Optional<User> user = userRepository.findByIdAndDeletedIsFalse(id);
        if (user.isEmpty()) return false;
        user.get().setDeleted(true);
        userRepository.save(user.get());
        return true;
    }
}