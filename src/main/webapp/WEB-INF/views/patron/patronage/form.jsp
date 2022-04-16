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
	<acme:input-textbox code="patronage.label.code" path="code"/>
	<acme:input-textbox code="patronage.label.legalStuff" path="legalStuff"/>
	<acme:input-textbox code="patronage.label.budget" path="budget"/>
	<acme:input-textbox code="patronage.label.startDate" path="startDate"/>
	<acme:input-textbox code="patronage.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="patronage.label.link" path="link"/>
	<acme:input-textbox code="patronage.label.inventor.company" path="inventor.company"/>
	<acme:input-textbox code="patronage.label.inventor.statement" path="inventor.statement"/>
	<acme:input-textbox code="patronage.label.inventor.link" path="inventor.link"/>
</acme:form>