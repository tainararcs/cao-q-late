package br.trcs.petshop.enums;

/**
 * Enumeração que representa os diferentes status possíveis de um agendamento no sistema do pet shop.
 */
public enum SchedulingStatus {

    /** Agendamento marcado e ainda pendente. */
    SCHEDULED,  

    /** Serviço realizado e finalizado. */
    FINISHED;


	/**
	 * Construtor privado do enum.
	 */
	private SchedulingStatus() {}
	
}