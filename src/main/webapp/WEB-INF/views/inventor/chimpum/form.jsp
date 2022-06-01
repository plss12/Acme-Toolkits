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
	<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.chimpum.form.label.creationMoment" path="creationMoment"/>	
	<acme:input-textbox code="inventor.chimpum.form.label.title" path="title"/>	
	<acme:input-textbox code="inventor.chimpum.form.label.description" path="description"/>	
	<acme:input-textbox code="inventor.chimpum.form.label.startDate" path="startDate"/>	
	<acme:input-textbox code="inventor.chimpum.form.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-textbox code="inventor.chimpum.form.label.link" path="link"/>
	<acme:input-textbox code="inventor.chimpum.form.label.artefact.code" path="artefact.code"/>		
	
	<acme:submit code="inventor.chimpum.form.button.delete" action="/inventor/chimpum/delete"/>
</acme:form>