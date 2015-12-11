<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
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
</body>
</html>