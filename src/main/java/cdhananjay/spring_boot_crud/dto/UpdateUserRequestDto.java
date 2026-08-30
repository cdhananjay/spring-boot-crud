package cdhananjay.spring_boot_crud.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class UpdateUserRequestDto {
    @NotNull(message = "Id cannot be null.")
    private Long id;

    @NotBlank(message = "Name cannot be empty/blank/null.")
    private String name;

    @Email(message = "Invalid email.")
    private String email;

    @Min(value = 18, message = "Age must be 18 or above.")
    @Max(value = 100, message = "Age must be less than 100.")
    private int age;

    @Length(min = 2, max = 30, message = "Address length should be min 2 and max 30")
    private String address;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
