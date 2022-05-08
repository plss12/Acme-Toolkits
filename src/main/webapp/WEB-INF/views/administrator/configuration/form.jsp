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
	<acme:input-money code="administrator.system_configuration.form.acceptedCurrencies" path="acceptedCurrencies"/>
	<acme:input-money code="administrator.system_configuration.form.defaultCurrency" path="defaultCurrency"/>
	<acme:input-integer code="administrator.system_configuration.form.weakSpamThreshold" path="weakSpamTrheshold"/>
	<acme:input-textbox code="administrator.system_configuration.form.weakSpam" path="weakSpam"/>
	<acme:input-integer code="administrator.system_configuration.form.strongSpamThreshold" path="strongSpamTrheshold"/>
	<acme:input-textbox code="administrator.system_configuration.form.strongSpam" path="strongSpam"/>
	
	<jstl:choose>	 
		<jstl:when test="${command == 'show'}">
			<acme:button code="administrator.system_configuration.form.button.edit" action="/administrator/configuration/update"/>			
		</jstl:when>
		<jstl:when test="${command == 'update'}">
			<acme:submit code="administrator.system_configuration.form.button.update" action="/administrator/configuration/update"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>