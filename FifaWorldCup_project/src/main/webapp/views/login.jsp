<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FIFA World Cup Qatar 2022</title>
<spring:url value="/css/stylesheet.css" var="urlCss" />
<link rel="STYLESHEET" href="${urlCss}" type="text/css" />
<spring:url value="/video/hd0038.mov" var="video" />
<link rel="STYLESHEET" href="${video}" type="text/css" />
</head>
<body onload='document.loginForm.username.focus();'>

	<video width="100%" height="100%" class="background"
		autoplay="autoplay" muted="muted" loop="loop" id="video">
		<source src="${video}" type="video/mp4">
	</video>

	<form action='login' method='POST' class="form-card">
		<fieldset class="form-fieldset">
			<legend class="form-legend">FIFA World Cup Qatar 2022</legend>
			<h2><spring:message code="login.title" /></h2>

			<c:if test="${not empty error}">
				<div class="login_error"><spring:message
						code="login.error" /></div>
			</c:if>

			<c:if test="${not empty msg}">
				<div class="msg"><spring:message
						code="login.msg" /></div>
			</c:if>

			<div class="form-element form-input">
				<input class="form-element-field" type='text' name='username'
					value='' />
				<div class="form-element-bar"></div>
				<label class="form-element-label"><spring:message
						code="login.user" /></label>
			</div>
			<div class="form-element form-input">
				<input class="form-element-field" type='password' name='password'
					value='' />
				<div class="form-element-bar"></div>
				<label class="form-element-label"><spring:message
						code="login.password" /></label>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</fieldset>
		<div class="form-actions">
			<button class="form-btn" type="submit">
				<spring:message code="login.button" />
			</button>
		</div>

	</form>

</body>
</html>