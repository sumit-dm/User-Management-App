package com.usermanagement.rest;

import com.usermanagement.bindings.UnlockAccountForm;
import com.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnlockAccRestController{
    @Autowired
    private UserService userService;

    @PostMapping("/unlockAccount")
    public String unlockAcc(@RequestBody UnlockAccountForm unlockAccountForm){
        boolean unlockAccount = userService.unlockAccount(unlockAccountForm);
        if(unlockAccount){
            return "Account Unlocked";
        }
        else{
            return "Failed to unlock account";
        }
    }
}
