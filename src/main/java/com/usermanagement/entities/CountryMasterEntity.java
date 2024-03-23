package com.usermanagement.entities;

import io.swagger.models.auth.In;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "COUNTRY_MASTER")
public class CountryMasterEntity {
    @Id
    @Column(name = "COUNTRY_ID")
    private Integer countryId;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

}
