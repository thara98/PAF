package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	//A common method to connect to the DB
			private Connection connect()
			 {
			 Connection con = null;
			 try
			 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paymentManagement", "root", "");
			 }
			 catch (Exception e)
			 {e.printStackTrace();}
			 return con;
			 }
			public String insertPayment(String cardNo, String cvv , String expireDate , String cardholderName)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 // create a prepared statement
			 String query = " insert into payments(`paymentID`,`paymentCardNo`,`paymentCvv`,`paymentExpireDate`,`paymentCardholderName`)"
			 + " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, cardNo);
			 preparedStmt.setString(3, cvv);
			 preparedStmt.setString(4, expireDate);
			 preparedStmt.setString(5, cardholderName);
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while inserting the item.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			public String readPayments()
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Payment CardNo</th><th>Payment Cvv </th>" +
			 "<th>Payment Expire Date</th>" +
			 "<th>Payment Card holder Name</th>" +
			 "<th>Update</th><th>Remove</th></tr>";

			 String query = "select * from payments";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String paymentID = Integer.toString(rs.getInt("paymentID"));
			 String paymentCardNo = rs.getString("paymentCardNo");
			 String paymentCvv = rs.getString("paymentCvv");
			 String paymentExpireDate = Double.toString(rs.getDouble("paymentExpireDate"));
			 String paymentCardholderName = rs.getString("paymentCardholderName");
			 // Add into the html table
			 output += "<tr><td>" + paymentCardNo + "</td>";
			 output += "<td>" + paymentCvv + "</td>";
			 output += "<td>" + paymentExpireDate + "</td>";
			 output += "<td>" + paymentCardholderName + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='paymentID' type='hidden' value='" + paymentID+ "'>" + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
			 }
			 catch (Exception e)
			 {
			 output = "Error while reading the payments.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			public String updatePayment(String ID, String cardNo, String cvv, String expireDate, String cardholderName)
			{
				 String output = "";
				 try
				 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for updating."; }
				 // create a prepared statement
				 String query = "UPDATE payments SET paymentCardNo=?,paymentCvv=?,paymentExpireDate=?,paymentCardholderName=? WHERE paymentID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setString(1, cardNo);
				 preparedStmt.setString(2, cvv);
				 preparedStmt.setString(3, expireDate);
				 preparedStmt.setString(4, cardholderName);
				 preparedStmt.setInt(5, Integer.parseInt(ID));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Updated successfully";
				 }
				 catch (Exception e)
				 {
				 output = "Error while updating the payment.";
				 System.err.println(e.getMessage());
				 }
				 return output;
				 }
				
			public String deletePayment(String paymentID)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from payments where paymentID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(paymentID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the payment.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 } 
	}



