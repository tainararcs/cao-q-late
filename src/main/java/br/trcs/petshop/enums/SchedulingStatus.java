package br.trcs.petshop.enums;

/**
 * Enumeração que representa os diferentes status possíveis de um agendamento no sistema do pet shop.
 */
public enum SchedulingStatus {

    /** Agendamento marcado e ainda pendente de execução. */
    SCHEDULED("Agendado"),  

    /** Serviço realizado e agendamento finalizado. */
    FINISHED("Finalizado");


    /** Texto legível exibido nas interfaces. */
	private final String status;

	/**
	 * Construtor privado do enum.
	 * 
	 * @param status texto de exibição associado ao status.
	 */
	private SchedulingStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Retorna o nome legível do status.
	 * 
	 * @return string representando o status (ex: "Agendado", "Finalizado").
	 */
	public String getStatus() {
        return status;
    }
	
	/**
	 * Converte um texto legível  para o valor correspondente da enum {@link SchedulingStatus}.
	 * 
	 * <br>Exemplo: <pre>{@code SchedulingStatus s = SchedulingStatus.fromStatus("Finalizado"); // retorna SchedulingStatus.FINISHED }</pre>
	 * 
	 * @param status texto legível do status.
	 * @return o enum correspondente ao texto informado.
	 * @throws IllegalArgumentException se o texto não corresponder a nenhum status conhecido.
	 */
	public static SchedulingStatus fromStatus(String status) {
        for (SchedulingStatus s : values()) {
            if (s.getStatus().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Não há o status " + status);
    }
}