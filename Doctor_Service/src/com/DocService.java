package com;

import model.Doc;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doc")
public class DocService {
	
	Doc Obj = new Doc();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return Obj.readItems();
	}
	
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("DocName") String DocName,
	@FormParam("address") String address,
	@FormParam("phoneNo") String phoneNo,
	@FormParam("specialist") String specialist)
	{
	String output = Obj.insertItem(DocName, address, phoneNo, specialist);
	return output;
	}
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	String DocID = itemObject.get("Doc").getAsString();
	String DocName = itemObject.get("DocName").getAsString();
	String address = itemObject.get("address").getAsString();
	String phoneNo = itemObject.get("phoneNo").getAsString();
	String specialist = itemObject.get("specialist").getAsString();
	
	String output = Obj.updateItem(DocID, DocName, address, phoneNo, specialist);
	return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	//Read the value from the element <DocID>
	String DocID = doc.select("DocID").text();
	String output = Obj.deleteItem(DocID);
	return output;
	}
	
}



