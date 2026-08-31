package cdhananjay.spring_boot_crud.service;

import cdhananjay.spring_boot_crud.dto.UpdateUserRequestDto;
import cdhananjay.spring_boot_crud.dto.UserRequestDto;
import cdhananjay.spring_boot_crud.dto.UserResponseDto;
import cdhananjay.spring_boot_crud.entity.User;
import cdhananjay.spring_boot_crud.exception.DuplicateResourceException;
import cdhananjay.spring_boot_crud.exception.ResourceNotFoundException;
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
        if (userRepository.existsByEmail(userRequestDto.getEmail()))
            throw new DuplicateResourceException("User with email "+ userRequestDto.getEmail() +" already exist.");
        User user = mapToEntity(userRequestDto);
        userRepository.save(user);
        return mapToDto(user);
    }

    public UserResponseDto getUser(Long id) {
        User user = userRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " does not exist."));

        return mapToDto(user);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAllAndFindByDeletedIsFalse().stream().map(this::mapToDto).toList();
    }

    public UserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto){
        if (userRepository.existsByEmail(updateUserRequestDto.getEmail()))
            throw new DuplicateResourceException("User with email " + updateUserRequestDto.getEmail() + " already exists.");
        User _existingUser = userRepository.findByIdAndDeletedIsFalse(updateUserRequestDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id "+ updateUserRequestDto.getId() + " does not exist."));
        User user = mapToEntity(updateUserRequestDto);
        userRepository.save(user);
        return mapToDto(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException("User with id " + id + " does not exist.");
        userRepository.deleteById(id);
    }

    public void softDeleteUser(Long id) {
        User user = userRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " does not exist."));
        user.setDeleted(true);
        userRepository.save(user);
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