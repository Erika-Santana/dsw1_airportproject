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

/*	Servlet como FrontController, sendo o principal para direcionar, processar e salvar
 * todas as requisições feitas pelas páginas disponíveis na aplicação. 
 * A classe possui instancias do FlightDataCollection para acessar os voos registrados e também as instâncias
 * das listas dos observers, na qual cada uma possui o seu tipo salvo. * 
 * */

@WebServlet("/FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightDataCollection instanceFDC;
	private HallOne instanceHallOne;
	private HallTwo instanciaHallTwo;
	private SalaDeDesembarque instanceDesembarque;
	private SalaDeEmbarque instanciaSDE;

	// Construtor para ter acesso as funcionalidades do banco de voos e as listas de
	// cada observer. Aqui ele também realiza o registramento dos observers no
	// banco,para que assim sejam notificados a cada nova adição.
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

	/*
	 * O método Post fica responsável por receber as requisições enviadas pelas
	 * páginas para que sejam processadas e após serem retornadas uma reposta para o
	 * usuário. A partir do name em comum "action", cada página possui um valor
	 * diferente, na qual será utilizada no método getCommand e no redirectTotens
	 * para seus respectivos processamentos sejam feitos. Aqui também é realizado o
	 * salvamento da lista do FlightDataCollection em uma session, contendo todos os
	 * voos registrados no sistema.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String qualAcao = request.getParameter("action");
		Command comandoRecebido = getCommand(qualAcao);

		if (comandoRecebido == null) {
			redirectTotens(qualAcao, request, response);
		} else {

			String paginaADirecionar = comandoRecebido.execute(request, response);
			try {
				if (paginaADirecionar != null) {
					response.sendRedirect(paginaADirecionar);
				} else {
					throw new NullPointerException();
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

		}

		HttpSession session = request.getSession(false);
		session.setAttribute("listaVoos", instanceFDC.getAllFligthts());

	}

	/*
	 * Aqui foi implementado o padrão Command, que tem como objetivo separar as
	 * ações de uma classe em objetos da aplicação. Nessa função, a partir do que
	 * foi recuperado do value do parâmetro em comum "action", ele vai criar um novo
	 * objeto da ação correspondente, de acordo com o switch. São três commands
	 * feitos, que ao serem eecutados retornam uma página JSP, que no doPost, irá
	 * direcionar a request para ela.
	 */

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
	
	/*No método redirectTotens tem mais o objetivo de direcionamento dos Totens que estão disponibilizados na navBar*/

	private void redirectTotens(String paginaTotem, HttpServletRequest request, HttpServletResponse response) {

		try {
			/* Instanciação da sessão para verificação da existencia dela */
			HttpSession getSession = request.getSession(false);
			RequestDispatcher dispatcher = null;

			switch (paginaTotem) {

			/*Basicamente é a funcionalidade de Log Out da aplicação
			 * Quando entra nesse case, ele verifica se a sessão existe, caso sim, ele acaba com a sessão com o invalidade. Depois direciona para o index.jsp
			 * para que seja possível realizar o login novamente*/
			case "logout":
				if (getSession != null) {
					getSession.invalidate();

				}
				dispatcher = request.getRequestDispatcher("index.jsp");
				break;

			case "administracao":
				
				/* No case de administração é verificado se o usuário está logado como ADM para direcionar-lo para a página_voos.jsp, que basicamente é onde o ADM consegue cadastrar
				 * novos voos, e também atualizar os States dos voos.*/

				if (getSession != null) {

					String login = (String) getSession.getAttribute("usuario");

					if (login != null && !login.isEmpty()) {

						dispatcher = request.getRequestDispatcher("pagina_voos.jsp");

					} else {
						dispatcher = request.getRequestDispatcher("login.jsp");
					}
				}

				break;

				/*Página home simplesmente direciona para o index.jsp que é a página de login*/
			case "home":
				dispatcher = request.getRequestDispatcher("index.jsp");
				break;

				/*A parte dos totens tem como objetivo ser acessado por qualquer pessoa, já que seu objetivo é apenas mostrar informações sobre os voos e não modifica-los
				 * Aqui também é feito a parte de setAttribute das listas de cada observer, já que elas serão acessadas pelas suas páginas correspondentes para assim
				 * ter a construção da página com as informações de cada.
				 * 
				 * O que cada um faz:
				 * Na aba de desembarque ele vai mostrar todos os voos que possuem como Boarding*/
			case "desembarque":
				request.setAttribute("listaDesembarque", instanceDesembarque.retornaLista());
				dispatcher = request.getRequestDispatcher("sala_de_desembarque.jsp");
				break;
				
				/*	 * Na aba de embarque ele vai mostrar todos os voos que possuem como Arriving*/
			case "embarque":
				request.setAttribute("listaEmbarcando", instanciaSDE.retornaLista());
				dispatcher = request.getRequestDispatcher("sala_de_embarque.jsp");
				break;
				
				/*	 * Na aba de decolando ele vai mostrar todos os voos que possuem como TakingOff*/
			case "decolando":
				request.setAttribute("listaHallOne", instanceHallOne.retornaLista());
				dispatcher = request.getRequestDispatcher("hall_one.jsp");
				break;
				
				/*	 * Na aba de decolado ele vai mostrar todos os voos que possuem como TookOff*/
			case "decolado":
				request.setAttribute("listaHallTwo", instanciaHallTwo.retornaLista());
				dispatcher = request.getRequestDispatcher("hall_two.jsp");
				break;
				
			case "cadastroPagina":
				dispatcher = request.getRequestDispatcher("cadastro_voos.jsp");
				break;

			default:
				dispatcher = request.getRequestDispatcher("login.jsp");
				break;
			}
			
			// Após salvar no dispatcher o request, para não perder os dados salvos, ele lança esses dados juntamente com a request, contendo os dados das listas.
			if (dispatcher != null) {

				dispatcher.forward(request, response);
			}

		} catch (ServletException | IOException e) {

			e.printStackTrace();
		}
	}

}
