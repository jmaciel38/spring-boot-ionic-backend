package com.ccjmtecnologia.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccjmtecnologia.cursomc.domain.Categoria;
import com.ccjmtecnologia.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
//	Page<Produto> search(
//			@Param("name") String name,
//			@Param("categories") List<Categoria> categories,
//			Pageable pageRequest);
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNameContainingAndCategoriesIn(String name, List<Categoria> categories, Pageable pageRequest);
}