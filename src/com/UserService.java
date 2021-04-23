package com;
import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Users")
public class UserService
{
 User userObj = new User();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readUsers()
 {
	return userObj.readUsers();
 }

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertUser(@FormParam("name") String name,
@FormParam("email") String email,
@FormParam("phone") String phone,
@FormParam("uname") String uname,
@FormParam("password") String password,
@FormParam("utype") String utype)
{
String output = userObj.insertUser(name, email, phone, uname, password, utype);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateFund(String userData)
{
//Convert the input string to a JSON object
 JsonObject fundObject = new JsonParser().parse(userData).getAsJsonObject();
//Read the values from the JSON object
 String userid = fundObject.get("userid").getAsString();
 String name = fundObject.get("name").getAsString();
 String email = fundObject.get("email").getAsString();
 String phone = fundObject.get("phone").getAsString();
 String uname = fundObject.get("uname").getAsString();
 String password = fundObject.get("password").getAsString();
 String utype = fundObject.get("utype").getAsString();
 String output = userObj.updateUser(userid, name, email, phone, uname, password, utype);
	return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteFund(String userData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String userid = doc.select("userid").text();
 String output = userObj.deleteUser(userid);
return output;
}

}
