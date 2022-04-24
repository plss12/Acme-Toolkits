<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title"/>
</h2>

<h3>
	<acme:message code="administrator.dashboard.form.label.totalNumberOfComponents"/>
	<acme:print value="${totalNumberOfComponents}"/>
</h3>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.averageRetailPriceOfComponents"/>
			</h4>
			<table class="table table-sm" id="id-averageRetailPriceOfComponents">
				<caption>
					<acme:message
						code="administrator.dashboard.form.label.averageRetailPriceOfComponents"/>
				</caption>
				<jstl:forEach items="${technology}" var="technology">
					<tr>
						<th scope="row"><acme:print value="${technology}" /></th>
						<jstl:set
							value="${averageRetailPriceOfComponents.entrySet().stream().filter(x->x.getKey().getFirst().equals(technology)).iterator()}" var="entrySet"/>
						<jstl:if test="${entrySet.hasNext()}">
							<jstl:forEach items="${entrySet}" var="entry">
								<tr>
									<td style="width: 10%"><acme:print value="${entry.getKey().getSecond()}"/></td>
									<td><acme:print value="${entry.getValue()}" /></td>
								</tr>
							</jstl:forEach>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.deviationRetailPriceOfComponents"/>
			</h4>
			<table class="table table-sm" id="id-deviationRetailPriceOfComponents">
				<caption>
					<acme:message code="administrator.dashboard.form.label.deviationRetailPriceOfComponents"/>
				</caption>
				<jstl:forEach items="${technology}" var="technology">
					<tr>
						<th scope="row"><acme:print value="${technology}"/></th>
						<jstl:set
							value="${retailPriceDeviationOfComponents.entrySet().stream().filter(x->x.getKey().getFirst().equals(technology)).iterator()}" var="entrySet"/>
						<jstl:if test="${entrySet.hasNext()}">
							<jstl:forEach items="${entrySet}" var="entry">
								<tr>
									<td style="width: 10%"><acme:print value="${entry.getKey().getSecond()}"/></td>
									<td><acme:print value="${entry.getValue()}"/></td>
								</tr>
							</jstl:forEach>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.minimumRetailPriceOfComponents"/>
			</h4>
			<table class="table table-sm" id="id-minimumRetailPriceOfComponents">
				<caption>
					<acme:message code="administrator.dashboard.form.label.minimumRetailPriceOfComponents"/>
				</caption>
				<jstl:forEach items="${technology}" var="technology">
					<tr>
						<th scope="row"><acme:print value="${technology}"/></th>
						<jstl:set value="${minimumRetailPriceOfComponents.entrySet().stream().filter(x->x.getKey().getFirst().equals(technology)).iterator()}" var="entrySet"/>
						<jstl:if test="${entrySet.hasNext()}">
							<jstl:forEach items="${entrySet}" var="entry">
								<tr>
									<td style="width: 10%"><acme:print value="${entry.getKey().getSecond()}"/></td>
									<td><acme:print value="${entry.getValue()}"/></td>
								</tr>
							</jstl:forEach>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.maximumRetailPriceOfComponents"/>
			</h4>
			<table class="table table-sm" id="id-maximumRetailPriceOfComponents">
				<caption>
					<acme:message code="administrator.dashboard.form.label.maximumRetailPriceOfComponents"/>
				</caption>
				<jstl:forEach items="${technology}" var="technology">
					<tr>
						<th scope="row"><acme:print value="${technology}" /></th>
						<jstl:set value="${maximumRetailPriceOfComponents.entrySet().stream().filter(x->x.getKey().getFirst().equals(technology)).iterator()}" var="entrySet"/>
						<jstl:if test="${entrySet.hasNext()}">
							<jstl:forEach items="${entrySet}" var="entry">
								<tr>
									<td style="width: 10%"><acme:print value="${entry.getKey().getSecond()}"/></td>
									<td><acme:print value="${entry.getValue()}"/></td>
								</tr>
							</jstl:forEach>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<h3>
	<acme:message code="administrator.dashboard.form.label.totalNumberOfTools"/>
	<acme:print value="${totalNumberOfTools}"/>
</h3>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.averageRetailPriceOfTools"/>
			</h4>
			<table class="table table-sm" id="id-averageRetailPriceOfTools">
				<caption>
					<acme:message code="administrator.dashboard.form.label.averageRetailPriceOfTools"/>
				</caption>
				<jstl:forEach items="${currency}" var="currency">
					<tr>
						<th scope="row"><acme:print value="${currency}"/></th>
						<jstl:set value="${averageRetailPriceOfTools.entrySet().stream().filter(x->x.getKey().equals(currency)).iterator()}" var="entrySet"/>
						<jstl:if test="${entrySet.hasNext()}">
							<jstl:forEach items="${entrySet}" var="entry">
								<th scope="row"><acme:print value="${entry.getValue()}" /></th>
							</jstl:forEach>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.deviationRetailPriceOfTools"/>
			</h4>
			<table class="table table-sm" id="id-deviationRetailPriceOfTools">
				<caption>
					<acme:message code="administrator.dashboard.form.label.deviationRetailPriceOfTools"/>
				</caption>
				<jstl:forEach items="${currency}" var="currency">
					<tr>
						<th scope="row"><acme:print value="${currency}"/></th>
						<jstl:set value="${retailPriceDeviationOfTools.entrySet().stream().filter(x->x.getKey().equals(currency)).iterator()}" var="entrySet"/>
						<jstl:if test="${entrySet.hasNext()}">
							<jstl:forEach items="${entrySet}" var="entry">
								<th scope="row"><acme:print value="${entry.getValue()}"/></th>
							</jstl:forEach>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.minimumRetailPriceOfTools"/>
			</h4>
			<table class="table table-sm" id="id-minimumRetailPriceOfTools">
				<caption>
					<acme:message
						code="administrator.dashboard.form.label.minimumRetailPriceOfTools"/>
				</caption>
				<jstl:forEach items="${currency}" var="currency">
					<tr>
						<th scope="row"><acme:print value="${currency}"/></th>
						<jstl:set value="${minimumRetailPriceOfTools.entrySet().stream().filter(x->x.getKey().equals(currency)).iterator()}" var="entrySet"/>
						<jstl:if test="${entrySet.hasNext()}">
							<jstl:forEach items="${entrySet}" var="entry">
								<th scope="row"><acme:print value="${entry.getValue()}"/></th>
							</jstl:forEach>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.maximumRetailPriceOfTools"/>
			</h4>
			<table class="table table-sm" id="id-maximumRetailPriceOfTools">
				<caption>
					<acme:message code="administrator.dashboard.form.label.maximumRetailPriceOfTools"/>
				</caption>
				<jstl:forEach items="${currency}" var="currency">
					<tr>
						<th scope="row"><acme:print value="${currency}"/></th>
						<jstl:set value="${maximumRetailPriceOfTools.entrySet().stream().filter(x->x.getKey().equals(currency)).iterator()}" var="entrySet"/>
						<jstl:if test="${entrySet.hasNext()}">
							<jstl:forEach items="${entrySet}" var="entry">
								<th scope="row"><acme:print value="${entry.getValue()}"/></th>
							</jstl:forEach>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<h3>
	<acme:message code="administrator.dashboard.form.label.totalNumberOfPatronages"/>
</h3>

<table class="table table-sm" id="id-totalNumberOfPatronages">
	<caption>
		<acme:message code="administrator.dashboard.form.label.totalNumberOfPatronages"/>
	</caption>
	<jstl:forEach items="${totalNumberOfPatronages.keySet()}" var="key">
		<tr>
			<jstl:set value="${totalNumberOfPatronages.get(key)}" var="amount"/>
			<jstl:if test="${amount>0}">
				<th scope="row" style="width: 10%"><acme:message code="administrator.dashboard.form.status.${key}"/></th>
				<td><acme:print value="${amount}"/></td>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.averageBudgetOfPatronages"/>
			</h4>
			<table class="table table-sm" id="id-averageBudgetOfPatronages">
				<caption>
					<acme:message code="administrator.dashboard.form.label.averageBudgetOfPatronages"/>
				</caption>
				<jstl:forEach items="${averageBudgetOfPatronages.keySet()}" var="key">
					<tr>
						<jstl:set value="${averageBudgetOfPatronages.get(key)}" var="amount"/>
						<jstl:if test="${amount>0}">
							<th scope="row" style="width: 10%"><acme:message code="administrator.dashboard.form.status.${key.first}"/></th>
							<td><acme:print value="${amount}"/></td>
							<td><acme:print value="${key.second}"/></td>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.deviationBudgetOfPatronages"/>
			</h4>
			<table class="table table-sm" id="id-deviationBudgetOfPatronages">
				<caption>
					<acme:message code="administrator.dashboard.form.label.deviationBudgetOfPatronages"/>
				</caption>
				<jstl:forEach items="${deviationBudgetOfPatronages.keySet()}" var="key">
					<tr>
						<jstl:set value="${deviationBudgetOfPatronages.get(key)}" var="amount"/>
						<jstl:if test="${amount>=0}">
							<th scope="row" style="width: 10%"><acme:message code="administrator.dashboard.form.status.${key.first}"/></th>
							<td><acme:print value="${amount}"/></td>
							<td><acme:print value="${key.second}"/></td>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.minimumBudgetOfPatronages"/>
			</h4>
			<table class="table table-sm" id="id-minimumBudgetOfPatronages">
				<caption>
					<acme:message code="administrator.dashboard.form.label.minimumBudgetOfPatronages"/>
				</caption>
				<jstl:forEach items="${minimumBudgetOfPatronages.keySet()}" var="key">
					<tr>
						<jstl:set value="${minimumBudgetOfPatronages.get(key)}" var="amount"/>
						<jstl:if test="${amount>0}">
							<th scope="row" style="width: 10%"><acme:message code="administrator.dashboard.form.status.${key.first}"/></th>
							<td><acme:print value="${amount}"/></td>
							<td><acme:print value="${key.second}"/></td>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>

<table class="table table-sm">
	<caption>
	</caption>
	<tr>
		<th scope="row">
			<h4>
				<acme:message code="administrator.dashboard.form.label.maximumBudgetOfPatronages"/>
			</h4>
			<table class="table table-sm" id="id-maximumBudgetOfPatronages">
				<caption>
					<acme:message code="administrator.dashboard.form.label.maximumBudgetOfPatronages"/>
				</caption>
				<jstl:forEach items="${maximumBudgetOfPatronages.keySet()}" var="key">
					<tr>
						<jstl:set value="${maximumBudgetOfPatronages.get(key)}" var="amount"/>
						<jstl:if test="${amount>0}">
							<th scope="row" style="width: 10%"><acme:message code="administrator.dashboard.form.status.${key.first}"/></th>
							<td><acme:print value="${amount}"/></td>
							<td><acme:print value="${key.second}"/></td>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</table>
		</th>
	</tr>
</table>