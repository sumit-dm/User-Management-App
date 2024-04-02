package com.usermanagement.entities;

//import io.swagger.models.auth.In;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CITIES_MASTER")
public class CitiesMasterEntity {
    @Id
    @Column(name = "CITY_ID")
    private Integer cityId;

    @Column(name = "STATE_ID")
    private Integer stateId;

    @Column(name = "CITY_NAME")
    private String cityName;
}
