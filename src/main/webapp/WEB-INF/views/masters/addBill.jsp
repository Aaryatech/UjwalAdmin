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
<c:url var="getTaxByModelId" value="/getTaxByModelId" />
<c:url var="getPartListById" value="/getPartListById" />
<c:url var="getItemForDelete" value="/getItemForDelete" />
<c:url var="getUniqueCompanyCheck" value="/getUniqueCompanyCheck" />
<c:url var="getCustomerListById" value="/getCustomerListById" />
<c:url var="getItemForEdit" value="/getItemForEdit" />
<c:url var="getPartListByModelId" value="/getPartListByModelId" />
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


<!----------------------------------------Dropdown With Search----------------------------------------------- -->

<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/assets/css/lib/chosen/chosen.css">
<!--------------------------------------------------------------------------------------- -->

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
<body onload="isEdit(${isEditBill})">


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
									<span aria-hidden="true"></span>
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
									<span aria-hidden="true"></span>
								</button>
								<strong>Data Submitted Successfully</strong>
							</div>
						</div>
					</c:when>

				</c:choose>

				<div class="col-xs-12 col-sm-12"><form action="${pageContext.request.contextPath}/insertBill"
								id="submitForm" method="post">
					<div class="card">
						<div class="card-header">
							<%-- <div class="col-md-2">
								<strong>${title}</strong>
								
							</div> --%>		<div class="col-md-10"><div class="col-md-3"><b>Invoice No:</b></div>
									<div class="col-md-3">
									<c:if test="${isEditBill==1}">  ${bill.invoiceNo} </c:if><c:if test="${isEditBill==0}"> ${doc.docPrefix}/${year}/${srNo}</c:if>
									
								</div>
						<div class="col-md-2">Date</div>
									<div class="col-md-3">
										<input type="text" id="date" name="date"  style="height:25px;"
											style="width: 100%;" class="form-control" 
											value="${date}" autocomplete="off" /> <span class="error" aria-live="polite"></span>
									
								</div>
					
							</div>
							<div class="col-md-2" align="right">
								<a href="${pageContext.request.contextPath}/showBillList"><strong>Bill List</strong></a>
							</div>

						</div>
						
						<div class="card-body card-block">
							
							<input type="hidden" name="isEditBill" id="isEditBill" value="${isEditBill}"/>
 							<input type="hidden" id="isEdit" name="isEdit" value="0">
        				   <input type="hidden" id="index" name="index" value="0">
        				     <input type="hidden" id="CGST" name="CGST" value="0">
        				      <input type="hidden" id="SGST" name="SGST" value="0">
        				      <input type="hidden" id="IGST" name="IGST" value="0">
        				  
        				 
							<input type="hidden" name="compId" id="compId" value="${sessionScope.conpanyId }" onload="getCompId()">
        				     
							<div class="row">
							
									<div class="col-md-2">Customer Name*</div>
									<div class="col-md-4">
											<select id="cust_id" name="cust_id" style="width: 100%;" class="standardSelect" style="width:99% !important;" 
										
											onchange="getData()">
											<option value="">Select Customer</option>

											<c:forEach items="${custList}" var="cust">
											<c:choose>
											<c:when test="${cust.custId==bill.custId}">
												<option value="${cust.custId}" selected>${cust.custName} </option>
											</c:when>
											<c:otherwise>
											<c:if test="${isEditBill==1}">
										  <option value="${cust.custId}" disabled="disabled">${cust.custName} </option>	
											</c:if>
											<c:if test="${isEditBill==0}">
										  <option value="${cust.custId}">${cust.custName} - ${cust.custVinNo} - ${cust.custVehNo}</option>					
											</c:if>
											</c:otherwise>
											</c:choose>
												
											</c:forEach>
										</select>

									</div>
									<c:set var = "saleType" scope="session" value="${sessionScope.conpanyId}"></c:set>
 					
 					
										<div class="col-md-2">Sale Type*</div>
										<div class="col-md-4">
											<select name="sale_type" id="sale_type" class="standardSelect" style="width:99% !important;"  tabindex="6" 
											 required>
											 <c:choose>
 						<c:when test="${saleType==11 }">
											<option value="Customer Paid">Customer Paid</option>
											<option value="Goodwill">Goodwill</option>
											<option value="Insurance">Insurance</option>
											<option value="Warranty">Warranty</option>
											<option value="Dealer FOC">Dealer FOC </option>
											<option value="Recall">Recall</option>
											<option value="Warranty Deposit">Warranty Deposit</option>
											</c:when>
							<c:otherwise>
											
											<option value="Counter Sale">Counter Sale</option>
											<option value="Workshop Sale">Workshop Sale</option>
											<option value="Accessories">Accessories</option>
											<option value="Free Service">Free Service</option>
											<option value="PDI">PDI </option>
											<option value="Paid Service">Paid Service</option>
											<option value="RF Accidental">RF Accidental</option>
											<option value="RF Mechanical">RF Mechanical</option>
											<option value="Running Repair">Running Repair</option>
											
								
							</c:otherwise>
							</c:choose>
										</select> 
									</div>
									
								</div>
							
								<div class="form-group"></div>
							
							 <div class="row">
									
										<div class="col-md-2">Mode Of Payment*</div>
										<div class="col-md-4">
											<select name="payment_mode" id="payment_mode" class="standardSelect" style="width:99% !important;"  tabindex="6" 
											 required>
											
											<option value="Cash">Cash</option>
											<option value="Credit">Credit</option>
											<option value="NFT">NFT</option>
											<option value="RTGS">RTGS</option>
										
											
										</select> 
									</div>
							
									<div class="col-md-2">Ref. No.*</div>
									<div class="col-md-4">
										<input type="text" id="ref_no" name="ref_no" style="height:30px;"
											style="width: 100%;" class="form-control"
											value="" autocomplete="off" />
										<span class="error" aria-live="polite"></span>

									</div>
							
								</div>
							
								<div class="form-group"></div>
						
							
							<div class="row">
									
										<%-- <div class="col-md-2">Company Name*</div>
										<div class="col-md-4">
											<select name="compId" id="compId" class="standardSelect" style="width:99% !important;"  tabindex="6" 
											onchange="getCompId()" required>
											<option value="">Select Company</option>
											<c:forEach items="${compList}" var="makeList"> 
											<c:choose>
											<c:when test="${makeList.compId==companyId}">
								<option value="${makeList.compId}" selected><c:out value="${makeList.compName}"></c:out> </option>
											
											</c:when>
											<c:otherwise>
											<option value="${makeList.compId}" disabled="disabled"><c:out value="${makeList.compName}"></c:out> </option>
											
											</c:otherwise>
											</c:choose>
											
											
											 </c:forEach>
										</select> 
									</div> --%>
									
								
								</div>
							
								<div class="form-group"></div>
								<div class="row">

								<div class="col-md-2">Mobile No</div>
									<div class="col-md-4">
										<input type="text" id="cust_phone" name="cust_phone" style="height:30px;"
											style="width: 100%;" class="form-control"
											value="" autocomplete="off"
										 />
										<span class="error" aria-live="polite"></span>

									</div>
								<!-- 
								<div class="col-md-2">Email Id</div>
									<div class="col-md-4">
										<input type="text" id="cust_email" name="cust_email"   style="height:30px;"
											style="width: 100%;" class="form-control" autocomplete="off"
											
											maxlength="50" value=""
											onchange="try{setCustomValidity('')}catch(e){}" /> 
									</div> -->
									<div class="col-md-2">GST No</div>
							
									<div class="col-md-4">
										<input type="text" id="cust_gstn" name="cust_gstn"   style="height:30px;"
											onblur="getCheck()" style="width: 100%;" class="form-control"
											autocomplete="off"
											onkeydown="upperCaseF(this)"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>
								</div>

								
								
								<div class="form-group"></div>
								<div class="row">
								<!-- 	<div class="col-md-2">GST No</div>
							
									<div class="col-md-2">
										<input type="text" id="cust_gstn" name="cust_gstn"   style="height:30px;"
											onblur="getCheck()" style="width: 100%;" class="form-control"
											autocomplete="off"
											onkeydown="upperCaseF(this)"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div> -->
								
									
									<div class="col-md-2">Vehicle No</div>
									<div class="col-md-4">
										<input type="text" id="cust_veh_no" name="cust_veh_no"   style="height:30px;"
											style="width: 100%;" class="form-control" autocomplete="off"
										maxlength="20"
										value=""/> <span
											class="error" aria-live="polite"></span>

									</div>
								
				
									
									<div class="col-md-2">VIN No.</div>
									<div class="col-md-4">
										<input type="text" id="cust_chasi_no" name="cust_chasi_no"   style="height:30px;"
											style="width: 100%;" class="form-control"
											value="" autocomplete="off"
											oninvalid="setCustomValidity('Please enter chasi no')"
											onchange="try{setCustomValidity('')}catch(e){}"
											/> <span class="error"
											aria-live="polite"></span>

									</div>
									</div>
									<hr>
							
								<div class="form-group"></div>
								
								<div class="row">
							<div class="col-md-1">Model</div>

									<div class="col-md-3">
										<select id="model_id" name="model_id" class="standardSelect" style="width:99% !important;"  style="width:90%;"										
											onchange="onModelChange(this.value)">
											<option value="">Select Model</option>
												<c:forEach items="${modelList}" var="model">
												<option value="${model.modelId}">${model.modelName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-md-1">Part</div>

									<div class="col-md-4">
										<select id="part_id" name="part_id" class="standardSelect" style="width:99% !important;"  style="width:90%;"										
											onchange="getPartDetail(this.value)">
											<option value="">Select Part</option>
												<c:forEach items="${pList}" var="part">
												<option value="${part.partId}">${part.partName} - ${part.partNo}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-md-1">MRP</div>

									<div class="col-md-2">
									
										<input type="text" id="part_mrp" name="part_mrp"   value="0.0"  style="height:30px;" onchange="cal()"
											style="width: 40%;" class="form-control" autocomplete="off"/> 
								
									</div>
									
								</div>
									
	                 			
								<div class="form-group"></div>
							
								<div class="row">
								<div class="col-md-1">Qty</div>

									<div class="col-md-2">
										<input type="text" id="qty" name="qty"  value="1" min="0"  style="height:30px;"  onchange="cal()"
											style="width: 30%;" class="form-control" autocomplete="off"/> 
									</div>
									<div class="col-md-1">Disc %</div>

									<div class="col-md-2">
										<input type="text" id="disc" name="disc"  value="0.0"  style="height:30px;" onchange="cal()"
											style="width: 40%;" class="form-control" autocomplete="off"/> 
									</div>
								
								<div class="col-md-1">Dis Amt</div>

									<div class="col-md-2">
									
										<input type="text" id="disc_amt" name="disc_amt"  value="0.0" readonly style="height:30px;"
											style="width:50%;" class="form-control" autocomplete="off"/> 
								
									</div>	
										<div class="col-md-1">Taxable</div>

									<div class="col-md-2">
									
										<input type="text" id="taxable_amt" name="taxable_amt"  value="0.0" readonly style="height:30px;"
											style="width:50%;" class="form-control" autocomplete="off"/> 
								
									</div>	
									</div>
										<div class="form-group"></div>
							
								<div class="row">
								
									<div class="col-md-1">Tax Amt</div>

									<div class="col-md-2">
									
										<input type="text" id="tax_amt" name="tax_amt"  value="0.0" readonly style="height:30px;"
											style="width:50%;" class="form-control" autocomplete="off"/> 
								
									</div>	
									<div class="col-lg-2">
									<input type="button" class="btn btn-primary" value="Add" id="AddButton"
									style="width: 63px; background-color: #272c33;" onclick="add()">

								</div>
									</div>
								
						
						
						<div class="card-body card-block">
							

								<table id="billTable"
									class="table table-striped table-bordered" >
									<thead>
										<tr>
										<!-- <th class="check" style="text-align: center; width: 5%;"><input
												type="checkbox" name="selAll" id="selAll" /> Select All</th> --> 
											<th style="text-align: center; width: 5%;">Sr</th>
											<th style="text-align: center">Part_Name</th>
											 <th style="text-align: center">UOM</th>
											<th style="text-align: center">Qty</th>
											<th style="text-align: center">MRP</th>
											<th style="text-align: center">Disc %</th>
											<th style="text-align: center">Disc Amt</th>
											<th style="text-align: center">Tax %</th>
											<th style="text-align: center">Total Tax</th>
											<th style="text-align: center">Taxable Amt</th>
											<th style="text-align: center">Total</th> 
											<th style="text-align: center; width: 5%;">Action</th>
	
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${bill.billDetailList}" var="detail" varStatus="count">
											<tr>
											 	<td style="text-align: center">${count.index+1}</td>


												<td style="text-align: left"><c:out
														value="${detail.partName}" /></td>

												<td style="text-align: left"><c:out
														value="${detail.uomName}" /></td>

												<td style="text-align: center">${detail.qty}</td>
												
												<td style="text-align: center">${detail.mrp}</td>
												
												<td style="text-align: center">${detail.discPer}</td>
												
												<td style="text-align: center">${detail.discRs}</td>
											    <td style="text-align: center">${detail.cgstPer+detail.sgstPer}</td>
												
												<td style="text-align: center">${detail.totalTax}</td>
										    	<td style="text-align: center">${detail.taxableAmount}</td>
											    <td style="text-align: center">${detail.grandTotal}</td>

												<td style="text-align: center"><a
													href="#" onclick="callEdit(${detail.billDetailId},${count.index})"><i
														class="fa fa-edit" title="Edit"></i> 
														<span class="text-muted"></span></a>
													&nbsp;&nbsp; <a
													href="#"
													 onclick="callDelete(${detail.billDetailId},${count.index})"><i
														class="fa fa-trash-o" title="Delete"></i></a></td>


											</tr>
										</c:forEach>
									</tbody>
									</table>
									
					<div class="row">
				
									<div class="col-md-3">Remark</div>
									<div class="col-lg-3">
							<input type="text" id="remark_new" name="remark_new" required value="NA"
											style="width: 100%;" class="form-control" autocomplete="off"/> 
								</div>
						
							
									<div class="col-md-3" style="font-size:bold">Grand Total</div>
									<div class="col-lg-3">
							<input type="text" id="total_amt" readonly name="total_amt" value="${bill.grandTotal}" 
											style="width: 100%;" class="form-control"/> 
								</div>
								
								</div><div class="form-group"></div>
								<div class="row"><div class="col-lg-3"></div><div class="col-lg-3"></div>	<div class="col-lg-3"></div>	<div class="col-lg-3">
									<input type="submit" class="btn btn-primary" value="Submit"
										id="submitButton"
										style="align-content: center; width: 113px; margin-left: 40px; background-color: #272c33;">

								</div></div>
						</div>
					
					
						
					
					</div></form>
						
						
					
				</div>
			</div>


		</div>
		<!-- .animated -->
	</div>
	<!-- .content -->

	<!-- .animated -->
	<!-- .content -->

</div>
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
<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/chosen/chosen.jquery.js"
		type="text/javascript"></script>


<script type="text/javascript">
$(function () {
	 var cust_id = $("#cust_id");
	 var cust_model_no = $("#ref_no");
	

	 $("#submitButton").click(function () {
		 if (cust_id.val() == "") {
	          //If the "Please Select" option is selected display error.
	          alert("Please select customer !");
	          return false;
	      }   
		 else if (cust_model_no.val() == "") {
          //If the "Please Select" option is selected display error.
          alert("Please enter reference No. !");
          return false;
      }    
      else{
          return true;
      }
    });
});

</script>

	<script>
	$("#part_mrp").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
        if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
            event.preventDefault();
        }
    });
	
	$("#disc").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
        if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
            event.preventDefault();
        }
    });
	
	$("#qty").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
        if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
            event.preventDefault();
        }
    });
	
	</script>
	`

	<script
		src="${pageContext.request.contextPath}/resources/init.js"
		type="text/javascript" charset="utf-8"></script>
		
		<script type="text/javascript">
		function onModelChange(modelId,partId)
		{
			var valid = true;
			if (modelId == null || modelId == "") {
				valid = false;
				alert("Please select Model");
				var html='<option value="">Select Part</option>';
				html += '</option>';
				$('#part_id').html(html);
				$("#part_id").trigger("chosen:updated");
			}
			if (valid == true) {

				$.getJSON('${getPartListByModelId}', {
					modelId : modelId,
					ajax : 'true',
				},

				function(data) {
					
					var len = data.length;
					//alert("data " +JSON.stringify(data));
					var html='<option value="">Select Part</option>';
				
					for (var i = 0; i < len; i++) {
					
						if(partId==data[i].partId){
						html += '<option value="' + data[i].partId + ' "selected>'
								+data[i].partName+' - -'+data[i].partNo+ '</option>';
						}
						else
							{
							html += '<option value="' + data[i].partId + '">'
							+data[i].partName+' - - '+data[i].partNo+ '</option>';
							}
					}
					html += '</option>';
					$('#part_id').html(html);
					$("#part_id").trigger("chosen:updated");
				
				});
				
			}
			
			
			
		}
		</script>
	<script type="text/javascript">
			function getCompId() { 
		
			var compId = document.getElementById("compId").value;
			alert("id="+compId);
			var valid = true;
			if (compId == null || compId == "") {
				valid = false;
				alert("Please select Company");
				
				var html='<option value="">Select Customer</option>';
				html += '</option>';
				$('#cust_id').html(html);
				$("#cust_id").trigger("chosen:updated");
			}

			if (valid == true) {

				$.getJSON('${getCustomerListById}', {
					compId : compId,
					ajax : 'true',
				},

				function(data) {
					
					var len = data.length;
					//alert("data " +JSON.stringify(data));
					var html='<option value="">Select Customer</option>';

					for (var i = 0; i < len; i++) {

						html += '<option value="' + data[i].custId + '">'
								+ data[i].custName +"'--'"+'</option>';

					}
					html += '</option>';
					$('#cust_id').html(html);
					$("#cust_id").trigger("chosen:updated");
				
				});
			}//end of if

		}
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

	<script type="text/javascript">
	function isEdit(isEditBill) { 
		if(isEditBill==1)
			{
			getData();
			}
	}
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
				//	alert(data.custVehNo+"- "+data.custVinNo+" -"+data.custGstn+" -"+data.custEmail+" -"+data.custPhone);
					//document.getElementById("cust_email").value=data.custEmail
					document.getElementById("cust_phone").value=data.custPhone
					document.getElementById("cust_gstn").value=data.custGstn
					document.getElementById("cust_veh_no").value=data.custVehNo
					document.getElementById("cust_chasi_no").value=data.custVinNo
				
				
				});
			}//end of if

		}
	</script>
<script type="text/javascript">
			function getPartDetail(partId) { 
		
			//var partId = document.getElementById("part_id").value;
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
					document.getElementById("part_mrp").value=data.partMrp;
					document.getElementById("CGST").value=data.cgstPer;
					document.getElementById("SGST").value=data.sgstPer;
					document.getElementById("IGST").value=data.igstPer;
					
					cal(data.partMrp);
				});
			}//end of if

		}
	</script>
	
	<script type="text/javascript">
	function cal()
	{
		var mrp =parseFloat(document.getElementById("part_mrp").value);
		var cgstPer =parseFloat(document.getElementById("CGST").value);
		var sgstPer =parseFloat(document.getElementById("SGST").value);
		var igstPer =parseFloat(document.getElementById("IGST").value);
		var discPer =parseFloat(document.getElementById("disc").value);
		var qty = parseFloat(document.getElementById("qty").value);
		var modelId = document.getElementById("model_id").value ;
		
		$.getJSON('${getTaxByModelId}',
				{
					modelId : modelId, 
					 ajax:'true',
				},
			 	function(data) {
					//alert(data);
					var mrpBaseRate = 0;
					if(data==1){
						 mrpBaseRate=mrp;
					}else{
					mrpBaseRate=(mrp*100)/(100+sgstPer+cgstPer);
					mrpBaseRate=(mrpBaseRate).toFixed(2);
					}
					var taxableAmt =  mrpBaseRate * qty;	
					
					var discAmt = ((taxableAmt * discPer) / 100);		
					document.getElementById("disc_amt").value =discAmt.toFixed(2);
					
					taxableAmt = taxableAmt - discAmt;	

					var sgstRs = parseFloat((taxableAmt * sgstPer) / 100);	
					
					
					var cgstRs = parseFloat((taxableAmt * cgstPer) / 100);	
					
					
					var igstRs = parseFloat((taxableAmt * igstPer) / 100);		
					
					
					var totalTax = sgstRs + cgstRs;
					
					var grandTotal = parseFloat(totalTax + taxableAmt);		
					grandTotal=(grandTotal).toFixed(2);
					
					document.getElementById("tax_amt").value = totalTax.toFixed(2);
					
					document.getElementById("taxable_amt").value = taxableAmt.toFixed(2);
				 
			 		 
					});
		
	
	}
	</script>
<script type="text/javascript">
function add(){
	
	var partId=parseInt(document.getElementById("part_id").value);
	var qty =parseFloat(document.getElementById("qty").value);
	var isEdit = parseInt(document.getElementById("isEdit").value);
	var index= parseInt(document.getElementById("index").value);
	var partMrp =parseFloat(document.getElementById("part_mrp").value);
	var disc =parseFloat(document.getElementById("disc").value);
	var modelId =parseInt(document.getElementById("model_id").value);
	 
	$.getJSON('${addPartDetail}',
					{
						 isEdit:isEdit,
						 index:index,
						 partId:partId,
						 qty:qty,
						 partMrp:partMrp,
						 disc:disc,
						 modelId:modelId,
						 ajax:'true',
					},
				 	function(data) {
						
				 var gtotal=0;
				 var dataTable = $('#billTable').DataTable();
			     dataTable.clear().draw();

				$.each(data,function(i, v) {
					if(v.delStatus==0){	
					                var total=v.cgstPer+v.sgstPer;
									gtotal=gtotal+v.grandTotal;
									
							 var acButton = '<a href="#" class="action_btn" onclick="callEdit('
											+ v.billDetailId
											+ ','
											+ i
											+ ')"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="action_btn" onclick="callDelete('
												+ v.billDetailId
												+ ','
												+ i
												+ ')"><i class="fa fa-trash"></i></a>' 
									dataTable.row
											.add(
													[
															i + 1,
															v.partName,
															v.uomName,
															v.qty,
															v.mrp,
															v.discPer.toFixed(2),
															v.discRs.toFixed(2),
															v.sgstPer+v.cgstPer,
															v.totalTax,
															v.taxableAmount,
															v.grandTotal,
															
															acButton
															 ])
											.draw();
					}
								});  
					
				document.getElementById("total_amt").value = gtotal;
				 		 
						} 
					
	);
	 document.getElementById("part_id").value = "";
		$("#part_id").trigger("chosen:updated");
		document.getElementById("model_id").value = "";
		$("#model_id").trigger("chosen:updated");
	document.getElementById("qty").value = "1";
	document.getElementById("part_mrp").value = "0.0";
	document.getElementById("disc").value = "0.0";
	//document.getElementById("remark").value = "NA";   
	document.getElementById("isEdit").value = 0;
	document.getElementById("tax_amt").value = "0.0";
	document.getElementById("taxable_amt").value = "0.0";
	document.getElementById("disc_amt").value = "0.0";
	document.getElementById("CGST").value="0.0";
	document.getElementById("SGST").value="0.0";
	document.getElementById("IGST").value="0.0";
	}
	
	
</script>
<script type="text/javascript">

function callEdit(billDetailId, index) { 
	document.getElementById("index").value =index;
	$
			.getJSON(
					'${getItemForEdit}',
					{
						billDetailId:billDetailId,
						index : index,
						ajax : 'true',

					},
					function(data) {
						onModelChange(data.ex_int1,data.partId);
						
						document.getElementById("model_id").value =data.ex_int1;
						$("#model_id").trigger("chosen:updated");
				
					
						document.getElementById("qty").value = data.qty;
						//document.getElementById("remark").value = data.remark;
						document.getElementById("isEdit").value = 1;

						getPartDetail(data.partId);
						
						document.getElementById("disc").value =data.discPer; 
						
						
					});

}
</script>
<script type="text/javascript">

function callDelete(billDetailId, index) {
	var status=confirm("Do you want to delete Part?");
	if(status==true){
	$
			.getJSON(
					'${getItemForDelete}',
					{
						index : index,
						billDetailId:billDetailId,
						ajax : 'true',

					},
					function(data) {

				 var dataTable = $('#billTable')
						.DataTable();
				dataTable.clear().draw();

				$.each(data,function(i, v) {
									
							if(v.delStatus==0){		
					 var acButton = '<a href="#" class="action_btn" onclick="callEdit('
							+ v.billDetailId
							+ ','
							+ i
							+ ')"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="action_btn" onclick="callDelete('
								+ v.billDetailId
								+ ','
								+ i
								+ ')"><i class="fa fa-trash"></i></a>' 
									dataTable.row
											.add(
													[
														i + 1,
														v.partName,
														v.uomName,
														v.qty,
														v.mrp,
														v.discPer.toFixed(2),
														v.discRs.toFixed(2),
														v.sgstPer+v.cgstPer,
														v.totalTax,
														v.taxableAmount,
														v.grandTotal,
														
														acButton
															 ])
											.draw();
							}
								});  
					
				});
					}
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
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$('input[id$=date]').datepicker({
				dateFormat : 'dd-mm-yy'
			});
			
			
		});
	</script>
</body>
</html>