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
        UserResponseDto userResponseDto = userService.getUser(id);
        if (userResponseDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PutMapping()
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(updateUserRequestDto);
        if (userResponseDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean success = userService.deleteUser(id);
        if (success) return ResponseEntity.status(HttpStatus.OK).body("user deleted");
        else return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> softDeleteUser(@PathVariable Long id) {
        boolean success = userService.softDeleteUser(id);
        if (success) return ResponseEntity.status(HttpStatus.OK).body("user soft deleted");
        else return ResponseEntity.notFound().build();
    }
}
