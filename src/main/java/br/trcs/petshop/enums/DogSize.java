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
	SMALL,   

    /** Cães de médio porte (entre 11 kg e 25 kg). */
    MEDIUM,    

    /** Cães de grande porte (acima de 26 kg). */
    LARGE;    
	
	
	/**
	 * Construtor privado do enum.
	 */
	private DogSize() {}
    
}