package br.trcs.petshop.logic;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.trcs.petshop.dao.ClientDAO;
import br.trcs.petshop.dao.SchedulingDAO;
import br.trcs.petshop.enums.SchedulingStatus;
import br.trcs.petshop.model.Client;
import br.trcs.petshop.model.Scheduling;
import br.trcs.petshop.utils.Consts;

/**
 * Classe responsável por cadastrar um novo agendamento de serviço no sistema.
 * <br>Agenda um novo atendimento para um cliente e seu respectivo cachorro, associando uma data e os serviços desejados.
 * <br>O processo inclui validação da disponibilidade da data.
 */
public class AddScheduling implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String date = request.getParameter("date");
        String cpfClient = request.getParameter("client");
        int idDog = Integer.parseInt(request.getParameter("dog"));
        String[] selectedServices = request.getParameterValues("service"); // Lista de checkboxes selecionados.

        LocalDate formattedDate = LocalDate.parse(date);
        
        SchedulingDAO dao = new SchedulingDAO();
        
        // Verifica se o cpf é válido.
        cpfClient = cpfClient.trim();
        Client client = new ClientDAO().findByCpf(cpfClient); 
		if (client == null) {
		    request.getSession().setAttribute(Consts.ERROR, "Cliente não encontrado");
		    return Consts.REDIRECT_ADD_SCHEDULING; 
		}

	     // Verifica se a data está disponível.
	    if (!dao.isDateAvailable(formattedDate)) {
	        request.getSession().setAttribute(Consts.ERROR, "Data não disponível para novos agendamentos");
	        return Consts.REDIRECT_ADD_SCHEDULING;  
	    }
     
        // Converte os IDs dos serviços selecionados.
        List<Integer> servicesList = new ArrayList<Integer>();
        if (selectedServices != null) 
            for (String service : selectedServices) 
                servicesList.add(Integer.parseInt(service));

        
        Scheduling newScheduling = new Scheduling();
        newScheduling.setDate(formattedDate);
        newScheduling.setCpfClient(cpfClient);
        newScheduling.setIdDog(idDog);
        newScheduling.setIdServicesList(servicesList);
        newScheduling.setStatus(SchedulingStatus.SCHEDULED);

        boolean created = dao.create(newScheduling);

        if (created) {
            request.getSession().setAttribute(Consts.MSG, "Agendamento cadastrado com sucesso");
            return Consts.REDIRECT_HOME;
        } 

        request.getSession().setAttribute(Consts.ERROR, "Erro ao cadastrar o agendamento");
        return Consts.REDIRECT_ADD_SCHEDULING;  
    }
}