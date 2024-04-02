package com.usermanagement.repositories;

import com.usermanagement.entities.StateMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface StatesRepository extends JpaRepository<StateMasterEntity, Serializable> {
    public List<StateMasterEntity> findByCountryId(Integer countryId);
}
