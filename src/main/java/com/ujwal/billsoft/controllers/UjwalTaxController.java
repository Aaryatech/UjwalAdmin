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
import com.ujwal.billsoft.models.MPart;
import com.ujwal.billsoft.models.MTax;

@Controller
public class UjwalTaxController {
	RestTemplate restTamplate = null;
	
	@RequestMapping(value="/showAddTax", method=RequestMethod.GET)
	
	public ModelAndView addShowCompanyForm() {
		
		ModelAndView mav = new ModelAndView("masters/addTax");
		try {
		restTamplate = new RestTemplate();
		List<MTax> taxList = restTamplate.getForObject(Constants.url + "/ujwal/getAllTaxes", List.class);
		mav.addObject("tList", taxList);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}

	@RequestMapping(value="/insertTax", method=RequestMethod.POST)
	public String newCompany(HttpServletRequest req, HttpServletResponse resp) {
		int taxId=0;
		try
		{
			 taxId = Integer.parseInt(req.getParameter("tax_id"));
		}
		catch(Exception e){
			taxId=0;
		}
		try {
		
		String hsnCode = req.getParameter("hsn_code");
		String taxDesc = req.getParameter("tax_desc");
		float taxPer = Float.parseFloat(req.getParameter("tax_per"));
		float cgstPer = Float.parseFloat(req.getParameter("cgst_per"));
		float sgstPer = Float.parseFloat(req.getParameter("sgst_per"));
		float igstPer = Float.parseFloat(req.getParameter("igst_per"));
		float cessPer = Float.parseFloat(req.getParameter("cess_per"));
	
		
		
		MTax mTax = new MTax();
		mTax.setHsnCode(hsnCode);
		mTax.setCgstPer(cgstPer);
		mTax.setSgstPer(sgstPer);
		mTax.setIgstPer(igstPer);
		mTax.setCessPer(cessPer);
		mTax.setTaxId(taxId);
		mTax.setTaxPer(taxPer);
		mTax.setTaxDesc(taxDesc);
		
		restTamplate = new RestTemplate();
		MTax tax = restTamplate.postForObject(Constants.url + "/ujwal/addNewTax", mTax, MTax.class); 
		
		if(tax!=null) {
			System.out.println("Sucess");
		}else {
			System.out.println("Fail");
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return "redirect:/showAddTax";
		
	}
	
	//
	
@RequestMapping(value="/editTax/{taxId}", method=RequestMethod.GET)
	
	public ModelAndView editTax(@PathVariable("taxId") int id) {
		
		ModelAndView mav = new ModelAndView("masters/addTax");
		try {
		restTamplate = new RestTemplate();
		MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		MTax taxList = restTamplate.postForObject(Constants.url + "/ujwal/getTaxById", map, MTax.class);
		mav.addObject("taxList", taxList);
		List<MTax> tList = restTamplate.getForObject(Constants.url + "/ujwal/getAllTaxes", List.class);
		mav.addObject("tList", tList);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}



@RequestMapping(value="/deleteTaxId/{taxId}", method=RequestMethod.GET)

public String deleteCustomer(@PathVariable("taxId") int id) {
	
	
	try {
	restTamplate = new RestTemplate();
	MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
	map.add("id", id);
	Info info = restTamplate.postForObject(Constants.url + "/ujwal/deleteMTax", map, Info.class);
	//mav.addObject("editComp", compList);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}

	return "redirect:/showAddTax";
}
@RequestMapping(value = "/deleteRecordofTax", method = RequestMethod.POST)
public String deleteRecordofTax(HttpServletRequest request, HttpServletResponse response) {
	
	try {
		RestTemplate rest = new RestTemplate();
		String[] taxIds = request.getParameterValues("taxIds");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < taxIds.length; i++) {
			sb = sb.append(taxIds[i] + ",");

		}
		String items = sb.toString();
		items = items.substring(0, items.length() - 1);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("taxIds", items);

		Info errMsg = rest.postForObject(Constants.url + "/ujwal/deleteMultiTax", map, Info.class);

	} catch (Exception e) {

		System.err.println("Exception in /deleteRecordofTax @MastContr  " + e.getMessage());
		e.printStackTrace();
	}
return "redirect:/showAddTax";
}


}
