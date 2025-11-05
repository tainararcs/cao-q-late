package br.trcs.petshop.enums;

/**
 * Enumeração que representa os diferentes portes (tamanhos) dos cães atendidos no sistema.
 * <ul>
 *   <li>{@link #SMALL} — Cães de pequeno porte (até 10 kg)</li>
 *   <li>{@link #MEDIUM} — Cães de médio porte (entre 11 kg e 25 kg)</li>
 *   <li>{@link #LARGE} — Cães de grande porte (acima de 26 kg)</li>
 * </ul>
 *
 * Cada constante possui um rótulo legível em português, utilizado nas interfaces JSP e relatórios.
 */
public enum DogSize {
	
    /** Cães de pequeno porte (até 10 kg). */
	SMALL("Pequeno"),   

    /** Cães de médio porte (entre 11 kg e 25 kg). */
    MEDIUM("Médio"),    

    /** Cães de grande porte (acima de 26 kg). */
    LARGE("Grande");    
	
	/** Rótulo textual usado nas telas (exibido em português). */
	private final String label;
	
	/**
	 * Construtor privado do enum.
	 * 
	 * @param label texto de exibição associado ao porte.
	 */
	private DogSize(String label) {
		this.label = label;
	}
    
    /**
     * Retorna o rótulo legível do porte (ex: "Pequeno", "Médio", "Grande").
     * 
     * @return o nome legível do porte.
     */
    public String getLabel() {
        return label;
    }
    
    /**
     * Obtém o valor da enumeração correspondente a um rótulo informado.
     * 
     * <br>Exemplo: <pre>{@code DogSize size = DogSize.fromLabel("Médio"); // retorna DogSize.MEDIUM }</pre>
     * 
     * @param label texto legível do porte.
     * @return o enum {@link DogSize} correspondente.
     * @throws IllegalArgumentException se o rótulo não corresponder a nenhum porte cadastrado.
     */
    public static DogSize fromLabel(String label) {
        for (DogSize size : values()) {
            if (size.getLabel().equalsIgnoreCase(label)) {
                return size;
            }
        }
        throw new IllegalArgumentException("Porte " + label + " não cadastrado");
    }
}