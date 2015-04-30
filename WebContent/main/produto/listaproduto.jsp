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
			<table role="grid">
			  <thead>
			    <tr>
			      <th>#</th>
			      <th>Nome</th>
			      <th>Descrição</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <td>15</td>
			      <td>Content Goes Here</td>
			      <td>This is longer content Donec id elit non mi porta gravida at eget metus.</td>
			    </tr>
			  </tbody>
			</table>
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