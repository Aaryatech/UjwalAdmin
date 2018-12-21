package com.ujwal.billsoft.controllers;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.commons.DateConvertor;
import com.ujwal.billsoft.models.BillDetails;
import com.ujwal.billsoft.models.BillHeader;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MCustomer;
import com.ujwal.billsoft.models.MGetPart;
import com.ujwal.billsoft.models.MPart;
import com.ujwal.billsoft.models.MUser;
import com.ujwal.billsoft.models.Document;
import com.ujwal.billsoft.models.GetBillHeader;
import com.ujwal.billsoft.models.Info;
@Controller
public class UjwalBillController {

	RestTemplate rest=new RestTemplate();;
	List<BillDetails> detailList = new ArrayList<BillDetails>();
	BillHeader billHeader=new BillHeader();

	@RequestMapping(value="/showAddBill", method=RequestMethod.GET)
	public ModelAndView addShowBillForm() {
		
		ModelAndView mav = new ModelAndView("masters/addBill");
	try {
		detailList = new ArrayList<BillDetails>();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();		
			
		List<MCompany> custList = rest.getForObject(Constants.url + "/ujwal/getAllCustomer", List.class);
		mav.addObject("custList", custList);
		
		List<MPart> partList = rest.getForObject(Constants.url + "/ujwal/getAllPart", List.class);
		mav.addObject("pList", partList);
		
		mav.addObject("date", dateFormat.format(date));
		mav.addObject("title", "Add Bill");
		mav.addObject("isEditBill",0);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("docCode", 1);
		Document doc = rest.postForObject(Constants.url + "getDocument", map, Document.class);
		mav.addObject("doc", doc);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return mav;		
	}

@RequestMapping(value = "/getCustById", method = RequestMethod.GET)
public @ResponseBody MCustomer getCustById(HttpServletRequest request, HttpServletResponse response) {

	MCustomer cust=null;
	try {
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	int custId = Integer.parseInt(request.getParameter("custId"));

	map.add("id", custId);
	cust = rest.postForObject(Constants.url + "/ujwal/getCustomerById", map, MCustomer.class);

	System.err.println("Cust" + cust.toString());
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return cust;

 }
@RequestMapping(value = "/getPartListById", method = RequestMethod.GET)
public @ResponseBody MPart getPartById(HttpServletRequest request, HttpServletResponse response) {
	
	MPart part=null;
	try {
		
		int partId = Integer.parseInt(request.getParameter("partId"));

	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	map.add("id", partId);

	part = rest.postForObject(Constants.url + "/ujwal/getPartById", map, MPart.class);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return part;

}
@RequestMapping(value = "/editBill/{billHeadId}", method = RequestMethod.GET)
public ModelAndView editBill(HttpServletRequest request, HttpServletResponse response,@PathVariable int billHeadId) {

	ModelAndView model = null;
	try {

		model = new ModelAndView("masters/addBill");	
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("billHeadId", billHeadId);
		GetBillHeader	bill = rest.postForObject(Constants.url + "getBillHeaderById", map, GetBillHeader.class);
		System.err.println(bill.toString());

		
			billHeader = rest.postForObject(Constants.url + "getBillHeaderByHeaderId", map,BillHeader.class);
		detailList=billHeader.getBillDetailList();
		try {
		for(int i=0;i<billHeader.getBillDetailList().size();i++)
		{
			for(int j=0;j<bill.getGetBillDetail().size();j++)
			{
				if(billHeader.getBillDetailList().get(i).getBillDetailId()==bill.getGetBillDetail().get(j).getBillDetailId())
				{
					billHeader.getBillDetailList().get(i).setPartName(bill.getGetBillDetail().get(j).getPartName());
					billHeader.getBillDetailList().get(i).setUomName(bill.getGetBillDetail().get(j).getUomName());
					billHeader.getBillDetailList().get(i).setHsnCode(bill.getGetBillDetail().get(j).getHsnCode());
					
				}
					
			}
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		List<MCompany> custList = rest.getForObject(Constants.url + "/ujwal/getAllCustomer", List.class);
		model.addObject("custList", custList);
		
		List<MPart> partList = rest.getForObject(Constants.url + "/ujwal/getAllPart", List.class);
		model.addObject("pList", partList);
		
		model.addObject("date",bill.getBillDate());		
		model.addObject("bill", billHeader);
		model.addObject("isEditBill", 1);

		model.addObject("title", "Edit Bill");

	} catch (Exception e) {
		System.err.println("Exce in edit Bill " + e.getMessage());
		e.printStackTrace();
	}
	return model;
}

@RequestMapping(value = "/addPartDetail", method = RequestMethod.GET)
public @ResponseBody List<BillDetails> addPartDetail(HttpServletRequest request,HttpServletResponse response) {

	try {
		    int partId = Integer.parseInt(request.getParameter("partId"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int index = Integer.parseInt(request.getParameter("index"));
			
			float qty = Float.parseFloat(request.getParameter("qty"));
			float partMrp = Float.parseFloat(request.getParameter("partMrp"));
			float discPer =Float.parseFloat(request.getParameter("disc"));
			String remark = request.getParameter("remark");
			
			System.out.println("partId: "+partId+" index: "+index+" qty: "+qty+" isEdit: "+isEdit+" partMrp: "+partMrp+" disc"+discPer+" remark"+remark);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", partId);
			MGetPart parttax = rest.postForObject(Constants.url + "GetPartInfo", map, MGetPart.class);
		
			float mrpBaseRate=(partMrp*100)/(100+parttax.getCgstPer()+parttax.getSgstPer());
			
			mrpBaseRate=roundUp(mrpBaseRate);
			System.out.println("Base Rate: "+mrpBaseRate);
			
			float taxableAmt =  mrpBaseRate * qty;	
			System.out.println("taxableAmt: "+taxableAmt);
			
			float discAmt = ((taxableAmt * discPer) / 100);		
			System.out.println("discAmt: "+discAmt);
			
			taxableAmt = taxableAmt - discAmt;	
			System.out.println("taxableAmt: "+taxableAmt);

			float sgstRs = (taxableAmt * parttax.getSgstPer()) / 100;		
			sgstRs=roundUp(sgstRs);
			System.out.println("sgstRs: "+sgstRs);
			
			float cgstRs = (taxableAmt * parttax.getCgstPer()) / 100;	
			cgstRs=roundUp(cgstRs);
			System.out.println("cgstRs: "+cgstRs);
			
			float igstRs = (taxableAmt * parttax.getIgstPer()) / 100;		
			igstRs=roundUp(igstRs);
			
			System.out.println("igstRs: "+igstRs);
			
			float cessRs = (taxableAmt * parttax.getCessPer()) / 100;		
			cessRs=roundUp(cessRs);
			System.out.println("cessRs: "+cessRs);
			
			float totalTax = sgstRs + cgstRs;
			System.out.println("totalTax: "+totalTax);		
			
			float grandTotal = totalTax + taxableAmt;		
			System.out.println("grandTotal: "+grandTotal);	
			
			discAmt=roundUp(discAmt);	System.out.println("discAmt: "+discAmt);	
			taxableAmt=roundUp(taxableAmt);	System.out.println("taxableAmt: "+taxableAmt);	
			grandTotal=roundUp(grandTotal);	System.out.println("grandTotal: "+grandTotal);	
			grandTotal=(float) Math.ceil((double)grandTotal);
			if(isEdit==1)
			{/*
				detailList.get(index).setBillDetailId(0);
				detailList.get(index).setBillHeaderId(0);
				*/
				detailList.get(index).setPartId(partId);
				detailList.get(index).setPartName(parttax.getPartName());
				detailList.get(index).setHsnCode(parttax.getHsnCode());
				detailList.get(index).setQty(qty);
				detailList.get(index).setDiscPer(discPer);
				detailList.get(index).setDiscRs(discAmt);
				detailList.get(index).setTaxDesc(parttax.getTaxDesc());
				detailList.get(index).setRemark(remark);
				detailList.get(index).setMrp(partMrp);
				detailList.get(index).setBaseRate(mrpBaseRate);
				detailList.get(index).setUomName(parttax.getUomName());
				
				detailList.get(index).setCessPer(parttax.getCessPer());
				detailList.get(index).setCessRs(cessRs);
				
				detailList.get(index).setCgstPer(parttax.getCgstPer());
				detailList.get(index).setCgstRs(cgstRs);
				
				detailList.get(index).setSgstPer(parttax.getSgstPer());
				detailList.get(index).setSgstRs(sgstRs);
				
				detailList.get(index).setIgstPer(parttax.getIgstPer());
				detailList.get(index).setIgstRs(igstRs);
				
				detailList.get(index).setTotalTax(totalTax);
				detailList.get(index).setTaxableAmount(taxableAmt);
				
				detailList.get(index).setGrandTotal(grandTotal);
				detailList.get(index).setDelStatus(0);

			}else {
			BillDetails bill = new BillDetails();
			
			bill.setBillDetailId(0);
			bill.setBillHeaderId(0);
			bill.setPartId(partId);
			bill.setPartName(parttax.getPartName());
			bill.setHsnCode(parttax.getHsnCode());
			bill.setTaxDesc(parttax.getTaxDesc());
			bill.setQty(qty);
			
			bill.setDiscPer(discPer);
			bill.setDiscRs(discAmt);
			
			bill.setRemark(remark);
			bill.setMrp(partMrp);
			
			bill.setBaseRate(mrpBaseRate);
		
			bill.setUomName(parttax.getUomName());
			
			bill.setCgstPer(parttax.getCgstPer());
			bill.setCgstRs(cgstRs);
			
			bill.setSgstPer(parttax.getSgstPer());
			bill.setSgstRs(sgstRs);
			
			bill.setIgstPer(parttax.getIgstPer());
			bill.setIgstRs(igstRs);
			
			bill.setCessPer(parttax.getCessPer());
			bill.setCessRs(cessRs);
			
			bill.setDiscRs(discAmt);
			
			bill.setTaxableAmount(taxableAmt);
			bill.setTotalTax(totalTax);
			
			
			bill.setGrandTotal(grandTotal);
			bill.setDelStatus(0);
			
			detailList.add(bill);
			}
			System.err.println("detailList"+detailList.toString());
			System.out.println(parttax.toString());
			System.out.println("Part Name: "+parttax.getPartName());
			
	} catch (Exception e) {
		System.err.println("Exception in addPart " + e.getMessage());
		e.printStackTrace();
	}

	return detailList;

}

public static float roundUp(float d) {					
	return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();				
}					
@RequestMapping(value = "/getItemForEdit", method = RequestMethod.GET)
public @ResponseBody BillDetails getItemForEdit(HttpServletRequest request, HttpServletResponse response) {

	int index = Integer.parseInt(request.getParameter("index"));
	int billDetailId = Integer.parseInt(request.getParameter("billDetailId"));
	
	return detailList.get(index);

}

@RequestMapping(value = "/getItemForDelete", method = RequestMethod.GET)
public @ResponseBody List<BillDetails> getItemForDelete(HttpServletRequest request, HttpServletResponse response) {

	int index = Integer.parseInt(request.getParameter("index"));
	int billDetailId = Integer.parseInt(request.getParameter("billDetailId"));
 
	if(billDetailId==0)
	{
		detailList.remove(index);
	}
	else {
		detailList.get(index).setDelStatus(1);
	}
	
	return detailList;

}
int isError = 0;
@RequestMapping(value = "/insertBill", method = RequestMethod.POST)
public String insertBill(HttpServletRequest request, HttpServletResponse response) {

	ModelAndView model = null;
	try {

		int isEditBill=0;
		try {
			isEditBill= Integer.parseInt(request.getParameter("isEditBill"));
		}
		catch (Exception e) {
			isEditBill=0;
		}
			
		HttpSession session = request.getSession();
		MUser userResponse = (MUser) session.getAttribute("userInfo");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = dateFormat.format(new Date());
		String date=request.getParameter("date");
		
		String remark=request.getParameter("remark_new");
		System.out.println("Date: "+date);
		model = new ModelAndView("masters/addBill");

		int custId = Integer.parseInt(request.getParameter("cust_id"));
		BillHeader header= new BillHeader();
		if(isEditBill==1) {
		   header.setBillHeaderId(billHeader.getBillHeaderId());
		}else
		{
		   header.setBillHeaderId(0);
		}
		header.setCustId(custId);
	    header.setCompanyId(userResponse.getCompanyId());
	    header.setUserId(userResponse.getUserId());
	    header.setLocId(userResponse.getLocationId());
		
	    float grandTotal = 0;
		float taxableAmt=0;
		float totalTaxPer=0.0f;
		float discAmt=0;
		float cgstAmtTotal=0;float sgstAmtTotal=0;float igstAmtTotal=0;float totalTax=0;
		for (int i = 0; i < detailList.size(); i++) 
		{
			if(detailList.get(i).getDelStatus()==0) {
			grandTotal=detailList.get(i).getGrandTotal()+grandTotal;
			taxableAmt=detailList.get(i).getTaxableAmount()+taxableAmt;
			discAmt=detailList.get(i).getDiscRs()+discAmt;
			cgstAmtTotal=cgstAmtTotal+detailList.get(i).getCgstRs();
			sgstAmtTotal=sgstAmtTotal+detailList.get(i).getSgstRs();
			igstAmtTotal=sgstAmtTotal+detailList.get(i).getIgstRs();
			totalTax=totalTax+detailList.get(i).getTotalTax();
			totalTaxPer=totalTaxPer+(detailList.get(i).getCgstPer()+detailList.get(i).getSgstPer());
			}
		}
		header.setBillDate(date);
		header.setBillDateTime(curDate);
		header.setCgstAmt(roundUp(cgstAmtTotal));
		header.setSgstAmt(roundUp(sgstAmtTotal));
		header.setIgstAmt(roundUp(igstAmtTotal));
		header.setTotaTax(roundUp(totalTax));
		header.setRemark(remark);
		header.setRoundOff(0);
		header.setTaxPer(totalTaxPer);
		header.setGrandTotal(roundUp(grandTotal));
		header.setTaxableAmt(roundUp(taxableAmt));
		header.setDiscAmt(roundUp(discAmt));
	

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("docCode", 1);

		Document doc = rest.postForObject(Constants.url + "/getDocument", map, Document.class);
		header.setInvoiceNo(doc.getDocPrefix() + "" + doc.getSrNo());
		
		header.setBillDetailList(detailList);
		BillHeader insertbillHeadRes = rest.postForObject(Constants.url + "saveBill", header,BillHeader.class);
		
		if (insertbillHeadRes != null) {

			isError = 2;

			map = new LinkedMultiValueMap<String, Object>();

			map.add("srNo", doc.getSrNo() + 1);
			map.add("docCode", doc.getDocCode());

			Info updateDocSr = rest.postForObject(Constants.url + "updateDocSrNo", map, Info.class);

		} else {

			isError = 1;
		}
		
	} catch (Exception e) {
		isError = 1;
		System.err.println("exception In insertOrder at OrderController " + e.getMessage());

		e.printStackTrace();

	}

	return "redirect:/showAddBill";
}
@RequestMapping(value = "/showBillList", method = RequestMethod.GET)
public ModelAndView showBillList(HttpServletRequest request, HttpServletResponse response) {

	ModelAndView model = null;
	try {

		model = new ModelAndView("bill/billList");

		model.addObject("title", "Bill List");

		List<MCompany> custList = rest.getForObject(Constants.url + "/ujwal/getAllCustomer", List.class);
		model.addObject("custList", custList);

		String fromDate = null, toDate = null;

		if (request.getParameter("fromDate") == null || request.getParameter("fromDate") == "") {

			System.err.println("onload call  ");

			Calendar date = Calendar.getInstance();
			date.set(Calendar.DAY_OF_MONTH, 1);

			Date firstDate = date.getTime();

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");

			fromDate = dateFormat.format(firstDate);

			toDate = dateFormat.format(new Date());
			System.err.println("cu Date  " + fromDate + "todays date   " + toDate);

		} else {

			System.err.println("After page load call");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");

		}
		model.addObject("fromDate", fromDate);
		model.addObject("toDate", toDate);

	} catch (Exception e) {

		System.err.println("exception In billController " + e.getMessage());

		e.printStackTrace();

	}

	return model;
}
@RequestMapping(value = "/getBillListBetDate", method = RequestMethod.GET)
public @ResponseBody List<GetBillHeader> getBillListBetDate(HttpServletRequest request,
		HttpServletResponse response) {

	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	int custId = Integer.parseInt(request.getParameter("custId"));
	String fromDate = request.getParameter("fromDate");
	String toDate = request.getParameter("toDate");
 
	map.add("custId", custId);
	map.add("fromDate", DateConvertor.convertToYMD(fromDate));
	map.add("toDate", DateConvertor.convertToYMD(toDate));

	GetBillHeader[] ordHeadArray = rest.postForObject(Constants.url + "getBillHeadersByDate", map,
			GetBillHeader[].class);
	List<GetBillHeader> getBillList = new ArrayList<GetBillHeader>(Arrays.asList(ordHeadArray));

	return getBillList;
}

/*@RequestMapping(value = "pdf/showBillsPdf/{billHeadId}", method = RequestMethod.GET)
public ModelAndView showBillsPdf(@PathVariable("billHeadId") String[] billTempIds, HttpServletRequest request,
		HttpServletResponse response) {

	ModelAndView model = new ModelAndView("bill/allBillPdf");

	try {
		RestTemplate rest = new RestTemplate();
		String strBillTempIds = new String();
		for (int i = 0; i < billTempIds.length; i++) {
			strBillTempIds = strBillTempIds + "," + billTempIds[i];
		}
		strBillTempIds = strBillTempIds.substring(1);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("billTempIds", strBillTempIds);
		GetBillHeaderPdf[] billHeaderRes = rest.postForObject(Constants.url + "/findBillsByHeaderId", map,
				GetBillHeaderPdf[].class);
		ArrayList<GetBillHeaderPdf> billHeaders = new ArrayList<GetBillHeaderPdf>(Arrays.asList(billHeaderRes));

		System.err.println(billHeaders.toString());

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("Currency", new Currency());
		model.addObject("billHeaderList", billHeaders);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
}*/

}
