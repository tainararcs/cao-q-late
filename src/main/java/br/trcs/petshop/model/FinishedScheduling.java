package br.trcs.petshop.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa um agendamento finalizado no petshop.
 * <br>Contém informações sobre um agendamento que foi concluído, incluindo a data de execução, valores cobrados e se houve desconto aplicado.
 */
public class FinishedScheduling {
	
	private Integer idScheduling;
    private LocalDate executionDate;
    private BigDecimal grossPrice;  // Total Bruto.
	private Boolean discount;       // Houve desconto ou não.
	private BigDecimal finalPrice;
    

    public FinishedScheduling() {}


	public Integer getIdScheduling() {
		return idScheduling;
	}

	public void setIdScheduling(Integer idScheduling) {
		this.idScheduling = idScheduling;
	}

	public LocalDate getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(LocalDate executionDate) {
		this.executionDate = executionDate;
	}

	public BigDecimal getGrossPrice() {
		return grossPrice;
	}

	public void setGrossPrice(BigDecimal grossPrice) {
		this.grossPrice = grossPrice;
	}

	public Boolean getDiscount() {
		return discount;
	}

	public void setDiscount(Boolean discount) {
		this.discount = discount;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
}