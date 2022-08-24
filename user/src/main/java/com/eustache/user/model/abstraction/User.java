package com.eustache.user.model.abstraction;

import com.eustache.user.model.ConfirmationToken;
import com.eustache.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "user_table")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@RequiredArgsConstructor
@NoArgsConstructor
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

    @ManyToMany
    private Collection<Role> roles = new ArrayList<>();
}
