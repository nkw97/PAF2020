package model;

import java.sql.*;

public class Doc { // A common method to connect to the DB
	
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:8080", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public String insertItem(String DocName, String address, String phoneNo, String specialist) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
// create a prepared statement
			String query = " insert into Doc(`DocID`,`DocName`,`address`,`phoneNo`,`specialist`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, DocName);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, phoneNo);
			preparedStmt.setString(5, specialist);

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

	
	public String readItems()
	{
			String output = "";
			try
			{
					Connection con = connect();
					if (con == null)
					{
					return "Error while connecting to the database for reading.";
					}
					
					
					// Prepare the html table to be displayed
					output = "<table border=\"1\"><tr><th>Doctor Name </th>"
							+"<th>Address</th><th>Phone Number</th>"
							+ "<th>Specialist</th>"
							+ "<th>Update</th><th>Remove</th></tr>";
					
					
					String query = "select * from Doc";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					
					// iterate through the rows in the result set
					while (rs.next())
					{
							String DocID = Integer.toString(rs.getInt("DocID"));
							String DocName = rs.getString("DocName");
							String address = rs.getString("address");
							String phoneNo = Double.toString(rs.getDouble("phoneNo"));
							String specialist = rs.getString("specialist");
							
							// Add into the html table
							output += "<tr><td>" + DocName + "</td>";
							output += "<td>" + address + "</td>";
							output += "<td>" + phoneNo + "</td>";
							output += "<td>" + specialist + "</td>";
							
							// buttons
							output += "<td><input name=\"btnUpdate\" "
									+ " type=\"button\" value=\"Update\"></td>"
									+ "<td><form method=\"post\" action=\"Doc.jsp\">"
									+ "<input name=\"btnRemove\" "
									+ " type=\"submit\" value=\"Remove\">"
									+ "<input name=\"DocID\" type=\"hidden\" "
									+ " value=\"" + DocID + "\">" + "</form></td></tr>";
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
	}
	
	
	public String updateItem(String DocID, String DocName, String address, String phoneNo, String specialist) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE Doc SET DocName=?,address=?,phoneNo=?,itemDesc=?WHERE DocID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, DocName);
			preparedStmt.setString(2, address);
			preparedStmt.setString(3, phoneNo);
			preparedStmt.setString(4, specialist);
			preparedStmt.setInt(5, Integer.parseInt(DocID));
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

	
	
	public String deleteItem(String DocID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from Doc where DocID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(DocID));
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