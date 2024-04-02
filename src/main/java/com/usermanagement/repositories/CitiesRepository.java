package com.usermanagement.repositories;

import com.usermanagement.entities.CitiesMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<CitiesMasterEntity,Serializable> {
    public List<CitiesMasterEntity> findByStateId(Integer stateId);
}
