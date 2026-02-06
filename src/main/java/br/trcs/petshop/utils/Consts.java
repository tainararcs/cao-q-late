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
	/**	 */
	public static final String CONTROLLER = "controller";
	
   /** Nome do atributo usado para armazenar mensagens de erro em requisições. */
   public static final String ERROR = "error";
  
   /** Nome do atributo usado para armazenar mensagens de sucesso ou informação. */
   public static final String MSG = "msg";
  
   /** Cabeçalho padrão (menu de navegação). */
   public static final String MENU_JSP = "menu.jsp";
  
   /** Rodapé padrão. */
   public static final String FOOTER_JSP = "footer.jsp";

   /** */
   public static final String MESSAGES_JSP = "messages.jsp";
   
   public static final String ERROR_JSP = "error.jsp";
   
   // JSPs principais.
   public static final String LOGIN_JSP = "login.jsp";
   public static final String HOME_JSP = "home.jsp";
   public static final String ADD_CLIENT_JSP = "addclient.jsp";
   public static final String ADD_DOG_JSP = "adddog.jsp";
   public static final String ADD_SERVICE_JSP = "addservice.jsp";
   public static final String ADD_SCHEDULING_JSP = "addscheduling.jsp";
   public static final String ADD_FINISHED_SCHEDULING_JSP = "addfinishedscheduling.jsp";
   public static final String UPDATE_SERVICE_JSP = "updateservice.jsp";
   public static final String LIST_CLIENTS_JSP = "listclients.jsp";
   public static final String LIST_DOGS_JSP = "listdogs.jsp";
   public static final String LIST_SERVICES_JSP = "listservices.jsp";
   public static final String LIST_DOG_SCHEDULINGS_JSP = "listdogschedulings.jsp";
   public static final String LIST_NOT_FINISHED_SCHEDULING_JSP = "listschedulingsnotfinished.jsp";
   public static final String SHOW_REPORT_JSP = "showreport.jsp";
  
   // Redirecionamentos.
   public static final String REDIRECT_LOGIN_JSP = "redirect:login.jsp";
   public static final String REDIRECT_HOME_JSP = "redirect:home.jsp";
   public static final String REDIRECT_ADD_CLIENT_JSP = "redirect:addclient.jsp";
   public static final String REDIRECT_ADD_DOG_JSP = "redirect:adddog.jsp";
   public static final String REDIRECT_ADD_SERVICE_JSP = "redirect:addservice.jsp";
   public static final String REDIRECT_ADD_SCHEDULING_JSP = "redirect:addscheduling.jsp";
   public static final String REDIRECT_ADD_FINISHED_SCHEDULING_JSP = "redirect:addfinishedscheduling.jsp";
   public static final String REDIRECT_UPDATE_SERVICE_JSP = "redirect:updateservice.jsp";
   public static final String REDIRECT_LIST_CLIENTS_JSP = "redirect:listclients.jsp";
   public static final String REDIRECT_LIST_DOGS_JSP = "redirect:listdogs.jsp";
   public static final String REDIRECT_LIST_SERVICES_JSP = "redirect:listservices.jsp";
   public static final String REDIRECT_LIST_DOG_SCHEDULINGS_JSP = "redirect:listdogschedulings.jsp";
   public static final String REDIRECT_LIST_NOT_FINISHED_SCHEDULING_JSP = "redirect:listschedulingsnotfinished.jsp";
   public static final String REDIRECT_SHOW_REPORT_JSP = "redirect:showreport.jsp";
   
   // Lógicas.
   public static final String AUTH_ADMIN_LOGIC = "AuthAdmin";
   public static final String LOGOUT_LOGIC = "Logout";
   public static final String ADD_CLIENT_LOGIC = "AddClient";
   public static final String ADD_DOG_LOGIC = "AddDog";
   public static final String ADD_SERVICE_LOGIC = "AddService";
   public static final String ADD_SCHEDULING_LOGIC = "AddScheduling";
   public static final String ADD_FINISHED_SCHEDULING_LOGIC = "AddFinishedScheduling";
   public static final String UPDATE_SERVICE_LOGIC = "UpdateService";
   public static final String SEARCH_SERVICE_LOGIC = "SearchService";
   public static final String DELETE_SCHEDULING_LOGIC = "DeleteScheduling";
   public static final String LIST_CLIENTS_LOGIC = "ListClients";
   public static final String LIST_DOGS_LOGIC = "ListDogs";
   public static final String LIST_SERVICES_LOGIC = "ListServices";
   public static final String LIST_DOG_SCHEDULINGS_LOGIC = "ListDogSchedulings";
   public static final String LIST_NOT_FINISHED_SCHEDULING_LOGIC = "ListNotFinishedSchedulings";
   public static final String SHOW_REPORT_LOGIC = "ShowReport";
   
   // Títulos.
   public static final String LOGIN_TITLE = "Login";
   public static final String ADD_CLIENT_TITLE = "Cadastrar Cliente";
   public static final String ADD_DOG_TITLE = "Cadastrar Cão";
   public static final String ADD_SERVICE_TITLE = "Cadastrar Serviço";
   public static final String ADD_SCHEDULING_TITLE = "Agendar de Serviços";
   public static final String ADD_FINISHED_SCHEDULING_TITLE = "Finalizar Agendamento";
   public static final String UPDATE_SERVICE_TITLE = "Alterar Serviço";
   public static final String LIST_CLIENTS_TITLE = "Clientes Cadastrados";
   public static final String LIST_DOGS_TITLE = "Cães Cadastrados";
   public static final String LIST_SERVICES_TITLE = "Serviços Cadastrados";
   public static final String LIST_DOG_SCHEDULINGS_TITLE = "Agendamentos do Cão";
   public static final String LIST_NOT_FINISHED_SCHEDULING_TITLE = "Agendamentos não Finalizados";
   public static final String SHOW_REPORT_TITLE = "Relatório de Serviços Prestados";
   
   // Botões.
   public static final String LOGIN_BUTTON = "Login";
   public static final String ADD_BUTTON = "Cadastrar";
   public static final String SCHEDULE_BUTTON = "Agendar";
   public static final String FINALIZE_BUTTON = "Finalizar"; 
   public static final String UPDATE_BUTTON = "Alterar";
   public static final String SELECT_DOG = "Selecionar cão";
   public static final String LIST_CLIENTS_BUTTON = "Buscar clientes";
   public static final String LIST_DOGS_BUTTON = "Buscar cães";
   public static final String LIST_SERVICES_BUTTON = "Buscar Serviços";
   public static final String LIST_NOT_FINISHED_SCHEDULING_BUTTON = "Buscar Agendamentos Pendentes";
   public static final String GENERATE_REPORT_BUTTON = "Gerar Relatório";
   
   // Mensagens de erro.
   public static final Object FIELDS_ERROR = "Preencha todos os campos";
   public static final Object CPF_ERROR = "CPF inválido";
   public static final Object EMAIL_ERROR = "E-mail inválido";
   public static final Object DATE_ERROR = "Data não disponível para novos agendamentos";
   public static final Object LOGIN_ERROR = "E-mail ou senha incorretos";
   public static final Object ADD_CLIENT_ERROR = "Erro ao cadastrar cliente";
   public static final Object CLIENT_NOT_FOUND_ERROR = "Cliente não encontrado";
   public static final Object CLIENT_ALREADY_ADDED_ERROR = "Cliente já cadastrado";
   public static final Object ADD_DOG_ERROR = "Erro ao cadastrar cão";
   public static final Object SEARCH_DOG_ERROR = "Nenhum cão encontrado";
   public static final Object ADD_SERVICE_ERROR = "Erro ao cadastrar serviço";
   public static final Object SERVICE_NOT_FOUND_ERROR = "Serviço não econtrado";
   public static final Object SERVICE_ALREADY_ADDED_ERROR = "Serviço já cadastrado";
   public static final Object UPDATE_SERVICE_ERROR = "Erro ao alterar serviço";
   public static final Object ADD_SCHEDULING_ERROR = "Erro ao cadastrar o agendamento";
   public static final Object ADD_FINISHED_SCHEDULING_ERROR = "Erro ao finalizar agendamento";
   public static final Object SCHEDULING_NOT_FOUND_ERROR = "Nenhum agendamento encontrado";
   public static final Object SCHEDULING_ALREADY_FINALIZED_ERROR =  "Agendamento já finalizado";
   public static final Object DATE_FINALIZE_SCHEDULING_ERROR = "Nenhum agendamento finalizado no período informado";
   public static final Object DELETE_SCHEDULING_ERROR = "Erro ao cancelar agendamento";
   public static final Object DATE_DELETE_SCHEDULING_ERROR = "Não é possível cancelar agendamentos com menos de 24h de antecedência";
   
   // Mensagens de confirmação.
   public static final Object ADD_CLIENT_SUCCESS = "Cliente cadastrado com sucesso";
   public static final Object ADD_DOG_SUCCESS = "Cão cadastrado com sucesso";
   public static final Object ADD_SERVICE_SUCCESS = "Serviço cadastrado com sucesso";
   public static final Object UPDATE_SERVICE_SUCCESS = "Serviço alterado com sucesso";
   public static final Object ADD_SCHEDULING_SUCCESS = "Agendamento cadastrado com sucesso";
   public static final Object ADD_FINISHED_SCHEDULING_SUCCESS = "Agendamento finalizado com sucesso";
   public static final Object DELETE_SCHEDULING_SUCCESS = "Agendamento cancelado com sucesso";
}