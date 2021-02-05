package com.qa.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.ToDoDomain;

@Repository
public interface ToDoRepo extends JpaRepository<ToDoDomain, Long> {
	//CRUD -> h2 database
	
	// .save
	
	//find/findall
}
