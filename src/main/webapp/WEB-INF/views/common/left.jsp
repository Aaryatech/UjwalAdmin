
<html>
<head>
<style>
html {
	overflow: scroll;
	overflow-x: hidden;
}

::-webkit-scrollbar {
	width: 0px; /* remove scrollbar space */
	background: transparent; /* optional: just make scrollbar invisible */
}
/* optional: show position indicator in red */
::-webkit-scrollbar-thumb {
	background: #FF0000;
}
</style>
</head>
<body>
	<aside id="left-panel" class="left-panel">
		<nav class="navbar navbar-expand-sm navbar-default">

			<div class="navbar-header">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#main-menu" aria-controls="main-menu"
					aria-expanded="false" aria-label="Toggle navigation">
					<i class="fa fa-bars"></i>
				</button>
				<a class="navbar-brand" href="./getLogin"> Ujjwal Admin </a> <a
					class="navbar-brand hidden" href="./"> </a>
			</div>

			<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav"
					>
					<%-- <li><a href="${pageContext.request.contextPath}/home"> <i
							class="menu-icon fa fa-dashboard"></i> Dashboard
					</a></li> --%>

					<!--                     <h3 class="menu-title">UI elements</h3>/.menu-title
 -->
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="true"> <i
							class="menu-icon fa fa-table"></i> Masters
					</a>
						<ul class="sub-menu children dropdown-menu ">

                       	<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/addNewUser">Add
									User</a></li>
								
							<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showAddCompany">Add
									Company</a></li>

					
							<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showAddCustomer">Add
									Customer</a></li>
									
							<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showCustList">Customer
								List</a></li>

							<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showAddUom">Add
									Measurement Unit</a></li>

							<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showAddTax">Add
									 Tax</a></li>

							<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showAddPart">Add
									Part</a></li>
									
							<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showPartList">Part
									List</a></li>

							<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showAddLocation">Add
									Location</a></li>
									
							

							
							<%-- 		<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showCustList">
									Customer List</a></li>

							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddUser">Add
									User</a></li>
		<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showCompList">
									Company List</a></li>


							

							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddCustType">Add
									Customer Type</a></li>


							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddCustomer">Add
									Customer</a></li>

<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showUserList"> User
									List</a></li>

					<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddProject">Add
									Project</a></li>



							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showProjectList">
									Project List</a></li>


							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddUom">Add
									Measurement Unit</a></li> 

							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddTax">Add Tax</a></li>

							
							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddCompany">Add
									Company</a></li>
--%>
							


<%-- 
							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddDocTerm">Add
									Terms & Conditions</a></li>

							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showDocTermList">
									Terms & Conditions List</a></li>


							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddPaymentTerm">Add
									Payment Term</a></li>

							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddBankDetail">Add
									Bank Detail</a></li>

							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddVendor">Add
									Vendor</a></li>

							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showVendorList">
									Vendor List</a></li>

							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddEnqGenFact">Add
									Enquiry Source</a></li>
 --%>
						</ul></li>


					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="true"> <i
							class="menu-icon fa fa-calculator"></i> Transaction
					</a>
						<ul class="sub-menu children dropdown-menu ">
						<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showAddBill">Add
									Bill</a></li>
<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showBillList">
									Bill List</a></li>
							<%-- <li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showOrder">View
									order</a></li>


							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddOrder">Add
									Order</a></li>
										<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showOrderList">
									Orders List</a></li>

							<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showAddChalan">Add
									Chalan</a></li>
									<li class="active"><i class="fa fa-puzzle-piece"></i><a
								href="${pageContext.request.contextPath}/showChalanList">
									Chalan List</a></li>

 --%>


						</ul></li>

					<%-- 	<li class="menu-item-has-children dropdown"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="true"> <i class="menu-icon fa fa-table"></i>
						Work
				</a>
					<ul class="sub-menu children dropdown-menu ">

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showAddWorkHeader">Add
								Work</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showWorkHeadList">
								View Added Work</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showWorkList">Work
								List</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showUpdatePayment">Update
								Payment</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showUserAllocation">User
								Allocation</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showDocInOffice">
								Document In Office</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showDocSubmitAtRto">
								Document Submit At RTO</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showActualDocToCust">
								Handover Actual doc to Cust</a></li>



					</ul></li>
 --%>

					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="true"> <i
							class="menu-icon fa fa-calculator"></i> Reports
					</a>
						<ul class="sub-menu children dropdown-menu ">
						<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showItemsReport">ItemWise
									Report</a></li>
							
						<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showCustomerReport">Customer Wise
									Report</a></li>

								<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/billTaxreport">Bill
								Tax Report</a></li>
							
								<li class="active"><i class="fa fa-plus"></i><a
								href="${pageContext.request.contextPath}/showBillwiseReport">Billwise Report</a></li>
							

						</ul></li>

					<%-- 	<li class="menu-item-has-children dropdown"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="true"> <i class="menu-icon fa fa-table"></i>
						Work
				</a>
					<ul class="sub-menu children dropdown-menu ">

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showAddWorkHeader">Add
								Work</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showWorkHeadList">
								View Added Work</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showWorkList">Work
								List</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showUpdatePayment">Update
								Payment</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showUserAllocation">User
								Allocation</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showDocInOffice">
								Document In Office</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showDocSubmitAtRto">
								Document Submit At RTO</a></li>

						<li class="active"><i class="fa fa-puzzle-piece"></i><a
							href="${pageContext.request.contextPath}/showActualDocToCust">
								Handover Actual doc to Cust</a></li>



					</ul></li>
					
					
					

					<%-- 	
				<li class="menu-item-has-children dropdown"><a
					href="${pageContext.request.contextPath}/editHubUser/1"> <i
						class="menu-icon fa fa-table"></i> <spring:message
							code="label.userProfile" />
				</a></li> --%>

					<%-- 	<li class="menu-item-has-children dropdown"><a
					href="${pageContext.request.contextPath}/editHubUser/1"
					class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="true"> <i class="menu-icon fa fa-table"></i> <spring:message
							code="label.userProfile" /> --%>
					</a>
					<%-- 	 --%>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</nav>
	</aside>
	<!-- /#left-panel -->

	<!-- Left Panel -->

</body>
</html>