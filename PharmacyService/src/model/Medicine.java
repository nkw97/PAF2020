package model;

import java.sql.*;


public class Medicine {


	
	private Connection connect()
		{
		Connection con = null;
		try
		{
		/*
			Class.forName("com.mysql.jdbc.Driver");
		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");	*/
		Class.forName("org.mariadb.jdbc.Driver");
		 // loads driver
		 con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/paf_new", "root", ""); 
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
		

	public String insertMedicine(String code, String name, String price, String desc) {
		String output = "";
		try {
			Connection con = connect();
			
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			PreparedStatement ps = con.prepareStatement(" CREATE TABLE oshadi (itemID int NOT NULL AUTO_INCREMENT, itemCode varchar(50),itemName varchar(50),itemPrice varchar(50),itemDesc varchar(50), primary key (itemID)); ");
			//ps.execute();
			
			String query = " insert into oshadi(`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"+" values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(" insert into oshadi(`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`) values (?, ?, ?, ?, ?)");
			// binding values
			
			Double x = Double.valueOf(price);
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4,x);
			preparedStmt.setString(5, desc);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readMedicine() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			output = "<table border=\"1\"><tr><th>Item Code</th><th>Item Name</th><th>ItemPrice</th><th>ItemDescription</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from oshadi";
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
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\""+ itemID + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the medicines.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateMedicine(String ID, String code, String name, String price, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			
			String query = "UPDATE oshadi SET itemCode=?,itemName=?,itemPrice=?,itemDesc=? WHERE itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteMedicine(String itemID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from oshadi where itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
