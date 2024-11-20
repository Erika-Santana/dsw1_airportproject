<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="css/style.css">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<title>Gerenciamento de Voos</title>
</head>
<body>
		<jsp:include page="/includes/base.html"></jsp:include>
	    <div class="container-voos">
	        <form method="post" action="FrontControllerServlet">
	        <input type="hidden" name="action" value="cadastro_voos">
	            <div class="mb-3">
	                <label for="exampleInputEmail1" class="form-label">Numero do Voo</label>
	                <input type="text" class="form-control" id="numero-voo" name="numero_voo" aria-describedby="loginHelp">
	            
	            </div>
	            <div class="mb-3">
	                <label for="exampleInputPassword1" class="form-label">Companhia Aérea</label>
	                <input type="password" class="form-control" id="companhia" name="companhia">
	            </div>
	              <div class="mb-3">
	                <label for="exampleInputPassword1" class="form-label">Horário de Chegada</label>
	                <input type="password" class="form-control" id="horario" name="horario_chegada">
	            </div>
	            <button type="submit" class="btn btn-primary color">Enviar</button>
	        </form>
	    </div>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>

</body>
</html>