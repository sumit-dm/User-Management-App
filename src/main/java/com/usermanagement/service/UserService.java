package com.usermanagement.service;

import com.usermanagement.bindings.LoginForm;
import com.usermanagement.bindings.UnlockAccountForm;
import com.usermanagement.bindings.UserForm;

import java.util.Map;

public interface UserService {
    public String loginCheck(LoginForm loginForm);
    public Map<Integer,String> getCountries();
    public Map<Integer,String> getStates(Integer countryId);
    public Map<Integer,String> getCities(Integer stateId);
    public boolean emailUnique(String email);
    public boolean saveUser(UserForm userForm);
    public boolean unlockAccount(UnlockAccountForm unlockAccountForm);
    public String forgotPassword(String emailId);
}
