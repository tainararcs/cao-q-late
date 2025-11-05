package br.trcs.petshop.model;

/**
 * Representa um administrador do sistema PetShop.
 * <br>Modela os dados de um usuário administrador, responsável por acessar o painel de controle do sistema,* gerenciar cadastros e executar operações administrativas.
 * <br>O objeto {@code Admin} é armazenado na sessão HTTP para manter o estado do usuário logado.
 */
public class Admin {
	
	private String cpf;
	private String name;
	private String email;
	private String passwordHash;
	
	public Admin() {}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}