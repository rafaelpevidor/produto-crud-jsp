<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${msg != null}">
	<div data-alert class="alert-box success radius">
	  ${msg}
	</div>
</c:if>
<c:if test="${msgerror != null}">
	<div data-alert class="alert-box warning radius">
	  ${msgerror}
	</div>
</c:if>