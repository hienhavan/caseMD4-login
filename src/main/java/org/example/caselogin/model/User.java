package org.example.caselogin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size
            (min = 6, max = 8)
    private String password;
@Column(name = "phonenumber")
    private String phoneNumber;
@Column(name = "fullname")
    private String fullName;
@Column(name = "dateofbirth")
    private String dateOfBirth;
    private String address;
    private String identity;

    @NotNull
    private String role;


}
