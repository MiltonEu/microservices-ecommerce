package com.eustache.user.model;

import com.eustache.user.model.abstraction.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.util.Collection;

@Data
@SuperBuilder
@Entity
public class Admin extends User {

    private String firstName;

    private String lastName;


    public Admin() {
        super();
    }

    public Admin(Integer id, String email, String password, Collection<Role> roles,  boolean confirmed, String firstName, String lastName) {
        super(id, email,password , confirmed, null, roles);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
