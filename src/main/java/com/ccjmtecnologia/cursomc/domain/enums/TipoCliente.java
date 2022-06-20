package com.ccjmtecnologia.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int code;
	private String description;

	private TipoCliente(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static TipoCliente toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for(TipoCliente x : TipoCliente.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Invalid id: " + code);
	}
}
