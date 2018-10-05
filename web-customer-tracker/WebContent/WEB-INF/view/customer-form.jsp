<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Save Customer</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" >
</head>
<body>
	
	<div class="container">
			<header>
					<div class="display-4 text-info text-center ">CRM - Customer Relationship Manager</div>
	
					<hr />
			</header>
			
			<h3 class="text-center bg-dark text-light rounded py-2">Save Customer</h3>
				
			<form:form action="saveCustomer" modelAttribute="customer" method="POST" class="w-75 mx-auto">
			
				<!-- need to associate this data with customer id -->
				<form:hidden path="id" />
				
			
				<div class="form-group">
					<label for="">First Name: </label>
					<form:input path="firstName" class="form-control" />
				</div>
				<div class="form-group">
					<label for="">Last Name: </label>
					<form:input path="lastName" class="form-control" />
				</div>
				<div class="form-group">
					<label for="">Email Address: </label>
					<form:input path="email" class="form-control" />
				</div>
				
				<input type="submit" value="save" class="btn btn-block btn-success" />
			
			</form:form>
			
			<a href="${pageContext.request.contextPath}/customer/list" class="btn"> Back to List</a>
			
	</div>
	

</body>
</html>