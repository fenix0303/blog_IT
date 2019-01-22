package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.UserDetailsDTO;
import ua.logos.service.UserDetailsService;

import java.util.List;

@RestController
@RequestMapping("details")
public class UserDetailsController {


    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> createDetails(@RequestBody UserDetailsDTO userDetails) {
        UserDetailsDTO userDetailsDTO = userDetailsService.saveDetails(userDetails);
        return new ResponseEntity<>("User's details added", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getDetails() {
        List<UserDetailsDTO> details = userDetailsService.findAll();
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @GetMapping("{detailsId:[0-9]{1,5}}")
    public ResponseEntity<?> getDetailsById(@PathVariable("detailsId") Long id) {
        UserDetailsDTO details = userDetailsService.findById(id);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PutMapping("{detailsId:[0-9]{1,5}}")
    public ResponseEntity<?> updateUser(@PathVariable("detailsId") Long id, @RequestBody UserDetailsDTO detailsToUpdate) {
        UserDetailsDTO details = userDetailsService.updateDetails(id, detailsToUpdate);
        return new ResponseEntity<>("User's details updated", HttpStatus.OK);

    }

}
