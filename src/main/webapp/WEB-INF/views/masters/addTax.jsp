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
								<%-- <a href="${pageContext.request.contextPath}/showCustList"><strong>Tax
										List</strong></a> --%>
							</div>

						</div>
						<div class="card-body card-block">
							<form action="${pageContext.request.contextPath}/insertTax"
								id="submitForm" method="post">
								<input type="hidden" name="tax_id" id="tax_id"
									value="${taxList.taxId}">

								<div class="row">

									<div class="col-md-2">Tax Description*</div>
									<div class="col-md-4">
										<input type="text" id="tax_desc" name="tax_desc" 
											oninvalid="setCustomValidity('Please enter correct tax description')"
											
											value="${taxList.taxDesc}"
											style="width: 100%;" autocomplete="off" class="form-control"
											required>

									</div>

									<div class="col-md-2">CGST % *</div>
									<div class="col-md-4">
										<input type="text" id="cgst_per" name="cgst_per"
											style="width: 100%;" class="form-control"
											value="${taxList.cgstPer}" autocomplete="off"
											oninvalid="setCustomValidity('Please enter cgst price')"
											
											onchange="try{setCustomValidity('')}catch(e){}" required />
										<span class="error" aria-live="polite"></span>

									</div>	
								</div>
								<div class="form-group"></div>
								<div class="row">
									<div class="col-md-2">HSN Code*</div>
									
									<div class="col-md-4">
										<input type="text" id="hsn_code" name="hsn_code" 
											oninvalid="setCustomValidity('Please enter correct hsn code')"
											
											value="${taxList.hsnCode}"
											style="width: 100%;" autocomplete="off" class="form-control"
											required>

									</div>
									
									
									
	
									<div class="col-md-2">SGST % *</div>

									<div class="col-md-4">
										<input type="text" id="sgst_per" name="sgst_per"  class="form-control"
											oninvalid="setCustomValidity('Please enter correct sgst price')"
											onchange="try{setCustomValidity('')}catch(e){}" onblur="gstCal()"
											value="${taxList.sgstPer}"
											style="width: 100%;" autocomplete="off"
											required>

									</div>
								</div>


								<div class="form-group"></div>
								<div class="row">
								<div class="col-md-2">CESS* (Optional)</div>
							
									<div class="col-md-4">
										<input type="text" id="cess_per" name="cess_per" 
											onblur="getCheck()" style="width: 100%;" class="form-control"
											autocomplete="off"
											oninvalid="setCustomValidity('Please enter cess Price')"
											maxlength="20" value="${taxList.cessPer}"
											
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>
									<div class="col-md-2">IGST % *</div>
									<div class="col-md-4">
										<input type="text" id="igst_per" name="igst_per" required
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please enter igst price')"
											 value="${taxList.igstPer}" readonly="readonly"
											onchange="try{setCustomValidity('')}catch(e){}" /> 
									</div>
								</div>
								
								
								<div class="form-group"></div>
								<div class="row">
									<div class="col-md-2">Total Tax % *</div>

									<div class="col-md-4">
										<input type="text" id="tax_per" name="tax_per" class="form-control"
											style="width: 100%;" autocomplete="off" value="${taxList.taxPer}"
											oninvalid="setCustomValidity('Please enter tax price')"
										readonly="readonly" required>
									</div>
									
								
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
						
						<div class="card-body card-block">
							<form
								action="${pageContext.request.contextPath}/deleteRecordofTax"
								method="post">


								<table id="bootstrap-data-table"
									class="table table-striped table-bordered">
									<thead>
										<tr>
											<th class="check" style="text-align: center; width: 5%;"><input
												type="checkbox" name="selAll" id="selAll" /> </th>
											<th style="text-align: center; width: 5%;">Sr.</th>
											<th style="text-align: center">Tax Description</th>
											<th style="text-align: center">HSN Code</th>
											<th style="text-align: center">Tax </th>
											<th style="text-align: center">CGST </th>
											<th style="text-align: center">SGST </th>
											<th style="text-align: center">IGST </th>
											<th style="text-align: center">CESS </th>
											<th style="text-align: center; width: 5%;">Action</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${tList}" var="List" varStatus="count">
											<tr>
												<td><input type="checkbox" class="chk"
													name="taxIds" id="taxIds${count.index+1}"
													value="${List.taxId}" /></td>
												<td style="text-align: center">${count.index+1}</td>

												<td style="text-align: left"><c:out
														value="${List.taxDesc}" /></td>


												<td style="text-align: left"><c:out
														value="${List.hsnCode}" /></td>

												<td style="text-align: left"><c:out
														value="${List.taxPer}" /></td>

												

												<td style="text-align: left"><c:out
														value="${List.cgstPer}" /></td>
														
														<td style="text-align: left"><c:out
														value="${List.sgstPer}" /></td>
														<td style="text-align: left"><c:out
														value="${List.igstPer}" /></td>
													
														<td style="text-align: left"><c:out
														value="${List.cessPer}" /></td>
													
														
												
												<td style="text-align: center"><a
													href="${pageContext.request.contextPath}/editTax/${List.taxId}"><i
														class="fa fa-edit" title="Edit"></i> <span class="text-muted"></span></a>
													&nbsp; <a
													href="${pageContext.request.contextPath}/deleteTaxId/${List.taxId}"
													onClick="return confirm('Are you sure want to delete this record');"><i
														class="fa fa-trash-o" title="Delete"></i></a></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="col-lg-1">

									<input type="submit" class="btn btn-primary" value="Delete"
										id="deleteId"
										onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
										style="align-content: center; width: 113px; margin-left: 40px;">


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
	


	$("#cgst_per").on("keypress keyup blur",function (event) {
	            //this.value = this.value.replace(/[^0-9\.]/g,'');
	     $(this).val($(this).val().replace(/[^0-9\.]/g,''));
	            if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	                event.preventDefault();
	            }
	        });
	


	$("#sgst_per").on("keypress keyup blur",function (event) {
	            //this.value = this.value.replace(/[^0-9\.]/g,'');
	     $(this).val($(this).val().replace(/[^0-9\.]/g,''));
	            if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	                event.preventDefault();
	            }
	        });
	


	$("#igst_per").on("keypress keyup blur",function (event) {
	            //this.value = this.value.replace(/[^0-9\.]/g,'');
	     $(this).val($(this).val().replace(/[^0-9\.]/g,''));
	            if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	                event.preventDefault();
	            }
	        });
	


	$("#cess_per").on("keypress keyup blur",function (event) {
	            //this.value = this.value.replace(/[^0-9\.]/g,'');
	     $(this).val($(this).val().replace(/[^0-9\.]/g,''));
	            if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	                event.preventDefault();
	            }
	        });
	
	</script>
	
	
	<script>
	$('#selAll').click(function(e){
    var table= $(e.target).closest('table');
    $('td input:checkbox',table).prop('checked',this.checked);
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
function gstCal(){
var cgst = parseFloat(document.getElementById("cgst_per").value);
var sgst = parseFloat(document.getElementById("sgst_per").value);
var gstTotal = cgst+sgst;
//alert("IGST = "+gstTotal);
document.getElementById("igst_per").value = gstTotal;
document.getElementById("tax_per").value = gstTotal;
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

