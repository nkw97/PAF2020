package com;

import model.Appointment;

//import javax.annotation.Generated;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;
 
//For JSON 
import com.google.gson.*; 
 
//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
 
@Path("/Appointment") 
public class AppService 

{ 
	Appointment AppObjt = new Appointment(); 
 
 	@GET
 	@Path("/") 
 	@Produces(MediaType.TEXT_HTML) 
 	public String readAppointment() 
 	{ 
 	 	return AppObjt.readAppointment();
 	} 
 	
 	@POST 
 	@Path("/") 
 	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 	@Produces(MediaType.TEXT_PLAIN) 
 	
 	public String insertAppointment(@FormParam("HospitalName") String HospitalName,   	 
 	 	 	 	@FormParam("DoctorName") String DoctorName, 
 	 	 	 	@FormParam("AppointmentDate") String AppointmentDate,   	 
 	 	 	 	@FormParam("Category") String Category)
 				
 	{ 
 	 	String output = AppObjt.insertAppointment(HospitalName, DoctorName, AppointmentDate, Category);
 	 	
 	 	return output; 
 	} 
 	
 	
 	@PUT 
 	@Path("/") 
 	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.TEXT_PLAIN) 
 	public String updateAppointment(String AppData) 
 	{ 
 	 	//Convert the input string to a JSON object 
 	 	JsonObject AppObject = new JsonParser().parse(AppData).getAsJsonObject();  
 	 	//Read the values from the JSON object 
 	 	String AppointmentID = AppObject.get("AppointmentID").getAsString(); 
 	 	String HospitalName = AppObject.get("HospitalName").getAsString(); 
 	 	String DoctorName = AppObject.get("DoctorName").getAsString(); 
 	 	String AppointmentDate = AppObject.get("AppointmentDate").getAsString(); 
 	 	String Category = AppObject.get("Category").getAsString(); 
 	 
 	       String output = AppObjt.updateAppointment(AppointmentID, HospitalName, DoctorName, AppointmentDate, Category); 
 	 
 	 	return output; 
 	} 
 	

@DELETE 
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) @Produces(MediaType.TEXT_PLAIN) 
public String deleteAppointment(String AppData) 
{ 
 	//Convert the input string to an XML document 
 	Document doc = Jsoup.parse(AppData, "", Parser.xmlParser()); 
 	 	 
 	//Read the value from the element <AppointmentID> 
 	String AppointmentID = doc.select("AppointmentID").text();  
 	String output = AppObjt.deleteAppointment(AppointmentID); 
 
 	return output; 
} 
} 
