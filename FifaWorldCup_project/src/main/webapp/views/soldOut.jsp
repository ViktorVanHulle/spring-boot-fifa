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

	<div class="form-fieldset">
		<p class="form-legend">FIFA World Cup Qatar 2022</p>
		<h2>
			<spring:message code="soldOut.text" />
		</h2>
	</div>

	<div class="form-actions">
		<button class="form-btn-cancel -nooutline">
			<a href="/fifaWorldCup"><spring:message code="ticketForm.cancel" /></a>
		</button>
	</div>

</div>
</html>