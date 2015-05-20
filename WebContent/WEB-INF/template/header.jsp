<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/application/about.jsp" var="about"/>

<nav class="top-bar" data-topbar role="navigation">
  <ul class="title-area">
    <li class="name">
      <h1><a href="#">Produto CRUD</a></h1>
    </li>
     <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
    <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
  </ul>
  <section class="top-bar-section">
    <ul class="right">
      <li class="active"><a href="${about}">Sobre</a></li>
    </ul>
  </section>
</nav>