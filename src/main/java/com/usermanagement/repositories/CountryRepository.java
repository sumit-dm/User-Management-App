package com.usermanagement.repositories;

import com.usermanagement.entities.CountryMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CountryRepository extends JpaRepository<CountryMasterEntity, Serializable> {
}
