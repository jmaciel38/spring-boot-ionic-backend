package com.ccjmtecnologia.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ccjmtecnologia.cursomc.domain.Categoria;
import com.ccjmtecnologia.cursomc.domain.Produto;
import com.ccjmtecnologia.cursomc.repositories.CategoriaRepository;
import com.ccjmtecnologia.cursomc.repositories.ProdutoRepository;
import com.ccjmtecnologia.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository catRrepo;
	
	public Produto find(Integer id) {
		
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id: " + id + ", Type: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String name, List<Integer> ids,Integer page, Integer perPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, perPage, Direction.valueOf(direction),
				orderBy);
		List<Categoria> categories = catRrepo.findAllById(ids);
		return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
}
