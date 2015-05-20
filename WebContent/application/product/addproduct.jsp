<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE htm>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Pividori Systems:Rafael Pevidor">

<link rel="stylesheet" href="../../resources/css/app.css">
<link rel="stylesheet" href="../../resources/css/normalize.css">
<link rel="stylesheet" href="../../resources/css/foundation.css">
<title>.:Produto CRUD:. Produto > Inserir</title>
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
			
			<h3>Produto <small>Inserir</small></h3>
			<c:import url="../../WEB-INF/template/messages.jsp"></c:import>
			
			<form action="main" method="post">
				<input type="hidden" name="cmd" value="7">
				<div class="row">
				    <div class="small-4 large-4 columns">
				      <label>Fornecedor
				        <select id="cmbfornecedor" name="vendorid">
				        	<c:forEach items="${vendors}" var="vendor" varStatus="vendorid">
				        		<option value="${vendor.id}">${vendor.name}</option>
				        	</c:forEach>
				        </select>
				      </label>
				    </div>
				    <div class="small-8 large-8 columns">
				      <label>Nome
				        <input name="name" type="text" placeholder="Informe o nome" />
				      </label>
				    </div>
				</div>
				<div class="row">
				    <div class="small-3 large-3 columns">
				      <label>Lote
				        <input name="allotment" type="text" placeholder="Informe o lote" />
				      </label>
				    </div>
				    <div class="small-3 large-3 columns">
				      <label>Quantidade
				        <input name="amount" type="number" placeholder="Informe a quantidade" />
				      </label>
				    </div>
				     <div class="small-3 large-3 columns">
				      <label>Vlr. de Compra $
				        <input name="purchaseprice" type="text" pattern="[0-9]*\.?[0-9]*" placeholder="Informe o valor de compra [00.00]" />
				      </label>
				    </div>
				    <div class="small-3 large-3 columns">
				      <label>Vlr. de Venda $
				        <input name="saleprice" type="text" pattern="[0-9]*\.?[0-9]*" placeholder="Informe o valor para venda [00.00]" />
				      </label>
				    </div>
				</div>
				<div class="row">
					<div class="small-12 large-12 columns">
					  <label>Descrição
				        <input name="description" type="text" placeholder="Informe a descrição" />
				      </label>
					</div>
				</div>
				<div class="row">
					<div align="right" class="small-12 large-12 columns">
						<ul class="button-group radius">
						  <li><a href="listproduct.jsp" class="small button">Cancelar</a></li>
						  <li><button type="reset" class="small button secondary">Limpar</button></li>
						  <li><button type="submit" class="small button success">Salvar</button></li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<footer class="zurb-footer-bottom">
		<c:import url="../../WEB-INF/template/footer.jsp"></c:import>
	</footer>
	
	<script src="../../resources/js/vendor/jquery.js"></script>
  	<script src="../../resources/js/foundation/foundation.js"></script>
	<script src="../../resources/js/foundation/foundation.topbar.js"></script>
	<script>
    	$(document).foundation();
  	</script>
</body>
</html>