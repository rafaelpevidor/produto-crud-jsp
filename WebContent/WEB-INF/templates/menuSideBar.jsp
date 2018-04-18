<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- navbar-static-side -->
<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
			<li><a href="${pageContext.request.contextPath}/app/dashboard"><i class="fa fa-dashboard fa-fw"></i>Dashboard</a></li>
			<li><a href="#"><i class="fa fa-edit fa-fw"></i> Forms<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="${pageContext.request.contextPath}/app/unit/list">Units</a></li>
					<li><a href="${pageContext.request.contextPath}/app/vendor/list">Vendors</a></li>
					<li><a href="${pageContext.request.contextPath}/app/product/list">Products</a></li>
				</ul> <!-- /.nav-second-level --></li>
			<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>Stock</a></li>
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->
