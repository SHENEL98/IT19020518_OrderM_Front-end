package com;

//IT19020518

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Order {
	
	//databse method definition
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 
			con = DriverManager.getConnection("jdbc:mysql://localhost/gb_order?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertOrder(String buyerID, String productID, String price,String orderDate) {
		String output = "";

		 
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				
	 
				//Entered Data 
				String query = " INSERT INTO orders(`buyerID`, `productID`, `price`,`orderDate`) VALUES (?, ?, ,?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
					 
				preparedStmt.setString(2,buyerID);
				preparedStmt.setString(3,productID);
				preparedStmt.setString(4,price);
				preparedStmt.setString(5,orderDate);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
				 
			} catch (Exception e) {
				output = "Error while inserting the Orders.";
				System.err.println(e.getMessage());
			}
			
		
		return output ; 
		}
			
		
	
	public String readOrders() {
		String output3 = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			//Create Table Header
			output3 = "<table border='1'><tr><th>Order ID</th>"
					+ "<th>Buyer ID</th>"
					+ "<th>Product ID</th>"
					+ "<th>Price</th>" 
					+"<th>Ordered Date</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";

			String query = "select * from orders";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String orderID = Integer.toString(rs.getInt("orderID"));
				String buyerID = rs.getString("buyerID");
				String productID = rs.getString("productID");
				String price = Double.toString(rs.getDouble("price"));
				String orderDate = rs.getString("orderDate");
				

				// Add into the html table
				output3 += "<tr><td>" + orderID + "</td>";
				output3 += "<td>" + buyerID + "</td>";
				output3 += "<td>" + productID + "</td>";
				output3 += "<td>" + price + "</td>";
				output3 += "<td>" + orderDate + "</td>";
				output3 += "<th>Update</th><th>Remove</th></tr>";
				
				//btn
				output3 +="<td><input name='btnUpdate' type='button' value='Update' "
				+ "class='btnUpdate btn btn-secondary' data-itemid='" + orderID + "'></td>";
				output3 += "<td><input name='btnRemove' type='button' value='Remove'" 
						+" class='btnRemove btn btn-danger' data-itemid='" + orderID + "'>" + 
				"</td>";

 
			}
			con.close();

			// Display HTML table
			output3 += "</table>";
			
		} catch (Exception e) {
			output3 = "Error while reading the orders.";
			System.err.println(e.getMessage());
		}
		return output3;
	}
	


	
	//Update function
	public String updateOrderPrice(String orderID, String price) {
		String output5 = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String upd = "UPDATE orders SET price=? WHERE orderID=?";
			PreparedStatement preparedStmt = con.prepareStatement(upd);
			// binding values
//			preparedStmt.setString(1, price);
			preparedStmt.setDouble(1, Double.parseDouble(price)); 
			preparedStmt.setString(2, orderID);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output5 = "Price : Updated successfully";
		} catch (Exception e) {
			output5 = "Error while updating the order.";
			System.err.println(e.getMessage());
		}
		return output5;
	}
	
	//Delete Function
	public String deleteOrders(String ID) {
		String output6 = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			//query for delete 
			String del = "delete from orders where orderID=?";
			PreparedStatement preparedStmt = con.prepareStatement(del);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output6 = "Deleted Successfully";
		} catch (Exception e) {
			output6 = "Error while deleting the order.";
			System.err.println(e.getMessage());
		}
		return output6;
	}

}
