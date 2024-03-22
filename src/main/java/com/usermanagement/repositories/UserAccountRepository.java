package com.usermanagement.repositories;

import com.usermanagement.entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Serializable> {
    public UserAccountEntity findByEmailAndPassword(String email,String password);
}
