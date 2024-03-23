package com.usermanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "STATE_MASTER")
@Data
public class StateMasterEntity {
    @Id
    @Column(name = "STATE_ID")
    private Integer stateId;

    @Column(name = "STATE_NAME")
    private String  stateName;

    @Column(name = "COUNTRY_ID")
    private String countryId;

}
