package br.trcs.petshop.logic;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import br.trcs.petshop.dao.FinishedSchedulingDAO;
import br.trcs.petshop.dao.SchedulingDAO;
import br.trcs.petshop.enums.SchedulingStatus;
import br.trcs.petshop.model.FinishedScheduling;
import br.trcs.petshop.model.Scheduling;
import br.trcs.petshop.model.Service;
import br.trcs.petshop.utils.Consts;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Lógica responsável por finalizar um agendamento no sistema.
 * Processa requisições HTTP para cadastrar um agendamento como finalizado, calcular o valor total, aplicar descontos se necessário e atualizar o status do agendamento.
 */
public class AddFinishedScheduling implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer idScheduling = Integer.valueOf(request.getParameter("idscheduling"));
		String executionDate = request.getParameter("date");
	    
		BigDecimal grossPrice = BigDecimal.ZERO;  // Valor bruto do agendamento.
        Boolean discount = false;                 // Indica se houve desconto.
        BigDecimal finalPrice;                    // Valor final após desconto, se houver.
		
		// Formata a data.
		LocalDate formattedDate = LocalDate.parse(executionDate);
		
	    SchedulingDAO schedulingDAO = new SchedulingDAO();
		FinishedSchedulingDAO finishedDAO = new FinishedSchedulingDAO();
		
		// Cria objeto Scheduling com o ID recebido para buscar serviços.
		Scheduling scheduling = new Scheduling();
		scheduling.setId(idScheduling);
		
        // Recupera a lista de serviços associados ao agendamento.
		List<Service> servicesList = schedulingDAO.listServices(scheduling);
		
		if (!servicesList.isEmpty()) {
			// Calcula o valor bruto do agendamento.
			for (Service service : servicesList) 
			    grossPrice = grossPrice.add(service.getPrice());
			
			// Aplica desconto se houver 3 ou mais serviços.
			discount = servicesList.size() >= 3;
			finalPrice = discount ? grossPrice.subtract(grossPrice.multiply(BigDecimal.valueOf(0.1))) : grossPrice;
			
			if (discount) 
				System.out.println("\nDesconto: " + discount + ": " + grossPrice + " -> " + grossPrice.subtract(grossPrice.multiply(BigDecimal.valueOf(0.1))));
			
			// Verifica se o agendamento já foi finalizado.
			if (finishedDAO.searchSchedulingId(idScheduling)) {
			    request.setAttribute(Consts.ERROR, Consts.SCHEDULING_ALREADY_FINALIZED_ERROR);
			    return Consts.ADD_FINISHED_SCHEDULING_JSP; 
			}
			
			FinishedScheduling newFinished = new FinishedScheduling();
			newFinished.setIdScheduling(idScheduling);
			newFinished.setExecutionDate(formattedDate);
			newFinished.setGrossPrice(grossPrice.setScale(2, RoundingMode.HALF_UP));
			newFinished.setDiscount(discount);
			newFinished.setFinalPrice(finalPrice.setScale(2, RoundingMode.HALF_UP));
			
			boolean created = finishedDAO.create(newFinished);
			
			if (created) {
				// Atualiza o status do agendamento para "Finalizado".
			    schedulingDAO.updateStatus(idScheduling, SchedulingStatus.FINISHED);
		        request.setAttribute(Consts.MSG, Consts.ADD_FINISHED_SCHEDULING_SUCCESS);
		    } 
			else 
				request.setAttribute(Consts.ERROR, Consts.ADD_FINISHED_SCHEDULING_ERROR);
		}
		else
			request.setAttribute(Consts.ERROR, Consts.SCHEDULING_NOT_FOUND_ERROR);
		
        return Consts.ADD_FINISHED_SCHEDULING_JSP;
    }
}