package model;

import java.util.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.AppService;

public class Appointment {
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appoinment", "root", "");

			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertAppointment(String hosName, String docName, String AppDate, String category) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into appointment(`AppointmentID`,`HospitalName`,`DoctorName`,`AppointmentDate`,`Category`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hosName);
			preparedStmt.setString(3, docName);
			preparedStmt.setString(4, AppDate);
			preparedStmt.setString(5, category);
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";

		} catch (Exception e)

		{
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readAppointment() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			/*output = "<table border=\"1\"><tr><th>Appointment ID</th><th>Hospital Name</th>"
					+ "<th>Doctor Name</th><th>App Date</th><th>Category</><th>Update</th><th>Remove</th></tr>";
*/
	
			output = "<table border=\"1\"><tr><th>Appointment ID</th><th>Hospital Name</th><th>Doctor Name</th><th>Appointment Date</th><th>Category</><th>Update</th><th>Remove</th></tr>"; 
						 
			
			String query = "select * from appointment";

			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String AppointmentID = Integer.toString(rs.getInt("AppointmentID"));
				String HospitalName = rs.getString("HospitalName");
				String DoctorName = rs.getString("DoctorName");
				String AppointmentDate = rs.getString("AppointmentDate");
				String Category = rs.getString("Category");

				// Add into the html table
				output += "<tr><td>" + AppointmentID + "</td>";	                                         
				output += "<td>" + HospitalName + "</td>"; 
				output += "<td>" + DoctorName + "</td>";
				output += "<td>" + AppointmentDate + "</td>";
				output += "<td>" + Category + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" " + " type=\"button\" value=\"Update\" class=\btn-secondry\"></td>"
						+ "<td><form method=\"post\" action=\"app.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\">" + "<input name=\"AppointmentID\" type=\"hidden\" " + " value=\""
						+ AppointmentID + "\">" + "</form></td></tr>";

			}
			con.close();
			// Complete the html table

			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String updateAppointment(String appId, String hosName, String docName, String AppDate, String category) {
		String output = "";
		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement

			String query = "UPDATE Appointment SET HospitalName=?,DoctorName=?,AppointmentDate=?,Category=? WHERE AppointmentID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, hosName);
			preparedStmt.setString(2, docName);
			preparedStmt.setString(3, AppDate);
			preparedStmt.setString(4, category);
			preparedStmt.setInt(5, Integer.parseInt(appId));
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

	public String deleteAppointment(String AppointmentID) {
		String output = "";

		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from appointment where AppointmentID=?";
                                       
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			//String AppointmentID = null;
			preparedStmt.setInt(1, Integer.parseInt(AppointmentID));
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
