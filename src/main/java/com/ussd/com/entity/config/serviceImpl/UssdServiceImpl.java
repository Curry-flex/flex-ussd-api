package com.ussd.com.entity.config.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ussd.com.entity.UssdSession;
import com.ussd.com.repository.SessionRepository;
import com.ussd.com.service.UssdSessionService;



@Service
public class UssdServiceImpl implements UssdSessionService {


    private SessionRepository ussdSessionRepository;

    @Autowired
    public UssdServiceImpl(SessionRepository ussdSessionRepository) {
        this.ussdSessionRepository = ussdSessionRepository;
    }
    
	@Override
	public Iterable<UssdSession> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UssdSession findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UssdSession save(UssdSession ussdSession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

    

}
