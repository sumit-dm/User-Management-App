package com.usermanagement.rest;

import com.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPwdRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/forgotpwd/{email}")
    public String forgotPwd(@PathVariable String email){
        String status = userService.forgotPassword(email);
        return status;
    }
}
