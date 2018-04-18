<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${msgsuccess != null}">
	<div data-alert class="alert-box success radius">
	  ${msgsuccess}
	</div>
</c:if>
<c:if test="${msginfo != null}">
	<div data-alert class="alert-box success radius">
	  ${msginfo}
	</div>
</c:if>
<c:if test="${msgwarn != null}">
	<div data-alert class="alert-box warning radius">
	  ${msgwarn}
	</div>
</c:if>
<c:if test="${msgerror != null}">
	<div data-alert class="alert-box warning radius">
	  ${msgerror}
	</div>
</c:if>