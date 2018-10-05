<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Customers List</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" >
</head>
<body>

	<div class="display-4 text-info text-center ">CRM - Customer Relationship Manager</div>
	
	<hr />
	
	
	
	<div class="container w-75 text-center">
	
		<!-- Search by Name -->
		<form:form action="search" method = "POST" class="form-inline">
			<input type="search" name="theSearchName" class="form-control w-75 mr-2" placeholder="Search name" />
			<input type="submit" value="Search"  class="btn btn-outline-success"/> 
		</form:form>
		
		<br />
	
		<button onclick = "window.location.href='showFormForAdd'; return false;" class="btn btn-success my-3 float-left"> Add Customer</button>
	
		<!-- add our html table here  -->
		<table class="table table-striped table-dark">
			<thead>
				<tr>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Email Address</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					
					<!-- loop over and print our customer -->
					<c:forEach var="tempCustomer" items="${customers}">
					
						<!-- construct an "update" link with customer id -->
						<c:url var = "updateLink" value="/customer/showFormForUpdate">
							<c:param name = "customerId" value="${tempCustomer.id}" />
						</c:url>
						
						<!-- construct an "delete" link with customer id -->
						<c:url var = "deleteLink" value="/customer/delete">
							<c:param name = "customerId" value="${tempCustomer.id}" />
						</c:url>
						
						<tr>
							<td>${tempCustomer.firstName}</td>
							<td>${tempCustomer.lastName}</td>
							<td>${tempCustomer.email}</td>
							<td>
								<!-- display the update link -->
								<a href="${updateLink}" class="btn btn-warning">Update</a> 
								|
								<a href="${deleteLink}" onclick = "if (!(confirm('Are you sure you want to delete this customer?'))) return false" class="btn btn-danger">Delete</a>
							</td>
						</tr>
						
					</c:forEach>
					
				</tr>
			</tbody>
		</table>
	</div>
	

</body>
</html>