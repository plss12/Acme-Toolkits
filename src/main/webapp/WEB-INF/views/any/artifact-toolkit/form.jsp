<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readOnly}"> 
	<acme:input-textbox code="any.artifact.form.label.name" path="artifact.name"/>
	<acme:input-textbox code="any.artifact.form.label.code" path="artifact.code"/>
	<acme:input-textbox code="any.artifact.form.label.technology" path="artifact.technology"/>
	<acme:input-textarea code="any.artifact.form.label.description" path="artifact.description"/>
	<acme:input-money code="any.artifact.form.label.retailprice" path="artifact.retailPrice"/>
	<acme:input-textbox code="any.artifact.form.label.type" path="artifact.artifactType"/>
	<acme:input-textbox code="any.artifact.form.label.link" path="artifact.link"/>
	<acme:input-textbox code="any.artifact.form.label.artifactAmount" path="artifactAmount"/>

</acme:form>