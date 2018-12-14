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
import com.ujwal.billsoft.models.MGetPart;
import com.ujwal.billsoft.models.MPart;
import com.ujwal.billsoft.models.MTax;
import com.ujwal.billsoft.models.MUom;

@Controller
public class UjwalPartController {
	RestTemplate restTamplate = null;
	
	@RequestMapping(value="/showAddPart", method=RequestMethod.GET)
	
	public ModelAndView addShowCompanyForm() {
		
		ModelAndView mav = new ModelAndView("masters/addPart");
		try {
		restTamplate = new RestTemplate();
		List<MPart> partList = restTamplate.getForObject(Constants.url + "/ujwal/getAllPart", List.class);
		mav.addObject("pList", partList);
		List<MTax> taxList = restTamplate.getForObject(Constants.url + "/ujwal/getAllTaxes", List.class);
		mav.addObject("tList", taxList);
		List<MUom> muom = restTamplate.getForObject(Constants.url + "/ujwal/getAllMUom", List.class);
		mav.addObject("muomList", muom);
		List<MGetPart> getpartList = restTamplate.getForObject(Constants.url + "/getAllPartList", List.class);
		mav.addObject("getList", getpartList);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}

	@RequestMapping(value="/insertPart", method=RequestMethod.POST)
	public String newCompany(HttpServletRequest req, HttpServletResponse resp) {
		int partId=0;
		try
		{
			 partId = Integer.parseInt(req.getParameter("part_id"));
		}
		catch(Exception e){
			partId=0;
		}
		try {
		
		String partName = req.getParameter("part_name");
		String partRegisterNo = req.getParameter("part_register_no");
		int partUomId = Integer.parseInt(req.getParameter("part_uom_id"));
		int partTaxId = Integer.parseInt(req.getParameter("part_tax_id"));
		String partRoNo = req.getParameter("part_ro_no");
		String partMrp = req.getParameter("part_mrp");
		String partSpecification = req.getParameter("part_specification");
		String partNo = req.getParameter("part_no");
		
		
		
		MPart mPart = new MPart();
		mPart.setPartId(partId);
		mPart.setPartName(partName);
		mPart.setPartRegisterNo(partRegisterNo);
		mPart.setPartUomId(partUomId);
		mPart.setPartTaxId(partTaxId);
		mPart.setPartRoNo(partRoNo);
		mPart.setPartSpecification(partSpecification);
		mPart.setPartNo(partNo);
		mPart.setPartMrp(partMrp);
		
		restTamplate = new RestTemplate();
		MPart part = restTamplate.postForObject(Constants.url + "/ujwal/addNewPart", mPart, MPart.class); 
	
		if(part!=null) {
			System.out.println("Sucess");
		}else {
			System.out.println("Fail");
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return "redirect:/showAddPart";
		
	}
	
	//
	
@RequestMapping(value="/editPart/{partId}", method=RequestMethod.GET)
	
	public ModelAndView editCompany(@PathVariable("partId") int id) {
		
		ModelAndView mav = new ModelAndView("masters/addPart");
		try {
		restTamplate = new RestTemplate();
		MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		MPart partList = restTamplate.postForObject(Constants.url + "/ujwal/getPartById", map, MPart.class);
		mav.addObject("partList", partList);
		
		List<MPart> pList = restTamplate.getForObject(Constants.url + "/ujwal/getAllPart", List.class);
		mav.addObject("pList", pList);
		List<MTax> taxList = restTamplate.getForObject(Constants.url + "/ujwal/getAllTaxes", List.class);
		mav.addObject("tList", taxList);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}



@RequestMapping(value="/deletePart/{partId}", method=RequestMethod.GET)

public String deleteCustomer(@PathVariable("partId") int id) {
	
	
	try {
	restTamplate = new RestTemplate();
	MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
	map.add("id", id);
	Info info = restTamplate.postForObject(Constants.url + "/ujwal/deletePartId", map, Info.class);
	//mav.addObject("editComp", compList);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}

	return "redirect:/showAddPart";
}
@RequestMapping(value = "/deleteRecordofPart", method = RequestMethod.POST)
public String deleteRecordofPart(HttpServletRequest request, HttpServletResponse response) {
	
	try {
		RestTemplate rest = new RestTemplate();
		String[] partIds = request.getParameterValues("partIds");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < partIds.length; i++) {
			sb = sb.append(partIds[i] + ",");

		}
		String items = sb.toString();
		items = items.substring(0, items.length() - 1);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("partIds", items);

		Info errMsg = rest.postForObject(Constants.url + "/ujwal/deleteMultiPart", map, Info.class);

	} catch (Exception e) {

		System.err.println("Exception in /deleteRecordofPart @MastContr  " + e.getMessage());
		e.printStackTrace();
	}
return "redirect:/showAddPart";
}
}
