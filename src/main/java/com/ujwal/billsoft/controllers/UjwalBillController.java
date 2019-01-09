package com.ujwal.billsoft.controllers;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.commons.Currency;
import com.ujwal.billsoft.commons.DateConvertor;
import com.ujwal.billsoft.models.BillDetails;
import com.ujwal.billsoft.models.BillHeader;
import com.ujwal.billsoft.models.BillReport;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MCustomer;
import com.ujwal.billsoft.models.MGetPart;
import com.ujwal.billsoft.models.MModelBean;
import com.ujwal.billsoft.models.MPart;
import com.ujwal.billsoft.models.MUser;
import com.ujwal.billsoft.models.Document;
import com.ujwal.billsoft.models.GetBillHeader;
import com.ujwal.billsoft.models.Info;
@Controller
public class UjwalBillController {

	RestTemplate rest=new RestTemplate();
	List<BillDetails> detailList = new ArrayList<BillDetails>();
	BillHeader billHeader=new BillHeader();

	@RequestMapping(value="/showAddBill", method=RequestMethod.GET)
	public ModelAndView addShowBillForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("masters/addBill");
	try {
		detailList = new ArrayList<BillDetails>();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();		
		List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		
		HttpSession session = request.getSession();
		MUser userResponse = (MUser) session.getAttribute("userBean");
			
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", userResponse.getCompanyId());
		
		List<MCustomer> custList = rest.postForObject(Constants.url + "/ujwal/getCustomerByCompId",map, List.class);
		System.out.println("Response List= "+custList);
		mav.addObject("custList", custList);
		
		map = new LinkedMultiValueMap<>();
		map.add("companyId", userResponse.getCompanyId());
		List<MModelBean> modelList = rest.postForObject(Constants.url+ "/ujwal/getModelByCompanyId",map, List.class);
		mav.addObject("modelList", modelList);
	/*	
		List<MPart> partList = rest.getForObject(Constants.url + "/ujwal/getAllPartByCompanyId", List.class);
		mav.addObject("pList", partList);
		
		*/
		mav.addObject("date", dateFormat.format(date));
		mav.addObject("title", "Add Bill");
		mav.addObject("user",userResponse);
		mav.addObject("companyId", userResponse.getCompanyId());
		mav.addObject("isEditBill",0);
		 map = new LinkedMultiValueMap<String, Object>();
	
		map.add("docCode", 1);
		map.add("locationId", userResponse.getLocationId());
	
		Document doc = rest.postForObject(Constants.url + "getDocument", map, Document.class);
		mav.addObject("doc", doc);
		DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
		     int year = Integer.parseInt(df.format(Calendar.getInstance().getTime()));
            String yearFin="";
		    int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		    if (month < 3) {
		    	yearFin=(year - 1) + "-" + year;
		    } else {
		    	yearFin=year + "-" + (year + 1);
		    }
	     
	     mav.addObject("year",yearFin );
	     mav.addObject("srNo",String.format("%05d", doc.getSrNo()));

		System.err.println(doc.toString()+"getDocument");
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
public @ResponseBody MGetPart getPartById(HttpServletRequest request, HttpServletResponse response) {
	
	MGetPart part=null;
	try {
		
		int partId = Integer.parseInt(request.getParameter("partId"));

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("id", partId);
		part = rest.postForObject(Constants.url + "GetPartInfo", map, MGetPart.class);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return part;

}



@RequestMapping(value = "/getPartListByModelId", method = RequestMethod.GET)
public @ResponseBody List<MPart> getPartListByModelId(HttpServletRequest request, HttpServletResponse response) {
	
	List<MPart> partList=null;
	try {
		
		int modelId = Integer.parseInt(request.getParameter("modelId"));

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("modelId", modelId);
		 partList = rest.postForObject(Constants.url + "/ujwal/getAllPartByModelId",map, List.class);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return partList;

}
@RequestMapping(value = "/editBill/{billHeadId}", method = RequestMethod.GET)
public ModelAndView editBill(HttpServletRequest request, HttpServletResponse response,@PathVariable int billHeadId) {

	ModelAndView model = null;
	try {
		model = new ModelAndView("masters/addBill");	
		List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		model.addObject("compList", compList);
		
	
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
		
	/*	List<MPart> partList = rest.getForObject(Constants.url + "/ujwal/getAllPart", List.class);
		model.addObject("pList", partList);*/
		map = new LinkedMultiValueMap<>();
		map.add("companyId", billHeader.getCompanyId());
		List<MModelBean> modelList = rest.postForObject(Constants.url+ "/ujwal/getModelByCompanyId",map, List.class);
		model.addObject("modelList", modelList);
	
		model.addObject("date",bill.getBillDate());		
		model.addObject("bill", billHeader);
		model.addObject("isEditBill", 1);
		model.addObject("companyId", billHeader.getCompanyId());
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
			int modelId = 0;
			int partId = 0;
		    partId = Integer.parseInt(request.getParameter("partId"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int index = Integer.parseInt(request.getParameter("index"));
			
			float qty = Float.parseFloat(request.getParameter("qty"));
			float partMrp = Float.parseFloat(request.getParameter("partMrp"));
			float discPer =Float.parseFloat(request.getParameter("disc"));
			modelId = Integer.parseInt(request.getParameter("modelId"));
			
			System.out.println("partId: "+partId+" index: "+index+" qty: "+qty+" isEdit: "+isEdit+" partMrp: "+partMrp+" disc"+discPer+" remark");
			
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
				detailList.get(index).setRemark("");
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
				detailList.get(index).setEx_int1(modelId);

			}else {
			BillDetails bill = new BillDetails();
			
			bill.setBillDetailId(0);
			bill.setBillHeaderId(0);
			bill.setPartId(partId);
			bill.setPartName(parttax.getPartName());
			bill.setEx_var1(parttax.getHsnCode());
			bill.setTaxDesc(parttax.getTaxDesc());
			bill.setQty(qty);
			
			bill.setDiscPer(discPer);
			bill.setDiscRs(discAmt);
			
			bill.setRemark("");
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
			bill.setEx_int1(modelId);
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
		MUser userResponse = (MUser) session.getAttribute("userBean");
			
		System.out.println("User Cred="+userResponse.getUserName()+" "+userResponse.getCompanyId()+" "+userResponse.getUserId());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = dateFormat.format(new Date());
		String date=request.getParameter("date");
		int compId=Integer.parseInt(request.getParameter("compId"));
		String remark=request.getParameter("remark_new");
		String saleType=request.getParameter("sale_type");
		String paymentMode=request.getParameter("payment_mode");
		String refNo=request.getParameter("ref_no");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	    date=sdf2.format(sdf.parse(date));
		System.out.println("Date: "+date);
		model = new ModelAndView("masters/addBill");

		int custId = Integer.parseInt(request.getParameter("cust_id"));
		BillHeader header= new BillHeader();
		
		if(isEditBill==1) {
		   
			header.setBillHeaderId(billHeader.getBillHeaderId());
		    header.setCustId(custId);
		    header.setCompanyId(compId);
		    header.setUserId(billHeader.getUserId());
		    header.setLocId(billHeader.getLocId());

			header.setBillDate(date);
			header.setBillDateTime(billHeader.getBillDateTime());
		}else
		{
		    header.setBillHeaderId(0);
		    header.setCustId(custId);
		    header.setCompanyId(compId);
		    header.setUserId(userResponse.getUserId());
		    header.setLocId(userResponse.getLocationId());

			header.setBillDate(date);
			header.setBillDateTime(date);
		}
	
		
		
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
		header.setCgstAmt(roundUp(cgstAmtTotal));
		header.setSgstAmt(roundUp(sgstAmtTotal));
		header.setIgstAmt(roundUp(igstAmtTotal));
		header.setTotaTax(roundUp(totalTax));
		header.setRemark(remark);
		header.setSaleType(saleType);
		header.setEx_var1(paymentMode);
		header.setEx_var2(refNo);
		header.setRoundOff(0);
		header.setTaxPer(totalTaxPer);
		header.setGrandTotal(roundUp(grandTotal));
		header.setTaxableAmt(roundUp(taxableAmt));
		header.setDiscAmt(roundUp(discAmt));
	
		Document doc=null;
		if(isEditBill==1) {
			header.setInvoiceNo(billHeader.getInvoiceNo());
		}else {
			DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
		     int year = Integer.parseInt(df.format(Calendar.getInstance().getTime()));
           String yearFin="";
		    int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		    if (month < 3) {
		    	yearFin=(year - 1) + "-" + year;
		    } else {
		    	yearFin=year + "-" + (year + 1);
		    }
	     
	  
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("docCode", 1);
		map.add("locationId", userResponse.getLocationId());
		doc = rest.postForObject(Constants.url + "/getDocument", map, Document.class);
		header.setInvoiceNo(doc.getDocPrefix() + "/"+yearFin+"/" + String.format("%05d", doc.getSrNo()));
		}
		header.setBillDetailList(detailList);
		BillHeader insertbillHeadRes = rest.postForObject(Constants.url + "saveBill", header,BillHeader.class);
		
		if (insertbillHeadRes != null) {

			isError = 2;

			if(isEditBill!=1) {
			
			MultiValueMap<String, Object>	map = new LinkedMultiValueMap<String, Object>();

			map.add("srNo", doc.getSrNo() + 1);
			map.add("docCode", doc.getDocCode());
			map.add("locationId", userResponse.getLocationId());
			Info updateDocSr = rest.postForObject(Constants.url + "updateDocSrNo", map, Info.class);
			  
			}
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
		
		List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		model.addObject("compList", compList);
			
		/*List<MCompany> custList = rest.getForObject(Constants.url + "/ujwal/getAllCustomer", List.class);
		model.addObject("custList", custList);*/
		HttpSession session = request.getSession();
		MUser userResponse = (MUser) session.getAttribute("userBean");
	 
		model.addObject("compId", userResponse.getCompanyId());
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", userResponse.getCompanyId());
		
		List<MCustomer> custList = rest.postForObject(Constants.url + "/ujwal/getCustomerByCompId",map, List.class);
		System.out.println("Response List= "+custList);
		model.addObject("custList", custList);
	
		
		
		String fromDate = null, toDate = null;
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);

		Date firstDate = date.getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
		fromDate = dateFormat.format(firstDate);
		toDate = dateFormat.format(new Date());
		

		map.add("custId", 0);
		map.add("fromDate", DateConvertor.convertToYMD(fromDate));
		map.add("toDate", DateConvertor.convertToYMD(toDate));
		map.add("compId", userResponse.getCompanyId());

		List<GetBillHeader> billList = rest.postForObject(Constants.url + "getBillHeadersByDate", map, List.class);
		model.addObject("billList", billList);
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
	HttpSession session = request.getSession();
	MUser userResponse = (MUser) session.getAttribute("userBean");
		
	System.out.println("Data Get = "+custId+" "+fromDate+" "+toDate);
 
	map.add("custId", custId);
	map.add("fromDate", DateConvertor.convertToYMD(fromDate));
	map.add("toDate", DateConvertor.convertToYMD(toDate));
	map.add("compId", userResponse.getCompanyId());

	GetBillHeader[] ordHeadArray = rest.postForObject(Constants.url + "getBillHeadersByDate", map,
			GetBillHeader[].class);
	List<GetBillHeader> getBillList = new ArrayList<GetBillHeader>(Arrays.asList(ordHeadArray));
	System.out.println("List 2 Found = "+getBillList);
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
@RequestMapping(value = "pdf/showBillsPdf/{billHeadId}", method = RequestMethod.GET)
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
		GetBillHeader[] billHeaderRes = rest.postForObject(Constants.url + "/findBillsByHeaderId", map,
				GetBillHeader[].class);
		ArrayList<GetBillHeader> billHeaders = new ArrayList<GetBillHeader>(Arrays.asList(billHeaderRes));

		System.err.println(billHeaders.toString());

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("Currency", new Currency());
		model.addObject("billHeaderList", billHeaders);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
}

private Dimension format = PD4Constants.A4;
private boolean landscapeValue = false;
private int topValue = 8;
private int leftValue = 0;
private int rightValue = 0;
private int bottomValue = 8;
private String unitsValue = "m";
private String proxyHost = "";
private int proxyPort = 0;

private int userSpaceWidth = 750;
private static int BUFFER_SIZE = 1024;

@RequestMapping(value = "/pdf", method = RequestMethod.GET)
public void showPDF(HttpServletRequest request, HttpServletResponse response) {

	String url = request.getParameter("url");
	System.out.println("URL " + url);
	
	File f = new File("/home/aaryate1/exhibition.aaryatechindia.in/tomcat-8.0.18/webapps/ujwal/bill.pdf");
	

	System.out.println("I am here " + f.toString());
	try {
		runConverter(Constants.ReportURL + url, f, request, response);
		System.out.println("Come on lets get ");
	} catch (IOException e) {
		// TODO Auto-generated catch block

		System.out.println("Pdf conversion exception " + e.getMessage());
	}

	// get absolute path of the application
	ServletContext context = request.getSession().getServletContext();
	String appPath = context.getRealPath("");
	String filename = "ordermemo221.pdf";
	String filePath = "/home/aaryate1/exhibition.aaryatechindia.in/tomcat-8.0.18/webapps/ujwal/bill.pdf";
			
	String fullPath = appPath + filePath;
	File downloadFile = new File(filePath);
	FileInputStream inputStream = null;
	try {
		inputStream = new FileInputStream(downloadFile);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/pdf";
		}
		System.out.println("MIME type: " + mimeType);

		String headerKey = "Content-Disposition";

		// response.addHeader("Content-Disposition", "attachment;filename=report.pdf");
		response.setContentType("application/pdf");

		// get output stream of the response
		OutputStream outStream;

		outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void runConverter(String urlstring, File output, HttpServletRequest request, HttpServletResponse response)
		throws IOException {

	if (urlstring.length() > 0) {
		if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
			urlstring = "http://" + urlstring;
		}
		System.out.println("PDF URL " + urlstring);
		java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

		PD4ML pd4ml = new PD4ML();

		try {

			PD4PageMark footer = new PD4PageMark();
			footer.setPageNumberTemplate("page $[page] of $[total]");
			footer.setTitleAlignment(PD4PageMark.LEFT_ALIGN);
			footer.setPageNumberAlignment(PD4PageMark.RIGHT_ALIGN);
			footer.setInitialPageNumber(1);
			footer.setFontSize(8);
			footer.setAreaHeight(15);

			pd4ml.setPageFooter(footer);

		} catch (Exception e) {
			System.out.println("Pdf conversion method excep " + e.getMessage());
		}
		try {
			pd4ml.setPageSize(landscapeValue ? pd4ml.changePageOrientation(format) : format);
		} catch (Exception e) {
			System.out.println("Pdf conversion ethod excep " + e.getMessage());
		}

		if (unitsValue.equals("mm")) {
			pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
		} else {
			pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
		}

		pd4ml.setHtmlWidth(userSpaceWidth);

		pd4ml.render(urlstring, fos);

	}
}
}
