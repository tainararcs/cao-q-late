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
        String cpf = request.getParameter("cpf");
        int idDog = Integer.parseInt(request.getParameter("dog"));
        String[] selectedServices = request.getParameterValues("service"); // Lista de checkboxes selecionados.

        LocalDate formattedDate = LocalDate.parse(date);
        
        SchedulingDAO dao = new SchedulingDAO();
        
        cpf = cpf.trim();
        Client client = new ClientDAO().findByCpf(cpf); 
        
        // Verifica se o cpf é válido.
		if (client == null) {
		    request.setAttribute(Consts.ERROR, Consts.CLIENT_NOT_FOUND_ERROR);
		    return Consts.ADD_SCHEDULING_JSP; 
		}

	     // Verifica se a data está disponível.
	    if (!dao.isDateAvailable(formattedDate)) {
	        request.setAttribute(Consts.ERROR, Consts.DATE_ERROR);
	        return Consts.ADD_SCHEDULING_JSP;  
	    }
     
        // Converte os IDs dos serviços selecionados.
        List<Integer> servicesList = new ArrayList<Integer>();
        if (selectedServices != null) 
            for (String service : selectedServices) 
                servicesList.add(Integer.parseInt(service));
        
        Scheduling newScheduling = new Scheduling();
        newScheduling.setDate(formattedDate);
        newScheduling.setCpfClient(cpf);
        newScheduling.setIdDog(idDog);
        newScheduling.setIdServicesList(servicesList);
        newScheduling.setStatus(SchedulingStatus.SCHEDULED);

        boolean created = dao.create(newScheduling);

        if (created) {
            request.getSession().setAttribute(Consts.MSG, Consts.ADD_SCHEDULING_SUCCESS);
            request.removeAttribute("dogsList");
        }
        else 
        	request.getSession().setAttribute(Consts.ERROR, Consts.ADD_SCHEDULING_ERROR);
        
        return Consts.REDIRECT_ADD_SCHEDULING_JSP;  
    }
}