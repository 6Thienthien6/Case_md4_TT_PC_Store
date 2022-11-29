
package com.cg.model.dto;

import com.cg.model.Role;
import com.cg.model.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    @NotBlank(message = "The email is required")
    @Email(message = "The email address is invalid")
//    @Size( min = 5, max = 50, message = "The length of email must be between 5 and 50 characters")
    private String username;

    @NotBlank(message = "The password is required")
//    @Size( max = 30, message = "Maximum password length 30 characters")
    private String password;
@NotBlank(message = "full name is required")
    private String fullName;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "phone is required")
//    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\\\b", message = "phone start with 03-05-07-08-09")
    private String phone;

    @Valid
    private RoleDTO role;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDTO(Long id, String username, String fullName, String address, String phone, Role role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.role = role.toRoleDTO();
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setFullName(fullName)
                .setAddress(address)
                .setPhone(phone)
                .setRole(role.toRole());
    }

}