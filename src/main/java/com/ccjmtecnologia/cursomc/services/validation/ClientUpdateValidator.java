package com.ccjmtecnologia.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.ccjmtecnologia.cursomc.domain.Cliente;
import com.ccjmtecnologia.cursomc.domain.enums.TipoCliente;
import com.ccjmtecnologia.cursomc.dto.ClienteDTO;
import com.ccjmtecnologia.cursomc.repositories.ClienteRepository;
import com.ccjmtecnologia.cursomc.resources.exceptions.FieldMessage;
import com.ccjmtecnologia.cursomc.services.validation.utils.BR;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest req;
	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClientUpdate ann) {

	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map <String, String> map = (Map <String, String>) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email already exists."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
