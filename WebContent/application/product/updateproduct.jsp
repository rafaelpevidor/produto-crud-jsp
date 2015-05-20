<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE htm>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Pividori Systems">

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
				<input type="hidden" name="cmd" value="9">
				<div class="row">
					<div class="small-2 large-2 columns">
				      <label>#
				        <input name="id" type="text" value="${product.id}"/>
				      </label>
				    </div>
				    <div class="small-4 large-4 columns">
				      <label>Fornecedor
				        <select id="cmbfornecedor" name="vendorid">
				        	<option value="${product.vendor.id}">${product.vendor.name}</option>
				        	<c:forEach items="${vendors}" var="vendor" varStatus="vendorid">
				        		<option value="${vendor.id}">${vendor.name}</option>
				        	</c:forEach>
				        </select>
				      </label>
				    </div>
				    <div class="small-6 large-6 columns">
				      <label>Nome
				        <input name="name" type="text" placeholder="Informe o nome" value="${product.name}"/>
				      </label>
				    </div>
				</div>
				<div class="row">
				    <div class="small-3 large-3 columns">
				      <label>Lote
				        <input name="allotment" type="text" placeholder="Informe o lote" value="${product.allotment}"/>
				      </label>
				    </div>
				    <div class="small-3 large-3 columns">
				      <label>Quantidade
				        <input name="amount" type="text" placeholder="Informe a quantidade" value="${product.amount}"/>
				      </label>
				    </div>
				     <div class="small-3 large-3 columns">
				      <label>Vlr. de Compra
				        <input name="purchaseprice" type="text" placeholder="Informe o valor de compra" value="${product.purchasePrice}"/>
				      </label>
				    </div>
				    <div class="small-3 large-3 columns">
				      <label>Vlr. de Venda
				        <input name="saleprice" type="text" placeholder="Informe o valor para venda" value="${product.salePrice}"/>
				      </label>
				    </div>
				</div>
				<div class="row">
					<div class="small-12 large-12 columns">
					  <label>Descrição
				        <input name="description" type="text" placeholder="Informe a descrição" value="${product.description}"/>
				      </label>
					</div>
				</div>
				<div class="row">
					<div align="right" class="small-12 large-12 columns">
						<ul class="button-group radius">
						  <li><a href="listproduct.jsp" class="small button">Cancelar</a></li>
						  <li><button type="submit" form="frmapagar" class="small button secondary" onclick="confirmDelete()">Apagar</button></li>
						  <li><button type="submit" class="small button success">Atualizar</button></li>
						</ul>
					</div>
				</div>
			</form>
			<form id="frmapagar" action="main">
				<input id="cmd" name="cmd" type="hidden" value="10"/>
	      		<input id="id" name="id" type="hidden" value="${product.id}"/>
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