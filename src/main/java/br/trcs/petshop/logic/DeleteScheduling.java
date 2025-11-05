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
        
    	// Obtém o parâmetro ID enviado pelo formulário.
    	String idParam = request.getParameter("id");
    	// Recupera a data da URL para manter a tabela visível.
    	String dateParam = request.getParameter("date"); 
        
    	// Verifica se o parâmetro foi informado e não está vazio.
        if (idParam != null && !idParam.isEmpty()) {
            int idScheduling = Integer.parseInt(idParam);
            SchedulingDAO dao = new SchedulingDAO();
            
            // Busca o agendamento pelo ID informado.
            Scheduling scheduling = dao.findById(idScheduling);

            if (scheduling == null) {
                request.getSession().setAttribute(Consts.ERROR, "Agendamento não encontrado");
                return Consts.REDIRECT_SHOW_NOT_FINISHED_SCHEDULING;
            }

            LocalDate schedulingDate = scheduling.getDate();
                
            // Verifica se a data do agendamento é pelo menos 24 horas após a data atual.
            if (schedulingDate.isAfter(LocalDate.now().plusDays(1))) {
                boolean success = dao.delete(idScheduling);
                
                if (success) {
                	request.getSession().setAttribute(Consts.MSG, "Agendamento cancelado com sucesso");
                    
                    // Retorna ao formulário mantendo a data para continuar mostrando a tabela.
                    return Consts.SHOW_NOT_FINISHED_SCHEDULING + "?date=" + dateParam;
                }
            	request.getSession().setAttribute(Consts.ERROR, "Falha ao cancelar agendamento");
                return Consts.REDIRECT_SHOW_NOT_FINISHED_SCHEDULING;
            
            } else {
                request.setAttribute(Consts.ERROR, "Não é possível cancelar agendamentos com menos de 24h de antecedência");
                return Consts.REDIRECT_SHOW_NOT_FINISHED_SCHEDULING;
            }
        }

        request.getSession().setAttribute(Consts.ERROR, "Nenhum ID de agendamento informado");
        return Consts.REDIRECT_SHOW_NOT_FINISHED_SCHEDULING;
    }
}