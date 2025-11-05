package br.trcs.petshop.model;

import java.time.LocalDate;
import java.util.List;

import br.trcs.petshop.enums.SchedulingStatus;

/**
 * Representa um agendamento de serviços no sistema do PetShop.
 * <br>Contém as informações necessárias para registrar um agendamento, incluindo data, cliente, cachorro, serviços selecionados e o status atual do agendamento.
 */
public class Scheduling {
	private Integer id;
	private LocalDate date; 
	private SchedulingStatus status; 
	private String cpfClient; 
	private Integer idDog; 
	private List<Integer> idServicesList; 
	
	
	public Scheduling() {}
	
	
	public int getId() {
		return id;
	} 
	
	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public SchedulingStatus getStatus() {
		return status;
	}
	
	public void setStatus(SchedulingStatus status) {
		this.status = status;
	}

	public String getCpfClient() {
		return cpfClient;
	}

	public void setCpfClient(String cpfClient) {
		this.cpfClient = cpfClient;
	}

	public Integer getIdDog() {
		return idDog;
	}

	public void setIdDog(Integer idDog) {
		this.idDog = idDog;
	}

	public List<Integer> getIdServicesList() {
		return idServicesList;
	}

	public void setIdServicesList(List<Integer> idServicesList) {
		this.idServicesList = idServicesList;
	}	
}