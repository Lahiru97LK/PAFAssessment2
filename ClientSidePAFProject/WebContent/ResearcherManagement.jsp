<%@ page import="com.Researcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Researcher Service</title>

<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.6.0.min.js"></script> 
<script src="Components/validateResearcher.js"></script> 

</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1> Researcher SERVICE </h1>
			
				<form id="formResearcher" name="formResearcher" method="post" action="ResearcherManagement.jsp">  
					Researcher ID:  
 	 				<input id="researcherId" name="researcherId" type="text"  class="form-control form-control-sm">
					<br> Name:   
  					<input id="name" name="name" type="text" class="form-control form-control-sm">   
  					<br> Email:   
  					<input id="emailaddress" name="emailaddress" type="text"  class="form-control form-control-sm">
  					<br> Work on product:   
  					<input id="workOnProduct" name="workOnProduct" type="text"  class="form-control form-control-sm">
  					<br> Product category:   
  					<input id="productCategory" name="productCategory" type="text"  class="form-control form-control-sm">
  					<br> purpose Of Research:   
  					<input id="purposeOfResearch" name="purposeOfResearch" type="text"  class="form-control form-control-sm">
  					<br> previousProducts:   
  					<input id="previousProducts" name="previousProducts" type="text"  class="form-control form-control-sm">
					<br>
					  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hideResearcherIDSave" name="hideResearcherIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divResearcherGrid">
					<%
					    Researcher researcherObj = new Researcher();
						out.print(researcherObj.readResearcher());
					%>
				</div> 
			</div>
		</div>
</div>
 
</body>
</html>