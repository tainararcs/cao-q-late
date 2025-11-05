package br.trcs.petshop.model;

import java.time.LocalDate;

/**
 * Representa um cliente do pet shop.
 * <br>Cada cliente é identificado por um CPF único e possui informações pessoais como nome, data de nascimento, e meios de contato.
 */
public class Client {
	
	private String cpf;
	private String name;
	private LocalDate birthDate;
	private String email;
	private String phoneNumber;
	

	public Client() {}
	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}