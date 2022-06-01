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
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.startDate" path="startDate"/>
	<acme:input-moment code="patron.patronage.form.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="patron.patronage.form.label.link" path="link"/>
	
	<jstl:choose>		
		<jstl:when test="${command == 'show' && isPublic == true}">		
			<acme:input-textbox code="patron.patronage.form.label.status" path="status" readonly="true"/>	
			<acme:input-money code="patron.patronage.form.label.budgetExchange" path="budgetExchange" readonly="true"/>
			<acme:input-moment code="patron.patronage.form.label.budgetExchangeDate" path="budgetExchangeDate" readonly="true"/>	
			<acme:input-textbox code="patron.patronage.form.label.inventor" path="inventor.userAccount.username" readonly="true"/>
			<acme:input-textbox code="patron.patronage.form.label.inventor.company" path="inventor.company" readonly="true"/>
			<acme:input-textbox code="patron.patronage.form.label.inventor.statement" path="inventor.statement" readonly="true"/>
			<acme:input-textbox code="patron.patronage.form.label.inventor.link" path="inventor.link" readonly="true"/>
			<acme:button code="patron.patronage.form.button.patronageReport" action="/patron/patronage-report/list?masterId=${id}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
			<acme:input-select code="patron.patronage.form.label.inventor.username" path="inventorUsername">
				<jstl:forEach items="${inventorSelect}" var="inventorSelect">
					<acme:input-option code="${inventorSelect.userAccount.username}" value="${inventorSelect.userAccount.username}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:input-textbox code="patron.patronage.form.label.status" path="status" readonly="true"/>
			<acme:input-money code="patron.patronage.form.label.budgetExchange" path="budgetExchange" readonly="true"/>
			<acme:input-moment code="patron.patronage.form.label.budgetExchangeDate" path="budgetExchangeDate" readonly="true"/>
			<acme:input-textbox code="patron.patronage.form.label.inventor" path="inventor.userAccount.username" readonly="true"/>
			<acme:input-textbox code="patron.patronage.form.label.inventor.company" path="inventor.company" readonly="true"/>
			<acme:input-textbox code="patron.patronage.form.label.inventor.statement" path="inventor.statement" readonly="true"/>
			<acme:input-textbox code="patron.patronage.form.label.inventor.link" path="inventor.link" readonly="true"/>			
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
			<acme:button code="patron.patronage.form.button.patronageReport" action="/patron/patronage-report/list?masterId=${id}"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:input-select code="patron.patronage.form.label.inventor.username" path="inventorUsername">
				<jstl:forEach items="${inventorSelect}" var="inventorSelect">
					<acme:input-option code="${inventorSelect.userAccount.username}" value="${inventorSelect.userAccount.username}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>			
		</jstl:when>
	</jstl:choose>	
	
</acme:form>