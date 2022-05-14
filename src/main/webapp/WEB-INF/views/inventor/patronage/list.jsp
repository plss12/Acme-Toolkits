<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="authenticated.patronage.list.label.code" path="code"/>
	<acme:list-column code="authenticated.patronage.list.label.start_date" path="startDate"/>
	<acme:list-column code="authenticated.patronage.list.label.finish_date" path="finishDate"/>
	<acme:list-column code="authenticated.patronage.list.label.legal_stuff" path="legalStuff"/>
	<acme:list-column code="authenticated.patronage.list.label.status" path="status"/>
	<acme:list-column code="authenticated.patronage.list.label.is_public" path="isPublic"/>
</acme:list>