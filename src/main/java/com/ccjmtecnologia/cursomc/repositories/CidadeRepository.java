package com.ccjmtecnologia.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccjmtecnologia.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

	
}
