<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Produtos</title>
</head>
<body>
	<!-- The action is mapped with the name of the method that should be called when the form is submited -->
	<!-- CommandName attribute is related to the object validation, it need to have the name of the class of the object that is being validated -->
	<form:form method="post" action="${spring:mvcUrl(\"saveProduct\").build()}" commandName="product" enctype="multipart/form-data">
		<div>
			<label for="title">Title</label> 
			<input type="text" name="title" id="title" />
			<!-- This bit of code shows the errors found during the validation. -->
			<form:errors path="title"/>
		</div>
		<div>
			<label for="description">Description</label>
			<textarea rows="10" cols="20" name="description" id="description"></textarea>
		</div>
		<div>
			<label for="pages">Total of pages</label> 
			<input type="text" name="pages" id="pages" />
		</div>
		<div>
			<label for="releaseDate">Data de lançamento</label>
			<input type="text" name="releaseDate"/>
			<form:errors path="releaseDate" />
		</div>		
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label> <input
					type="text" name="prices[${status.index}].value"
					id="price_${bookType}" /> <input type="hidden"
					name="prices[${status.index}].bookType" value="${bookType}" />
			</div>
		</c:forEach>
		<div>
			<label for="summary">Sumario do livro</label>
			<input type="file" name="summary" />
			<form:errors path="summaryPath" />
		</div>
		<div>
			<input type="submit" value="Send">
		</div>
	</form:form>
</body>
</html>