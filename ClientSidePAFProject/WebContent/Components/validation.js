$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------------------
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
// Form validation----------------------------------  
	var status = validateResearcherForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
// If valid field-----------------------------------------  
	var type = ($("#hideResearcherIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "ResearcherService",  
			type : type,  
			data : $("#formResearcher").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onResearcherSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onResearcherSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divResearcherGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving data.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hideResearcherIDSave").val("");  
	$("#formResearcher")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hideResearcherIDSave").val($(this).closest("tr").find('#hideResearcherIDUpdate').val());     
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#emailaddress").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#workOnProduct").val($(this).closest("tr").find('td:eq(2)').text());
	$("#productCategory").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#purposeOfResearch").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#previousProducts").val($(this).closest("tr").find('td:eq(5)').text());    
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "ResearcherService",   
		type : "DELETE",   
		data : "researcherId=" + $(this).data("researcherId"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onResearcherDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onResearcherDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divResearcherGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting data.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting data..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateResearcherForm() 
{  
	// NAME  
	if ($("#name").val().trim() == "")  
	{   
		return "Insert name.";  
	} 

	// email address------------------------  
	if ($("#emailaddress").val().trim() == "")  
	{   
		return "Insert email address.";  
	} 
	// work On Product------------------------  
	if ($("#workOnProduct").val().trim() == "")  
	{   
		return "Insert work On Product.";  
	}
		// productCategory------------------------  
	if ($("#productCategory").val().trim() == "")  
	{   
		return "Insert product Category.";  
	}
		// purpose Of Research------------------------  
	if ($("#purposeOfResearch").val().trim() == "")  
	{   
		return "Insert purpose Of Research.";  
	}
		// previous Products------------------------  
	if ($("#previousProducts").val().trim() == "")  
	{   
		return "Insert epreviousProducts.";  
	}
	
	return true; 
}