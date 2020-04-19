package com;

import model.Medicine;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Medicine")
public class PharmacyService {

	Medicine itemObj = new Medicine();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readMedicine() {

		return itemObj.readMedicine();
	}

	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertMedicine(@FormParam("itemCode") String itemCode, @FormParam("itemName") String itemName,
			@FormParam("itemPrice") String itemPrice, @FormParam("itemDesc") String itemDesc) {
		String output = itemObj.insertMedicine(itemCode, itemName, itemPrice, itemDesc);
		return output;
	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) { // eka ohoma tibbata awlk na nwh ow shape
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		// Read the values from the JSON object
		String itemID = itemObject.get("itemID").getAsString();
		String itemCode = itemObject.get("itemCode").getAsString();
		String itemName = itemObject.get("itemName").getAsString();
		String itemPrice = itemObject.get("itemPrice").getAsString();
		String itemDesc = itemObject.get("itemDesc").getAsString();
		System.out.println(itemID);
		String output = itemObj.updateMedicine(itemID, itemCode, itemName, itemPrice, itemDesc);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteMedicine(String itemData) {

		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		// Read the value from the element <itemID>

		String itemID = doc.select("itemID").text();
		String output = itemObj.deleteMedicine(itemID);
		return output;
	}
}
