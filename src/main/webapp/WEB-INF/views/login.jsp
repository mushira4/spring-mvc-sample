<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customTags" %>

<customTags:page bodyClass="" title="Login Page">
<jsp:body>
	<form:form servletRelativeAction="/login">
		<div>
			<label> User <input type="text" name="username" value="" />
			</label>
		</div>
		<div>
			<label> Password <input type="password" name="password">
			</label>
			<div>
				<input type="submit" name="submit" value="Login" />
			</div>
		</div>
	</form:form>
</jsp:body>
</customTags:page>
