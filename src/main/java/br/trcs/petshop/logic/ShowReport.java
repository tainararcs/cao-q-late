package br.trcs.petshop.logic;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.trcs.petshop.dao.ReportDAO;
import br.trcs.petshop.model.Report;
import br.trcs.petshop.utils.Consts;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Lógica responsável por gerar o relatório de serviços prestados.
 * <br>Pocessa requisições HTTP para obter a lista de serviços realizados em um período especificado e calcular o total faturado.
 */
public class ShowReport implements Logic {
	
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String startParam = request.getParameter("start");
        String endParam = request.getParameter("end");

        if (startParam != null && endParam != null) {
        	// Converte os parâmetros String para LocalDate.
            LocalDate start = LocalDate.parse(startParam);
            LocalDate end = LocalDate.parse(endParam);

            ReportDAO dao = new ReportDAO();
            
            // Recupera a lista de serviços realizados no período especificado.
            List<Report> reportList = dao.getServicesReport(start, end);
            
            if (!reportList.isEmpty()) {
            	// Calcula o total faturado no período.
                BigDecimal totalRevenue = dao.getTotalRevenue(start, end);

                // Armazena os resultados como atributos da requisição.
                request.setAttribute("reportList", reportList);
                request.setAttribute("totalRevenue", totalRevenue);
                request.setAttribute("start", startParam);
                request.setAttribute("end", endParam);
    	    } 
    	    else {
    	    	request.getSession().setAttribute(Consts.ERROR, Consts.DATE_FINALIZE_SCHEDULING_ERROR);
    	        return Consts.REDIRECT_SHOW_REPORT_JSP;
    	    }
        }
        
        // Retorna ao formulário.
        return Consts.SHOW_REPORT_JSP;
    }
}