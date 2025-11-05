package br.trcs.petshop.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa um relatório de serviço realizado no petshop.
 */
public class Report {
   
    private String serviceName;
    private BigDecimal price;
    private LocalDate date;
    private String dogName;
    private String clientName;

    
    public Report() {}
    

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}