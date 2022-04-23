<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-moment code="authenticated.announcement.form.label.moment" path="moment"/>
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>	
	<acme:input-select code="authenticated.announcement.form.label.status" path="status">
		<acme:input-option code="INFO" value="INFO" selected="${status == 'INFO'}"/>
		<acme:input-option code="WARNING" value="WARNING" selected="${status == 'WARNING'}"/>
		<acme:input-option code="IMPORTANT" value="IMPORTANT" selected="${status == 'IMPORTANT'}"/>
	</acme:input-select>
	<acme:input-textarea code="authenticated.announcement.form.label.body" path="body"/>
	<acme:input-url code="authenticated.announcement.form.label.link" path="link"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="administrator.announcement.form.label.confirmation" path="confirmation"/>
		<acme:submit code="administrator.announcement.form.button.create" action="/administrator/announcement/create"/>
	</jstl:if>
</acme:form>