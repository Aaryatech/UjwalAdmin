<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Ujjwal Billing Software</title>


<c:url var="getCustByPlantId" value="/getCustByPlantId" />
<c:url var="getBillListBetDate" value="/getBillListBetDate" />
<c:url var="getCustomerListById" value="/getCustomerListById" />
<%-- 

<c:url var="getCustInfoByCustId" value="/getCustInfoByCustId" />

<c:url var="getProjectByCustId" value="/getProjectByCustId" />


<c:url var="getPoDetailForOrderByPoId"
	value="/getPoDetailForOrderByPoId" />
	
	
<c:url var="getTempOrderHeader"
	value="/getTempOrderHeader" /> --%>
	

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

<style>
.alert {
    padding: 20px;
    background-color: red;
    color: white;
    
}
.alert1 {
    padding: 20px;
    background-color: green;
    color: white;
    
}

.closebtn {
    margin-left: 15px;
    color: white;
    font-weight: bold;
    float: right;
    font-size: 22px;
    line-height: 20px;
    cursor: pointer;
    transition: 0.3s;
}

.closebtn:hover {
    color: black;
}
</style>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.buttonload {
    background-color: white; /* Green background */
    border: none; /* Remove borders */
    color: #ec268f; /* White text */
    padding: 12px 15px; /* Some padding */
    font-size: 13px; /* Set a font-size */
    display:none;
}

/* Add a right margin to each icon */
.fa {
    margin-left: -12px;
    margin-right: 8px;
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
							
							<div class="alert">
							
							<span class="closebtn"
						onclick="this.parentElement.style.display='none';">&times;</span>
					<strong>Failed !</strong>     Data not submitted  !!
				</div>
							
							</c:when>
							
							<c:when test="${isError==2}">
							
							<div class="alert1">
							
							<span class="closebtn"
						onclick="this.parentElement.style.display='none';">&times;</span>
					<strong>Success</strong>     Data Submitted !!
				</div>
							
							</c:when>
							
							</c:choose>

				<div class="col-xs-12 col-sm-12">
					<div class="card">
						<div class="card-header">
							<div class="col-md-2">
								<strong>Bill List</strong>
							</div>
							 <div class="col-md-8"></div>
							<div class="col-md-2" align="left">
								<a href="${pageContext.request.contextPath}/showAddBill"><strong>Add
										Bill</strong></a>
							</div> 
							

						</div>
						<div class="card-body card-block">
							
									<div class="row">
									
										<div class="col-md-2">Company Name*</div>
										<div class="col-md-4">
											<select name="compId" id="compId" class="standardSelect" tabindex="6" 
											onchange="getCompId()" required>
											<option value="">Select Company</option>
											<c:forEach items="${compList}" var="makeList"> 
												<option value="${makeList.compId}"><c:out value="${makeList.compName}"></c:out> </option>
											 </c:forEach>
										</select> 
									</div>

									<%-- <div class="col-md-2">Select Customer</div>
									<div class="col-md-4">
										<select id="cust_name" name="cust_name" class="standardSelect"
											tabindex="1" required
											oninvalid="setCustomValidity('Please select customer')"
											onchange="getCustInfo()">
                                     <c:forEach items="${custList}" var="cust">
												<option value="${cust.custId}">${cust.custName}</option>
											</c:forEach>
										</select>
									</div> --%>
									<div class="col-md-2">Customer Name</div>
									<div class="col-md-4">
										<select id="cust_id" name="cust_id" style="width: 100%;" class="standardSelect"
										
											
											oninvalid="setCustomValidity('Please select customer')"
											onchange="getCustInfo()">
                                     <c:forEach items="${custList}" var="cust">
												<option value="${cust.custId}">${cust.custName}</option>
											</c:forEach>
										</select>

									</div>

								</div>
								<div class="form-group"></div>
								
								<div class="row">
									<div class="col-md-2">From Date</div>
									<div class="col-md-4">
										<input type="text" autocomplete="off" id="from_date" name="from_date" required
											style="width: 100%;" class="form-control"
											value="${fromDate}"> <span class="error"
											aria-live="polite"></span>
									</div>
									
									
									<div class="col-md-2">To Date</div>
									<div class="col-md-4">
										<input type="text" autocomplete="off"  id="to_date" name="to_date"
											style="width: 100%;" class="form-control"
											value="${toDate}"> <span
											class="error" aria-live="polite"></span>
									</div>

								</div>

								
								<div class="form-group"></div>
								<div class="row">
								<div class="col-md-6"></div>
									<div class="col-md-3">
										<input type="button" class="btn btn-primary"  onclick="showBill()" value="Submit">
									<button class="buttonload" id="loader">
                                   <i class="fa fa-spinner fa-spin"></i>Loading
                                   </button>
									</div>
								</div>
								

								<div class="form-group"></div>
								
								</div>
								
								<%-- <input type="checkbox" value="${item.itemId}" name="selectItem"> --%>
								
								<div class="card-body card-block">
									<table id="bootstrap-data-table"
										class="table table-striped table-bordered">
										<thead>
											<tr>
											
												<th style="text-align: center"><!-- <input type="checkbox" id="selectAll" />  -->Sr.</th>
												<th style="text-align: center">Bill No</th>
												<th style="text-align: center">Bill Date</th>
												<th style="text-align: center">Customer Name</th>
<!-- 												<th style="text-align: center">Status</th>
 -->												<th style="text-align: center">Action</th>
											</tr>
										</thead>

									</table>
								</div>

				<center>
										<input type="button" margin-right: 5px;" id="btn_submit"
											class="btn btn-primary" onclick="billPdf()" 
											value="Bill Pdf" />
							</center>
								

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


	<script type="text/javascript">
			function getCompId() { 
		
			var compId = document.getElementById("compId").value;
			var valid = true;
		
			if (compId == null || compId == "") {
				valid = false;
				alert("Please select Company Name");
			}

			if (valid == true) {

				$.getJSON('${getCustomerListById}', {
					compId : compId,
					ajax : 'true',
				},

				function(data) {
					
					var len = data.length;
					//alert("data " +JSON.stringify(data));
					var html='<option value="-1">All</option>';

					for (var i = 0; i < len; i++) {

						html += '<option value="' + data[i].custId + '">'
								+data[i].custName+ '</option>';

					}
					html += '</option>';
					$('#cust_id').html(html);
					$("#cust_id").trigger("chosen:updated");
				
				});
			}//end of if

		}
	</script>	

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
	<script type="text/javascript">
		$(document).ready(function() {
			$('#bootstrap-data-table').DataTable();
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
		function showBillReport() {

			//alert("Hi View Orders  ");

			var compId = document.getElementById("compId").value;
			var custId = document.getElementById("cust_id").value;
			var fromDate = document.getElementById("from_date").value;
			var toDate = document.getElementById("to_date").value;

			//alert(compId);

			var valid = true;

			if (compId == null || compId == "") {
				valid = false;
				alert("Please select company");
			}
			
			else if (custId == null || custId == "") {
				valid = false;
				alert("Please select company");
			}
		
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
			

				$.getJSON('${getBillListBetDate}', {
					
					custId : custId,
					fromDate : fromDate,
					toDate : toDate,
					ajax : 'true',

				},
					
				function(data) {
					//document.getElementById("expExcel").disabled = false;
					//document.getElementById("PDFButton").disabled = false;

					if (data == "") {
						alert("No records found !!");
					//	document.getElementById("expExcel").disabled = true;
					//	document.getElementById("PDFButton").disabled = true;

					}

					var dataTable = $('#bootstrap-data-table').DataTable();
					dataTable.clear().draw();

					$.each(data, function(i, v) {
  						alert("hello");									
						dataTable.row.add(
								[ i + 1,
								  v.billDetailId,
								  v.billDate,
								  v.custName
								
								]).draw();
					});

				});

			}//end of if valid ==true

		}
		
	</script>

	<script type="text/javascript">
		function showBill() {
		
			var compId = document.getElementById("compId").value;
			var custId = document.getElementById("cust_id").value;
			var fromDate = document.getElementById("from_date").value;
			var toDate = document.getElementById("to_date").value;
  			
			
			var valid = true;
			
			 if (compId == null || compId == "") {
				valid = false;
				alert("Please select Company");
			}
			
			 else if (custId == null || custId == "") {
				valid = false;
				alert("Please Select Customer");
				
				var dataTable = $('#bootstrap-data-table')
				.DataTable();
				dataTable.clear().draw();

			}
			else if(custId<0){
				valid = false;

			}
			
			else if (fromDate == null || fromDate == "") {
					valid = false;
					alert("Please select from date");
				}			
			 
			else if (toDate == null || toDate == "") {
				valid = false;
				alert("Please select to date");
			}			
		
			if(fromDate > toDate){
				valid = false;
				alert("from date greater than todate ");
			}
			if(valid==true){
				$('#loader').show();
				$
						.getJSON(
								'${getBillListBetDate}',
								{
									custId : custId,
									fromDate : fromDate,
									toDate : toDate,
									ajax : 'true',
								},

								function(data) {
									$('#loader').hide();
									//alert("Order Data " +JSON.stringify(data));
									
									 var dataTable = $('#bootstrap-data-table')
									.DataTable();
							dataTable.clear().draw();

							$.each(data,function(i, v) {
												//alert("hdjfh");
										
var checkB = '<input  type="checkbox" name=select_to_print id=select_to_print'+v.billHeaderId+' class="chk"  value='+v.billHeaderId+' >'
//var ordQty = '<input  type="text"  class="form-control"  id="ordQty'+v.itemId+'" name="ordQty'+v.itemId+'" onchange="calTotal('+v.itemId+','+v.poRate+','+v.poDetailId+','+v.poRemainingQty+')"/>'
//var itemTotal = '<input  type="text" readonly  class="form-control"  id="itemTotal'+v.itemId+'" name='+v.itemId+'/>'
										 var acButton = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="action_btn" onclick="callEdit('
														+ v.billHeaderId
														+ ','
														+ i
														+ ')"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
 
												dataTable.row
														.add(
																[
																	checkB+''+ (i + 1),
																		v.billDetailId,
																		v.billDate,
																		v.custName,
																		acButton
																		 ])
														.draw();
											}); 
						
								});	
				
}//end of if valid ==true
						
		}
	
	function callEdit(billHeadId){
		
		window.open("${pageContext.request.contextPath}/editBill/"+billHeadId);
		
	}
	</script>
	
		
<script type="text/javascript">
function billPdf()
{
	var checkedVals = $('input:checkbox:checked').map(function() {
	    return this.value;
	}).get();
//checkedVals=checkedVals.slice(0,- 1);alert(checkedVals);
checkedVals=checkedVals.join(",");
var str2 = checkedVals.replace('/',"");
	
if(checkedVals=="")
	{
	alert("Please Select Bill")
	}
else
	{
	   window.open('${pageContext.request.contextPath}/pdf?url=pdf/showBillsPdf/'+str2);
	}
}
</script>
<script type="text/javascript">
$('#selectAll').click(function (e) {
    $(this).closest('table').find('td input:checkbox').prop('checked', this.checked);
});
</script>


</body>
</html>