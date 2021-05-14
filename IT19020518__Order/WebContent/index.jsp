 <%@page import = "com.Order" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/order.js"></script>
</head>

	<style>
		input[type=text], select {
		  width: 100%;
		  padding: 12px 20px;
		  margin: 8px 0;
		  display: inline-block;
		  border: 1px solid #ccc;
		  border-radius: 4px;
		  box-sizing: border-box;
		}
		input[type=date], select {
		  width: 100%;
		  padding: 12px 20px;
		  margin: 8px 0;
		  display: inline-block;
		  border: 1px solid #ccc;
		  border-radius: 4px;
		  box-sizing: border-box;
		}
		
		input[type=button] {
		  width: 100%;
		  background-color: #4CAF50;
		  color: black;
		  padding: 14px 18px;
		  margin: 8px 0;
		  border: none;
		  border-radius: 4px;
		  cursor: pointer;
		}
		
		input[type=submit]:hover {
		  background-color: #45a089;
		}
		
		div {
		  border-radius: 5px;
		  background-color: #f2f2f2;
		  padding: 18px;
		}
	</style>
<body>
		
		 <div>
			 	<center>
				 <h3>Order Details</h3> 
				</center>
				 <form id="formOrder"> 
				 
				 <div class="container">
 					 <div class="row">
				 	  <div class="col-sm-6">
						<label for="bid">BuyerID</label>
						<input type="text" id="buyerID" name="buyerID"  placeholder="Buyer ID" required >
					 </div>
					  <div class="col-sm-6">
						<label for="pid">ProductID</label>
						<input type="text" id="productID" name="productID" placeholder="Product ID" required>
				 	   </div>
				 	   <br>
				 	  <div class="col-sm-6">
				 		<label for="price">Price</label>
						<input type="text" id="price" name="price" placeholder="Price" onkeypress="isInputNumber(event)" required>
					 </div>
					 <div class="col-sm-6">
					 	<label for="odate">Order Date </label>
						 <input type="date" id="orderDate" name="orderDate">
					 </div>
				 	
				 	<div id="alertSuccess" class="alert alert-success"></div>
 					<div id="alertError" class="alert alert-danger"></div>
				 	
				 	<input id="btnSave" name="btnSave" type="button" value="Save">
					<input type="hidden" id="orderID"name="orderID" value="">
				 
				 	</div>
				</div>
				 </form>
			 
		</div>
				 
				 <br> 
		<div class="row">
			 <div class="col-12" id="colOrders">
			 
			 	<div id="divItemsGrid">
						 
						 <%
						 Order ordProduct = new Order(); 
						 out.print(ordProduct.readOrders()); 
						 %>
									 	
			 	</div>
			 </div>
		 
		</div>
	
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	




</body>
</html>