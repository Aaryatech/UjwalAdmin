<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Ujwal Billing Software</title>

<c:url var="getCustById" value="/getCustById" />
<c:url var="addPartDetail" value="/addPartDetail" />

<c:url var="getPartListById" value="/getPartListById" />
<c:url var="getItemForDelete" value="/getItemForDelete" />
<c:url var="getUniqueCompanyCheck" value="/getUniqueCompanyCheck" />

<c:url var="getItemForEdit" value="/getItemForEdit" />
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
									<span aria-hidden="true">Ã—</span>
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
									<span aria-hidden="true">Ã—</span>
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
								<a href="${pageContext.request.contextPath}/showCustList"><strong>Customer
										List</strong></a>
							</div>

						</div>
						<div class="card-body card-block">
							<form action="${pageContext.request.contextPath}/insertBill"
								id="submitForm" method="post">
							
								<div class="row">



									<div class="col-md-2">Customer Name*</div>
									<div class="col-md-10">
										<select id="cust_id" name="cust_id" class="standardSelect" 
										
											onchange="getData()">
											<option value="">Select Customer</option>

											<c:forEach items="${custList}" var="cust">
												<option value="${cust.custId}">${cust.custName}</option>
											</c:forEach>
										</select>

									</div>
									
								</div>
								<div class="form-group"></div>
								<div class="row">


								<div class="col-md-2">Mobile No*</div>
									<div class="col-md-4">
										<input type="text" id="cust_phone" name="cust_phone"
											style="width: 100%;" class="form-control"
											value="" autocomplete="off"
											oninvalid="setCustomValidity('Please enter correct mob no')"
											pattern="^[1-9]{1}[0-9]{9}$" maxlength="10"
											onchange="try{setCustomValidity('')}catch(e){}" required />
										<span class="error" aria-live="polite"></span>

									</div>
									<div class="col-md-2">Customer Address*</div>

									<div class="col-md-4">
										<textarea id="cust_address" name="cust_address" class="form-control"
											style="width: 100%;" autocomplete="off"
											oninvalid="setCustomValidity('Please enter customer address')"
											maxlength="200"
											onchange="try{setCustomValidity('')}catch(e){}" required></textarea>
									</div>


									
									
								</div>


								<div class="form-group"></div>
								<div class="row">

	
									<div class="col-md-2">Email Id*</div>
									<div class="col-md-4">
										<input type="text" id="cust_email" name="cust_email" required
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please enter email')"
											pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
											maxlength="50" value=""
											onchange="try{setCustomValidity('')}catch(e){}" /> 
									</div>
									<div class="col-md-2">State*</div>

									<div class="col-md-4">
										<input type="text" id="cust_state" name="cust_state"  class="form-control"
											oninvalid="setCustomValidity('Please enter correct customer state')"
											onchange="try{setCustomValidity('')}catch(e){}"
											pattern="^[A-Za-z\s]+$" value=""
											style="width: 100%;" autocomplete="off"
											required>

									</div>	
								
								</div>
								
								
								<div class="form-group"></div>
								<div class="row">
									<div class="col-md-2">GST No*</div>
							
									<div class="col-md-4">
										<input type="text" id="cust_gstn" name="cust_gstn" required
											onblur="getCheck()" style="width: 100%;" class="form-control"
											autocomplete="off"
											oninvalid="setCustomValidity('Please enter GST no')"
											maxlength="20" value=""
											pattern="^([0]{1}[1-9]{1}|[1-2]{1}[0-9]{1}|[3]{1}[0-7]{1})([a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9a-zA-Z]{1}[zZ]{1}[0-9a-zA-Z]{1})+$"
											onkeydown="upperCaseF(this)"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>
								<div class="col-md-2">Registration No*</div>
									<div class="col-md-4">
										<input type="text" id="cust_regis_no" name="cust_regis_no" required
											style="width: 100%;" class="form-control"
										
											value="" autocomplete="off"
											/> <span
											class="error" aria-live="polite"></span>
									</div>
								
								</div>
								
								<div class="form-group"></div>

								<div class="row">
									<div class="col-md-2">PAN No*</div>
									<div class="col-md-4">
										<input type="text" id="cust_pan" name="cust_pan" required
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please enter PAN no')"
											maxlength="10" value=""
											pattern="[A-Za-z]{5}\d{4}[A-Za-z]{1}"
											onkeydown="upperCaseF(this)"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>

									<div class="col-md-2">Vhicle No*</div>
									<div class="col-md-4">
										<input type="text" id="cust_veh_no" name="cust_veh_no" required
											style="width: 100%;" class="form-control" autocomplete="off"
										maxlength="20"
										value=""
										/> <span
											class="error" aria-live="polite"></span>

									</div>
								</div>
          <input type="hidden" id="isEdit" name="isEdit" value="0">
           <input type="hidden" id="index" name="index" value="0">
								<div class="form-group"></div>

								<div class="row">

									
									<div class="col-md-2">Ro No*</div>
									<div class="col-md-4">
										<input type="text" id="cust_ro_no" name="cust_ro_no" required
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please enter Ro No')"
											value="" maxlength="30"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>							
									
											<div class="col-md-2">Chasi No.*</div>
									<div class="col-md-4">
										<input type="text" id="cust_chasi_no" name="cust_chasi_no" 
											style="width: 100%;" class="form-control"
											value="" autocomplete="off"
											oninvalid="setCustomValidity('Please enter chasi no')"
											onchange="try{setCustomValidity('')}catch(e){}"
											/> <span class="error"
											aria-live="polite"></span>

									</div></div>
									
									<h3 style="margin-top:10px;margin-bottom:20px; font-style:bold">Add Part</h3>
								<div class="form-group"></div>
							
								<div class="row">
							
									<div class="col-md-2">Part Name</div>

									<div class="col-md-4">
										<select id="part_id" name="part_id" class="form-control" style="width: 50%;"
									
										
											onchange="getPartDetail()">
											<option value="">Select Part</option>
												<c:forEach items="${pList}" var="part">
												<option value="${part.partId}">${part.partName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-md-2">Quantity</div>

									<div class="col-md-4">
										<input type="text" id="qty" name="qty" 
											style="width: 50%;" class="form-control" autocomplete="off"/> 
									</div>
								</div>
									
	                 			
								<div class="form-group"></div>
							
								<div class="row">
								<div class="col-md-2">MRP</div>

									<div class="col-md-4">
									
										<input type="text" id="part_mrp" name="part_mrp" 
											style="width: 50%;" class="form-control" autocomplete="off"/> 
								
									</div>
									<div class="col-md-2">Disc %</div>

									<div class="col-md-4">
										<input type="text" id="disc" name="disc" 
											style="width: 50%;" class="form-control" autocomplete="off"/> 
									</div>
								</div>
									<div class="form-group"></div>
							
								<div class="row">
								<div class="col-md-2">Remark</div>

									<div class="col-md-4">
									
										<input type="text" id="remark" name="remark" 
											style="width: 50%;" class="form-control" autocomplete="off"/> 
								
									</div>	</div>
											<div class="form-group"></div>
									<div class="col-lg-3">
									<input type="button" class="btn btn-primary" value="Add"
										id="AddButton"
										style="align-content: center; width: 113px; margin-left: 40px;" onclick="add()">

								</div>
							
						
							
						
						
						
						<div class="card-body card-block">
							

								<table id="bootstrap-data-table"
									class="table table-striped table-bordered" >
									<thead>
										<tr>
										<!-- <th class="check" style="text-align: center; width: 5%;"><input
												type="checkbox" name="selAll" id="selAll" /> Select All</th> --> 
											<th style="text-align: center; width: 5%;">Sr No</th>
											<th style="text-align: center">Part Name</th>
											 <th style="text-align: center">UOM Name</th>
											<th style="text-align: center">Quantity</th>
											<th style="text-align: center">MRP</th>
											<th style="text-align: center">Disc %</th>
											<th style="text-align: center">Disc Amount</th>
											<th style="text-align: center">Tax %</th>
											<th style="text-align: center">Taxable Amount</th>
											<th style="text-align: center">Total Tax Amount</th>
											<th style="text-align: center">Total</th> 
										

									

											<th style="text-align: center; width: 5%;">Action</th>
	
										</tr>
									</thead>
									</table>
									<!-- <table>
									<tr></tr>
							<tr><th>Remark</th>
							
							<td>
										<input type="text" id="remark_new" name="remark_new" required
											style="width: 100%;" class="form-control"/> 
								</td>
										<th>Total Amount</th>
							
							<td>
										<input type="text" id="total_amt" name="total_amt" required
											style="width: 100%;" class="form-control"/> 
								</td></tr>
									
								</table> -->
					
							<div class="form-group"></div>
								<div class="col-lg-4"></div>
								<div class="col-lg-3">
									<input type="submit" class="btn btn-primary" value="Submit"
										id="submitButton"
										style="align-content: center; width: 113px; margin-left: 40px;">

								</div>
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

	<script type="text/javascript">
	
	// on plant change function 
		function getData() { 
		
			var custId = document.getElementById("cust_id").value;
			//alert(custId);
			var valid = true;

			if (custId == null || custId == "") {
				valid = false;
				alert("Please select Customer");
			}

			if (valid == true) {

				$.getJSON('${getCustById}', {
					custId : custId,
					ajax : 'true',
				},

				function(data) {
					document.getElementById("cust_email").value=data.custEmail
					document.getElementById("cust_address").value=data.custAddress
					document.getElementById("cust_phone").value=data.custPhone
					document.getElementById("cust_state").value=data.custState
					document.getElementById("cust_gstn").value=data.custGst
					document.getElementById("cust_regis_no").value=data.custRegisNo
					document.getElementById("cust_pan").value=data.custPan
					document.getElementById("cust_veh_no").value=data.custVehNo
					document.getElementById("cust_ro_no").value=data.custRoNo
					document.getElementById("cust_chasi_no").value=data.custChasiNo
				
				
		
					
				
				});
			}//end of if

		}
	</script>
<script type="text/javascript">
	
	// on plant change function 
		function getPartDetail() { 
		
			var partId = document.getElementById("part_id").value;
			//alert(custId);
			var valid = true;

			if (partId == null || partId == "") {
				valid = false;
				alert("Please select Part Name");
			}

			if (valid == true) {

				$.getJSON('${getPartListById}', {
					partId : partId,
					ajax : 'true',
				},

				function(data) {
					document.getElementById("part_mrp").value=data.partMrp
				
		
					
				
				});
			}//end of if

		}
	</script>
<script type="text/javascript">
function add(){
	 //alert("hii1");
	
	 var partId= document.getElementById("part_id").value;
	//alert("hii"+partId);
	var qty = document.getElementById("qty").value;
	var isEdit = document.getElementById("isEdit").value;alert(isEdit);
	var index= document.getElementById("index").value;
	var partMrp = document.getElementById("part_mrp").value;
	var disc = document.getElementById("disc").value;
	var remark = document.getElementById("remark").value;
	 
//	alert("hii2");
	 
	$.getJSON(
					'${addPartDetail}',
					{
						isEdit :isEdit,
						index:index,
						 partId :partId,
						 qty : qty,
						 partMrp : partMrp,
						 disc : disc,
						 remark : remark,
						ajax : 'true',
					},

				 	function(data) {
						
						/* alert("Order Data " +JSON.stringify(data)); */
						
				 var dataTable = $('#bootstrap-data-table')
						.DataTable();
				dataTable.clear().draw();

				$.each(data,function(i, v) {
									var total=v.cgstPer+v.sgstPer;
									alert(total);
							 var acButton = '<a href="#" class="action_btn" onclick="callDelete('
											+ v.billDetailId
											+ ','
											+ i
											+ ')"><i class="fa fa-trash"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="action_btn" onclick="callEdit('
											+ v.billDetailId
											+ ','
											+ i
											+ ')"><i class="fa fa-edit"></i></a>' 
									dataTable.row
											.add(
													[
															i + 1,
															v.partName,
															v.uomName,
															v.qty,
															v.partMrp,
															v.baseRate.toFixed(2),
															v.discPer.toFixed(2),
															total,
															v.taxableAmount,
															v.totalTax,
															v.grandTotal,
															
															acButton
															 ])
											.draw();
								});  
					
							
				 		 
						} 
					
	);
	document.getElementById("part_id").value = "";
	document.getElementById("qty").value = "";
	document.getElementById("part_mrp").value = "";
	document.getElementById("disc").value = "";
	document.getElementById("remark").value = "";    
	}
	
	

function callEdit(billDetailId, index) {
	$
			.getJSON(
					'${getItemForEdit}',
					{
						index : index,
						ajax : 'true',

					},
					function(data) {

						 document.getElementById("part_id").value = data.partId;
						$("#part_id").trigger("chosen:updated");
						document.getElementById("qty").value = data.qty;
						document.getElementById("remark").value = data.remark;
						document.getElementById("isEdit").value = 1;
						document.getElementById("part_mrp").value = data.partMrp; 
						document.getElementById("disc").value = data.discPer; 
					});

}

function callDelete(billDetailId, index) {
	$
			.getJSON(
					'${getItemForDelete}',
					{
						index : index,
						ajax : 'true',

					},
					function(data) {

						
						/* alert("Order Data " +JSON.stringify(data)); */
						
				 var dataTable = $('#bootstrap-data-table')
						.DataTable();
				dataTable.clear().draw();

				$.each(data,function(i, v) {
									var total=v.cgstPer+v.sgstPer;
									alert(total);
							 var acButton = '<a href="#" class="action_btn" onclick="callDelete('
											+ v.billDetailId
											+ ','
											+ i
											+ ')"><i class="fa fa-trash"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="action_btn" onclick="callEdit('
											+ v.billDetailId
											+ ','
											+ i
											+ ')"><i class="fa fa-edit"></i></a>' 
									dataTable.row
											.add(
													[
															i + 1,
															v.partName,
															v.uomName,
															v.qty,
															v.partMrp,
															v.baseRate.toFixed(2),
															v.discPer.toFixed(2),
															total,
															v.taxableAmount,
															v.totalTax,
															v.grandTotal,
															
															acButton
															 ])
											.draw();
								});  
					
							
					});

}
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
<!-- 
 <script type="text/javascript">

function getData() { 
	var custId = document.getElementById("cust_id").value;
	document.getElementById("isEdit").value = 0;
	var valid = true;

	if (custId == null || custId == "") {
		valid = false;
		alert("Please select customer");
	}

	if (valid == true) {

		$.getJSON('${getCustByPlantId}', {
			plantId : plantId,
			ajax : 'true',
		},

		function(data) {
			
			document.getElementById("cust_email").value=data.custEmail
			document.getElementById("cust_address").value=data.custAddress
			document.getElementById("cust_chasi_no").value=data.custChasiNo

			document.getElementById("cust_gst").value=data.custGst
			document.getElementById("cust_pan").value=data.custPan
			document.getElementById("cust_phone").value=data.custPhone
			document.getElementById("cust_regis_no").value=data.custRegisNo
			document.getElementById("cust_ro_no").value=data.custRoNo
			document.getElementById("cust_veh_no").value=data.custVehNo
		}
	}
}
</script> -->
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