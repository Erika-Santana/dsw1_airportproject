<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Greetings</title>
<link rel="stylesheet" href="css/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="/includes/base.html"></jsp:include>
	<div class="greetings">
		<h2>Greetings!</h2>
		<p>Exerc�cio Avaliativo da disciplina de Desenvolvimento WEB do professor Ednilson Rossi</p>
		<p>O projeto � um sistema web para visualiza��o de voos em tempo real, desenvolvido utilizando Java Servlets e JSP (Java Server Pages).<br>
		 Ele oferece uma interface para exibir, gerenciar e monitorar informa��es relacionadas a voos, como status e hor�rios.<br>
		 Ao fornecer o login e a senha do Administrador, � poss�vel atualizar os estados, al�m de cadastrar novos voos na p�gina de <a href="FrontControllerServlet?action=administracao">Administra��o</a>.</p>
		 <p>Tamb�m � poss�vel visualizar os voos que est�o <a href="FrontControllerServlet?action=embarque">Embarcando</a>, <a href="FrontControllerServlet?action=desembarque">Desembarque</a>, <a href="FrontControllerServlet?action=decolando">Decolando</a> e que j�  
		 <a href="FrontControllerServlet?action=decolado">Decolaram</a> na barra de navega��o. </p>
	</div>
</body>
</html>
