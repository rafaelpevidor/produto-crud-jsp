<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/application/vendor/" var="vendor"/>
<c:url value="/application/product/" var="product"/>

<ul class="side-nav">
  <li><a href="${vendor}addvendor.jsp">Adicionar Fornecedor</a></li>
  <li><a href="${vendor}listvendor.jsp">Lista de Fornecedores</a></li>
  <li class="divider"></li>
  <li><a href="${product}addproduct.jsp">Adicionar Produto</a></li>
  <li><a href="${product}listproduct.jsp">Lista de Produtos</a></li>
  <li class="divider"></li>
  <li><a href="http://foundation.zurb.com/learn/about.html">Sobre o Foundation Framework</a></li>
</ul>
