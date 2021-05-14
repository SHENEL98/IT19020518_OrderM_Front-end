$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	
	// If valid------------------------
	var type = ($("orderID").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "OrderAPI",
	type : type,
	data : $("#formOrder").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onItemSaveComplete(response.responseText, status);
	}
	});
});


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#orderID").val($(this).data("itemid"));
	$("#buyerID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#productID").val($(this).closest("tr").find('td:eq(1)').text());
	$("#price").val($(this).closest("tr").find('td:eq(2)').text());
	$("#orderDate").val($(this).closest("tr").find('td:eq(3)').text());
});

//UPDATE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "OrderAPI",
		type : "DELETE",
		data : "orderID=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status)
		{
			onIteleteComplete(response.responseText, status);
		}
	});
});


//CLIENT-MODEL================================================================
function validateItemForm()
{

		if ($("#buyerID").val().trim() == "")
		{
			return "Insert Buyer ID.";
		}
		
		
		if ($("#productID").val().trim() == "")
		{
			return "Insert Product ID.";
		}
		
		
		if ($("#price").val().trim() == "")
		{
			return "Insert Price.";
		}
		if ($("#orderDate").val().trim() == "")
		{
			return "Insert Date.";
		}
		
}



function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();	
			$("#divItemsGrid").html(resultSet.data);
			
			 Swal.fire({
                 position: 'center',
                 icon: 'success',
                 title: 'Saved !',
                 showConfirmButton: false,
                 timer: 1500
                 });
			
			
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
	}
		$("#orderID").val("");
		$("#formOrder")[0].reset();
}



function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Removed.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
			
			 Swal.fire({
                 position: 'center',
                 icon: 'success',
                 title: 'Removed',
                 showConfirmButton: false,
                 timer: 1500
                 }); 
			 
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while removing.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while removing..");
			$("#alertError").show();
	}
}

function isInputNumber(evt) {
    var ch = String.fromCharCode(evt.which);

      if(!(/[0-9]/.test(ch))){
          evt.preventDefault();
      }
    
  }



