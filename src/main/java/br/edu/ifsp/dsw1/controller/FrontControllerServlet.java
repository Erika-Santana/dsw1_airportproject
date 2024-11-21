package br.edu.ifsp.dsw1.controller;

import jakarta.servlet.RequestDispatcher;
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
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;
import br.edu.ifsp.dsw1.model.observer.HallOne;
import br.edu.ifsp.dsw1.model.observer.HallTwo;
import br.edu.ifsp.dsw1.model.observer.SalaDeDesembarque;
import br.edu.ifsp.dsw1.model.observer.SalaDeEmbarque;

@WebServlet("/FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightDataCollection instanceFDC;
	private HallOne instanceHallOne;
	private HallTwo instanciaHallTwo;
	private SalaDeDesembarque instanceDesembarque;
	private SalaDeEmbarque instanciaSDE;

	public FrontControllerServlet() {
		super();
		instanceFDC = new FlightDataCollection();
		instanceDesembarque = new SalaDeDesembarque();
		instanceHallOne = new HallOne();
		instanciaHallTwo = new HallTwo();
		instanciaSDE = new SalaDeEmbarque();
		instanceFDC.register(instanceDesembarque);
		instanceFDC.register(instanceHallOne);
		instanceFDC.register(instanciaSDE);
		instanceFDC.register(instanciaHallTwo);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String qualAcao = request.getParameter("action");
		Command comandoRecebido = getCommand(qualAcao);

		if (comandoRecebido == null) {
			redirectTotens(qualAcao, request, response);
		} else {

			String paginaADirecionar = comandoRecebido.execute(request, response);
			response.sendRedirect(paginaADirecionar);
		}

		// Lista dos voos registrados pra montar a página dos voos com eles em uma
		// tabela.

		HttpSession session = request.getSession(false);
		session.setAttribute("listaVoos", instanceFDC.getAllFligthts());

	}

	private Command getCommand(String action) {
		switch (action) {
		case "login":
			return new LoginCommand();
		case "cadastro_voos":
			return new CadastroCommand(instanceFDC);
		case "atualizar_voo":
			return new AtualizarCommand(instanceFDC);
		default:

			return null;
		}
	}

	private void redirectTotens(String paginaTotem, HttpServletRequest request, HttpServletResponse response) {
		
		
		
		try {
			HttpSession getSession = request.getSession(false);
			RequestDispatcher dispatcher = null;

			switch (paginaTotem) {
			
			case "logout":
			
				if (getSession != null) {
					getSession.invalidate()	;
					
				}
				dispatcher = request.getRequestDispatcher("index.jsp");
				break;

			case "administracao":
				// Aqui eu vou pegar a sessão e verificar se a pessoa está logada como ADM, se o
				// usuário dela existe na sessão

			
				if (getSession != null) {

					String login = (String) getSession.getAttribute("usuario");

					if (login != null && !login.isEmpty()) {

						dispatcher = request.getRequestDispatcher("pagina_voos.jsp");

					} else {
						dispatcher = request.getRequestDispatcher("index.jsp");
					}
				}

				break;

			case "home":
				dispatcher = request.getRequestDispatcher("index.jsp");
				break;

			case "desembarque":

				request.setAttribute("listaDesembarque", instanceDesembarque.retornaLista());

				dispatcher = request.getRequestDispatcher("sala_de_desembarque.jsp");
				break;
			case "embarque":

				request.setAttribute("listaEmbarcando", instanciaSDE.retornaLista());
				dispatcher = request.getRequestDispatcher("sala_de_embarque.jsp");
				break;

			case "decolando":

				request.setAttribute("listaHallOne", instanceHallOne.retornaLista());
				dispatcher = request.getRequestDispatcher("hall_one.jsp");
				break;
			case "decolado":

				request.setAttribute("listaHallTwo", instanciaHallTwo.retornaLista());
				dispatcher = request.getRequestDispatcher("hall_two.jsp");
				break;

			default:

				dispatcher = request.getRequestDispatcher("index.jsp");
				break;
			}

			if (dispatcher != null) {

				dispatcher.forward(request, response);
			}

		} catch (ServletException | IOException e) {

			e.printStackTrace();
		}
	}
	
	

}
