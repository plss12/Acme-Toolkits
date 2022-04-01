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

<acme:form readonly="${readonly}">
	<acme:input-moment code="authenticated.chirp.form.label.creationMoment" path="creationMoment"/>
	<acme:input-textbox code="authenticated.chirp.form.label.title" path="title"/>	
	<acme:input-textbox code="authenticated.chirp.form.label.author" path="author"/>		
	<acme:input-textarea code="authenticated.chirp.form.label.body" path="body"/>
	<acme:input-url code="administrator.chirp.form.label.emailAddress" path="emailAddress"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="authenticated.chirp.form.label.confirmation" path="confirmation"/>
		<acme:submit code="authenticated.chirp.form.button.create" action="/authenticated/chirp/create"/>
	</jstl:if>
</acme:form>
