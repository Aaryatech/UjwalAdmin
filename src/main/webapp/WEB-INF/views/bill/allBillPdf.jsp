<%@page contentType="text/html; charset=ISO8859_1"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <jsp:useBean id="currency" scope="session"
             class="com.ujwal.billsoft.commons.Currency"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Bill PDF</title>

</head>
<body>
 	 <c:forEach items="${billHeaderList}" var="billHeaderRes" varStatus="cnt"> 
					<h6 align="center">TAX INVOICE</h6>
<table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-left:1px solid #313131;border-right:1px solid #313131;border-top:1px solid #313131;">
  <tr>
    <td colspan="6" rowspan="2" width="50%" style="border-bottom:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
   <p><img src="file:///${imgUrl}${billHeaderRes.logo}" alt="Smiley face" style="height:130px; width:250px;"></img>
</p> 
     <p style="color:black; font-size:20px; text-align:left; margin:0px;font-weight: bold;">${billHeaderRes.compName}</p>
 <%-- <p style="color:#000; font-size:11px; text-align:left;margin:0px;">${billHeaderRes.address} ,<br></br> Nashik, Maharashtra 422007 <br></br>GSTIN/UIN :  ${billHeaderRes.gstid} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; State : Maharashtra, Code: 27  <br></br>Phone :  ${billHeaderRes.phoneNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="float:right;">Email : ${billHeaderRes.email}</span></p>
 --%>
</td>

    <td width="25%" colspan="3" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:10px;color:#000; font-size:15px; text-align:center">
    	<p style="color:#000; font-size:11px; text-align:left;margin:0px;">Invoice No. : <!-- </p> 
      	<p style="color:#000; font-size:11px; text-align:left;margin:0px;"> --><b>${billHeaderRes.invoiceNo}</b></p> 
  
 </td>
    <td colspan="3" width="25%" style="border-left:1px solid #313131; border-bottom:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
   <p style="color:#000; font-size:11px; text-align:left;margin:0px;">Date : <!-- </p> 
      	<p style="color:#000; font-size:11px; text-align:left;margin:0px;"> --><b>${billHeaderRes.billDate}</b></p> 
    </td>
    
  </tr>
 <tr>
     <td width="30%" colspan="3" style="border-left:1px solid #313131;border-bottom:1px solid #313131;  padding:30px;color:#000; font-size:15px; text-align:center">
    	<p style="color:#000; font-size:15px; text-align:left; margin:0px;">Customer :  <b>${billHeaderRes.custName}</b></p> 
       <p style="color:#000; font-size:12px; text-align:left; margin:0px;"> GSTIN/UIN : ${billHeaderRes.custGstn}</p>
        <p style="color:#000; font-size:12px; text-align:left; margin:0px;">State :  Maharashtra, Code: 27</p>
            <p style="color:#000; font-size:12px; text-align:left; margin:0px;">Contact : ${billHeaderRes.custPhone}</p> 
  
 </td>
    <td colspan="3" width="25%" style="border-left:1px solid #313131;border-bottom:1px solid #313131;  padding:10px;color:#FFF; font-size:15px;">
   <p style="color:#000; font-size:11px; text-align:left;margin:0px;"> </p> <br>
      	<p style="color:#000; font-size:11px; text-align:left;margin:0px;">Sale Type : <b>${billHeaderRes.saleType }</b></p> 
 		<p style="color:#000; font-size:11px; text-align:left;margin:0px;"></p>
    </td>
    </tr>
  <tr>
    <td width="50%"  rowspan="4" colspan="6" style="padding:8px;color:#FFF; font-size:14px;">
        <p style="color:#000; font-size:11px; text-align:left;margin:0px;">${billHeaderRes.address} ,<br></br> Nashik, Maharashtra 422007 <br></br>GSTIN/UIN :  ${billHeaderRes.gstid} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; State : Maharashtra, Code: 27  <br></br>Phone :  ${billHeaderRes.phoneNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email : ${billHeaderRes.email}</p>

    </td>

    
    <td width="25%" colspan="3" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:10px;color:#000; font-size:15px; text-align:center">
    	<p style="color:#000; font-size:11px; text-align:left;margin:0px;">Vehicle No. : <b>${billHeaderRes.custVehNo}</b></p> 
      	<p style="color:#000; font-size:11px; text-align:left;margin:0px;"></p> 
 
 </td>
    <td colspan="3" width="25%" style="border-left:1px solid #313131; border-bottom:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
   <p style="color:#000; font-size:11px; text-align:left;margin:0px;">Payment : <b>${billHeaderRes.exVar1 } </b></p> 
      	<p style="color:#000; font-size:11px; text-align:left;margin:0px;"> </p> 
    </td>
    
  </tr>
 <tr>
     <td width="25%" colspan="3" style="border-left:1px solid #313131; padding:10px;color:#000; font-size:15px; text-align:center">
    	<p style="color:#000; font-size:11px; text-align:left;margin:0px;">VIN No : <b>${billHeaderRes.custVinNo}</b> </p> 
     	<p style="color:#000; font-size:11px; text-align:left;margin:0px;"></p>
 </td>
     <td width="25%" colspan="3" style="border-left:1px solid #313131; padding:10px;color:#000; font-size:15px; text-align:center">
    	<p style="color:#000; font-size:11px; text-align:left;margin:0px;">Reference No : <b>${billHeaderRes.exVar2}</b></p>
      	<p style="color:#000; font-size:11px; text-align:left;margin:0px;"></p>
 </td>
    </tr>

      </table>
      
      <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-right:1px solid #313131">
  <tr>
    <td rowspan="2"  width="2%"  style="border-bottom:1px solid #313131; border-top:1px solid #313131;border-left:1px solid #313131; padding:5px;color:#000; font-size:10px;">No.</td>
    <td align="left" width="30%" rowspan="2" style="border-bottom:1px solid #313131;border-top:1px solid #313131; border-left:1px solid #313131; padding:15px;color:#000; font-size:10px;text-align: left">Decription Of Goods.</td>
   <td align="center" width="8%" rowspan="2" style="border-bottom:1px solid #313131;border-top:1px solid #313131; border-left:1px solid #313131; padding:0.2px;color:#000; font-size:10px;">HSN/SAC</td>
 
         <td align="center" width="6%" rowspan="2" style="border-bottom:1px solid #313131;border-top:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">UOM </td>
     
 <td align="center" width="6%" rowspan="2" style=" border-bottom:1px solid #313131;border-top:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Qty</td>
    <td align="center" width="6%" rowspan="2" style="border-bottom:1px solid #313131; border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Rate</td>
             <td align="center" width="8%" rowspan="2" style="border-bottom:1px solid #313131;border-top:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Taxable Amt</td>
    
       <td align="center" width="10%" colspan="2" style="border-left:1px solid #313131;  border-top:1px solid #313131;padding:10px;color:#000; font-size:10px; text-align:center;"> CGST</td>
    <td align="center" width="10%" colspan="2" style="border-left:1px solid #313131; border-top:1px solid #313131;padding:10px;color:#000; font-size:10px;text-align:center;">SGST</td>
        <td align="center" width="10%" colspan="2" style="border-left:1px solid #313131; border-top:1px solid #313131;padding:10px;color:#000; font-size:10px;text-align:center;">Disc.</td>
    
    <td align="center" width="10%" rowspan="2" style="border-bottom:1px solid #313131; border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Amount</td>


 
  </tr>
  <tr>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">CGST % </td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131;  padding:4px;color:#000; font-size:10px;">Amount</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">SGST %</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">Amount</td>
<td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">Disc. %</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">Amount</td>

 
  </tr>
 
  <c:set var = "totalQty" value = "0"/>
  <c:set var = "taxableAmt" value = "0"/>
   <c:set var = "totalAmt" value = "0"/>
    <c:set var = "totalCgst" value = "0"/>
      <c:set var = "totalSgst" value = "0"/>
      <c:set var = "totalDisc" value = "0"/>
   <c:forEach items="${billHeaderRes.getBillDetail}" var="billDetails" varStatus="count">
     <tr>
    <td  style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;">${count.index+1}</td>
    <td style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">${billDetails.partName}</td>
    <td align="left" style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">${billDetails.hsnCode}</td>
        <td align="left" style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">${billDetails.uomName}</td>
   
    
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.qty}"/></td>
			  <c:set var = "totalQty" value = "${totalQty+billDetails.qty}"/>			
    <td align="center" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;">${billDetails.mrp}</td>
   	
   
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.taxableAmount}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.cgstPer}"/></td>
								 <c:set var = "taxableAmt" value = "${taxableAmt+billDetails.taxableAmount}"/>
								   <c:set var = "totalAmt" value = "${totalAmt+billDetails.grandTotal}"/>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.cgstRs}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.sgstPer}"/></td>
								  <c:set var = "totalCgst" value = "${totalCgst+billDetails.cgstRs}"/> 
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.sgstRs}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.discPer}"/></td>
	<td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.discRs}"/></td>
	<td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.grandTotal}"/></td>							
						 <c:set var = "totalSgst" value = "${totalSgst+billDetails.sgstRs}"/> 
						  <c:set var = "totalDisc" value = "${totalDisc+billDetails.discRs}"/>  
								
	 		  
  </tr>
      </c:forEach> 
    <%--   <c:forEach items="${billHeaderRes.billDetailReportList}" var="billDetails" varStatus="count">
   <c:set var = "billQty" value = "${billDetails.billQty-(billDetails.returnQty+billDetails.distLeakageQty)}"/>
  <tr>
    <td  style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;">${count.index+1}</td>
    <td style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">${billDetails.itemName}</td>
    <td align="left" style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">${billDetails.hsnCode}</td>
        <td align="left" style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">${billDetails.batchNo}</td>
    
    
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billQty}"/></td>
			  <c:set var = "totalQty" value = "${totalQty+billQty}"/>					
    <td align="center" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;">${billDetails.uom} </td>
     <c:set var = "taxPer" value = "${billDetails.cgstPer+billDetails.sgstPer}"/>	
     <c:set var = "baseRate" value = "${((billDetails.rate)*100)/(100+taxPer)}"/>	
      <c:set var = "taxableAmt" value = "${baseRate*billQty}"/>	
        <c:set var = "cgstRs" value = "${(taxableAmt*billDetails.cgstPer)/100}"/>	
          <c:set var = "sgstRs" value = "${(taxableAmt*billDetails.sgstPer)/100}"/>	
    
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${baseRate}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.cgstPer}"/></td>
								   <c:set var = "totalAmt" value = "${totalAmt+taxableAmt}"/>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${cgstRs}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.sgstPer}"/></td>
								  <c:set var = "totalCgst" value = "${totalCgst+cgstRs}"/>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${sgstRs}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${taxableAmt}"/></td>
						 <c:set var = "totalSgst" value = "${totalSgst+sgstRs}"/>
								
	 		  
  </tr>
  </c:forEach> --%>
   <tr>
    <td  align="left" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
    <td align="left" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b>Total</b></td>
   
       <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
    <td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalQty}"/></b></td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${taxableAmt}"/></b></td>
     <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
 
 <td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalCgst}"/></b></td>
								    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
								
    <td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalSgst}"/></b></td>
   	<td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
   
	<td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalDisc}"/></b></td>
   
	<td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalAmt}" var="wtotal"/> ${wtotal}</b></td>
	  				
  </tr>  
   <tr>
   
    <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
        <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
           <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
           <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
       <td align="center" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
     <td style="border-bottom:1px solid #313131; font-size:0px;">-</td>  <td style="border-bottom:1px solid #313131; font-size:0px;">-</td>
    <td style="border-bottom:1px solid #313131; font-size:0px;">-</td><td style="border-bottom:1px solid #313131;padding:4px;color:#000; font-size:0px;">-</td><td style="border-bottom:1px solid #313131;font-size:0px;">-</td><td style="border-bottom:1px solid #313131;padding:4px;color:#000; font-size:0px;">-</td><td style="border-bottom:1px solid #313131;font-size:0px;">-</td><td style="border-bottom:1px solid #313131;font-size:12px;"><b>Total:</b></td>
    <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalAmt}"/></b></td>
  </tr>
</table>

    
  <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-right:1px solid #313131;border-left:1px solid #313131;">
  
 
    
   <%-- <tr>
  <fmt:formatNumber type="number"	maxFractionDigits="2" minFractionDigits="2" value="${totalCgst+totalSgst}" var="wtax"/>
  
    <td colspan="8" width="50%" style=" padding:8px;color:#000; font-size:12px;">Tax Amt(in words):
     <p style="color:#000; font-size:12px; text-align:left;margin:0px;"><b>${currency.convertToIndianCurrency(wtax)}</b></p>
</td>
    <td colspan="4" width="50%" rowspan="2" style="padding:8px;color:#000;border-left:1px solid #313131; font-size:12px;">Amt.Chargeble (in words): <p style="color:#000; font-size:12px; text-align:left;margin:0px;">
   <b>${currency.convertToIndianCurrency(wtotal)} </b></p>
    </td>
  </tr> --%>
  
     <tr>
    <td colspan="4" width="25%"  style="text-align:center; border-top:1px solid #313131; padding:8px;color:#000; font-size:12px;"></td>
      <td colspan="4" width="25%"  style="text-align:center; border-top:1px solid #313131; padding:8px;color:#000; font-size:12px;"></td>
         </tr>
   
<%--     
  <tr>
    <td colspan="8" width="60%"  style="border-top:1px solid #313131; padding:8px;color:#000; font-size:13px;">
Company's VAT TIN: <br />
Company's CST No:<br />
Company's PAN: ${billHeaderRes.compPanNo}
</td>
    <td colspan="4" width="60%" style="border-top:1px solid #313131;border-left:1px solid #313131;  padding:8px;color:#000;font-size:15px;">     
    <p style="color:#000; font-size:13px; text-align:left;margin:0px;">
    Company's Bank Details:<br />
Bank Name:  ${billHeaderRes.bankDetail.bankName}<br />
A/C No:     ${billHeaderRes.bankDetail.accNo}<br />
Branch & IFSC Code: ${billHeaderRes.bankDetail.bankAddress} ---- ${billHeaderRes.bankDetail.bankIfsc}
    </p></td>
  </tr>  --%>
  <tr>
    <td colspan="12" width="100%"  style="border-top:1px solid #313131; padding:8px;color:#000; font-size:13px;">&nbsp;
Declaration: We declare that this invoice shows actual price of goods described .
</td>
  </tr>
  <tr>
    <td colspan="8"  width="60%" style="border-bottom:1px solid #313131;border-top:1px solid #313131; padding:0px;color:#000; font-size:11px;">
     <p style="color:#000; font-size:12px; text-align:center;margin:0px;">Customer's Seal & Signature<br /></p>
</td>
    <td  align="center" colspan="4" width="40%" style="border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000;font-size:12px;">
  <b>  for ${billHeaderRes.compName}</b><br /><br /><br />
    
    Authorised Signature
    
    </td>
  </tr>
  
</table>

<p>-----------------------------------------------------------------------------------------------------------------------------------------</p>
 <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-right:1px solid #313131;border-left:1px solid #313131;">
  
 
    
   <%-- <tr>
  <fmt:formatNumber type="number"	maxFractionDigits="2" minFractionDigits="2" value="${totalCgst+totalSgst}" var="wtax"/>
  
    <td colspan="8" width="50%" style=" padding:8px;color:#000; font-size:12px;">Tax Amt(in words):
     <p style="color:#000; font-size:12px; text-align:left;margin:0px;"><b>${currency.convertToIndianCurrency(wtax)}</b></p>
</td>
    <td colspan="4" width="50%" rowspan="2" style="padding:8px;color:#000;border-left:1px solid #313131; font-size:12px;">Amt.Chargeble (in words): <p style="color:#000; font-size:12px; text-align:left;margin:0px;">
   <b>${currency.convertToIndianCurrency(wtotal)} </b></p>
    </td>
  </tr> --%>
  
     <!-- <tr>
    <td colspan="4" width="25%"  style="text-align:center; border-top:1px solid #313131; padding:8px;color:#000; font-size:12px;"></td>
      <td colspan="4" width="25%"  style="text-align:center; border-top:1px solid #313131; padding:8px;color:#000; font-size:12px;"></td>
         </tr> -->
   
<%--     
  <tr>
    <td colspan="8" width="60%"  style="border-top:1px solid #313131; padding:8px;color:#000; font-size:13px;">
Company's VAT TIN: <br />
Company's CST No:<br />
Company's PAN: ${billHeaderRes.compPanNo}
</td>
    <td colspan="4" width="60%" style="border-top:1px solid #313131;border-left:1px solid #313131;  padding:8px;color:#000;font-size:15px;">     
    <p style="color:#000; font-size:13px; text-align:left;margin:0px;">
    Company's Bank Details:<br />
Bank Name:  ${billHeaderRes.bankDetail.bankName}<br />
A/C No:     ${billHeaderRes.bankDetail.accNo}<br />
Branch & IFSC Code: ${billHeaderRes.bankDetail.bankAddress} ---- ${billHeaderRes.bankDetail.bankIfsc}
    </p></td>
  </tr>  --%>
  <tr>
    <td colspan="12" width="100%"  style="border-top:1px solid #313131; padding:8px;color:#000; font-size:13px;">&nbsp;
		Grand Total : <b>${totalAmt}</b>
</td>
  </tr>
   <!-- <tr>
    <td colspan="12" width="100%"  style="border-top:1px solid #313131; padding:8px;color:#000; font-size:13px;">&nbsp;
		Rupees  : 
</td>
  </tr> -->
  <tr>
    <td colspan="8"  width="60%" style="border-bottom:1px solid #313131;border-top:1px solid #313131; padding:10px;color:#000; font-size:11px;">
     <p style="color:#000; font-size:13px; margin:0px;">Invoice No. : <b>${billHeaderRes.invoiceNo}</b></p><br />
     <p style="color:#000; font-size:13px; margin:0px;">Customer Name : <b>${billHeaderRes.custName}</b></p><br />
     
</td>
    <td  align="center" colspan="4" width="40%" style="border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:none; padding:10px;color:#000;font-size:12px;">
  <p style="color:#000; font-size:13px; margin:0px;">Date : <b>${billHeaderRes.billDate}</b></p><br />
  <p style="color:#000; font-size:13px; margin:0px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sale Type : <b>${billHeaderRes.saleType }</b></p><br />
    
    </td>
  </tr>
  
</table>
<div style="text-align: center;font-size: 12px;">This is computer generated invoice.</div>

<div style="page-break-after: always;"></div>
 </c:forEach> 

</body>
</html> 