package com.bootcamp.microservicemeetup.utils;

import com.bootcamp.microservicemeetup.model.entity.Registration;

public class FakeObjectCreator {

    public static Registration createRegistration(String registration){
        return Registration.builder()
                .name("Jessica")
                .dateOfRegistration("01/01/2022")
                .registration(registration)
                .build();
    }
}
