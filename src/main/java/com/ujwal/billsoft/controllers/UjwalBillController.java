package com.ujwal.billsoft.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MPart;

@Controller
public class UjwalBillController {
	RestTemplate restTamplate = null;
	
	@RequestMapping(value="/showAddBill", method=RequestMethod.GET)
	
	public ModelAndView addShowBillForm() {
		
		ModelAndView mav = new ModelAndView("masters/addBill");
	try {
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
}
