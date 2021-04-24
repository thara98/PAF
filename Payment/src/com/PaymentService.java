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
@Path("/Payments")

public class PaymentService {
	
	Payment paymentObj = new Payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readPayments()
	 {
		return paymentObj.readPayments();	
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("paymentCardNo") String paymentCardNo,
	 @FormParam("paymentCvv") String paymentCvv,
	 @FormParam("paymentExpireDate") String paymentExpireDate,
	 @FormParam("paymentCardholderName") String paymentCardholderName)
	{
	 String output = paymentObj.insertPayment(paymentCardNo, paymentCvv, paymentExpireDate, paymentCardholderName);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	 String paymentID = paymentObject.get("paymentID").getAsString();
	 String paymentCardNo = paymentObject.get("paymentCardNo").getAsString();
	 String paymentCvv = paymentObject.get("paymentCvv").getAsString();
	 String paymentExpireDate = paymentObject.get("paymentExpireDate").getAsString();
	 String paymentCardholderName = paymentObject.get("paymentCardholderName").getAsString();
	 String output = paymentObj.updatePayment(paymentID, paymentCardNo, paymentCvv, paymentExpireDate, paymentCardholderName);
	return output;
	}
	
	
	
}

