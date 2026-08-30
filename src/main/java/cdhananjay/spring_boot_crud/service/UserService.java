package cdhananjay.spring_boot_crud.service;

import cdhananjay.spring_boot_crud.dto.UpdateUserRequestDto;
import cdhananjay.spring_boot_crud.dto.UserRequestDto;
import cdhananjay.spring_boot_crud.dto.UserResponseDto;
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
    
    public UserResponseDto createUser(UserRequestDto userRequestDto){
        User user = mapToEntity(userRequestDto);
        userRepository.save(user);
        return mapToDto(user);
    }

    public UserResponseDto getUser(Long id) {
        Optional<User> user = userRepository.findByIdAndDeletedIsFalse(id);
        return user.map(this::mapToDto).orElse(null);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAllAndFindByDeletedIsFalse().stream().map(this::mapToDto).toList();
    }

    public UserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto){
        User user = mapToEntity(updateUserRequestDto);
        Optional<User> existingUser = userRepository.findByIdAndDeletedIsFalse(user.getId());
        if (existingUser.isEmpty()) return null;
        userRepository.save(user);
        return mapToDto(user);
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
    
    private User mapToEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setDeleted(false);
        user.setAddress(userRequestDto.getAddress());
        user.setAge(userRequestDto.getAge());
        user.setEmail(userRequestDto.getEmail());
        user.setName(userRequestDto.getName());
        return user;
    }

    private User mapToEntity(UpdateUserRequestDto updateUserRequestDto) {
        User user = new User();
        user.setId(updateUserRequestDto.getId());
        user.setAddress(updateUserRequestDto.getAddress());
        user.setAge(updateUserRequestDto.getAge());
        user.setEmail(updateUserRequestDto.getEmail());
        user.setName(updateUserRequestDto.getName());
        return user;
    }

    private UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setAddress(user.getAddress());
        userResponseDto.setAge(user.getAge());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        userResponseDto.setId(user.getId());
        return userResponseDto;
    }
}