<%@page import="java.util.ArrayList"%>
<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<jsp:include page="/includes/base.html"></jsp:include>
	<div class="tabela_voos">
		<h3 class="info_voos">Informações dos voos</h3>
		<table>
			<thead>
				<tr>
					<th>Número do Voo</th>
					<th>Companhia</th>
					<th>Data de Chegada</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<FlightData> flights = (ArrayList<FlightData>) request.getAttribute("listaDesembarque");
				for (FlightData flight : flights) {
				%>
				<tr>
					<td><%=flight.getFlightNumber()%></td>
					<td><%=flight.getCompany()%></td>
					<td><%=flight.getTime()%></td>
					<td><%=flight.getState().getClass().getSimpleName()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

</body>
</html>