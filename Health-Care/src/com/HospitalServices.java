package com;

import model.Hospital;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Hospital") 

public class HospitalServices {
	
	 Hospital hospital1 = new Hospital();
	   @GET
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   @Produces(MediaType.TEXT_PLAIN)
	
	   public String viewHospital() {
	
		   return hospital1.viewHospital();
		  
	   }
	   
	   
	   
//insert Hospital	   
	   @POST
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   @Produces(MediaType.TEXT_PLAIN)	   
	   
	   public String insertHospital(
	    @FormParam("hosName") String hosName,
	    @FormParam("hosTelNo") String hosTelNo,
	    @FormParam("hosAddress") String hosAddress,
	    @FormParam("hosEmail") String hosEmail
	 )
	  {
	    
		   String output = hospital1.insertHospital(hosName,hosTelNo ,hosAddress , hosEmail);
		   return output;
	   }
	   
	//update Hospital
	   
	   @PUT
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.TEXT_PLAIN)
	  
	   public String updateItem(String itemData)
	   {
	     //Convert the input string to a JSON object
	     JsonObject hospital = new JsonParser().parse(itemData).getAsJsonObject();
	   
	     //Read the values from the JSON object
	      String hosID = hospital.get("hosID").getAsString();
	      String hosName = hospital.get("hosName").getAsString();
	      String hosTelNo = hospital.get("hosTelNo").getAsString();
	      String hosAddress = hospital.get("hosAddress").getAsString();
	      String hosEmail = hospital.get("hosEmail").getAsString();
	      String output = hospital1.updateHospital(hosID, hosName, hosTelNo, hosAddress, hosEmail);

	      return output;
	   }
	   
	   //Delete Hospital
	   @DELETE
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_XML)
	   @Produces(MediaType.TEXT_PLAIN)
	  
	   public String deleteItem(String hospitalData)
	   {
	   
		   //Convert the input string to an XML document
	      Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());
	  
	      //Read the value from the element <itemID>
	   
	      String hId = doc.select("itemID").text();
	   
	      String output = hospital1.deletaHospitals(hId);
	   
	      return output;
	   }
}
