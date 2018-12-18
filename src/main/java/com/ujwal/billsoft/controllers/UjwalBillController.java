package com.ujwal.billsoft.controllers;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.models.BillDetails;
import com.ujwal.billsoft.models.BillHeader;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MCustomer;
import com.ujwal.billsoft.models.MGetPart;
import com.ujwal.billsoft.models.MPart;

@Controller
public class UjwalBillController {
	RestTemplate restTamplate = null;
	List<BillDetails> detailList = new ArrayList<BillDetails>();

	@RequestMapping(value="/showAddBill", method=RequestMethod.GET)
	
	public ModelAndView addShowBillForm() {
		
		ModelAndView mav = new ModelAndView("masters/addBill");
	try {
		detailList = new ArrayList<BillDetails>();
		restTamplate = new RestTemplate();
		List<MCompany> custList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCustomer", List.class);
	//	System.out.println(custList.toString());
		mav.addObject("custList", custList);
		List<MPart> partList = restTamplate.getForObject(Constants.url + "/ujwal/getAllPart", List.class);
		mav.addObject("pList", partList);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return mav;		
	}
@RequestMapping(value="/showOrder", method=RequestMethod.GET)
	
	public ModelAndView addShoworderForm() {
		
		ModelAndView mav = new ModelAndView("masters/addOrder");
		/*try {
		restTamplate = new RestTemplate();
		List<MCompany> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}*/

		return mav;		
	}


List<MCustomer> custList;
RestTemplate rest = new RestTemplate();


@RequestMapping(value = "/getCustById", method = RequestMethod.GET)
public @ResponseBody MCustomer getCustById(HttpServletRequest request, HttpServletResponse response) {

	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

	int custId = Integer.parseInt(request.getParameter("custId"));

	map.add("id", custId);

	MCustomer custArray = rest.postForObject(Constants.url + "/ujwal/getCustomerById", map, MCustomer.class);

	System.err.println("Ajax custList List " + custArray.toString());

	return custArray;

}
@RequestMapping(value = "/getPartListById", method = RequestMethod.GET)
public @ResponseBody MPart getPartById(HttpServletRequest request, HttpServletResponse response) {

	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

	int partId = Integer.parseInt(request.getParameter("partId"));

	map.add("id", partId);

	MPart custArray = rest.postForObject(Constants.url + "/ujwal/getPartById", map, MPart.class);

	//System.err.println("Ajax custList List " + custArray.toString());

	return custArray;

}

@RequestMapping(value = "/addPartDetail", method = RequestMethod.GET)
public @ResponseBody List<BillDetails> addPartDetail(HttpServletRequest request,
		HttpServletResponse response) {

	try {
			int partId = Integer.parseInt(request.getParameter("partId"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int index = Integer.parseInt(request.getParameter("index"));
			System.out.println("isEdit isEdit: "+isEdit);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", partId);
			MGetPart parttax = rest.postForObject(Constants.url + "GetPartInfo", map, MGetPart.class);
		
			
			float qty = Float.parseFloat(request.getParameter("qty"));
			float partMrp = Float.parseFloat(request.getParameter("partMrp"));
			float disc =Float.parseFloat(request.getParameter("disc"));
			String remark = request.getParameter("remark");
							
			
		//float tax=parttax.getCgstPer()+parttax.getSgstPer();
			float mrpBaseRate=(partMrp*100)/(100+parttax.getCgstPer()+parttax.getSgstPer());
			mrpBaseRate=roundUp(mrpBaseRate);
			System.out.println("Base Rate: "+mrpBaseRate);
			
			float taxableA =  mrpBaseRate * qty;	
			System.out.println("Base Rate: "+taxableA);
			
			float discAmt = ((taxableA * disc) / 100);		
			discAmt=roundUp(discAmt);
			taxableA = taxableA - discAmt;	
			taxableA=roundUp(taxableA);

			float sgstRs = (taxableA * parttax.getSgstPer()) / 100;		
			sgstRs=roundUp(sgstRs);
			float cgstRs = (taxableA * parttax.getCgstPer()) / 100;	
			cgstRs=roundUp(cgstRs);
			float igstRs = (taxableA * parttax.getIgstPer()) / 100;		
			igstRs=roundUp(igstRs);
			float cessRs = (taxableA * parttax.getCessPer()) / 100;		
			cessRs=roundUp(cessRs);
			float totalTax = sgstRs + cgstRs;		
			
			float grandTotal = totalTax + taxableA;		
			grandTotal=roundUp(grandTotal);
			if(isEdit==1)
			{
				detailList.get(index).setPartId(partId);
				detailList.get(index).setPartName(parttax.getPartName());
				detailList.get(index).setQty(qty);
				detailList.get(index).setDiscPer(disc);
				detailList.get(index).setDiscRs(discAmt);
				detailList.get(index).setTaxDesc(parttax.getTaxDesc());
				detailList.get(index).setRemark(remark);
				detailList.get(index).setMrp(partMrp);
				detailList.get(index).setBaseRate(mrpBaseRate);
				detailList.get(index).setUomName(parttax.getUomName());
				detailList.get(index).setCessPer(parttax.getCessPer());
				detailList.get(index).setCessRs(cessRs);
				detailList.get(index).setBillDetailId(0);
				detailList.get(index).setBillHeaderId(0);
				detailList.get(index).setCgstPer(parttax.getCgstPer());
				detailList.get(index).setCgstRs(cgstRs);
				detailList.get(index).setSgstPer(parttax.getSgstPer());
				detailList.get(index).setSgstRs(sgstRs);
				detailList.get(index).setTotalTax(totalTax);
				detailList.get(index).setTaxableAmount(taxableA);
				detailList.get(index).setGrandTotal(grandTotal);
				detailList.get(index).setDelStatus(0);

			}else {
			BillDetails bill = new BillDetails();
			bill.setPartId(partId);
			bill.setPartName(parttax.getPartName());
			bill.setQty(qty);
			bill.setDiscPer(disc);
			bill.setRemark(remark);
			bill.setMrp(partMrp);
			bill.setBaseRate(mrpBaseRate);
			bill.setDiscRs(discAmt);
			bill.setTaxableAmount(taxableA);
			bill.setTotalTax(totalTax);
			bill.setGrandTotal(grandTotal);
			bill.setUomName(parttax.getUomName());
			bill.setCgstPer(parttax.getCgstPer());
			bill.setCgstRs(cgstRs);
			bill.setSgstPer(parttax.getSgstPer());
			bill.setSgstRs(sgstRs);
			bill.setIgstPer(parttax.getIgstPer());
			bill.setIgstRs(igstRs);
			bill.setCessPer(parttax.getCessPer());
			bill.setCessRs(cessRs);
			
			detailList.add(bill);
			}
			System.err.println("detailList"+detailList.toString());
			System.out.println(parttax.toString());
			System.out.println("Part Name: "+parttax.getPartName());
			
	} catch (Exception e) {
		System.err.println("Exce In " + e.getMessage());
		e.printStackTrace();
	}
//	System.err.println("  part List " + detailList.toString());

	return detailList;

}

public static float roundUp(float d) {					
	return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();				
}					
@RequestMapping(value = "/getItemForEdit", method = RequestMethod.GET)
public @ResponseBody BillDetails getItemForEdit(HttpServletRequest request, HttpServletResponse response) {

	int index = Integer.parseInt(request.getParameter("index"));

	return detailList.get(index);

}

@RequestMapping(value = "/getItemForDelete", method = RequestMethod.GET)
public @ResponseBody List<BillDetails> getItemForDelete(HttpServletRequest request, HttpServletResponse response) {

	int index = Integer.parseInt(request.getParameter("index"));
	detailList.remove(index);
	
	return detailList;

}
int isError = 0;
@RequestMapping(value = "/insertBill", method = RequestMethod.POST)
public String insertBill(HttpServletRequest request, HttpServletResponse response) {

	ModelAndView model = null;
	
	try {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = dateFormat.format(new Date());
		String date=request.getParameter("date");
		String remark=request.getParameter("remark_new");
		System.out.println("Date: "+date);
		model = new ModelAndView("masters/addBill");

		int custId = Integer.parseInt(request.getParameter("cust_id"));
		BillHeader header= new BillHeader();
		header.setBillHeaderId(0);
		header.setCustId(custId);
	    header.setCompanyId(0);
	    header.setUserId(0);
		
	    float total = 0;
		float taxableAmt=0;float totalTaxPer=0.0f;
		float disc=0;
		float cgstAmtTotal=0;float sgstAmtTotal=0;float totalTax=0;
		for (int i = 0; i < detailList.size(); i++) 
		{
            total=detailList.get(i).getGrandTotal()+total;
			taxableAmt=detailList.get(i).getTaxableAmount()+taxableAmt;
			disc=detailList.get(i).getDiscRs()+disc;
			cgstAmtTotal=cgstAmtTotal+detailList.get(i).getCgstRs();
			sgstAmtTotal=sgstAmtTotal+detailList.get(i).getSgstRs();
			totalTax=totalTax+detailList.get(i).getTotalTax();
			totalTaxPer=totalTaxPer+(detailList.get(i).getCgstPer()+detailList.get(i).getSgstPer());
		}
		header.setBillDate(date);
		header.setBillDateTime(curDate);
		header.setCgstAmt(roundUp(cgstAmtTotal));
		header.setSgstAmt(roundUp(sgstAmtTotal));
		header.setIgstAmt(0);
		header.setTotaTax(roundUp(totalTax));
		header.setRemark(remark);
		header.setRoundOff(0);
		header.setTaxPer(totalTaxPer);
		header.setGrandTotal(roundUp(total));
		header.setTaxableAmt(roundUp(taxableAmt));
		header.setDiscAmt(roundUp(disc));
		header.setBillDetailList(detailList);

		BillHeader insertbillHeadRes = rest.postForObject(Constants.url + "saveBill", header,BillHeader.class);
		
	
		/*MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("docCode", 3);

		Document doc = rest.postForObject(Constants.url + "getDocument", map, Document.class);
		ordHeader.setOrderNo(doc.getDocPrefix() + "" + doc.getSrNo());

		if (poDetailForOrdList.get(0).getTaxAmt() == 0) {

			ordHeader.setIsTaxIncluding(0);// tax not included

		} else {

			ordHeader.setIsTaxIncluding(1);// yes tax included

		}

		ordHeader.setOrderDetailList(ordDetailList);

		OrderHeader insertOrdHeadRes = rest.postForObject(Constants.url + "saveOrder", ordHeader,
				OrderHeader.class);

		if (insertOrdHeadRes != null) {

			isError = 2;

			map = new LinkedMultiValueMap<String, Object>();

			map.add("srNo", doc.getSrNo() + 1);
			map.add("docCode", doc.getDocCode());

			Info updateDocSr = rest.postForObject(Constants.url + "updateDocSrNo", map, Info.class);

		} else {

			isError = 1;
		}
		System.err.println("insertOrdHeadRes " + insertOrdHeadRes.toString());
*/
	} catch (Exception e) {
		isError = 1;
		System.err.println("exception In insertOrder at OrderController " + e.getMessage());

		e.printStackTrace();

	}

	return "redirect:/showAddBill";
}
}
