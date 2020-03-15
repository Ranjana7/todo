package com.test.deloitte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.test.deloitte.model.User;

@Repository
@Transactional
@CrossOrigin("*")
public interface UserRepository extends JpaRepository<User, Long> {
  
  User findUserByName(String name);
}