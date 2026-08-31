package cdhananjay.spring_boot_crud.controller;

import cdhananjay.spring_boot_crud.dto.UpdateUserRequestDto;
import cdhananjay.spring_boot_crud.dto.UserRequestDto;
import cdhananjay.spring_boot_crud.dto.UserResponseDto;
import cdhananjay.spring_boot_crud.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUser(id));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PutMapping()
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(updateUserRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> softDeleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
