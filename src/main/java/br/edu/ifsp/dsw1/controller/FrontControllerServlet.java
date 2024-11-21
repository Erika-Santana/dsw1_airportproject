package br.edu.ifsp.dsw1.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import br.edu.ifsp.dsw1.controller.command.AtualizarCommand;
import br.edu.ifsp.dsw1.controller.command.CadastroCommand;
import br.edu.ifsp.dsw1.controller.command.Command;
import br.edu.ifsp.dsw1.controller.command.LoginCommand;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;

@WebServlet("/FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightDataCollection instance;
       
   
    public FrontControllerServlet() {
        super();
    	instance = new FlightDataCollection();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qualAcao = request.getParameter("action");
		Command comandoRecebido = getCommand(qualAcao);
			
		String paginaADirecionar = comandoRecebido.execute(request, response);
		response.sendRedirect(paginaADirecionar);
		
		//Lista dos voos registrados pra montar a página dos voos com eles em uma tabela.

		HttpSession session = request.getSession(false);
		session.setAttribute("listaVoos", instance.getAllFligthts());
		
		
	}
	
	private Command getCommand(String action) {
	    switch (action) {
	        case "login":
	            return new LoginCommand();
	        case "cadastro_voos":
	        	return new CadastroCommand(instance);
	        case "atualizar_voo":
	        	return new AtualizarCommand(instance);
	        default:
	            return null;
	    }
	}


}
