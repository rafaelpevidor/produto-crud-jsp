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
<title>Produto CRUD</title>
</head>
<body>
	
	<header>
		<c:import url="../../WEB-INF/template/header.jsp"></c:import>
	</header>
	
	<div id="container">
		<div id="content-container" class="row-app">
			<div id="menu-side-bar" class="small-2 large-2 columns">
				<c:import url="../../WEB-INF/template/side-bar.jsp"></c:import>
			</div>
			<div id="content" class="small-10 large-10 columns" style="padding-top: 20px;">
				É um fato conhecido de todos que um leitor se distrairá com o conteúdo de texto legível de uma página quando estiver examinando sua diagramação. A vantagem de usar Lorem Ipsum é que ele tem uma distribuição normal de letras, ao contrário de "Conteúdo aqui, conteúdo aqui", fazendo com que ele tenha uma aparência similar a de um texto legível. Muitos softwares de publicação e editores de páginas na internet agora usam Lorem Ipsum como texto-modelo padrão, e uma rápida busca por 'lorem ipsum' mostra vários websites ainda em sua fase de construção. Várias versões novas surgiram ao longo dos anos, eventualmente por acidente, e às vezes de propósito (injetando humor, e coisas do gênero).
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