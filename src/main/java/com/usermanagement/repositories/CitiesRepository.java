package com.usermanagement.repositories;

import com.usermanagement.entities.CitiesMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface CitiesRepository extends JpaRepository<CitiesMasterEntity,Serializable> {
    public List<CitiesMasterEntity> findByStateId(Integer stateId);
}
