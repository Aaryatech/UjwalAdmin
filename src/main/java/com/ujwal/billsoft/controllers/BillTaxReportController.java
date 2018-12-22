package com.ujwal.billsoft.controllers;

import java.util.LinkedHashMap;
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
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.TaxBillBean;

@Controller
public class BillTaxReportController {
		
	RestTemplate restTamplate = null;

	@RequestMapping(value="/billTaxreport", method=RequestMethod.GET)
	public ModelAndView addShowCompanyForm() {
		
		ModelAndView mav = new ModelAndView("report/BillTax");
		try {
		restTamplate = new RestTemplate();
		List<MCompany> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		mav.addObject("title", "Bill Tax Report");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}
	
	@RequestMapping(value = "/getTaxWiseBill", method = RequestMethod.GET)
	
	public @ResponseBody List<TaxBillBean> getTaxwiseBillReport(HttpServletRequest req, HttpServletResponse resp) {
		
		String fromDate = req.getParameter("fromDate");
		String toDate = req.getParameter("toDate");
		int comp_id = Integer.parseInt(req.getParameter("comp_id"));
		
		System.out.println(fromDate+" "+toDate+" "+comp_id);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("fromDate", fromDate);
		map.add("toDate", toDate);
		map.add("comp_id", comp_id);
		
		List<TaxBillBean> taxList = restTamplate.postForObject(Constants.url + "/ujwal/getBillTaxReport", map, List.class);
		System.out.println("Response ="+taxList);
		return taxList;
		
	}
}
