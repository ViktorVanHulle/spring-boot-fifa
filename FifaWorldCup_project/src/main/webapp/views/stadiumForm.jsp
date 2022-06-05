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
<spring:url value="/video/logout.png" var="logout" />
<link rel="STYLESHEET" href="${logout}" type="text/css" />
</head>
<body>

	<video width="100%" height="100%" class="background"
		autoplay="autoplay" muted="muted" loop="loop" id="video">
		<source src="${video}" type="video/mp4">
	</video>

	<div class="form-card">
		<form action='/login?logout' method='post'>

			<div class="form-actions logout ${loginState ? "" : "close"}">
				<button class="form-btn-cancel -nooutline" type="submit">
					<img src="${logout}">
					<spring:message code="button.logout" />
				</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</div>

		</form>
		<form:form action="fifa" method="post" modelAttribute="stadium">

			<fieldset class="form-fieldset">
				<p>${tickets}</p>
				<legend class="form-legend">FIFA World Cup Qatar 2022</legend>
				<div class="form-element form-selecNewFile.jspt">
					<form:select id="field-be1h8i-ll2hpg-q4efzm-nfjj1e-udkw5r"
						class="form-element-field" path="value" required="true">
						<option disabled selected value="" class="form-select-placeholder"></option>
						<c:forEach var="naam" items="${stadiums}">
							<form:option value="${naam}">
						${naam}
					</form:option>
						</c:forEach>
					</form:select>
					<div class="form-element-bar"></div>
					<label class="form-element-label"
						for="field-be1h8i-ll2hpg-q4efzm-nfjj1e-udkw5r"> <spring:message
							code="stadiumForm.label" />
					</label>
				</div>
			</fieldset>

			<div class="form-actions">
				<button class="form-btn" type="submit">
					<spring:message code="stadiumForm.button" />
				</button>
			</div>

		</form:form>
	</div>
</body>
</html>