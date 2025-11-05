package br.trcs.petshop.model;

import java.math.BigDecimal;

/**
 * Representa um serviço oferecido pelo pet shop.
 */
public class Service {
	
	private int id;
	private String name;
	private BigDecimal price;
	
	
	public Service() {}
	

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
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}