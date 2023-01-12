package com.springrds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrds.model.Television;

@Repository
public interface TelevisionRepository extends JpaRepository<Television, Long>{

	
	
}
