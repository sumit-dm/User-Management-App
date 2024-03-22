package com.usermanagement.bindings;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserForm {
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private LocalDate dob;
    private String gender;
    private Integer cityId;
    private Integer stateId;
    private Integer countryId;
}
