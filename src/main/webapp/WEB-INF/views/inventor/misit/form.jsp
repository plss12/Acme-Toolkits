<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.misit.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.misit.form.label.creationMoment" path="creationMoment"/>	
	<acme:input-textbox code="inventor.misit.form.label.subject" path="subject"/>	
	<acme:input-textbox code="inventor.misit.form.label.explanation" path="explanation"/>	
	<acme:input-textbox code="inventor.misit.form.label.startDate" path="startDate"/>	
	<acme:input-textbox code="inventor.misit.form.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="inventor.misit.form.label.quantity" path="quantity"/>
	<acme:input-textbox code="inventor.misit.form.label.additionalInfo" path="additionalInfo"/>
		
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">	
	<acme:input-textbox code="inventor.misit.form.label.artefact.code" path="artefact.code" readonly="true"/>
	<acme:submit code="inventor.misit.form.button.update" action="/inventor/misit/update"/>
	<acme:submit code="inventor.misit.form.button.delete" action="/inventor/misit/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
		<acme:submit code="inventor.misit.form.button.create" action="/inventor/misit/create?masterId=${masterId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>