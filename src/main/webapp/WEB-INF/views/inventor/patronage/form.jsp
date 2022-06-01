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

<acme:form>
	<acme:input-textbox code="authenticated.patronage.form.label.code" path="code" readonly="true"/>	
	<acme:input-money code="authenticated.patronage.form.label.budget" path="budget" readonly="true"/>
	<acme:input-money code="patron.patronage.form.label.budgetExchange" path="budgetExchange" readonly="true"/>
	<acme:input-moment code="patron.patronage.form.label.budgetExchangeDate" path="budgetExchangeDate" readonly="true"/>
	<acme:input-moment code="authenticated.patronage.form.label.start_date" path="startDate" readonly="true"/>
	<acme:input-moment code="authenticated.patronage.form.label.finish_date" path="finishDate" readonly="true"/>
	<acme:input-textbox code="authenticated.patronage.form.label.legal_stuff" path="legalStuff" readonly="true"/>
	<acme:input-textbox code="authenticated.patronage.form.label.link" path="link" readonly="true"/>
	<acme:input-textbox code="authenticated.patronage.form.label.patron" path="patron.userAccount.username" readonly="true"/>
	<acme:input-textbox code="authenticated.patronage.form.label.company" path="patron.company" readonly="true"/>
	<acme:input-textbox code="authenticated.patronage.form.label.patron.link" path="patron.link" readonly="true"/>
	<acme:input-textbox code="authenticated.patronage.form.label.statement" path="patron.statement" readonly="true"/>
	
	<jstl:choose>	 
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox code="authenticated.patronage.form.label.status" path="status"/>
			<jstl:if test="${isProposedAndPublic.equals(true)}">			
				<acme:button code="inventor.patronage.form.button.edit" action="/inventor/patronage/update?id=${id}"/>
			</jstl:if>
			<acme:button code="authenticated.patronage.form.button.patronageReport" action="/inventor/patronage-report/list?masterId=${id}"/>			
		</jstl:when>
		<jstl:when test="${command == 'update'}">
			<acme:input-select code="authenticated.patronage.form.label.status" path="status">
				<acme:input-option code="authenticated.patronage.form.label.status.accepted" value="${accepted}"/>
				<acme:input-option code="authenticated.patronage.form.label.status.denied" value="${denied}"/>
			</acme:input-select>
			<acme:submit code="inventor.patronage.form.button.update" action="/inventor/patronage/update"/>
		</jstl:when>		
	</jstl:choose>

</acme:form>	