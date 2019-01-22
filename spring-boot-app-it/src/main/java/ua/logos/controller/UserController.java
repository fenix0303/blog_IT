package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.UserDTO;
import ua.logos.entity.UserEntity;
import ua.logos.service.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        UserDTO userDTO = userService.saveUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<UserDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{userId:[0-9]{1,5}}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long id) {
        UserDTO user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{userId:[0-9]{1,5}}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long id, @RequestBody UserDTO userToUpdate) {
        UserDTO user = userService.updateUser(id, userToUpdate);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
