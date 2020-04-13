package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Connection//good
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_db?useTimezone=true&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			System.out.println("errrrror - " + e);
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String hosName, String hosTelNo, String hosAddress, String hosEmail) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into hospital(`hosName`,`hosTelNo`,`hosAddress`,`hosEmail`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, hosName);
			preparedStmt.setString(2, hosTelNo);
			preparedStmt.setString(3, hosAddress);
			preparedStmt.setString(4, hosEmail);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
			System.out.println("Inserted successfully.......................................");
		} catch (Exception e) {
			output = "Error while inserting the Hospitals.";
			System.out.println("Error while inserting the Hospitals........." + e);
			System.err.println(e.getMessage());
		}
		return output;
	}
	//

	public String viewHospital() {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {

				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed

			output = "<table border=\"1\"><tr><th>Item Code</th><th>Item Name</th><th>HOSPITALS>";
			String query = "select * from hospitals";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String itemID = Integer.toString(rs.getInt("itemID"));
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				String itemDesc = rs.getString("itemDesc");

				// Add into the html table
				output += "<tr><td>" + itemCode + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice + "</td>";
				output += "<td>" + itemDesc + "</td>";
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
	public String updateHospital(String hosID, String hosName, String hosTelNo, String hosAddress, String hosEmail) {
		
		String output = "";
		 try
		 {
		   Connection con = connect();
		    if (con == null)
		
		 {
			return "Error while connecting to the database for updating."; 
			
		 }
		
		// create a prepared statement
		String query = "UPDATE items SET itemCode=?,itemName=?,itemPrice=?,itemDesc=?WHERE itemID=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		  preparedStmt.setString(1, hosName);
		  preparedStmt.setString(2, hosTelNo);
		  preparedStmt.setDouble(3, Double.parseDouble(hosAddress));
		  preparedStmt.setString(4, hosEmail);
		  preparedStmt.setInt(5, Integer.parseInt(hosID));
		 
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
	public String deletaHospitals(String hId) {
		
		String output = "";
		try
		{
	  	  Connection con = connect();
		
	  	  if (con == null)
		{
	  		  return "Error while connecting to the database for deleting."; 
		}
		
	  	  // create a prepared statement
		   String query = "delete from items where itemID=?";
		   PreparedStatement preparedStmt = con.prepareStatement(query);
		
		   // binding values
		   preparedStmt.setInt(1, Integer.parseInt(hId));
		
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