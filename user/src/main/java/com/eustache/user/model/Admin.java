package com.eustache.user.model;

import com.eustache.user.model.abstraction.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin extends User {

    private String firstName;

    private String lastName;



}
