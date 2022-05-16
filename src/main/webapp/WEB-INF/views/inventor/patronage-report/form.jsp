<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.patronageReport.form.label.memorandum" path="memorandum"/>
	<acme:input-textbox code="inventor.patronageReport.form.label.link" path="link"/>
		
	<jstl:choose>
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox code="inventor.patronageReport.form.label.sequenceNumber" path="sequenceNumber"/>	
			<acme:input-moment code="inventor.patronageReport.form.label.creationMoment" path="creationMoment"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="inventor.patronageReport.form.label.confirm" path="confirm"/>
			<acme:submit code="inventor.patronageReport.form.button.create" action="/inventor/patronage-report/create?masterId=${masterId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
