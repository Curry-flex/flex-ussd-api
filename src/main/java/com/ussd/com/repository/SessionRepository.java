package com.ussd.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ussd.com.entity.UssdSession;

@Repository
public interface SessionRepository extends CrudRepository<UssdSession, String> {

}
