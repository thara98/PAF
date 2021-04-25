package model;

import java.sql.*;

public class Product {
	
	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/gbdb", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 }
	
	public String insertProduct(String code, String name, String price, String desc) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into product(`productId`,`productCode`,`productName`,`productPrice`,`productDescription`) values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, code); 
	 preparedStmt.setString(3, name); 
	 preparedStmt.setDouble(4, Double.parseDouble(price)); 
	 preparedStmt.setString(5, desc); 
	// execute the statement
	 
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the product."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String readProduct() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Product Code</th><th>Project Name</th>" +
	 "<th>Product Price</th>" + 
	 "<th>Product Description</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from product"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String productId = Integer.toString(rs.getInt("productId")); 
	 String productCode = rs.getString("productCode"); 
	 String productName = rs.getString("productName"); 
	 String productPrice = Double.toString(rs.getDouble("productPrice")); 
	 String productDescription = rs.getString("productDescription"); 
	 // Add into the html table
	 output += "<tr><td>" + productCode + "</td>"; 
	 output += "<td>" + productName + "</td>"; 
	 output += "<td>" + productPrice + "</td>"; 
	 output += "<td>" + productDescription + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='productId' type='hidden' value='" + productId 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the product."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String updateProduct(String ID, String code, String name, String price, String desc)
	
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 
		 // create a prepared statement
		 String query = "UPDATE product SET productCode=?,productName=?,productPrice=?,productDescription=? WHERE productId=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setString(1, code); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setDouble(3, Double.parseDouble(price)); 
		 preparedStmt.setString(4, desc); 
		 preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the product."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	
		public String deleteProduct(String productId) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 
		 // create a prepared statement
		 String query = "delete from product where productId=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(productId)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the product."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
}

