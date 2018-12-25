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
									
										<div class="col-md-2">Company Name*</div>
									<div class="col-md-4">
											<select name="compId" id="compId" class="form-control chosen" tabindex="6" required>
											<option value="">Select Company</option>
											<c:forEach items="${compList}" var="makeList"> 
												<option value="${makeList.compId}"><c:out value="${makeList.compName}"></c:out> </option>
											 </c:forEach>
										</select> 
									</div>
									
								</div>
								<div class="form-group"></div>
								<div class="row">



									<div class="col-md-2">Customer Address*</div>

									<div class="col-md-4">
										<textarea id="cust_address" name="cust_address" class="form-control"
											style="width: 100%;" autocomplete="off"
											oninvalid="setCustomValidity('Please enter customer address')"
											maxlength="200"
											onchange="try{setCustomValidity('')}catch(e){}" required>${cust.custAddress}</textarea>
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


									<div class="col-md-2">State*</div>

									<div class="col-md-4">
										<input type="text" id="cust_state" name="cust_state"  class="form-control"
											oninvalid="setCustomValidity('Please enter correct customer state')"
											onchange="try{setCustomValidity('')}catch(e){}"
											pattern="^[A-Za-z\s]+$" value="${cust.custState}"
											style="width: 100%;" autocomplete="off"
											required>

									</div>	
									
									<div class="col-md-2">Email Id*</div>
									<div class="col-md-4">
										<input type="text" id="cust_email" name="cust_email" required
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
										<input type="text" id="cust_gstn" name="cust_gstn" required
											onblur="getCheck()" style="width: 100%;" class="form-control"
											autocomplete="off"
											oninvalid="setCustomValidity('Please enter GST no')"
											maxlength="20" value="${cust.custGstn}"
											pattern="\d{2}[A-Z]{5}\d{4}[A-Z]{1}\d[Z]{1}[A-Z\d]{1}"
											onkeydown="upperCaseF(this)"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>
								<div class="col-md-2">Registration No.*</div>
									<div class="col-md-4">
										<input type="text" id="cust_regis_no" name="cust_regis_no" required
											style="width: 100%;" class="form-control"
											value="${cust.custRegisNo}" autocomplete="off"/> 
											<span class="error" aria-live="polite" id="reg"></span>
									</div>
																	
								</div>
								
								<div class="form-group"></div>

								<div class="row">
									<div class="col-md-2">PAN No.*</div>
									<div class="col-md-4">
										<input type="text" id="cust_pan" name="cust_pan" required
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please enter PAN no')"
											maxlength="10" value="${cust.custPan}"
											pattern="[A-Za-z]{5}\d{4}[A-Za-z]{1}"
											onkeydown="upperCaseF(this)"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>

									<div class="col-md-2">Vehicle No.*</div>
									<div class="col-md-4">
										<input type="text" id="cust_veh_no" name="cust_veh_no" required
											style="width: 100%;" class="form-control" autocomplete="off"
										maxlength="20"
										value="${cust.custVehNo}"
										/> <span
											class="error" aria-live="polite"></span>

									</div>
								</div>

								<div class="form-group"></div>

								<div class="row">

									
									<div class="col-md-2">Ro No.*</div>
									<div class="col-md-4">
										<input type="text" id="cust_ro_no" name="cust_ro_no" required
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please enter Ro No')"
											value="${cust.custRoNo}" maxlength="30"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>							
									
											<div class="col-md-2">Chassis No.*</div>
									<div class="col-md-4">
										<input type="text" id="cust_chasi_no" name="cust_chasi_no" 
											style="width: 100%;" class="form-control" maxlength="17"
											value="${cust.custChasiNo}" autocomplete="off"
											oninvalid="setCustomValidity('Please enter chasi no')"
											onchange="try{setCustomValidity('')}catch(e){}" required/> 
											<span class="error"	aria-live="polite" id="chassis"></span>

									</div>
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
						
						<%-- <div class="card-body card-block">
							<form
								action="${pageContext.request.contextPath}/deleteRecordofCustomer"
								method="post">


								<table id="bootstrap-data-table"
									class="table table-striped table-bordered">
									<thead>
										<tr>
											<th class="check" style="text-align: center; width: 5%;"><input
												type="checkbox" name="selAll" id="selAll" /> Select All</th>
											<th style="text-align: center; width: 5%;">Sr No</th>
											<th style="text-align: center">Customer Name</th>
											<th style="text-align: center">Customer Address</th>
											<th style="text-align: center">Mobile. No.</th>
											<th style="text-align: center">State</th> 
											<th style="text-align: center">Email</th>
											<!-- <th style="text-align: center">GST No</th>
											<th style="text-align: center">Regs No</th>
											<th style="text-align: center">PAN No</th>
											<th style="text-align: center">Vhicle No</th>
											<th style="text-align: center">Ro No</th>
											<th style="text-align: center">Chasi No</th>
											 -->
											

									

											<th style="text-align: center; width: 5%;">Action</th>
											<th style="text-align: center">More Details</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${custList}" var="cust" varStatus="count">
											<tr>
												<td><input type="checkbox" class="chk"
													name="custIds" id="custIds${count.index+1}"
													value="${cust.custId}" /></td>
												<td style="text-align: center">${count.index+1}</td>


												<td style="text-align: left"><c:out
														value="${cust.custName}" /></td>

												<td style="text-align: left"><c:out
														value="${cust.custAddress}" /></td>

												

												<td style="text-align: left"><c:out
														value="${cust.custPhone}" /></td>
														
														<td style="text-align: left"><c:out
														value="${cust.custState}" /></td>
														<td style="text-align: left"><c:out
														value="${cust.custEmail}" /></td>
														<td style="text-align: left"><c:out
														value="${cust.custGstn}" /></td>
														<td style="text-align: left"><c:out
														value="${cust.custRegisNo}" /></td>
														<td style="text-align: left"><c:out
														value="${cust.custPan}" /></td>
														<td style="text-align: left"><c:out
														value="${cust.custVehNo}" /></td>
														<td style="text-align: left"><c:out
														value="${cust.custRoNo}" /></td>
														<td style="text-align: left"><c:out
														value="${cust.custChasiNo}" /></td>
												
												<td style="text-align: center"><a
													href="${pageContext.request.contextPath}/editCustomer/${cust.custId}"><i
														class="fa fa-edit" title="Edit"></i> <span class="text-muted"></span></a>
													&nbsp; <a
													href="${pageContext.request.contextPath}/deleteCustomer/${cust.custId}"
													onClick="return confirm('Are you sure want to delete this record');"><i
														class="fa fa-trash-o" title="Delete"></i></a></td>
														
														<td style="text-align: center"><a
													href="${pageContext.request.contextPath}/moreCustomerDetails/${cust.custId}"><i
														class="" title="Edit"></i> <span class="text-muted">Details</span></a>
													&nbsp; </td>

											</tr>
										</c:forEach>
									</tbody>
											
								</table>
								<div class="col-lg-1">

									<input type="submit" class="btn btn-primary" value="Delete"
										id="deleteId"
										onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
										style="align-content: center; width: 113px; margin-left: 40px; background-color: #272c33;">


								</div>
							</form>

						</div> --%>
						
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


<script type="text/javascript">
	var minLength = 10;
	var maxLength = 50;
	$(document).ready(function(){
	    $('#cust_name').blur(function(){
	        var field = $(this).val();
	        var charLength = $(this).val().length;
	        if(charLength < minLength){
	            $('#cName').text('Customer Name is short, minimum '+minLength+' required.');
	        }else if(charLength > maxLength){
	            $('#cName').text('Customer Name is not valid, maximum '+maxLength+' allowed.');
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

