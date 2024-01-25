package com.ussd.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ussd.com.entity.UssdSession;
import com.ussd.com.repository.SessionRepository;

@Service
public class SessionService {


    @Autowired
    private SessionRepository ussdSessionRepository;

    public UssdSession createUssdSession(UssdSession session) {
        return ussdSessionRepository.save(session);
    }

    public UssdSession get(String id) {
        return ussdSessionRepository.findById(id).orElse(null);
    }

    public UssdSession update(UssdSession session) {
        if (ussdSessionRepository.existsById(session.getId())) {
            return ussdSessionRepository.save(session);
        }
        throw new IllegalArgumentException("A car must have an id to be updated");
    }

    public void delete(String id) {
        // deleting the session
        ussdSessionRepository.deleteById(id);
    }
}
