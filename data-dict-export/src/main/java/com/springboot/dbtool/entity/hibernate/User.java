package com.springboot.dbtool.entity.hibernate;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "userName")
    String userName;
    @Column(name = "rank")
    Integer rank;
    @Column(name = "departmentId")
    Long departmentId;
}
