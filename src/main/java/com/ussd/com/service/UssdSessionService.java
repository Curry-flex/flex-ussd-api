package com.ussd.com.service;

import org.springframework.stereotype.Service;

import com.ussd.com.entity.UssdSession;


public interface UssdSessionService {


    Iterable<UssdSession> findAll();

    UssdSession findById(String id);

    UssdSession save(UssdSession ussdSession);

    void deleteById(String id);
}
