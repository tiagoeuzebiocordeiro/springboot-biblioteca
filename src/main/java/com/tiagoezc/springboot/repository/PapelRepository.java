package com.tiagoezc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagoezc.springboot.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {

	Papel findByPapel(String papel);
	
}
