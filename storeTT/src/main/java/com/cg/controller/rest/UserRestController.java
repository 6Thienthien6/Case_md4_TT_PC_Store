package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.model.dto.UserUpdateDTO;
import com.cg.service.user.IUserService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController

@RequestMapping("api/users")
public class UserRestController {
    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(userService.getAllUserWhereDeletedIsFalse(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getById (@PathVariable Long userId ) {
        Optional<User> userOptional = userService.findById(userId);
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<UserUpdateDTO> update(@RequestBody UserUpdateDTO userUpdateDTO){
        Optional<User> userOptional = userService.findById(userUpdateDTO.getId());
        if(!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userUpdateDTO.toUser();
        user.setPassword(userOptional.get().getPassword());
        User newUser = userService.saveUserNotPassword(user);
        return new ResponseEntity<>(newUser.toUserUpdateDTO(),HttpStatus.ACCEPTED);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long userId) {
        Optional<User> userOptional = userService.findById(userId);
        if(!userOptional.isPresent()){
            throw new DataInputException("ID not valid.");
        }
        try {
            userService.softDelete(userId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataInputException("Contact Administrator.");
        }
    }
}
