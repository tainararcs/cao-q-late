package br.trcs.petshop.logic;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import br.trcs.petshop.dao.SchedulingDAO;
import br.trcs.petshop.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe responsável por listar agendamentos que ainda não foram finalizados, a partir de uma data informada.
 */
public class ListNotFinishedSchedulings implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateParam = request.getParameter("date");
		
	   List<Map<String, Object>> schedulingList = null;
	   
	   if (dateParam != null && !dateParam.isEmpty()) {
	       LocalDate startDate = LocalDate.parse(dateParam);
	       SchedulingDAO dao = new SchedulingDAO();
	       schedulingList = dao.listNotFinishedFromDate(startDate);
	       
	       if (!schedulingList.isEmpty()) 
				request.setAttribute("schedulingList", schedulingList);
			else 
				request.setAttribute(Consts.ERROR, Consts.SCHEDULING_NOT_FOUND_ERROR);
	   }
	   
	   return Consts.LIST_NOT_FINISHED_SCHEDULING_JSP;
	}
}