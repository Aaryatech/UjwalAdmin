<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Ujjwal Billing Software</title>


<c:url var="getItemListBetweenDate" value="/getItemListBetweenDate" />
<c:url var="getBillListBetweenDate" value="/getBillListBetweenDate" />

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


				<div class="col-xs-12 col-sm-12">
					<div class="card">
						<div class="card-header">
							<div class="col-md-4">
								<strong>${title}</strong>
							</div>
						</div>

						<div class="card-body card-block">

							<div class="form-group"></div>

							<div class="row">


								<div class="col-md-2">Item Name</div>
								<div class="col-md-4">
									<select id="item_id" name="item_id" class="standardSelect"
										tabindex="1" required
										oninvalid="setCustomValidity('Please select item')">
										<option value="0">All</option>
										<c:forEach items="${partList}" var="item">
											<option value="${item.partId}">${item.partName}</option>
										</c:forEach>

									</select>
								</div>

							</div>


							<div class="form-group"></div>
							

							<div class="row">
								<div class="col-md-2">From Date</div>
								<div class="col-md-4">
									<input type="text" autocomplete="off" id="from_date" 
										name="from_date" required style="width: 100%;"
										class="form-control" value="${fromDate}"> <span
										class="error" aria-live="polite"></span>
								</div>
								<div class="col-md-2">To Date</div>
								<div class="col-md-4">
									<input type="text" autocomplete="off" id="to_date" 
										name="to_date" style="width: 100%;" class="form-control"
										value="${toDate}"> <span class="error"
										aria-live="polite"></span>
								</div>

							</div>

							<div class="form-group"></div>

														<div class="row">
								<div class="col-md-6"></div>
								<div class="col-md-2">
									<input type="button" class="btn btn-primary" style="background-color: #272c33;"
										onclick="showItemReport()" value="Submit">
								</div>
							</div>


							<div class="form-group"></div>

							<div class="card-body card-block">
								<table id="bootstrap-data-table"
									class="table table-striped table-bordered">
									<thead>
										<tr>	
											<th style="text-align: center; width: 5%;">Sr No</th>
											<!-- <th style="text-align: center">Invoice No.</th> -->
											<th style="text-align: center">Item Name</th>
											<th style="text-align: center">HSN Code</th>
											<th style="text-align: center">Tax Rate</th>
											<th style="text-align: center">Qty.</th>
											<th style="text-align: center">Taxble Amount</th>
											<th style="text-align: center">CGST</th>
											<th style="text-align: center">SGST</th>
											<th style="text-align: center">IGST</th>
											<th style="text-align: center">Total Tax</th>
											<th style="text-align: center">Total Amount</th>
										
										</tr>
									</thead>

								</table>
								<div class="col-md-2"></div>

								<div class="col-md-3">

									<button type="button" class="btn btn-primary"
										onclick="exportToExcel();" disabled="disabled" id="expExcel"
										style="align-content: center; width: 200px; margin-left: 80px; background-color: #272c33;">
										Export To Excel</button>
								</div>


								<div class="col-md-3">

									<button type="button" class="btn btn-primary"
										onclick="genPdf()" disabled="disabled" id="PDFButton"
										style="align-content: center; width: 100px; margin-left: 80px; background-color: #272c33;">
										PDF</button>
								</div>
								&nbsp;

							</div>


						</div>


					</div>
				</div>

			</div>
		</div>

	</div>
	<!-- .animated -->
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
				disable_search_threshold : 1,
				no_results_text : "Oops, nothing found!",
				width : "100%"
			});
		});
	</script>




	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$('input[id$=from_date]').datepicker({
				dateFormat : 'dd-mm-yy'
			});

			$('input[id$=to_date]').datepicker({
				dateFormat : 'dd-mm-yy'
			});

		});
	</script>





	<script type="text/javascript">
		// onclick of submit to search order 
		function showItemReport() {

			//alert("Hi View Orders  ");

			var itemId = document.getElementById("item_id").value;
			var fromDate = document.getElementById("from_date").value;
			var toDate = document.getElementById("to_date").value;

			//alert(compId);

			var valid = true;

			if (itemId == null || itemId == "") {
				valid = false;
				alert("Please select item");
			}

		
			//alert("plantId" + plantId);
		

			else if (fromDate == null || fromDate == "") {
				valid = false;
				alert("Please select from date");
			}

			else if (toDate == null || toDate == "") {
				valid = false;
				alert("Please select to date");
			}

			if (fromDate > toDate) {
				valid = false;
				alert("from date greater than todate ");
			}
			if (valid == true) {

				$.getJSON('${getItemListBetweenDate}', {
					itemId : itemId,
				
					fromDate : fromDate,
					toDate : toDate,
					ajax : 'true',

				},

				function(data) {

					document.getElementById("expExcel").disabled = false;
					document.getElementById("PDFButton").disabled = false;

					if (data == "") {
						alert("No records found !!");
						document.getElementById("expExcel").disabled = true;
						document.getElementById("PDFButton").disabled = true;

					}

					var dataTable = $('#bootstrap-data-table').DataTable();
					dataTable.clear().draw();

					$.each(data, function(i, v) {
							var igst = 0;
							var taxRate=v.cgstPer+v.sgstPer; 
  							var totaltax=v.cgst+v.sgst;
  							var totalAmt=v.taxableAmount+totaltax; 
							//alert("total="+totalAmt);
						dataTable.row.add(
								[ i + 1, v.partName, v.hsnCode, taxRate, v.qty, v.taxableAmount,
										 v.cgst, v.sgst, igst, totaltax, totalAmt

								]).draw();
					});

				});

			}//end of if valid ==true

		}

		function callEdit(weighId) {

			window.open("${pageContext.request.contextPath}/editWeighing/"
					+ weighId);

		}

		function callDelete(weighId) {
			window.open("${pageContext.request.contextPath}/deleteWeighing/"
					+ weighId);
   
		}
	</script>


	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#bootstrap-data-table').DataTable();

							$("#selAll")
									.click(
											function() {
												$(
														'#bootstrap-data-table tbody input[type="checkbox"]')
														.prop('checked',
																this.checked);
											});
						});
	</script>
	<script type="text/javascript">
		// on plant change function 
		function getData() {
			var compId = document.getElementById("compId").value;
			var valid = true;

			if (compId == null || compId == "") {
				valid = false;
				alert("Please select company");
			}

			if (valid == true) {

				$.getJSON('${getPlantByCompId}', {
					compId : compId,
					ajax : 'true',
				},

				function(data) {
					var html;
					var len = data.length;

					var html = '<option selected value="0">All</option>';

					for (var i = 0; i < len; i++) {

						html += '<option value="' + data[i].plantId + '">'
								+ data[i].plantName + '</option>';

					}
					html += '</option>';

					$('#plantId').html(html);
					$("#plantId").trigger("chosen:updated");

					var dataTable = $('#bootstrap-data-table').DataTable();
					dataTable.clear().draw();

				});
			}//end of if

		}
	</script>

	<script type="text/javascript">
		function exportToExcel() {

			window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled = true;
		}
	</script>

	<script type="text/javascript">
		function genPdf() {
			//alert("hiii");
			var itemId = document.getElementById("item_id").value;
			var fromDate = document.getElementById("from_date").value;
			var toDate = document.getElementById("to_date").value;

			window.open('${pageContext.request.contextPath}/showItemWisePdf/'
					+ fromDate + '/' + toDate+ '/' + itemId);
			document.getElementById("expExcel").disabled = true;

		}
	</script>


</body>
</html>