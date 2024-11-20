package br.edu.ifsp.dsw1.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.edu.ifsp.dsw1.controller.command.Command;
import br.edu.ifsp.dsw1.controller.command.LoginCommand;

@WebServlet("/FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FrontControllerServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qualAcao = request.getParameter("action");
		Command comandoRecebido = getCommand(qualAcao);
		
		String paginaADirecionar = comandoRecebido.execute(request, response);
		response.sendRedirect(paginaADirecionar);
		
		
		
		
	}
	
	private Command getCommand(String action) {
	    switch (action) {
	        case "login":
	            return new LoginCommand();
	        
	        default:
	            return null;
	    }
	}


}
