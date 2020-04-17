package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import Util.Dbconnection;


public class Hospital {
	// A common method to connect to the DB
	Dbconnection db=new Dbconnection();
	
	

	public String insertHospital(String hosName, String hosTelephone, String hosEmail, String hosAddress) {
		
		String output = "";
		output= "Database INsert";

		try {
			
			Connection con= db.connect();
			System.out.println("Connect Db");

			
			if (con == null) {
				System.out.println("Connect Db2");

				return "Error while connecting to the database for inserting.";

			}
			// create a prepared statement
			String query = " insert into `hospitals`(`hosName`,`hosTelephone`,`hosEmail`,`hosAddress`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, hosName);
			preparedStmt.setString(2, hosTelephone);
			preparedStmt.setString(3, hosEmail);
			preparedStmt.setString(4, hosAddress);
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
			System.out.println("Inserted successfully.......................................");
		} 
		catch (Exception e) {
			output = "Error while inserting the Hospitals.";
			System.out.println("Error while inserting the Hospitals........."+ e);
			System.err.println(e.getMessage());
		}
		return output;
	}
	//

	public String viewHospital() {

		String output = "";

		try {
			

			Connection con= db.connect();

			if (con == null) {

				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed

				output = "<table border=\\\"1\\\">\"\r\n" + 
											 "<th>Hospital Name</th\"\r\n" + 
											 "><th>Contatct No</th>\"\r\n" + 
											 "<th>E-Mail</th>\"\r\n" + 
											 "<th>Address</th>\"";
			
			String query = "SELECT * FROM `hospitals`";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String hos_id = Integer.toString(rs.getInt("hosId"));
				String hosName = rs.getString("hosName");
				String hosTelephone = rs.getString("hosTelephone");
				String hosEmail = rs.getString("hosEmail");
				String hosAddress = rs.getString("hosAddress");

				// Add into the html table
				//output+="<tr><td><input id=\"hidHospitalIDUpdate\"name=\"hidHospitalIDUpdate\"type=\"hidden\" value=\"" + hos_id + "\">";        
				output += "<tr><td>" + hosName + "</td>";
				output += "<td>" + hosTelephone + "</td>";
				output += "<td>" + hosEmail + "</td>";
				output += "<td>" + hosAddress + "</td>";
				// buttons
			}
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error reading Hospitals.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	//update Hospital
	public String updateHospital(String hosId, String hosName, String hosTelephone, String hosEmail, String hosAddress) {
		
		String output = "";
		 try
		 {
				Connection con= db.connect();
   
		    if (con == null)
		
		 {
			return "Error while connecting to the database for updating."; 
			
		 }
		
		// create a prepared statement
		String query = "UPDATE `hospitals` SET `hosName`=?,`hosTelephone`=?,`hosEmail`=?,`hosAddress`=? WHERE `hosId`=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		  preparedStmt.setString(1, hosName);
		  preparedStmt.setString(2, hosTelephone);
		  preparedStmt.setString(3, hosEmail);
		  preparedStmt.setString(4, hosAddress);
		  preparedStmt.setInt(5, Integer.parseInt(hosId));
		
		 
		  // execute the statement
		  preparedStmt.execute();
		  con.close();
		  output = "Updated successfully";
		}
		
		 catch (Exception e)
		{
		output = "Error while updating the Hospital.";
		System.err.println(e.getMessage());
		}
		
		
		return output;
			
	}
	
	//deleting hospital
	public String deletaHospitals(String hosId) {
		
		String output = "";
		try
		{
			Connection con= db.connect();

	  	  if (con == null)
		{
	  		  return "Error while connecting to the database for deleting."; 
		}
		
	  	  // create a prepared statement
		   String query = "delete from hospitals where hosID=?";
		   PreparedStatement preparedStmt = con.prepareStatement(query);
		
		   // binding values
		   preparedStmt.setInt(1, Integer.parseInt(hosId));
		
		   // execute the statement
		   preparedStmt.execute();
		   con.close();
		    output = "Deleted successfully";
		}
		
		catch (Exception e)
		{
		    output = "Error while deleting the Hospital.";
		     System.err.println(e.getMessage());
		}
		
		
		
		return output;
		
		
	}
	
}