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
<spring:url value="/video/logout.png" var="logout" />
<link rel="STYLESHEET" href="${logout}" type="text/css" />
<title>FIFA World Cup Qatar 2022</title>
</head>
<video width="100%" height="100%" class="background" autoplay="autoplay"
	muted="muted" loop="loop" id="video">
	<source src="${video}" type="video/mp4">
</video>

<div action="fifa" method="post" modelAttribute="stadium"
	class="form-card">
	<form action='/login?logout' method='post'>

		<div class="form-actions logout ${loginState ? "" : "close"}">
			<button class="form-btn-cancel -nooutline" type="submit">
				<img src="${logout}">
				<spring:message code="button.logout" /></button>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</div>

	</form>

	<div class="form-fieldset">

		<p>${tickets}</p>
		<p class="form-legend">FIFA World Cup Qatar 2022</p>
		<h2>
			<spring:message code="title.stadion" />
			: ${stadium}
		</h2>
		<h2>
			<spring:message code="table.title" />
		</h2>

		<div class="form-element form-selecNewFile.jspt">
			<div class="table-wrapper">

				<table class="fl-table">

					<tr>
						<th>Nr</th>
						<th><spring:message code="table.secondRow" /></th>
						<th><spring:message code="table.thirdRow" /></th>
						<th><spring:message code="table.fourthRow" /></th>
						<th><spring:message code="table.lastRow" /></th>
					</tr>

					<c:forEach var="wedstrijd" items="${lijstWedstrijden}">
						<tr>
							<td><a href="/fifa/${wedstrijd.wedstrijd.id}">${wedstrijd.wedstrijd.id}</a>
							</td>
							<td>${wedstrijd.wedstrijd.landen.split(",")[0]}-
								${wedstrijd.wedstrijd.landen.split(",")[1]}</td>
							<td>
							${wedstrijd.wedstrijd.dag.getDate()} ${wedstrijd.wedstrijd.getMonthForInt()}
							</td>
							<td>${wedstrijd.wedstrijd.uur}:00</td>
							<td>${wedstrijd.tickets}</td>
						</tr>
					</c:forEach>

				</table>

			</div>
		</div>
	</div>
</div>
</html>
