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
								 <a href="${pageContext.request.contextPath}/showPartList"><strong>Part
										List</strong></a>
							</div>

						</div>
						<div class="card-body card-block">
							<form action="${pageContext.request.contextPath}/insertPart"
								id="submitForm" method="post">
								<input type="hidden" name="part_id" id="part_id"
									value="${partList.partId}">

									
									<div class="row">
									
										<div class="col-md-2">Company Name*</div>
									<div class="col-md-4">
											<select name="compId" id="compId" class="standardSelect" tabindex="6" required>
											<option value="">Select Company</option>
											<c:forEach items="${compList}" var="makeList"> 
												<option value="${makeList.compId}"><c:out value="${makeList.compName}"></c:out> </option>
											 </c:forEach>
										</select> 
									</div>
									
									<div class="col-md-2">Part Name*</div>
									<div class="col-md-4">
										<input type="text" id="part_name" name="part_name"
											oninvalid="setCustomValidity('Please enter correct part name')"
											onchange="try{setCustomValidity('')}catch(e){}"
											pattern="^[A-Za-z\s]+$" value="${partList.partName}"
											style="width: 100%;" autocomplete="off" class="form-control"
											required>

									</div>
									
									
								</div>
								<div class="form-group"></div>


								<div class="row">


									<div class="col-md-2">Part Specification*</div>
									<div class="col-md-4">
										<input type="text" id="part_specification" name="part_specification" required
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please specification')"
											 value="${partList.partSpecification}"
											onchange="try{setCustomValidity('')}catch(e){}" /> 
									</div>  
									
									<div class="col-md-2">Part No.*</div>

									<div class="col-md-4">
										<input type="text" id="part_no" name="part_no" class="form-control"
											style="width: 50%;" autocomplete="off" value="${partList.partNo}"
											oninvalid="setCustomValidity('Please enter part no.')"
											maxlength="200"
											onchange="try{setCustomValidity('')}catch(e){}" required>
									</div>
								</div>
								<div class="form-group"></div>
								<div class="row">

									 <div class="col-md-2">Select Tax*</div>
									<div class="col-md-4">
										<select id="part_tax_id" name="part_tax_id" class="standardSelect">
											<option value="">Select Tax</option>

											<c:forEach items="${tList}" var="tax">
												<option value="${tax.taxId}">${tax.taxDesc}</option>
											</c:forEach>
										</select>
									</div> 
								
									<div class="col-md-2">Measurement Unit*</div>
									<div class="col-md-4">
										<select id="measurement_of_unit" name="measurement_of_unit" class="standardSelect">
											<option value="">Measurement Unit</option>

											<c:forEach items="${muomList}" var="muom">
												<option value="${muom.uomId}">${muom.uomName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="form-group"></div>
								<div class="row">
									<div class="col-md-2">Part MRP*</div>
									<div class="col-md-4">
										<input type="text" id="part_mrp" name="part_mrp" required
											style="width: 100%;" class="form-control"
											oninvalid="setCustomValidity('Please enter part mrp')"
											
											value="${partList.partMrp}" autocomplete="off"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span class="error" aria-live="polite"></span>
									</div>
									<div class="col-md-2">Model No.*</div>
									<div class="col-md-4">
										<input type="text" id="model_no" name="model_no" required
											style="width: 100%;" class="form-control"
											oninvalid="setCustomValidity('Please enter part regs no.')"
											
											value="${partList.partRoNo}" autocomplete="off"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span class="error" aria-live="polite"></span>
									</div>
								
									</div>
								
								<div class="form-group"></div>
								<div class="col-lg-4"></div>
								<div class="col-lg-3">
									<input type="submit" class="btn btn-primary" value="Submit"
										id="submitButton"
										style="align-content: center; width: 113px; margin-left: 40px;">

								</div>
								<div class="col-lg-3">
									<input type="reset" class="btn btn-primary" value="Clear"
										style="align-content: center; width: 113px; margin-left: 40px;">

								</div>
								
							</form>
							
						</div>
						
						<%-- <div class="card-body card-block">
							<form
								action="${pageContext.request.contextPath}/deleteRecordofPart"
								method="post">


								<table id="bootstrap-data-table"
									class="table table-striped table-bordered">
									<thead>
										<tr>
											<th class="check" style="text-align: center; width: 5%;"><input
												type="checkbox" name="selAll" id="selAll" /> Select All</th>
											<th style="text-align: center; width: 5%;">Sr No</th>
											<th style="text-align: center">Part Name</th>
											<th style="text-align: center">Part No</th>
											<th style="text-align: center">Registration No.</th>
											<th style="text-align: center">Part UOM Name</th>
											<th style="text-align: center">Part Specification</th>
											<th style="text-align: center">Tax Description</th>
											<th style="text-align: center">Part MRP</th>
											<th style="text-align: center">Ro. No.</th>
										
											
											

									

											<th style="text-align: center; width: 5%;">Action</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pList}" var="pList" varStatus="count">
											<tr>
												<td><input type="checkbox" class="chk"
													name="partIds" id="partIds${count.index+1}"
													value="${pList.partId}" /></td>
												<td style="text-align: center">${count.index+1}</td>


												<td style="text-align: left"><c:out
														value="${pList.partName}" /></td>

												<td style="text-align: left"><c:out
														value="${pList.partNo}" /></td>

												

												<td style="text-align: left"><c:out
														value="${pList.partRegisterNo}" /></td>
														
														<td style="text-align: left"><c:out
														value="${pList.partUomId}" /></td>
														<td style="text-align: left"><c:out
														value="${pList.partSpecification}" /></td>
												
														<td style="text-align: left"><c:out
														value="${pList.partTaxId}" /></td>
														<td style="text-align: left"><c:out
														value="${pList.partMrp}" /></td>
														<td style="text-align: left"><c:out
														value="${pList.partRoNo}" /></td>
																										
												<td style="text-align: center"><a
													href="${pageContext.request.contextPath}/editPart/${pList.partId}"><i
														class="fa fa-edit" title="Edit"></i> <span class="text-muted"></span></a>
													&nbsp; <a
													href="${pageContext.request.contextPath}/deletePart/${pList.partId}"
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

