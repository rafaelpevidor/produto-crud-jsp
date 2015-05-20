<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Pividori Systems">

<link rel="stylesheet" href="../resources/css/app.css">
<link rel="stylesheet" href="../resources/css/normalize.css">
<link rel="stylesheet" href="../resources/css/foundation.css">
<title>.:Produto CRUD:. Sobre</title>
</head>
<body>

	<header>
		<c:import url="../WEB-INF/template/header.jsp"></c:import>
	</header>
	
	<div id="content-container" class="row-app" role="content">
		<aside id="side-bar" class="small-2 large-2 columns">
			<c:import url="../WEB-INF/template/side-bar.jsp"></c:import>
		</aside>
		<div id="content" class="small-10 large-10 columns" style="padding-top: 20px;">
			<div class="panel">
				<h2>Sobre o projeto</h2>
				<p>Esta é uma aplicação Java Web desenvolvida na arquitetura MVC e é composta de dois CRUDs (Fornecedor e Produto).</p>
				<p>
					Para o desenvolvimento deste projeto foram utilizadas algumas tecnologias e padrões de projeto que podem ser encontradas
					na apostila <a href="http://www.caelum.com.br/apostila-java-web/">FJ-21</a> da <a href="https://www.caelum.com.br/">Caelum</a>, ou na apostila <a href="http://online.k19.com.br/">K19-K11</a> da <a href="http://www.k19.com.br/">K19</a>
			 	</p>
			 	<p>
			 		Estas apostilas vão te instruir sobre os conhecimentos necessários para criação de uma aplicação Java Web, como esta, 
			 		e por isso sua leitura é recomendada para que você aprenda todos os conceitos envolvidos na criação de uma aplicação Java Web.
		 		</p>
			</div>
			<ul class="accordion" data-accordion>
			  <li class="accordion-navigation">
			    <a href="#panel1a">Java Web</a>
			    <div id="panel1a" class="content active">
			      <p>
			      	Java é uma linguagem de programação orientada a objeto desenvolvida na década de 90 por uma equipe de programadores chefiada por James Gosling, na empresa Sun Microsystems. 
				  	Diferentemente das linguagens convencionais, que são compiladas para código nativo, a linguagem Java é compilada para um bytecode que é executado por uma máquina virtual. (Wikipédia)
				  </p>
				  <p>
				  	Java Platform, Enterprise Edition (ou Java EE, ou em português Plataforma Java Edição Empresarial) é uma plataforma de programação para servidores na linguagem de programação Java.
					A plataforma fornece uma API e um ambiente de tempo de execução para o desenvolvimento e execução de softwares corporativos, incluindo serviços de rede e <strong>WEB</strong>, 
					e outras aplicações de rede de larga escala, multicamadas, escaláveis, confiáveis e seguras. (Wikipédia)
				  </p>
				  <p>
				  	Logo, Java Web é parte integrante da especificação Java EE. A partir da versão 6 (na data desta publicação a última versão é JEE 7) um dos objetivos da especificação foi trazer facilidade e simplicidade
				  	para os desenvolvedores quanto ao uso de suas tecnologias, introduzindo o conceito de <em>profiles</em>.
				  </p>
				  <p>
				  	Hoje o que é conhecido como Java Web é um <em>profile</em> chamado <strong><em>Web Profile</em></strong>, que inclui as principais tecnologias usada pelos desenvolvedores web, como <strong>Servlets, JSP, JSTL</strong> e outras.
				  </p>
				  <p>
				  	Para que uma aplicação Java Web seja executada ela deve ser executada sob um <a href="http://pt.wikipedia.org/wiki/Container_%28tipo_de_dado_abstrato%29">Container Web</a> ou <a href="http://pt.wikipedia.org/wiki/Servidor_de_aplica%C3%A7%C3%A3o">Application Server</a>. 
				  	No nosso caso tanto um quanto o outro podem ser usados, mas utilizamos o container web <a href="http://tomcat.apache.org/">Tomcat</a>. Um exemplo de <a href="http://pt.wikipedia.org/wiki/JBoss_Application_Server">servidor de aplicação java</a> é o <a href="http://wildfly.org/about/"></a>JBoss/Wildfly.
				  </p>
				  <p>
				  	Neste projeto, para comunicação com o banco de dados, utilizamos a tecnologia <a href="http://pt.wikipedia.org/wiki/JDBC">JDBC</a>. Atualmente esta tecnologia caiu em desuso,
			 		sendo substituida pela <a href="http://www.caelum.com.br/apostila-java-web/uma-introducao-pratica-ao-jpa-com-hibernate/">JPA</a>.
			 		Mas em alguns <a href="http://uaihebert.com/alternativas-ao-jpa-hibernate-o-que-usar-quando-o-jpa-nao-atende-meu-projeto/">casos específicos</a> a adoção de JDBC pode ser uma escolha mais acertada em comparação a JPA.
				  </p>
				  <div class="panel callout radius">
					  <h3>Links</h3>
					  <ul>
					  	<li><a href="http://pt.wikipedia.org/wiki/Servlet">Servlets</a>.</li>
					  	<li><a href="http://pt.wikipedia.org/wiki/JavaServer_Pages">JSP</a>.</li>
					  	<li><a href="http://pt.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>.</li>
					  	<li><a href="http://www.caelum.com.br/apostila-java-web/apendice-java-ee-6/#18-1-java-ee-6-e-as-novidades">JEE6 e as novidades</a>.</li>
					  	<li><a href="https://developer.jboss.org/wiki/JavaEE6UtilizandoWebProfileOuFullProfileNoJBossAS7">Utilizando Web Profile</a>.</li>
					  	<li><a href="http://www.devmedia.com.br/introduzindo-o-servidor-de-aplicacao-apache-tomcat/27939">Utilizando o Tomcat</a>.</li>
					  </ul>
				  </div>
			    </div>
			  </li>
			  <li class="accordion-navigation">
			    <a href="#panel2a">Tecnologias Web</a>
			    <div id="panel2a" class="content">
			      <p>Como o próprio nome sugere, tecnologias web são tecnologias usadas para construção de sites e/ou aplicações web. São elas: HTML, CSS e JavaScript</p>
			      <p>A Mozilla Foundation, através da MDN - Mozilla Developers Network, possui uma excelente <a href="https://developer.mozilla.org/pt-BR/docs/Web">documentação</a> a respeito,
			      	 no intuito de instruir os desenvolvedores no uso destas tencnologias.
		      	  </p>
		      	  <p>
		      	  	Atualmente existem alguns frameworks que auxiliam/aceleram o uso destas tecnologias para construção de sites/aplicações.
		      	  	Neste projeto foi utilizado o HTML5 (versão mais recente na data de criação deste projeto) e o <a href="http://foundation.zurb.com/learn/about.html">Foundation Framework</a>.
		      	  	Podemos citar também o <a href="https://jquery.com/">jQuery</a> (<a href="http://pt.wikipedia.org/wiki/JQuery">JavaScript e Ajax</a>) e <a href="https://jqueryui.com/">jQueryUI</a> (JavaScript, Ajax e CSS).
		      	  </p>
		      	  <div class="panel callout radius">
				      <h3>Links</h3>
					  <ul>
					  	<li><a href="http://tableless.com.br/o-que-html-basico/">HTML</a>.</li>
					  	<li><a href="http://www.matera.com/br/2012/07/25/o-que-e-css-e-qual-sua-importancia/">CSS</a>.</li>
					  	<li><a href="http://pt.wikipedia.org/wiki/JavaScript">JavaScript</a>.</li>
					  	<li><a href="http://pt.slideshare.net/alcidesqueiroz/html5-css3-do-zero-e-do-jeito-certo">HTML5 e CSS3</a>.</li>
					  </ul>
		      	  </div>
			    </div>
			  </li>
			  <li class="accordion-navigation">
			    <a href="#panel3a">Padrões de Projeto e Boas Práticas</a>
			    <div id="panel3a" class="content">
			      <p>No desenvolvimento desta aplicação foram utilizados alguns <a href="http://pt.wikipedia.org/wiki/Padr%C3%A3o_de_projeto_de_software">padrões de projeto</a>, são eles: MVC, Command, DAO e Singleton.</p>
			      <p>
			      	Estes padrões, também chamados de <em>Design Patterns</em>, são soluções reutilizáveis para problemas recorrentes. Por exemplo, praticamente toda aplicação precisa se comunicar com o banco de dados e para uma melhor comunicação foi criado o padrão DAO.
			      	O uso destes padrões garante agilidade e qualidade no desenvolvimento de software, portanto, quanto mais padrões o desenvolvedor conhecer, melhor.
			      </p>
			      <div class="panel callout radius">
				      <h3>Links</h3>
					  <ul>
					  	<li><a href="http://blog.thiagobelem.net/o-que-e-o-mvc/">MVC</a>.</li>
					  	<li><a href="http://www.matera.com/br/2012/07/25/o-que-e-css-e-qual-sua-importancia/">DAO</a>.</li>
					  	<li><a href="http://pt.wikipedia.org/wiki/Singleton">Singleton</a>.</li>
					  	<li><a href="http://pt.wikipedia.org/wiki/Command">Command</a>.</li>
					  	<li><a href="http://www.k19.com.br/downloads/apostilas/java/k19-k51-design-patterns-em-java">Design Patterns em Java</a>.</li>
					  </ul>
		      	  </div>
			    </div>
			  </li>
			</ul>
		</div>
	</div>
	
	<footer class="zurb-footer-bottom">
		<c:import url="../WEB-INF/template/footer.jsp"></c:import>
	</footer>
	
<script src="../resources/js/vendor/jquery.js"></script>
<script src="../resources/js/foundation/foundation.js"></script>
<script src="../resources/js/foundation/foundation.topbar.js"></script>
<script src="../resources/js/foundation/foundation.accordion.js"></script>
<script>
	$(document).foundation();
</script>
</body>
</html>