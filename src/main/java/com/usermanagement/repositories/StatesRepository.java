package com.usermanagement.repositories;

import com.usermanagement.entities.StateMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface StatesRepository extends JpaRepository<StateMasterEntity, Serializable> {
    public List<StateMasterEntity> findByCountryId(Integer countryId);
}
