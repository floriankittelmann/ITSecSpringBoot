package com.example.mse.itsec.demonstrationservice.persistence.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, Integer> id;

    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";
    public static final String ID = "id";
}
