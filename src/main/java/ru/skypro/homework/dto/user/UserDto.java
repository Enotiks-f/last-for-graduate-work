package ru.skypro.homework.dto.user;

import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.dto.reg.Role;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;
}
