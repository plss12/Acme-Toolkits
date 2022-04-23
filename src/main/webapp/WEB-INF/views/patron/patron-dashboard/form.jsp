<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.dashboard.form.title"/>
</h2>

<h3>
	<acme:message code="patron.dashboard.form.label.totalNumberOfPatronages"/>
</h3>

<table class="table table-sm" id="id-totalNumberOfPatronages">
	<caption>
		<acme:message code="patron.dashboard.form.label.totalNumberOfPatronages"/>
	</caption>
	<jstl:forEach items="${totalNumberOfPatronages.keySet()}" var="key">
		<tr>
			<jstl:set value="${totalNumberOfPatronages.get(key)}" var="amount"/>
			<jstl:if test="${amount>0}">
				<th scope="row" style="width: 10%"><acme:message code="patron.dashboard.form.status.${key}"/></th>
				<td><acme:print value="${amount}"/></td>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<table class="table table-sm">
	<h4>
		<acme:message code="patron.dashboard.form.label.averageBudgetOfPatronages"/>
	</h4>
	<table class="table table-sm" id="id-averageBudgetOfPatronages">
		<caption>
			<acme:message code="patron.dashboard.form.label.averageBudgetOfPatronages"/>
		</caption>
		<jstl:forEach items="${averageBudgetOfPatronages.keySet()}" var="key">
			<tr>
				<jstl:set value="${averageBudgetOfPatronages.get(key)}" var="amount"/>
				<jstl:if test="${amount>0}">
					<th scope="row" style="width: 10%"><acme:message code="patron.dashboard.form.status.${key.first}"/></th>
					<td><acme:print value="${amount}"/></td>
					<td><acme:print value="${key.second}"/></td>
				</jstl:if>
			</tr>
		</jstl:forEach>
	</table>
</table>

<table class="table table-sm">
	<h4>
		<acme:message code="patron.dashboard.form.label.deviationBudgetOfPatronages"/>
	</h4>
	<table class="table table-sm" id="id-deviationBudgetOfPatronages">
		<caption>
			<acme:message code="patron.dashboard.form.label.deviationBudgetOfPatronages"/>
		</caption>
		<jstl:forEach items="${deviationBudgetOfPatronages.keySet()}" var="key">
			<tr>
				<jstl:set value="${deviationBudgetOfPatronages.get(key)}" var="amount"/>
				<jstl:if test="${amount>=0}">
					<th scope="row" style="width: 10%"><acme:message code="patron.dashboard.form.status.${key.first}"/></th>
					<td><acme:print value="${amount}"/></td>
					<td><acme:print value="${key.second}"/></td>
				</jstl:if>
			</tr>
		</jstl:forEach>
	</table>
</table>

<table class="table table-sm">
	<h4>
		<acme:message code="patron.dashboard.form.label.minimumBudgetOfPatronages"/>
	</h4>
	<table class="table table-sm" id="id-minimumBudgetOfPatronages">
		<caption>
			<acme:message code="patron.dashboard.form.label.minimumBudgetOfPatronages"/>
		</caption>
		<jstl:forEach items="${minimumBudgetOfPatronages.keySet()}" var="key">
			<tr>
				<jstl:set value="${minimumBudgetOfPatronages.get(key)}" var="amount"/>
				<jstl:if test="${amount>0}">
					<th scope="row" style="width: 10%"><acme:message code="patron.dashboard.form.status.${key.first}"/></th>
					<td><acme:print value="${amount}"/></td>
					<td><acme:print value="${key.second}"/></td>
				</jstl:if>
			</tr>
		</jstl:forEach>
	</table>
</table>

<table class="table table-sm">
	<h4>
		<acme:message code="patron.dashboard.form.label.maximumBudgetOfPatronages"/>
	</h4>
	<table class="table table-sm" id="id-maximumBudgetOfPatronages">
		<caption>
			<acme:message code="patron.dashboard.form.label.maximumBudgetOfPatronages"/>
		</caption>
		<jstl:forEach items="${maximumBudgetOfPatronages.keySet()}" var="key">
			<tr>
				<jstl:set value="${maximumBudgetOfPatronages.get(key)}" var="amount"/>
				<jstl:if test="${amount>0}">
					<th scope="row" style="width: 10%"><acme:message code="patron.dashboard.form.status.${key.first}"/></th>
					<td><acme:print value="${amount}"/></td>
					<td><acme:print value="${key.second}"/></td>
				</jstl:if>
			</tr>
		</jstl:forEach>
	</table>
</table>