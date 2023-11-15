
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/Deskadmin/deskadmin.js"></script>


<style>

/* The Modal (background) */rrrgr
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

.mar {
	margin: 18px;
}

.len {
	right: 1232px;
}

.rej {
	background-color: #f24636;
	color: white;
}

.rej:hover {
	color: maroon;
	text-decoration: none;
	cursor: pointer;
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.panel-head {
	text-align: center;
}
</style>
<script src="js/Responsive/bootstrap.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
	getAssemblyList()
	var msg="${msg}";
	if(msg){
		alert(msg);
	}
	
	});
	
	
	function getUserNames(){
		
		var userType=document.getElementById("type").value;
		var options="";
		$.getJSON("getUserNames",{userType:userType},function(j) {
			
			if(j.length < 0){
				alert("No UserNames Allocated to "+userType+" Type");
				return false;
			}
			options = '<option value="' + "--Select--" + '">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length; i++) {
					options += '<option value="' + j[i]+ '" name="' + j[i]+ '">'+ j[i] + '</option>';
			}				
			$("select#userNames").html(options);	
			
		});
	}
	
	function getAssemblyList(){
		var options="";
		$.getJSON("getAssemblyList",function(j) {
			
			options = '<option value="' + "--Select--" + '">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length; i++) {
					options += '<option value="' + j[i]+ '" name="' + j[i]+ '">'+ j[i] + '</option>';
			}				
			$("select#assembly").html(options);	
			
		});
	}
	 var fields=0;
	  function addColony()
	  {	
		 var a="";
		fields++;
		var userType=document.getElementById("assembly").value;
	  	 document.getElementById("fields").value=fields;
	  	$("table#colonyDetails").append(
	      		'<tr id="'+fields+'"><td>Assembly-'+fields+'</td>'	
	      		+'<td><input class="form-control"  id="deficiency_detail'+fields+'" name="assembly'+fields+'" value="'+userType+'" /></td>' 
	      		+'</tr>');

	  	///////+'<td><input type="text" name="def'+fields+'" id="def'+fields+'" style="width:60%" /></td>' 
	  }
	
</script>

</head>

<body id="page-top" class="index">
	<form:form modelAttribute="colonyAssignment"
		name="createUser" id="createUser" action="assignAssembly"
		method='POST'>
		 <input type="hidden" id="fields"  name="fields_count" value="1">
		<div id="print_window" class="mar">
			<div class="form-head mar">
				<h5>colony assignment</h5>
			</div>
			<hr>
			<div class="row form-group mar">
				<div class="col col-md-6">
				<div class="col col-md-4">
                        <label for="text-input" class=" form-control-label">User Type :<span style="color: red;"> *</span></label>
                      </div>
                    <div class="col-8 col-md-6">
						<select id="type" name="type" class="form-control" onchange="getUserNames();">
							<option value="--Select--">--Select--</option>
							<option value="ASO">ASO</option>
							<option value="SURVEY">SURVEY</option>
							<option value="AD">AD</option>
							<option value="DD">DD</option>
							<option value="DIRECTOR">DIRECTOR</option>
						</select>
					</div>	
					</div>
				<div class="col col-md-2">
                        <label for="text-input" class=" form-control-label">UserName :<span style="color: red;"> *</span></label>
                      </div>
                    <div class="col-12 col-md-3">
						 <select   class="form-control" id="userNames" name="username">
                        </select>
					</div>
               </div>
			<div class="row form-group mar">
				<div class="col col-md-11">
				<div class="col col-md-2">
                        <label for="text-input" class=" form-control-label">ASSEMBLY :<span style="color: red;"> *</span></label>
                      </div>
                    <div class="col-8 col-md-8">
						<select id="assembly"  class="form-control">
							
						</select>
					</div>	
					<a href="#" class="btn btn-success" onclick="addColony()">Add Assembly</a>
  			</div>
               </div>
                      
                        <table border="1" id="colonyDetails" class="table table-striped">
 						<tr>
						<!--  <td>Deficiency-1</td> -->
						<!-- <td>Deficiency-1</td> -->
						<!--  <td><input class="form-control"  id="deficiency_detail1" name="deficiency_detail1" /></td> -->
						<!--   <td><input name="submit" type="submit" value="Submit"  class="btn btn-info" style="font-size: 15px;width: 150px;"/>&nbsp;</td> -->
						</tr>
 						</table> 
				<div class="col-md-12">
					<div class="well well-sm well-primary" align="center">
						<div class="actions-box">

							<input id="saveData" class="btn btn-primary btn-sm"
								value="Create User" type="submit"
								onclick="return validateForm();">
						</div>
					</div>
				</div>
			</div>

	</form:form>
</body>
</html>
