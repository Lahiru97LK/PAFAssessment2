package com;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Researcher {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/pafassessment2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	 
	// Insert researcher details to the database 
	//Create a method called InsertResearcher
	
	public String insertResearcher(String name, String emailaddress, String workOnProduct, String productCategory, String purposeOfResearch, String previousProducts) 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
				{
					return "Error while connecting to the database for inserting."; 
				} 
			// create a prepared statement
			String query = " insert into researcher(`researcherId`,`name`,`emailaddress`,`workOnProduct`,`productCategory`,`purposeOfResearch`,`previousProducts`)"+ " values (?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, name); 
			preparedStmt.setString(3, emailaddress); 
			preparedStmt.setString(4, workOnProduct); 
			preparedStmt.setString(5, productCategory); 
			preparedStmt.setString(6, purposeOfResearch); 
			preparedStmt.setString(7, previousProducts); 
			// execute the statement3
			preparedStmt.execute(); 
			con.close(); 
			//output = "Inserted successfully"; 
			
			String newResearcher = readResearcher(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";    
			
		} 
			catch (Exception e) 
			{ 
				//output = "Error while inserting the researcher data."; 
				//System.err.println(e.getMessage()); 
				
				output =  "{\"status\":\"error\", \"data\": \"Error while inserting .\"}";  
				System.err.println(e.getMessage());
			}		 
			return output; 
	 } //end of the insert method 
	
	public String readResearcher() 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Researcher name</th>" +
							"<th>Email Address</th>" +
							"<th>Work on products</th>"+
							"<th>productCategory</th>" +
							"<th>purpose Of Research</th>"+
							"<th>previous Products</th>"+
							"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from researcher"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			
			while (rs.next()) 
				{ 
					String researcherId = Integer.toString(rs.getInt("researcherId")); 
					String name = rs.getString("name"); 
					String emailaddress = rs.getString("emailaddress");  
					String workOnProduct = rs.getString("workOnProduct");
					String productCategory = rs.getString("productCategory"); 
					String purposeOfResearch = rs.getString("purposeOfResearch"); 
					String previousProducts = rs.getString("previousProducts"); 
					
					// Add into the html table
					
					output += "<tr><td><input id='hideResearcherIDUpdate' name='hideResearcherIDUpdate' type='hidden' value='" + researcherId+ "'>" + name + "</td>"; 
					//output += "<td>" + name + "</td>"; 
					output += "<td>" + emailaddress + "</td>"; 
					output += "<td>" + workOnProduct + "</td>";
					output += "<td>" + productCategory + "</td>"; 
					output += "<td>" + purposeOfResearch + "</td>"; 
					output += "<td>" + previousProducts + "</td>";
					
					output += "<td><input name='btnUpdate' "
							+ " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-researcherId='" + researcherId
							+ "'></td>" + "<td><form method='post' action='ResearcherManagement.jsp'>" + "<input name='btnRemove' "
							+ " type='button' value='Remove' class='btnRemove btn btn-danger' data-researcherId='" + researcherId + "'>"
							+ "<input name='hidResearcherIDDelete' type='hidden' " + " value='" + researcherId + "'>"
							+ "</form></td></tr>";
				} 
				con.close(); 
				// Complete the html table
				output += "</table>"; 
				} 
					catch (Exception e) 
						{ 
							output = "Error while reading the items."; 
							System.err.println(e.getMessage()); 
						} 
				return output; 
	 } // End of the readSearcher() method - - - 
	
	//implement of updateResearcher() method
	public String updateResearcher(String researcherId, String name, String emailaddress, String workOnProduct, String productCategory, String purposeOfResearch, String previousProducts)
	 	{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for updating."; } 
				// create a prepared statement
				String query = "UPDATE researcher SET name=?,emailaddress=?,workOnProduct=?,productCategory=?,purposeOfResearch=?,previousProducts=? WHERE researcherId=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setString(1, name); 
				preparedStmt.setString(2, emailaddress); 
				preparedStmt.setString(3, workOnProduct);
				preparedStmt.setString(4, productCategory); 
				preparedStmt.setString(5, purposeOfResearch); 
				preparedStmt.setString(6, previousProducts); 
				preparedStmt.setInt(7, Integer.parseInt(researcherId)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				
				//output = "Updated successfully"; 
				String newResearchers = readResearcher();    
				output = "{\"status\":\"success\", \"data\": \"" + newResearchers + "\"}";
			} 
			catch (Exception e) 
				{ 
					//output = "Error while updating the researcher details."; 
					//System.err.println(e.getMessage()); 
				    
					output =  "{\"status\":\"error\", \"data\": \"Error while updating.\"}";   
					System.err.println(e.getMessage());
				} 
			return output; 
	 } // End of the update method ---
	// beginning of the deleteResearcher method---
	
	public String deleteResearcher(String researcherId) 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
				{return "Error while connecting to the database for deleting."; } 
				// create a prepared statement
				String query = "delete from researcher where researcherId=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(researcherId));
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				//output = "Deleted successfully"; 
				String newResearcher = readResearcher();
				output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";
				
		} 
			catch (Exception e) 
		{ 
				//output = "Error while deleting the item."; 
				//System.err.println(e.getMessage()); 
				output =  "{\"status\":\"error\", \"data\": \"Error while delete.\"}";   
				System.err.println(e.getMessage());
		} 
			return output; 
	 	} 

} //close the researcher class
