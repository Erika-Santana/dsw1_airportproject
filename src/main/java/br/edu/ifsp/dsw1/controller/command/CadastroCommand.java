package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import br.edu.ifsp.dsw1.model.flightstates.Boarding;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastroCommand implements Command {
	
	private FlightDataCollection instance;

	public CadastroCommand(FlightDataCollection instance) {
		this.instance = instance;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Long voo = Long.valueOf((request.getParameter("numero_voo")));
		String horario = request.getParameter("horario_chegada");
		String companhia = request.getParameter("companhia");
		
		FlightData novoRegistro = new FlightData(voo, companhia, horario);
		Arriving status = Arriving.getIntance();
		novoRegistro.setState(status);
		
		instance.insertFlight(novoRegistro);
		
		return "pagina_voos.jsp";
	}

}
