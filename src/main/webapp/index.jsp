<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
  	 <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	
	<%
			
			String error = (String)session.getAttribute("error");
			if(error != null){
	%>
		
		<div class="container_mensagem" id="error-message">
			<h2>Error ao realizar o Login!</h2>
		</div>
	<%
			session.removeAttribute("error");
		}	
				
	%>
	
	<script>
		
		var errorMessage = document.getElementById("error-message");

		if (errorMessage) {
			
			setTimeout(function() {
				errorMessage.style.display = "none";
			}, 3000); 
		}
	</script>

	
		<jsp:include page="/includes/base.html"></jsp:include>
		<!-- Dentro do form, eu possuo um input hidden nomeado action e de valor "login" apenas para fazer com que 
		o FrontController consiga identificar otipo de comando que ele precisa executar  -->
	    <div class="container-form-login">
	        <form method="post" action="FrontControllerServlet">
	        <input type="hidden" name="action" value="login">
	            <div class="mb-3">
	                <label for="exampleInputEmail1" class="form-label">Login</label>
	                <input type="text" class="form-control" id="login" name="login" aria-describedby="loginHelp">
	            
	            </div>
	            <div class="mb-3">
	                <label for="exampleInputPassword1" class="form-label">Senha</label>
	                <input type="password" class="form-control" id="senha" name="senha">
	            </div>
	            <button type="submit" class="btn btn-primary color">Entrar</button>
	        </form>
	    </div>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
