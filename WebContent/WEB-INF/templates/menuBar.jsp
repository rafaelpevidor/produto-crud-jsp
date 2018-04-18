<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Navigation-->
<nav class="navbar navbar-inverse navbar-static-top" role="navigation" style="margin-bottom: 0">
	<!-- navbar-header -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/app/dashboard">Stock</a>
	</div>
	<!-- /.navbar-header -->
	<!-- navbar-top-links -->
	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="${pageContext.request.contextPath}/app/profile"><i class="fa fa-user fa-fw"></i>
						Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/app/settings"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
				<li class="divider"></li>
				<li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out fa-fw"></i>
						Logout</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->
	
	<c:import url="../templates/menuSideBar.jsp"></c:import>
</nav>
<!-- ./Navigation-->