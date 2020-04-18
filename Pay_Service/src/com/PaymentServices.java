//IT18003406
//M. F. MOHAMED

package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")

public class PaymentServices {

	Payment paymentObj = new Payment();

//View Payment	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)

	public String viewPayment() {

		return paymentObj.viewPayments();

	}

//insert Payment	   
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)

	public String insertPayment(
			@FormParam("paymentReference") String paymentReference,
			@FormParam("paymentMethod") String paymentMethod, 
			@FormParam("paymentAmount") String paymentAmount,
			@FormParam("paymentDate") String paymentDate
			) 
	{

		String output = paymentObj.insertPayment(paymentReference, paymentMethod, paymentAmount, paymentDate);
		return output;
	}

// update Payment
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)

	public String updatePayment(String paymentData) {
		
// Convert the input string to a JSON object
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();

// Read the values from the JSON object
		String paymentID = paymentObject.get("paymentID").getAsString();
		String paymentReference = paymentObject.get("paymentReference").getAsString();
		String paymentMethod = paymentObject.get("paymentMethod").getAsString();
		String paymentAmount = paymentObject.get("paymentAmount").getAsString();
		String paymentDate = paymentObject.get("paymentDate").getAsString();
		
		String output = paymentObj.updatePayment(paymentID, paymentReference, paymentMethod, paymentAmount, paymentDate);
				
				

		return output;
	}

// Delete Payment
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)

	public String deletePayment(String paymentData) {

// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

// Read the value from the element <paymentID>
		String paymentId = doc.select("paymentID").text();

		String output = paymentObj.deletePayment(paymentId);

		return output;
	}

}
