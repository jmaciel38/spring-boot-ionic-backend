package com.ccjmtecnologia.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ccjmtecnologia.cursomc.domain.enums.TipoCliente;
import com.ccjmtecnologia.cursomc.dto.ClienteNewDTO;
import com.ccjmtecnologia.cursomc.resources.exceptions.FieldMessage;
import com.ccjmtecnologia.cursomc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClientInsert ann) {

	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getType().equals(TipoCliente.PESSOAFISICA.getCode()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Invalid CPF."));
		}

		if(objDto.getType().equals(TipoCliente.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Invalid CNPJ."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
