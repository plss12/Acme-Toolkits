<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-moment code="any.form.list.label.creationMoment" path="creationMoment"/>
	<acme:input-textbox code="any.form.list.label.title" path="title"/>
	<acme:input-textbox code="any.form.list.label.author" path="author"/>
	<acme:input-textarea code="any.form.list.label.body" path="body"/>
	<acme:input-email code="any.form.list.label.email" path="email"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="any.chirp.form.button.create" action="/any/chirp/create"/>
		</jstl:when>
	</jstl:choose>

</acme:form>