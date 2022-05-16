<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>	
	<acme:list-column code="inventor.patronageReport.list.label.creationMoment" path="creationMoment" width="20%"/>
	<acme:list-column code="inventor.patronageReport.list.label.memorandum" path="memorandum" width="20%"/>
	<acme:list-column code="inventor.patronageReport.list.label.asociatedPatronage" path="patronage.code" width="20%"/>
</acme:list>
<acme:button code="inventor.patronageReport.list.button.create" action="/inventor/patronage-report/create?masterId=${masterId}"/>