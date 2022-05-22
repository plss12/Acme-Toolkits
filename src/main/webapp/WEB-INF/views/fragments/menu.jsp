	<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Provider,acme.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
    
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.any.chirp.list" action="/any/chirp/list"/>
			<acme:menu-suboption code="master.menu.any.user-accounts.list" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.any.artifact.list" action="/any/artifact/list"/>
      		<acme:menu-suboption code="master.menu.any.toolkit.list" action="/any/toolkit/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="47549618Q: Navarro Rodriguez, Julio" action="https://vandal.elespanol.com/"/>
			<acme:menu-suboption code="29552748W: Parejo Ramos, Salvador" action="https://sevillafc.es"/>
			<acme:menu-suboption code="49126856Y: Cabezas Villalba, Juan Pablo" action="http://dnd5e.wikidot.com/"/>
			<acme:menu-suboption code="49826018Z: Moreno Calderon, Alvaro" action="https://copilot.github.com/"/>
			<acme:menu-suboption code="20063803Y: Martinez Jaen, Javier" action="http://www.realbetisbalompie.es/"/>
			<acme:menu-suboption code="53912470X: Soto Santos, Pedro Luis" action="https://www.thingiverse.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.createAnnouncement" action="/administrator/announcement/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.systemConfiguration" action="/administrator/configuration/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.patron.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.patronages" action="/patron/patronage/list-mine"/>
			<acme:menu-suboption code="master.menu.patron.dashboard" action="/patron/patron-dashboard/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="hasRole('Authenticated')">
			<acme:menu-suboption code="master.menu.authenticated.announcement.list-recent" action="/authenticated/announcement/list-recent"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.any.chirp.list" action="/any/chirp/list"/>
			<acme:menu-suboption code="master.menu.any.user-accounts.list" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.any.artifact.list" action="/any/artifact/list"/>
      <acme:menu-suboption code="master.menu.any.toolkit.list" action="/any/toolkit/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.system-configuration.show" action="/authenticated/configuration/show"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.artifact.list" action="/inventor/artifact/list"/>
			<acme:menu-suboption code="master.menu.inventor.patronage.list" action="/inventor/patronage/list"/>
			<acme:menu-suboption code="master.menu.inventor.toolkit.list" action="/inventor/toolkit/list"/>
			<acme:menu-suboption code="master.menu.inventor.artifact-toolkit.create" action="/any/artifact-toolkit/create"/>
		</acme:menu-option>
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.inventor" action="/authenticated/inventor/update" access="hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

