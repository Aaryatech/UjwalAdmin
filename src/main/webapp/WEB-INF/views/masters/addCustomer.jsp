<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="">										
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Ujjwal Billing Software</title>
<c:url var="getUniqueCompanyCheck" value="/getUniqueCompanyCheck" />
<c:url var="getUniqueModelNo" value="/getUniqueModelNo" />
<meta name="description" content="Sufee Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="apple-icon.png">
<link rel="shortcut icon" href="favicon.ico">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/normalize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/flag-icon.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/cs-skin-elastic.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/lib/chosen/chosen.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/scss/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/lib/chosen/chosen.min.css">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/lib/datatable/dataTables.bootstrap.min.css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'>

<style type="text/css">
.right {
	text-align: right;
}

.left {
	text-align: left;
}

<style>
div.scrollmenu {
  background-color: #333;
  overflow: auto;
  white-space: nowrap;
}

</style>
</head>
<body>


	<!-- Left Panel -->
	<jsp:include page="/WEB-INF/views/common/left.jsp"></jsp:include>
	<!-- Left Panel -->


	<!-- Header-->
	<jsp:include page="/WEB-INF/views/common/right.jsp"></jsp:include>
	<!-- Header-->



	<div class="content mt-3">
		<div class="animated fadeIn">

			<div class="row">
				<c:choose>
					<c:when test="${isError==1}">
						<div class="col-sm-12">
							<div
								class="sufee-alert alert with-close alert-danger alert-dismissible fade show">

								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<strong>Data not Submitted</strong>
							</div>
						</div>
					</c:when>

					<c:when test="${isError==2}">
						<div class="col-sm-12">
							<div
								class="sufee-alert alert with-close alert-success alert-dismissible fade show">

								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<strong>Data Submitted Successfully</strong>
							</div>
						</div>
					</c:when>

				</c:choose>

				<div class="col-xs-12 col-sm-12">
					<div class="card">
						<div class="card-header">
							<div class="col-md-2">
								<strong>${title}</strong>
							</div>
							<div class="col-md-8"></div>
							<div class="col-md-2" align="left">
								<a href="${pageContext.request.contextPath}/showCustList"><strong>Customers List</strong></a>
							</div>

						</div>
						<div class="card-body card-block">
							<form action="${pageContext.request.contextPath}/insertCustomer"
								id="submitForm" method="post">
								<input type="hidden" name="cust_id" id="cust_id"
									value="${cust.custId}">

								<div class="row">

								<div class="col-md-2">Company Name*</div>
									<div class="col-md-4">
									 <select name="compId" id="compId" class="standardSelect" tabindex="6" required
									 oninvalid="setCustomValidity('Please select company')" onchange="getCompId()"> 
											<option value="">Select Company</option>
											<c:forEach items="${compList}" var="makeList"> 
											<c:choose>
											<c:when test="${makeList.compId == cust.compId}">
											<option value="${makeList.compId}" selected="selected">${makeList.compName}</option>
											</c:when>
											<c:otherwise><option value="${makeList.compId}">${makeList.compName}</option></c:otherwise>
											</c:choose>
											 </c:forEach>
										</select>
									</div>
								

									
									<div class="col-md-2">Model Name*</div>
									<div class="col-md-4">
										
											<select name="cust_model_no" id="cust_model_no" class="standardSelect" tabindex="6" required>
											<option value="">Select Model</option>
											<c:forEach items="${custList}" var="modelList">
											<c:choose>
											<c:when test="${modelList.custModelNo == cust.custModelNo }">
												<option value="${modb.modelId}" selected>${modb.modelName}</option>
											</c:when>
											<c:otherwise>
												<option value="${modb.modelId}">${modb.modelName}</option>
											</c:otherwise>
											</c:choose>
											 </c:forEach>
										</select>  <span
											class="error" aria-live="polite"></span>
									</div>	
								
									
																			
								</div>
								<div class="form-group"></div>
								<div class="row">

								 <div class="col-md-2">Customer Name*</div>
									<div class="col-md-4">
										<input type="text" id="cust_name" name="cust_name"
											oninvalid="setCustomValidity('Please enter correct customer name')"
											onchange="try{setCustomValidity('')}catch(e){}"
											pattern="^[A-Za-z\s]+$" value="${cust.custName}"
											style="width: 100%;" autocomplete="off" class="form-control"
											required>
									<span id="cName"></span>
									</div> 

								


									
									<div class="col-md-2">Mobile No*</div>
									<div class="col-md-4">
										<input type="text" id="cust_phone" name="cust_phone"
											style="width: 100%;" class="form-control"
											value="${cust.custPhone}" autocomplete="off"
											oninvalid="setCustomValidity('Please enter correct mob no')"
											pattern="^[1-9]{1}[0-9]{9}$" maxlength="10"
											onchange="try{setCustomValidity('')}catch(e){}" required />
										<span class="error" aria-live="polite"></span>

									</div>
								</div>
							
							<div class="form-group"></div>
								<div class="row">

									
									<div class="col-md-2">Vehicle No.*</div>
									<div class="col-md-4">
										<input type="text" id="cust_veh_no" name="cust_veh_no" required
										onkeyup="this.value = this.value.toUpperCase();"
											style="width: 100%;" class="form-control" autocomplete="off"
										maxlength="20"
										value="${cust.custVehNo}"
										/> <span
											class="error" aria-live="polite"></span>					

									</div>
									
									<div class="col-md-2">Customer Address*</div>

									<div class="col-md-4">
										<textarea id="cust_address" name="cust_address" class="form-control"
											style="width: 100%;" autocomplete="off"
											oninvalid="setCustomValidity('Please enter customer address')"
											maxlength="200"
											onchange="try{setCustomValidity('')}catch(e){}" required>${cust.custAddress}</textarea>
									</div> 
								</div>

								<div class="form-group"></div>
								<div class="row">


									<div class="col-md-2">State*</div>

									<div class="col-md-4">
										<input type="text" id="cust_state" name="cust_state"  class="form-control"
											oninvalid="setCustomValidity('Please enter correct customer state')"
											onchange="try{setCustomValidity('')}catch(e){}"
											pattern="^[A-Za-z\s]+$" value="${custState}"
											style="width: 100%;" autocomplete="off"
											>

									</div>	
									
									<div class="col-md-2">Email Id*</div>
									<div class="col-md-4">
										<input type="text" id="cust_email" name="cust_email" 
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please enter email')"
											pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
											maxlength="50" value="${cust.custEmail}"
											onchange="try{setCustomValidity('')}catch(e){}" /> 
									</div>
								</div>
								
								
								<div class="form-group"></div>
								<div class="row">
									<div class="col-md-2">GST No.*</div>
							
									<div class="col-md-4">
										<input type="text" id="cust_gstn" name="cust_gstn"
											onblur="getCheck()" style="width: 100%;" class="form-control"
											autocomplete="off"
											oninvalid="setCustomValidity('Please enter GST no')"
											maxlength="20" value="${cust.custGstn}"
											pattern="\d{2}[A-Z]{5}\d{4}[A-Z]{1}\d[Z]{1}[A-Z\d]{1}"
											onkeydown="upperCaseF(this)"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>
									
										<div class="col-md-2">VIN No.*</div>
									<div class="col-md-4">
										<input type="text" id="cust_vin_no" name="cust_vin_no" 
											style="width: 100%;" class="form-control" maxlength="17"
											value="${cust.custVinNo}" autocomplete="off"
											oninvalid="setCustomValidity('Please enter chasi no')"
											onchange="try{setCustomValidity('')}catch(e){}" /> 
											<span class="error"	aria-live="polite" id="chassis"></span>

									</div>
								
							<%--	<div class="col-md-2">PAN No.*</div>
									<div class="col-md-4">
										<input type="text" id="cust_pan" name="cust_pan"
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please enter PAN no')"
											maxlength="10" value="${cust.custPan}"
											pattern="[A-Za-z]{5}\d{4}[A-Za-z]{1}"
											onkeydown="upperCaseF(this)"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>	 --%>			
								</div> 
								
								<div class="form-group"></div>

								

								<div class="form-group"></div>

								<div class="row">

									
														
									
										
									<span></span>


								</div>

								
								<div class="form-group"></div>
								<div class="col-lg-4"></div>
								<div class="col-lg-3">
									<input type="submit" class="btn btn-primary" value="Submit"
										id="submitButton"
										style="align-content: center; width: 113px; margin-left: 40px; background-color: #272c33;">

								</div>
								<div class="col-lg-3">
									<input type="reset" class="btn btn-primary" value="Clear"
										style="align-content: center; width: 113px; margin-left: 40px; background-color: #272c33;">

								</div>
								
							</form>
							
						</div>
						
						
						
					</div>
				</div>
			</div>


		</div>
		<!-- .animated -->
	</div>
	<!-- .content -->

	<!-- .animated -->
	<!-- .content -->


	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<!-- Footer -->

	<script
		src="${pageContext.request.contextPath}/resources/assets/js/vendor/jquery-2.1.4.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/plugins.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>


	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/datatables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/dataTables.buttons.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/buttons.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/jszip.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/pdfmake.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/vfs_fonts.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/buttons.html5.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/buttons.print.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/buttons.colVis.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/datatables-init.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/chosen/chosen.jquery.min.js"></script>
	<script>
	$(function () {
        $("#submitButton").click(function () {
            var ddlFruits = $("#compId");
            if (ddlFruits.val() == "") {
                //If the "Please Select" option is selected display error.
                alert("Please select an company!");
                return false;
            }
            return true;
        });
    });
	</script>
	
	<script>
	$(function () {
        $("#submitButton").click(function () {
            var compId = $("#compId");
            if (compId.val() == "") {
                //If the "Please Select" option is selected display error.
                alert("Please select an company!");
                return false;
            }
            return true;
        });
    });
	</script>
	
	<script>
	$(function () {
        $("#submitButton").click(function () {
            var cust_model_no = $("#cust_model_no");
            if (cust_model_no.val() == "") {
                //If the "Please Select" option is selected display error.
                alert("Please select an model!");
                return false;
            }
            return true;
        });
    });
	</script>

<script type="text/javascript">
	var minLength = 7;
	var maxLength = 50;
	$(document).ready(function(){
	    $('#cust_name').blur(function(){
	        var field = $(this).val();
	        var charLength = $(this).val().length;
	        if(charLength < minLength){
	            $('#cName').text('Invalid Customer Name');
	        }else if(charLength > maxLength){
	            $('#cName').text('Invalid Customer Name.');
	            $(this).val(field.substring(0, maxLength));
	        }else{
	            $('#cName').text('');
	        }
	    });
	});	
	</script>

<script type="text/javascript">
	var minLength = 10;
	var maxLength = 50;
	$(document).ready(function(){
	    $('#cust_regis_no').blur(function(){
	        var field = $(this).val();
	        var charLength = $(this).val().length;
	        if(charLength < minLength){
	            $('#reg').text('Registration Number is short, minimum '+minLength+' required.');
	        }else if(charLength > maxLength){
	            $('#reg').text('Registration Number is not valid, maximum '+maxLength+' allowed.');
	            $(this).val(field.substring(0, maxLength));
	        }else{
	            $('#reg').text('');
	        }
	    });
	});	
	</script>

<script type="text/javascript">
$(document).ready(function(){
   $('#submitButton').submit(function(){
        if ($('#cust_chasi_no').val() == ""){
            alert('Enter Chassis Number');
        }
    });
});
</script>

<script type="text/javascript">
	var minLength = 10;
	var maxLength = 50;
	$(document).ready(function(){
	    $('#cust_chasi_no').blur(function(){
	        var field = $(this).val();
	        var charLength = $(this).val().length;
	        if(charLength < minLength){
	            $('#chassis').text('Chassis Number is short, minimum '+minLength+' required.');
	        }else if(charLength > maxLength){
	            $('#chassis').text('Chassis Number is not valid, maximum '+maxLength+' allowed.');
	            $(this).val(field.substring(0, maxLength));
	        }else{
	            $('#chassis').text('');
	        }
	    });
	});	
	</script>




	<script>
		jQuery(document).ready(function() {
			jQuery(".standardSelect").chosen({
				disable_search_threshold : 2,
				no_results_text : "Oops, nothing found!",
				width : "100%"
			});
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#bootstrap-data-table-export').DataTable();
		});
	</script>



	<script>
		function upperCaseF(a) {
			setTimeout(function() {
				a.value = a.value.toUpperCase();
			}, 1);
		}
	</script>


	<script type="text/javascript">
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');
						return true;
					});
		});
	</script>
	
	
 <script type="text/javascript">
			function getCompId() { 
		
			var companyId = document.getElementById("compId").value;
			var valid = true;
			if (compId == null || compId == "") {
				valid = false;
				alert("Please select Model Name");
			}

			if (valid == true) {

				$.getJSON('${getUniqueModelNo}', {
					companyId : companyId,
					ajax : 'true',
				},

				function(data) {
					
					var len = data.length;
					//alert("data");
					var html='<option value="">Select Model</option>';

					for (var i = 0; i < len; i++) {

						html += '<option value="' + data[i].modelId + '">'
								+data[i].modelName+ '</option>';

					}
					html += '</option>';
					$('#cust_model_no').html(html);
					$("#cust_model_no").trigger("chosen:updated");
				
				});
			}//end of if

		}
	</script>
	<script type="text/javascript">
		function getCheck() {

			var gstNo = $("#gst_no").val();
			var comp_id = document.getElementById("comp_id").value;

			$
					.getJSON(
							'${getUniqueCompanyCheck}',
							{

								gstNo : gstNo,
								comp_id : comp_id,

								ajax : 'true',

							},
							function(data) {

								if (comp_id == 0) {
									if (data.error == true) {
										alert("Company Already Exist");

										document.getElementById("gst_no").value = "";

										document.getElementById("submitButton").disabled = true;
									} else {
										document.getElementById("submitButton").disabled = false;

									}
								}
							}

					);

		}
	</script>
</body>
</html>

