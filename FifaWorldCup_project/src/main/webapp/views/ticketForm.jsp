<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/css/stylesheet.css" var="urlCss" />
<link rel="STYLESHEET" href="${urlCss}" type="text/css" />
<spring:url value="/video/hd0038.mov" var="video" />
<link rel="STYLESHEET" href="${video}" type="text/css" />
<title>FIFA World Cup Qatar 2022</title>
</head>
<video width="100%" height="100%" class="background" autoplay="autoplay"
	 muted="muted" loop="loop" id="video">
	<source src="${video}" type="video/mp4">
</video>
<div class="form-card">
	<form:form id="MyForm" action="/fifaWorldCup" method="post"
		modelAttribute="tickets">
		<fieldset class="form-fieldset">
			<div></div>
			<legend class="form-legend">FIFA World Cup Qatar 2022</legend>
			<h2>
				<spring:message code="title.stadion" />: ${stadium}
			</h2>
			<h3>
				${wedstrijd.wedstrijd.landen.split(",")[0]} vs
				${wedstrijd.wedstrijd.landen.split(",")[1]}
				<spring:message code="ticketForm.on" />
				${wedstrijd.wedstrijd.dag.getDate()}-${wedstrijd.wedstrijd.dag.getMonth()+1}
			</h3>
			<h3>
				<spring:message code="ticketForm.tickets" />
				= ${wedstrijd.tickets}
			</h3>

			<div class="form-element form-input">
				<form:input id="field-uyzeji-352rnc-4rv3g1-bvlh88-9dewuz"
					class="form-element-field" placeholder="" type="email" path="email"
					required="true" />
				<div class="form-element-bar"></div>
				<label class="form-element-label"
					for="field-uyzeji-352rnc-4rv3g1-bvlh88-9dewuz">Email</label> <small
					class="form-element-hint"> &nbsp;<form:errors path="email"
						cssClass="error" />
				</small>
			</div>

			<div class="form-element form-input">
				<form:input id="field-x98ezh-s6s2g8-vfrkgb-ngrhef-atfkop"
					class="form-element-field -hasvalue" placeholder=" " type="number"
					value="1" min="1" max="${wedstrijd.tickets < 25 ? wedstrijd.tickets : 25}" path="value" required="true" />
				<div class="form-element-bar"></div>
				<label class="form-element-label"
					for="field-x98ezh-s6s2g8-vfrkgb-ngrhef-atfkop"> <spring:message
						code="ticketForm.tickets" />
				</label> <small class="form-element-hint"> &nbsp; <form:errors
						class="form-element-hint" path="value" cssClass="error" />
				</small>
			</div>

			<div class="form-element form-input ${error ? "
				form-has-error": ""}">
				<form:input id="field-x98ezh-s6s2g8-vfrkgb-ngrhef-atfkop"
					class="form-element-field -hasvalue" placeholder=" " type="number"
					min="1" value="10" required="true" path="voetbalcode1" />
				<div class="form-element-bar"></div>
				<label class="form-element-label"
					for="field-x98ezh-s6s2g8-vfrkgb-ngrhef-atfkop"><spring:message
						code="ticketForm.vc1" /></label> <small class="form-element-hint">
					&nbsp; <form:errors path="voetbalcode1" cssClass="error" />
				</small>
			</div>

			<div class="form-element form-input ${error ? "form-has-error": ""}">
				<form:input id="field-x98ezh-s6s2g8-vfrkgb-ngrhef-atfkop"
					class="form-element-field -hasvalue" placeholder=" " type="number"
					min="1" value="20" required="true" path="voetbalcode2" />
				<div class="form-element-bar"></div>
				<label class="form-element-label"
					for="field-x98ezh-s6s2g8-vfrkgb-ngrhef-atfkop"><spring:message
						code="ticketForm.vc2" /></label> <small class="form-element-hint">
					&nbsp; <form:errors path="voetbalcode2" cssClass="error" />
				</small>

			</div>
		</fieldset>

		<div class="form-actions">
			<button class="form-btn" type="submit">
				<spring:message code="ticketForm.submit" />
			</button>
			<button class="form-btn-cancel -nooutline" type="reset">
				<a href="/fifaWorldCup"><spring:message code="ticketForm.cancel" /></a>
			</button>
		</div>

	</form:form>
</div>

</html>