package com.usermanagement.repositories;

import com.usermanagement.entities.CountryMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryMasterEntity, Serializable> {
    List<CountryMasterEntity> findAll();

}
