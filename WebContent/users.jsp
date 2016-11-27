<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kullanıcı Yönetim Sistemi</title>

<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/script.js"></script>

</head>
<body>
	<div class="social">

		<a href="twitter.com"> <img src="images/twitter.jpg" /></a> <a
			href="youtube.com"> <img src="images/youtube.jpg" /></a>


	</div>


	<div class="navigation">

		<ul class="nav">

			<li><a href="#">Home</a></li>
			<li><a href="#">Abut Us</a></li>
			<li><a href="#">Contact Us</a></li>
		</ul>


	</div>

	<header> <a href="#"><img src="images/logo.jpg"></a> </header>


	<h2 align="center">Kullanıcı Yönetim Sistemi</h2>
	
	
	
	
<!-- 	edit request -->
	
	
	
	

	<div class="container">

		<div class="main" align="center">
			
			<c:if test="${requestScope.error ne null }">
			
				<strong style="color:red">
				
					<c:out value="${requestScope.error }"></c:out>
				
				</strong>
			
			
			</c:if>
			
			
			<c:if test="${requestScope.success ne null }">
			
				<strong style="color:green">
				
					<c:out value="${requestScope.success }"></c:out>
				
				</strong>
			
			
			</c:if>
			
			<c:url var="addUserUrl" value="/addUser"></c:url>
			<c:url var="editUserUrl" value="/editUser"></c:url>
			
			<c:if test="${requestScope.user ne null}">
		
					<form action='<c:out value="${editUserUrl}"></c:out>' method="post">
				
					Id:<input type="text" value="${requestScope.user.id }" readonly="readonly" name="id"><br>
					Name:<input type="text" value="${requestScope.user.name }"  name="name"><br>
					Email:<input type="text" value="${requestScope.user.email }"  name="emailId"><br>
					Password:<input type="password" value="${requestScope.user.password }"  name="password"><br>
					<input type="submit" value="Edit User">
					
					</form>
	
			</c:if>
			
			
			
			
			
			<c:if test="${requestScope.user eq null }">

			<form action='<c:out value="${addUserUrl}"></c:out>' method="post">


				Name:<input type="text" name="name"><br> 
				Email:<input type="text" name="emailId"><br>
				Password:<input type ="password" name="password"><br>
				<input type="submit" value="Add user">
			</form>
			
			</c:if>
		</div>
		
		
		<c:if test="${not empty requestScope.users}"> 
		
			<table bordercolor="green" border="2" align="center" bgcolor="yellow">
			
				<caption><strong>Registered User Details</strong> </caption>
				
				<tbody>
				
					<tr bgcolor="green" style="color: white">
					
						<th>Id</th>
						<th>Name</th>
						<th>Email</th>
						<th>Password</th>
						<th>Edit</th>
						<th>Delete</th>
						
					
					
					</tr>
					
					
					<c:forEach items="${requestScope.users }" var="user">
					
							<c:url var="editUserUrl" value="/editUser">
							
								<c:param name="id" value="${user.id }"></c:param>
							
							</c:url>
							
							<c:url var="deleteURL" value="/deleteUser">
							
								<c:param name="id" value="${user.id }"></c:param>
							
							</c:url>
						
						
						<tr>
						
						
							 	<td><c:out value="${user.id }"></c:out></td>
								<td><c:out value="${user.name }"></c:out></td>
								<td><c:out value="${user.email }"></c:out></td>
								<td><c:out value="***********"></c:out></td>
								<td><a href='<c:out value="${editUserUrl}" escapeXml="true"></c:out>'>Edit</a></td>
								<td><a href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Delete</a></td>
						
								
						
						
						</tr>
						
						
						
					
						
					</c:forEach>
				
				
				
				</tbody>
			
			
			
			
			</table>
		
		
		
		
		</c:if>
		
		
		
		

	</div>









</body>
</html>