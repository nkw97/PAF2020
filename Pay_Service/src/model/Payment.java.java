//IT18003406
//M. F. MOHAMED

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
// Insert Payment
	public String insertPayment(String reference, String pay_method, String amount, String paid_on) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
// create a prepared statement
			String query = " insert into payments(`paymentID`,`paymentReference`,`paymentMethod`,`paymentAmount`,`paymentDate`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, reference);
			preparedStmt.setString(3, pay_method);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			preparedStmt.setString(5, paid_on);

// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

// View Payment
	public String viewPayments() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment Reference</th><th>Payment Method</th><th>Payment Amount</th><th>Payment Date</th></tr>";
			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

// iterate through the rows in the result set
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String paymentReference = rs.getString("paymentReference");
				String paymentMethod = rs.getString("paymentMethod");
				String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
				String paymentDate = rs.getString("paymentDate");

// Add into the html table
				output += "<tr><td>" + paymentReference + "</td>";
				output += "<td>" + paymentMethod + "</td>";
				output += "<td>" + paymentAmount + "</td>";
				output += "<td>" + paymentDate + "</td>";

// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						 + "<td><form method=\"post\" action=\"payment.jsp\">"
						 + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						 + "<input name=\"paymentID\" type=\"hidden\" value=\"" + paymentID
						 + "\">" + "</form></td></tr>";

			}
			con.close();

// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payments.";
			System.err.println(e.getMessage());
		}
		return output;
	}

// Update Payment	
	public String updatePayment(String paymentid, String reference, String pay_method, String amount, String paid_on) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

// create a prepared statement
			String query = "UPDATE payments SET paymentReference=?,paymentMethod=?,paymentAmount=?,paymentDate=? WHERE paymentID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
			preparedStmt.setString(1, reference);
			preparedStmt.setString(2, pay_method);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setString(4, paid_on);
			preparedStmt.setInt(5, Integer.parseInt(paymentid));

// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} 
		catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

// Delete payment	
	public String deletePayment(String paymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

// create a prepared statement
			String query = "delete from payments where paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentID));

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
