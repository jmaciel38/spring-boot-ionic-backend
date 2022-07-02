package com.ccjmtecnologia.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccjmtecnologia.cursomc.domain.Cidade;
import com.ccjmtecnologia.cursomc.domain.Cliente;
import com.ccjmtecnologia.cursomc.domain.Endereco;
import com.ccjmtecnologia.cursomc.domain.enums.TipoCliente;
import com.ccjmtecnologia.cursomc.dto.ClienteDTO;
import com.ccjmtecnologia.cursomc.dto.ClienteNewDTO;
import com.ccjmtecnologia.cursomc.repositories.ClienteRepository;
import com.ccjmtecnologia.cursomc.repositories.EnderecoRepository;
import com.ccjmtecnologia.cursomc.services.exceptions.DataIntegrityException;
import com.ccjmtecnologia.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository addressRepo;
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id: " + id + ", Type: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente save(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepo.saveAll(obj.getAddresses());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Not possible delete this because have related entities.");
		}

	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer perPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, perPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getName(), objDto.getEmail(),
				objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getType()));
		Cidade city = new Cidade(objDto.getCityId(), null, null);
		Endereco address = new Endereco(null, objDto.getAddress(), objDto.getNumber(),
				objDto.getComplement(), objDto.getDistrict(), objDto.getCep(), cli, city);
		cli.getAddresses().add(address);
		cli.getPhones().add(objDto.getPhone1());
		if(objDto.getPhone2() != null) {
			cli.getPhones().add(objDto.getPhone2());
		}
		if(objDto.getPhone3() != null) {
			cli.getPhones().add(objDto.getPhone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
