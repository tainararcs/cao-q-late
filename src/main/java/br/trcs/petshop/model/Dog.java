package br.trcs.petshop.model;

import br.trcs.petshop.enums.DogSize;

/**
 * Representa um cão cadastrado no sistema do pet shop.
 * <br>Cada cão pertence a um cliente (referenciado pelo CPF) e possui informações como nome, raça e porte. 
 * @see br.trcs.petshop.enums.DogSize
 */
public class Dog {
	
	private int id; 
	private String name;
	private String breed;
	private DogSize size; 
	private String cpfClient;

	
	public Dog() {}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public DogSize getSize() {
		return size;
	}

	public void setSize(DogSize size) {
		this.size = size;
	}

	public String getCpfClient() {
		return cpfClient;
	}

	public void setCpfClient(String cpfClient) {
		this.cpfClient = cpfClient;
	}
}