<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.patronageReport.form.label.sequenceNumber" path="sequenceNumber"/>	
	<acme:input-moment code="inventor.patronageReport.form.label.creationMoment" path="creationMoment"/>
	<acme:input-textbox code="inventor.patronageReport.form.label.memorandum" path="memorandum"/>
	<acme:input-textbox code="inventor.patronageReport.form.label.link" path="link"/>
</acme:form>