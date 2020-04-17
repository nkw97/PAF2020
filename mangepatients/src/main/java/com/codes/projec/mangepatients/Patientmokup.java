package com.codes.projec.mangepatients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Patientmokup {
	
	
List<Patient> patients = new ArrayList<Patient>();
	
	//for checking
	public Patientmokup(){
		
		Patient patient1 = new Patient("1","jack","colombo","13244","12","see");
		Patient patient2 = new Patient("2","john","galle","132234","22","redo");
		Patient patient3 = new Patient("3","su","mana","112244","32","nowa");
		
		patients = Arrays.asList(patient1,patient2,patient3);
		
		}
	//
	
	//retrive  patient details from database
	
	public List<Patient> getPatients() throws SQLException{
		
		List<Patient> patients = new ArrayList<Patient>();
		
		ResultSet result = getConnection().createStatement().executeQuery("select * from patient");
		while(result.next()) {
			
			Patient pat = new Patient();
			
			pat.setPid(result.getString(1));
			pat.setPname(result.getString(2));
			pat.setPaddress(result.getString(3));
			pat.setPmobile(result.getString(4));
			pat.setPage(result.getString(5));
			pat.setPwd(result.getString(6));
			
			patients.add(pat);
		}
		
		return patients;
	
	
	}
	
	/*
	public Patient getPatient(String pname) {
	return patients.stream().filter(p -> p.getPname().equals(pname)).findAny().orElse(null);
	} 
	*/
	
	//inserting patient details to database
	
	public void createpatient(Patient patient) throws Exception{
		
		 PreparedStatement ps = getConnection().prepareStatement("insert into patient (pid,pname,paddress,pmobile,page,pwd) values (?,?,?,?,?,?)");
		ps.setString(1, patient.getPid());
		ps.setString(2, patient.getPname());
		ps.setString(3, patient.getPaddress());
		ps.setString(4, patient.getPmobile());
		ps.setString(5, patient.getPage());
		ps.setString(6, patient.getPwd());
		
		ps.executeUpdate();
		
	}
	
	
	//updating patient details from the databse
	
	public String updatepatient(Patient patient)
	 {
		String output = "";
	try
	 {
		Connection con = getConnection();
	 if (con == null)
	 {
		 return "Error while connecting to the database for updating."; }
	 
	 String query = "UPDATE patient SET pid=?,pname=?,paddress=?,pmobile=?,pwd=? WHERE pid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setString(1, patient.getPid());
	 preparedStmt.setString(2, patient.getPname());
	 preparedStmt.setString(3, patient.getPaddress());
	 preparedStmt.setString(4, patient.getPmobile());
	 preparedStmt.setString(5, patient.getPage());
	 preparedStmt.setString(6, patient.getPwd());
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 output = "Updated successfully";
	 }
	 
	 catch (Exception e)
	 {
	 
		 output = "Error while updating the item.";
	     System.err.println(e.getMessage());
	 
	 }
	 
	 return output;
	 
	 }
	
	//delete patient from database
	
	public void droppatient(String name) throws Exception{
		
		String output = "";
		 try
		 { 
			 
		 
		 PreparedStatement ps = getConnection().prepareStatement("delete from patient where pname=?");
		ps.setString(1, name);
		
		int count = ps.executeUpdate();
		
		System.out.println("deleted success");
		
		output = "Deleted successfully"; 
		 }catch (Exception e)
		 {
			 output = "Error while deleting the item.";
			 System.err.println(e.getMessage());
			 } 
}
	
	
	//databse connection
	
public Connection getConnection() throws SQLException{
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf_db","root","");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;

}

}
