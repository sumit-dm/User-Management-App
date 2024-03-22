package com.usermanagement.service;

import com.usermanagement.bindings.LoginForm;
import com.usermanagement.bindings.UnlockAccountForm;
import com.usermanagement.bindings.UserForm;
import com.usermanagement.entities.CountryMasterEntity;
import com.usermanagement.entities.UserAccountEntity;
import com.usermanagement.repositories.CountryRepository;
import com.usermanagement.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class UserServiceImplementation implements UserService{
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Override
    public String loginCheck(LoginForm loginForm) {
        UserAccountEntity userAccount = userAccountRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        if(userAccount != null){
            String accountStatus = userAccount.getAccStatus();
            if(accountStatus.equals("Locked")){
                return "Your Account is Locked";
            }
            else {
                return "Success";
            }
        }else{
            return "Invalid Credentials";
        }
    }

    @Override
    public Map<Integer, String> getCountries() {
        List<CountryMasterEntity> counries = countryRepository.findAll();


        return null;
    }

    @Override
    public Map<Integer, String> getStates(Integer countryId) {
        return null;
    }

    @Override
    public Map<Integer, String> getCities(Integer stateId) {
        return null;
    }

    @Override
    public boolean emailUnique(String email) {
        return false;
    }

    @Override
    public boolean saveUser(UserForm userForm) {
        return false;
    }

    @Override
    public boolean unlockAccount(UnlockAccountForm unlockAccountForm) {
        return false;
    }

    @Override
    public String forgotPassword(String emailId) {
        return null;
    }
}
