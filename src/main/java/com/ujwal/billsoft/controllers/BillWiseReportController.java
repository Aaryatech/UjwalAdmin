package com.ujwal.billsoft.controllers;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.models.MCompany;

@Controller
@Scope("session")
public class BillWiseReportController {

	RestTemplate restTamplate = null;
	@RequestMapping(value = "/showBillwiseReport", method=RequestMethod.GET)
	public ModelAndView billReport() {
		
		ModelAndView mav = new ModelAndView("report/BillReport");
		try {
		restTamplate = new RestTemplate();
		List<MCompany> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		mav.addObject("title", "Bill Report");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}

}
