package com.study.annotation;

@Table(name = "Persons")
public class Person {

    @Id
    @Column(name = "person_id")
    int id;

    @Column(name = "person_name")
    String name;

    @Column
    double salary;


}
