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
		<p>Exercício Avaliativo da disciplina de Desenvolvimento WEB do professor Ednilson Rossi</p>
		<p>O projeto é um sistema web para visualização de voos em tempo real, desenvolvido utilizando Java Servlets e JSP (Java Server Pages).<br>
		 Ele oferece uma interface para exibir, gerenciar e monitorar informações relacionadas a voos, como status e horários.<br>
		 Ao fornecer o login e a senha do Administrador, é possível atualizar os estados, além de cadastrar novos voos na página de <a href="FrontControllerServlet?action=administracao">Administração</a>.</p>
		 <p>Também é possível visualizar os voos que estão <a href="FrontControllerServlet?action=embarque">Embarcando</a>, <a href="FrontControllerServlet?action=desembarque">Desembarque</a>, <a href="FrontControllerServlet?action=decolando">Decolando</a> e que já  
		 <a href="FrontControllerServlet?action=decolado">Decolaram</a> na barra de navegação. </p>
	</div>
</body>
</html>
