package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dbconnection {


		// TODO Auto-generated method stub

		public static  Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Connection//good
				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospitals?useTimezone=true&serverTimezone=UTC",
						"root", "");
				
				System.out.println("Conection Sucessfull");
				
			} 
			
			catch (Exception e) {
				System.out.println("errrrror - " + e);
				e.printStackTrace();
			}
			return con;
		}
		
	}

