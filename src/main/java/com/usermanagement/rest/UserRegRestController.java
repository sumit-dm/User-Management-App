package com.usermanagement.rest;

import com.usermanagement.bindings.UserForm;
import com.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserRegRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/countries")
    public Map<Integer,String> countries(){
        return userService.getCountries();
    }
    @GetMapping("/states/{countryId}")
    public Map<Integer,String> states(@PathVariable Integer countryId){
        return userService.getStates(countryId);
    }
    @GetMapping("/cities/{stateId}")
    public Map<Integer,String> cities(@PathVariable Integer stateId){
        return userService.getStates(stateId);
    }
    @GetMapping("/emailcheck/{email}")
    public String emailCheck(@PathVariable String email){
        boolean emailUnique = userService.emailUnique(email);
        if(emailUnique){
            return "UNIQUE";
        }
        return "DUPLICATE";
    }
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody UserForm userForm){
        boolean saveUser = userService.saveUser(userForm);
        if(saveUser){
            return "SUCCESS";
        }
        return "FAIL";
    }



}
