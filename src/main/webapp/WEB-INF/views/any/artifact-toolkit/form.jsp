<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form> 
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, delete')}">
				<acme:input-textbox code="any.artifact.form.label.name" path="artifact.name" readonly="true"/>
				<acme:input-textbox code="any.artifact.form.label.code" path="artifact.code" readonly="true"/>
				<acme:input-textbox code="any.artifact.form.label.technology" path="artifact.technology" readonly="true"/>
				<acme:input-textarea code="any.artifact.form.label.description" path="artifact.description" readonly="true"/>
				<acme:input-money code="any.artifact.form.label.retailprice" path="artifact.retailPrice" readonly="true"/>
				<acme:input-textbox code="any.artifact.form.label.type" path="artifact.artifactType" readonly="true"/>
				<acme:input-textbox code="any.artifact.form.label.link" path="artifact.link" readonly="true"/>
				<acme:input-textbox code="any.artifact.form.label.artifactAmount" path="artifactAmount" readonly="true"/>
				
				<acme:submit code="any.artifact.form.button.delete" action="/any/artifact-toolkit/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
		
				
				<acme:input-select code="any.artifact.form.label.artifact" path="artifact.code">
					<jstl:forEach items="${artifactSelected}" var="artifactSelected">
						<acme:input-option code="${artifactSelected.code}, ${artifactSelected.name}" value="${artifactSelected.code}"/>
					</jstl:forEach>
				</acme:input-select>
				
				<acme:input-textbox code="any.artifact.form.label.artifactAmount" path="artifactAmount"/>
				
				<acme:input-select code="any.artifact.form.label.toolkit" path="toolkit.code">
					<jstl:forEach items="${toolkitSelected}" var="toolkitSelected">
						<acme:input-option code="${toolkitSelected.code}, ${toolkitSelected.title}" value="${toolkitSelected.code}"/>
					</jstl:forEach>
				</acme:input-select>
				
				
				<acme:submit code="inventor.toolkit.form.button.create" action="/any/artifact-toolkit/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
