package br.trcs.petshop.logic;

import java.io.IOException;
import java.time.LocalDate;

import br.trcs.petshop.dao.SchedulingDAO;
import br.trcs.petshop.model.Scheduling;
import br.trcs.petshop.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe responsável por realizar o cancelamento (exclusão) de um agendamento.
 * <br>O cancelamento só é permitido se o agendamento estiver marcado para uma data superior a 24 horas a partir do momento atual.
 */
public class DeleteScheduling implements Logic {

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String idParam = request.getParameter("id");
    	String dateParam = request.getParameter("date"); // Recupera a data da URL para manter a tabela visível.
        
        if (idParam != null && !idParam.isEmpty()) {
            int idScheduling = Integer.parseInt(idParam);
            SchedulingDAO dao = new SchedulingDAO();
            
            // Busca o agendamento pelo ID informado.
            Scheduling scheduling = dao.findById(idScheduling);

            if (scheduling == null) {
                request.getSession().setAttribute(Consts.ERROR, Consts.SCHEDULING_NOT_FOUND_ERROR);
                return Consts.REDIRECT_LIST_NOT_FINISHED_SCHEDULING_JSP;
            }

            LocalDate schedulingDate = scheduling.getDate();
                
            // Verifica se a data do agendamento é pelo menos 24 horas após a data atual.
            if (schedulingDate.isAfter(LocalDate.now().plusDays(1))) {
                boolean success = dao.delete(idScheduling);
                
                if (success) {
                	request.getSession().setAttribute(Consts.MSG, Consts.DELETE_SCHEDULING_SUCCESS);
                    
                    // Retorna ao formulário mantendo a data para continuar mostrando a tabela.
                    return Consts.LIST_NOT_FINISHED_SCHEDULING_JSP + "?date=" + dateParam;
                }
            	request.getSession().setAttribute(Consts.ERROR, Consts.DELETE_SCHEDULING_ERROR);
                return Consts.REDIRECT_LIST_NOT_FINISHED_SCHEDULING_JSP;
            
            } else {
                request.setAttribute(Consts.ERROR, Consts.DATE_DELETE_SCHEDULING_ERROR);
                return Consts.REDIRECT_LIST_NOT_FINISHED_SCHEDULING_JSP;
            }
        }

        request.getSession().setAttribute(Consts.ERROR, Consts.DELETE_SCHEDULING_ERROR);
        return Consts.REDIRECT_LIST_NOT_FINISHED_SCHEDULING_JSP;
    }
} 