package com.eustache.user.model.abstraction;

import com.eustache.user.model.Admin;
import com.eustache.user.model.ConfirmationToken;
import com.eustache.user.model.Customer;
import com.eustache.user.model.Role;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@Entity(name = "user_table")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuperBuilder
@Data
@JsonTypeInfo(use = NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Customer.class, name = "customer"),
        @JsonSubTypes.Type(value = Admin.class, name = "admin")
})
public abstract class User  {

    @Id
    @SequenceGenerator(name = "user_id_sequence",
            sequenceName = "user_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence")
    private Integer userId;

    private String email;


    private String password;


    private boolean confirmed = false;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<ConfirmationToken> tokens;

    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Role> roles = new ArrayList<>();

    public User() {

    }

    public User(Integer userId, String email, String password, boolean confirmed, List<ConfirmationToken> tokens, Collection<Role> roles) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.confirmed = confirmed;
        this.tokens = tokens;
        this.roles = roles;
    }
}
