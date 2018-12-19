package com.ujwal.billsoft.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.models.Info;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MCustomer;

@Controller
public class UjwalCustomerController {
	RestTemplate restTamplate = null;
	
	@RequestMapping(value="/showAddCustomer", method=RequestMethod.GET)
	
	public ModelAndView addShowCompanyForm() {
		
		ModelAndView mav = new ModelAndView("masters/addCustomer");
		try {
		restTamplate = new RestTemplate();
		List<MCustomer> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCustomer", List.class);
		mav.addObject("custList", compList);
		mav.addObject("title", "Add Customer");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}

	@RequestMapping(value="/insertCustomer", method=RequestMethod.POST)
	public String newCompany(HttpServletRequest req, HttpServletResponse resp) {
		
		int custId=0;
		try
		{
			custId = Integer.parseInt(req.getParameter("cust_id"));
		}
		catch(Exception e){
			custId=0;
		}
		try {
			
		
		String custName = req.getParameter("cust_name");
		String custAddress = req.getParameter("cust_address");
		String custPhone = req.getParameter("cust_phone");
		String custState = req.getParameter("cust_state");
		String custEmail = req.getParameter("cust_email");
		String custGstn = req.getParameter("cust_gstn");
		String custRegisNo = req.getParameter("cust_regis_no");
		String custPan = req.getParameter("cust_pan");
		String custVehNo = req.getParameter("cust_veh_no");
		String custRoNo = req.getParameter("cust_ro_no");
		String custChasiNo = req.getParameter("cust_chasi_no");
		
		
		MCustomer mCust = new MCustomer();
		mCust.setCustId(custId);
		mCust.setCustName(custName);
		mCust.setCustAddress(custAddress);
		mCust.setCustPhone(custPhone);
		mCust.setCustState(custState);
		mCust.setCustGstn(custGstn);
		mCust.setCustChasiNo(custChasiNo);
		mCust.setCustPan(custPan);
		mCust.setCustRegisNo(custRegisNo);
		mCust.setCustVehNo(custVehNo);
		mCust.setCustDelStatus(0);
		mCust.setCustEmail(custEmail);
		mCust.setCustRoNo(custRoNo);
		
		
		restTamplate = new RestTemplate();
		MCustomer company = restTamplate.postForObject(Constants.url + "/ujwal/addNewCustomer", mCust, MCustomer.class); 
		
		if(company!=null) {
			System.out.println("Sucess");
		}else {
			System.out.println("Fail");
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return "redirect:/showAddCustomer";
		
	}
	
	//
	
@RequestMapping(value="/editCustomer/{custId}", method=RequestMethod.GET)
	
	public ModelAndView editCompany(@PathVariable("custId") int id) {
		
		ModelAndView mav = new ModelAndView("masters/addCustomer");
		try {
		restTamplate = new RestTemplate();
		MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		MCustomer custList = restTamplate.postForObject(Constants.url + "/ujwal/getCustomerById", map, MCustomer.class);
		mav.addObject("cust", custList);
		List<MCustomer> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCustomer", List.class);
		mav.addObject("custList", compList);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}



@RequestMapping(value="/deleteCustomer/{custId}", method=RequestMethod.GET)

public String deleteCustomer(@PathVariable("custId") int id) {
	
	
	try {
	restTamplate = new RestTemplate();
	MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
	map.add("id", id);
	Info info = restTamplate.postForObject(Constants.url + "/ujwal/deleteCustomerId", map, Info.class);
	//mav.addObject("editComp", compList);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}

	return "redirect:/showAddCustomer";
}

@RequestMapping(value = "/deleteRecordofCustomer", method = RequestMethod.POST)
public String deleteRecordofCustomer(HttpServletRequest request, HttpServletResponse response) {
	
	try {
		RestTemplate rest = new RestTemplate();
		String[] custIds = request.getParameterValues("custIds");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < custIds.length; i++) {
			sb = sb.append(custIds[i] + ",");

		}
		String items = sb.toString();
		items = items.substring(0, items.length() - 1);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("custIds", items);

		Info errMsg = rest.postForObject(Constants.url + "/ujwal/deleteMultiCustomer", map, Info.class);

	} catch (Exception e) {

		System.err.println("Exception in /deleteRecordofCustomer @MastContr  " + e.getMessage());
		e.printStackTrace();
	}
return "redirect:/showAddCustomer";
}

@RequestMapping(value="/moreCustomerDetails/{custId}", method=RequestMethod.GET)

public ModelAndView CustomerDetails(@PathVariable("custId") int id ) {
	
	ModelAndView mav = new ModelAndView("masters/customerDetails");
	try {
	restTamplate = new RestTemplate();
	MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
	map.add("id", id);
	MCustomer custList = restTamplate.postForObject(Constants.url + "/ujwal/getCustomerById", map, MCustomer.class);
	mav.addObject("cust", custList);
	mav.addObject("title", "Customer Details");
	}catch(Exception e){
		System.out.println(e.getMessage());
	}

	return mav;		
}


//showCustList
@RequestMapping(value="/showCustList", method=RequestMethod.GET)

public ModelAndView showCustList() {
	
	ModelAndView mav = new ModelAndView("masters/customerList");
	try {
	restTamplate = new RestTemplate();
	List<MCustomer> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCustomer", List.class);
	mav.addObject("custList", compList);
	mav.addObject("title", "Customers List");
	}catch(Exception e){
		System.out.println(e.getMessage());
	}

	return mav;		
}

}
