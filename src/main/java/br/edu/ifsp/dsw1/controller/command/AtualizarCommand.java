package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;

import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AtualizarCommand implements Command{
	
	private FlightDataCollection instance;
	
	public AtualizarCommand(FlightDataCollection instancia) {
		instance = instancia;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long numeroDoVoo = Long.valueOf(request.getParameter("numero_voo"));
		instance.updateFlight(numeroDoVoo);
		
		return "pagina_voos.jsp";
	}
}
