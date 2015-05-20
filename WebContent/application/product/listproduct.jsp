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
<title>.:Produto CRUD:. Produto > Lista</title>
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
		
			<h3>Produto <small>Listagem</small></h3>
			<c:import url="../../WEB-INF/template/messages.jsp"></c:import>
			
			<div id="pesquisa-produtos">
				<form action="main">
					<input type="hidden" name="cmd" value="11">
					<div class="row prefix-radius">
					    <div class="small-12 large-12 columns">
					      <div class="row collapse">
					        <div class="small-10 large-10 columns">
					          <input id="keyword" name="keyword" type="text" placeholder="Informe um nome para pesquisar...">
					        </div>
					        <div class="small-2 large-2 columns">
					          <button type="submit" class="button postfix">Pesquisar</button>
					        </div>
					      </div>
					    </div>
					</div>
				</form>
			</div>
			
			<div id="tblprodutos">
				<c:if test="${products != null}">
					<table role="grid">
					  <thead>
					    <tr>
					      <th>#</th>
					      <th>Nome</th>
					      <th>Vlr. de venda</th>
					      <th>Quantidade</th>
					      <th>Ação</th>
					    </tr>
					  </thead>
					  <tbody>
					    <c:forEach var="product" items="${products}" varStatus="productid">
							    <tr>
							      <td>${product.id}</td>
							      <td>${product.name}</td>
							      <td>$ ${product.salePrice}</td>
							      <td>${product.amount}</td>
							      <td>
							      	<form id="frmapagar" action="main" method="post" style="float: left">
							      		<input id="cmd" name="cmd" type="hidden" value="10"/>
							      		<input id="id" name="id" type="hidden" value="${product.id}"/>
							      		<button type="submit" class="button tiny alert" onclick="confirmDelete()">Apagar</button>
							      	</form>
							      	<form id="frmeditar" action="main">
							      		<input id="cmd" name="cmd" type="hidden" value="8"/>
							      		<input id="id" name="id" type="hidden" value="${product.id}"/>
							      		<button type="submit" class="button tiny">Editar</button>
							      	</form>
						      	  </td>
							    </tr>
						  	</c:forEach>
					  </tbody>
					</table>
				</c:if>
				<c:if test="${products == null && msgregistros != null}">
					<div data-alert class="alert-box info radius">
					  ${msgregistros}
					</div>
				</c:if>
			</div>
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