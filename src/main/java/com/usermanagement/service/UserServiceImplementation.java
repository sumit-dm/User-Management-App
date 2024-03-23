package com.usermanagement.service;

import com.usermanagement.bindings.LoginForm;
import com.usermanagement.bindings.UnlockAccountForm;
import com.usermanagement.bindings.UserForm;
import com.usermanagement.entities.CitiesMasterEntity;
import com.usermanagement.entities.CountryMasterEntity;
import com.usermanagement.entities.StateMasterEntity;
import com.usermanagement.entities.UserAccountEntity;
import com.usermanagement.repositories.CitiesRepository;
import com.usermanagement.repositories.CountryRepository;
import com.usermanagement.repositories.StatesRepository;
import com.usermanagement.repositories.UserAccountRepository;
import com.usermanagement.utils.EmailUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class UserServiceImplementation implements UserService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StatesRepository statesRepository;
    @Autowired
    private CitiesRepository citiesRepository;
    @Autowired
    private EmailUtils emailUtils;

    @Override
    public String loginCheck(LoginForm loginForm) {
        UserAccountEntity userAccount = userAccountRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        if (userAccount != null) {
            String accountStatus = userAccount.getAccStatus();
            if (accountStatus.equals("Locked")) {
                return "Your Account is Locked";
            } else {
                return "Success";
            }
        } else {
            return "Invalid Credentials";
        }
    }

    @Override
    public Map<Integer, String> getCountries() {
        List<CountryMasterEntity> countries = countryRepository.findAll();
        Map<Integer, String> countryMap = new HashMap<>();
        countries.forEach(country -> {
            countryMap.put(country.getCountryId(), country.getCountryName());
        });
        return countryMap;
    }
    @Override
    public Map<Integer, String> getStates(Integer countryId) {
        List<StateMasterEntity> states = statesRepository.findByCountryId(countryId);
        Map<Integer, String> statesMap = new HashMap<>();

        states.forEach(state -> {
            statesMap.put(state.getStateId(), state.getStateName());
        });
        return statesMap;
    }

    @Override
    public Map<Integer, String> getCities(Integer stateId) {
        List<CitiesMasterEntity> cities = citiesRepository.findByStateId(stateId);
        Map<Integer, String> citiesMap = new HashMap<>();

        cities.forEach(city -> {
            citiesMap.put(city.getCityId(), city.getCityName());
        });
        return citiesMap;
    }

    @Override
    public boolean emailUnique(String email) {
        UserAccountEntity user = userAccountRepository.findByEmail(email);
        if (user == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveUser(UserForm userForm) {
        userForm.setAccountStatus("LOCKED");
        userForm.setPassword(generateTempPassword());
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        BeanUtils.copyProperties(userForm, userAccountEntity);
        UserAccountEntity save = userAccountRepository.save(userAccountEntity);

        String subject = "User Registration Successfully | Sumit IT";
        String body = getUserRegEmailBody(userForm);

        if (save.getUserId() != null) {
            //logic to send email to unlock the account
            emailUtils.sendEmail(userForm.getEmail(), subject, body);

        }
        return false;
    }

    @Override
    public boolean unlockAccount(UnlockAccountForm unlockAccountForm) {
        String email = unlockAccountForm.getEmail();
        String tempPwd = unlockAccountForm.getTemporaryPassword();

        UserAccountEntity userAccount = userAccountRepository.findByEmailAndPassword(email, tempPwd);
        if (userAccount != null) {
            userAccount.setPassword(unlockAccountForm.getNewPassword());
            userAccount.setAccStatus("Unlocked");
            userAccountRepository.save(userAccount);
            return true;
        }
        return false;
    }

    @Override
    public String forgotPassword(String emailId) {
        UserAccountEntity user = userAccountRepository.findByEmail(emailId);
        if (user != null) {
            //send email to user with password
            String subject = "Forgot Password | Sumit IT";
            String body = getForgotPwdEmailBody(user);
            emailUtils.sendEmail(user.getEmail(), subject, body);
            return "Email sent with password";
        }
        return "Enter Correct Email Id";
    }

    private String generateTempPassword() {
        //logic
        String generatedPwd = RandomStringUtils.randomAlphanumeric(6);
        return generatedPwd;
    }

    private String getUserRegEmailBody(UserForm userForm) {
        //logic to read the body
        StringBuffer sb = new StringBuffer();
        String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            lines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.forEach(line -> {
            if (line.contains("{FNAME}")) {
                line.replace("{FNAME}", userForm.getFirstName());
            }
            if (line.contains("{LNAME}")) {
                line.replace("{LNAME}", userForm.getLastName());
            }
            if (line.contains("{TEMP-PWD}")) {
                line.replace("{TEMP-PWD}", userForm.getPassword());
            }
            if (line.contains("{EMAIL}")) {
                line.replace("{EMAIL}", userForm.getPassword());
            }
            sb.append(line);
        });
        return sb.toString();
    }

    private String getForgotPwdEmailBody(UserAccountEntity userAccountEntity) {
        //logic to read the body
        StringBuffer sb = new StringBuffer();
        String filename = "RECOVER-PASSWORD-EMAIL-TEMPLATE.txt";

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            lines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.forEach(line -> {
            if (line.contains("{FNAME}")) {
                line.replace("{FNAME}", userAccountEntity.getFirstName());
            }
            if (line.contains("{LNAME}")) {
                line.replace("{LNAME}", userAccountEntity.getLastName());
            }
            if (line.contains("{PWD}")) {
                line.replace("{PWD}", userAccountEntity.getPassword());
            }
            sb.append(line);
        });
        return sb.toString();
    }
}
