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
	   @Produces(MediaType.TEXT_HTML)
	
	   public String viewHospital() {
	
		   return hospital1.viewHospital();
		  
	   }
	   
	   
	   
//insert Hospital	   
	   @POST
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   @Produces(MediaType.TEXT_PLAIN)	   
	   
	   public String insertHospital(
    	@FormParam("hosName") String hos_name,
	    @FormParam("hosTelephone") String hos_telephone,
	    @FormParam("hosEmail") String hos_email,
	    @FormParam("hosAddress") String hos_address
	 )
	  {
	    
		   String output = hospital1.insertHospital(hos_name,hos_telephone ,hos_email , hos_address);
		   return output;
	   }
	   
	//update Hospital
	   
	   @PUT
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.TEXT_PLAIN)
	  
	   public String updateHospital(String dData)
	   {
	     //Convert the input string to a JSON object
	     JsonObject hospital = new JsonParser().parse(dData).getAsJsonObject();
	   
	     //Read the values from the JSON object
	      String hosId = hospital.get("hosId").getAsString();
	      String hosName = hospital.get("hosName").getAsString();
	      String hosTelephone = hospital.get("hosTelephone").getAsString();
	      String hosEmail = hospital.get("hosEmail").getAsString();
	      String hosAddress = hospital.get("hosAddress").getAsString();
	      String output = hospital1.updateHospital(hosId, hosName, hosTelephone, hosEmail, hosAddress);

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
	   
	      String hosId = doc.select("hosID").text();
	   
	      String output = hospital1.deletaHospitals(hosId);
	    
	      return output;
	   }
}
