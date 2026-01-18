package br.trcs.petshop.utils;

import java.util.regex.Pattern;

/**
* Classe utilitária responsável por centralizar métodos de validação de dados básicos utilizados em diferentes partes do sistema.
*/
public class ValidationUtils {
  
   /**
    * Valida o CPF conforme as regras oficiais brasileiras.
    * <br>Rejeita sequências repetidas (ex: 111.111.111-11) e realiza o cálculo dos dígitos verificadores.
    *
    * @param cpf número de CPF no formato com ou sem pontuação.
    * @return {@code true} se o CPF for válido; {@code false} caso contrário.
    */
   public static boolean isValidCPF(String cpf) {
       if (cpf == null) return false;
      
       cpf = cpf.replaceAll("[^\\d]", ""); // Remove . e -
      
       // Não permite um dígito qualquer seguido dele mesmo repetido 10 vezes.
       if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}"))
           return false;
      
       // Cálculo dos dígitos verificadores.
       try {
           for (int j = 9; j < 11; j++) {
               int sum = 0;
               for (int i = 0; i < j; i++)
               	sum += (cpf.charAt(i) - '0') * ((j + 1) - i);
              
               int rest = (sum * 10) % 11;
              
               if (rest == 10) rest = 0;
               if (rest != (cpf.charAt(j) - '0'))
                   return false;
           }
           return true;
       } catch (Exception e) {
           return false;
       }
   }
   /**
    * Valida o formato de e-mail, garantindo que siga o padrão geral <strong>nome@dominio.tld</strong> (ex: joao@empresa.com.br).
    *
    * @param email endereço de e-mail a ser validado.
    * @return {@code true} se o formato for válido; {@code false} caso contrário.
    */
   public static boolean isValidEmail(String email) {
       if (email == null)
       	return false;
      
       /* '^'	Início da string: Garante que a correspondência comece no início.
        * '[A-Za-z0-9+_.-]+' Usuário do e-mail: Permite letras, números e os símbolos +, _, ., -, uma ou mais vezes.
        * '@'	Símbolo obrigatório de e-mail.
        * '[A-Za-z0-9.-]+'	Domínio:	Permite letras, números, ponto e hífen (ex: gmail.com ou empresa-xyz.org).
        * '\.' Ponto literal: O \ escapa o ponto, que no regex normal significa "qualquer caractere".
        * '[A-Za-z]{2,}' TLD (Top-Level Domain): Permite apenas letras, com no mínimo 2 caracteres (como .br, .com, .info).
        * '$' Fim da string: Garante que não há mais nada após o domínio.
        */
       String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
       return Pattern.matches(regex, email);
   }
}