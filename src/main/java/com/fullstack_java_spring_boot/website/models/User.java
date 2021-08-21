package com.fullstack_java_spring_boot.website.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@ToString @EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name="id")
    private Long id;
    @Getter @Setter @Column(name="name")
    private String name;
    @Getter @Setter @Column(name="surname")
    private String surname;
    @Getter @Setter @Column(name="email")
    private String email;
    @Getter @Setter @Column(name="phone")
    private String phone;
    @Getter @Setter @Column(name="password")
    private String password;
}