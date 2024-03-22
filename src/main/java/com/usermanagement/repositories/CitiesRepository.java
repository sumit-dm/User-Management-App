package com.usermanagement.repositories;

import com.usermanagement.entities.CitiesMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CitiesRepository extends JpaRepository<CitiesMasterEntity,Serializable> {

}
