package com;
import model.Product; 
import java.sql.Date;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Product")

public class ProductService {

	Product productObj = new Product(); 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProduct()
	{
		return productObj.readProduct();
	}
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct(@FormParam("productCode") String productCode,@FormParam("productName") String productName,@FormParam("productPrice") String productPrice, @FormParam("productDescription") String productDescription) 
	{ 
		String output = productObj.insertProduct(productCode, productName, productPrice, productDescription); 
		return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProduct(String ProductData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject productObject = new JsonParser().parse(ProductData).getAsJsonObject(); 
		 
		//Read the values from the JSON object
		String productId = productObject.get("productId").getAsString(); 
		String productCode = productObject.get("productCode").getAsString(); 
		String productName = productObject.get("productName").getAsString(); 
		String productPrice = productObject.get("productPrice").getAsString(); 
		String productDescription = productObject.get("productDescription").getAsString(); 
		 
		String output = productObj.updateProduct(productId, productCode, productName, productPrice, productDescription); 
		 
		
		return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProduct(String ProductData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(ProductData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <projectId>
		String productId = doc.select("productId").text(); 
		String output = productObj.deleteProduct(productId); 
		return output; 
	}

	
}
