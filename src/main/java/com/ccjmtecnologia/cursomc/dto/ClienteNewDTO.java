package com.ccjmtecnologia.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ccjmtecnologia.cursomc.services.validation.ClientInsert;

@ClientInsert
public class ClienteNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Obrigatory field.")
	@Length(min = 5, max = 120, message = "Length between 5 and 120 caracters.")
	private String name;
	@NotEmpty(message = "Obrigatory field.")
	@Email(message = "Invalid email.")
	private String email;
	@NotEmpty(message = "Obrigatory field.")
	private String cpfOuCnpj;
	private Integer type;

	@NotEmpty(message = "Obrigatory field.")
	private String address;
	@NotEmpty(message = "Obrigatory field.")
	private String number;
	private String complement;
	private String district;
	@NotEmpty(message = "Obrigatory field.")
	private String cep;

	@NotEmpty(message = "Obrigatory field.")
	private String phone1;
	private String phone2;
	private String phone3;
	
	private Integer cityId;

	public ClienteNewDTO() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	
}
