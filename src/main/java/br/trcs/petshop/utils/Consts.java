package br.trcs.petshop.utils;

/**
* Classe que centraliza todas as constantes globais utilizadas no sistema.
* <p>
* As constantes incluem:
* <ul>
*   <li>Mensagens genéricas (erro e sucesso)</li>
*   <li>Paths fixos de JSPs (para cabeçalho, rodapé e páginas principais)</li>
*   <li>Redirecionamentos usados pelas classes de lógica</li>
* </ul>
*/
public class Consts {
   /** Nome do atributo usado para armazenar mensagens de erro em requisições. */
   public static final String ERROR = "error";
  
   /** Nome do atributo usado para armazenar mensagens de sucesso ou informação. */
   public static final String MSG = "msg";
  
   /** Cabeçalho padrão (menu de navegação). */
   public static final String MENU = "menu.jsp";
  
   /** Rodapé padrão (footer). */
   public static final String FOOTER = "footer.jsp";
  
   // JSPs principais
   public static final String LOGIN = "login.jsp";
   public static final String HOME = "home.jsp";
   public static final String ADD_CLIENT = "addclient.jsp";
   public static final String ADD_DOG = "adddog.jsp";
   public static final String ADD_SERVICE = "addservice.jsp";
   public static final String ADD_SCHEDULING = "addscheduling.jsp";
   public static final String ADD_FINISHED_SCHEDULING = "addfinishedscheduling.jsp";
   public static final String UPDATE_SERVCICE = "updateservice.jsp";
   public static final String SHOW_DOG_SCHEDULINGS = "showdogschedulings.jsp";
   public static final String SHOW_NOT_FINISHED_SCHEDULING = "showschedulingsnotfinished.jsp";
   public static final String SHOW_REPORT = "showreport.jsp";
   public static final String LIST_CLIENTS = "listclients.jsp";
   public static final String LIST_DOGS = "listdogs.jsp";
   public static final String LIST_SERVICES = "listservices.jsp";
  
   // Redirecionamentos
   public static final String REDIRECT_LOGIN = "redirect:login.jsp";
   public static final String REDIRECT_HOME = "redirect:home.jsp";
   public static final String REDIRECT_ADD_CLIENT = "redirect:addclient.jsp";
   public static final String REDIRECT_ADD_DOG = "redirect:adddog.jsp";
   public static final String REDIRECT_ADD_SERVICE = "redirect:addservice.jsp";
   public static final String REDIRECT_ADD_SCHEDULING = "redirect:addscheduling.jsp";
   public static final String REDIRECT_ADD_FINISHED_SCHEDULING = "redirect:addfinishedscheduling.jsp";
   public static final String REDIRECT_UPDATE_SERVCICE = "redirect:updateservice.jsp";
   public static final String REDIRECT_SHOW_DOG_SCHEDULINGS = "redirect:showdogschedulings.jsp";
   public static final String REDIRECT_SHOW_NOT_FINISHED_SCHEDULING = "redirect:showschedulingsnotfinished.jsp";
   public static final String REDIRECT_SHOW_REPORT = "redirect:showreport.jsp";
}