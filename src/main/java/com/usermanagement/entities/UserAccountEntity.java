package com.usermanagement.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "USER_ACCOUNT")
@Data
public class UserAccountEntity {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USER_EMAIL",unique = true)
    private String email;

    @Column(name = "USER_PWD")
    private String password;

    @Column(name = "USER_MOBILE")
    private Long phoneNumber;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "CITY_ID")
    private Integer cityId;

    @Column(name = "STATE_ID")
    private Integer stateId;

    @Column(name = "COUNTRY_ID")
    private Integer countryId;

    @Column(name = "ACC_STATUS")
    private String accStatus;

    @Column(name = "CREATED_DATE",updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;

    @Column(name = "UPDATED_DATE",updatable = false)
    @UpdateTimestamp
    private LocalDate updatedDated;
}
