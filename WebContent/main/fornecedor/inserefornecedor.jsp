<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE htm>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Pividori Systems">

<link rel="stylesheet" href="../resources/css/app.css">
<link rel="stylesheet" href="../resources/css/normalize.css">
<link rel="stylesheet" href="../resources/css/foundation.css">
<title>.:Produto CRUD:. Fornecedor > Inserir</title>
</head>
<body>
	
	<header>
		<c:import url="../../WEB-INF/template/header.jsp"></c:import>
	</header>
	
	<div id="content-container" class="row-app" role="content">
		<aside id="side-bar" class="small-2 large-2 columns">
			<c:import url="../../WEB-INF/template/side-bar.jsp"></c:import>
		</aside>
		<div id="content" class="small-10 large-10 columns" style="padding-top: 20px;">
			<h3>Fornecedor <small>Inserir</small></h3>
			<span class="error">Teste</span>
			<form action="main" method="post">
				<div class="row">
				    <div class="small-4 large-4 columns">
				      <label>Nome
				        <input name="nome" type="text" placeholder="Informe o nome" />
				      </label>
				    </div>
				    <div class="small-8 large-8 columns">
				      <label>Descrição
				        <input name="descricao" type="text" placeholder="Informe uma descrição" />
				      </label>
				    </div>
				</div>
				<div class="row">
					<div align="right" class="small-12 large-12 columns">
						<ul class="button-group radius">
						  <li><a href="#" class="small button">Cancelar</a></li>
						  <li><a href="#" class="small button secondary">Limpar</a></li>
						  <li><a href="#" class="small button success">Salvar</a></li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<footer class="zurb-footer-bottom">
		<c:import url="../../WEB-INF/template/footer.jsp"></c:import>
	</footer>
	
	<script src="../resources/js/vendor/jquery.js"></script>
  	<script src="../resources/js/foundation/foundation.js"></script>
	<script src="../resources/js/foundation/foundation.topbar.js"></script>
	<script>
    	$(document).foundation();
  	</script>
</body>
</html>