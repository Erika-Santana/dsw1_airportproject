package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
	
	/*Classe que atribui o commando encapsulado de Login.
	 * Aqui tem a lógica de autenticação do sistema. Caso for autenticado ele retorna a próxima página que o 
	 * Admionistrador possui acesso "genrenciamento_voos.jsp"
	 * Caso não, ele retorna para a página inicial "index.jsp"
	 * Aqui ele também guarda alguns valores na sessão além do Login, que é a parte de error, para caso seja 
	 * retornado para o Index, ele mostre a mensagem de "Erro ao fazer o login"
	 * --Também guarda a de sucesso mas vou tirar ;P*/
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String loginUsuario = request.getParameter("login");
		String passwordUsuario = request.getParameter("senha");
		//Criação da sessão
		HttpSession session = request.getSession();
		
		
		if (loginUsuario.equals("admin") && passwordUsuario.equals("admin")) {
			
			session.setAttribute("sucesso", "Login realizado com sucesso!");
			//Salvando o login do usuário na sessão
			session.setAttribute("usuario", loginUsuario);
			return "pagina_voos.jsp";
			
			
		}else {
			session.setAttribute("error", "Usuário ou senha inseridos incorretos.");
			return "index.jsp";
		}
		
	}

}
