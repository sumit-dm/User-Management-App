package com.usermanagement.repositories;

import com.usermanagement.entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Serializable> {
    public UserAccountEntity findByEmailAndPassword(String email,String password);
    public UserAccountEntity findByEmail(String email);
}
