package com.mindbowser.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindbowser.demo.model.ManagerModel;

@Repository
public interface DemoRepository extends JpaRepository<ManagerModel, Long> {

	ManagerModel findByEmail(String email);

	ManagerModel findByEmailAndPassword(String email, String password);

}
