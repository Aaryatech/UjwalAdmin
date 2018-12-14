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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ujwal.billsoft.models.Info;
import com.ujwal.billsoft.models.MUom;
import com.ujwal.billsoft.commons.Constants;

@Controller
public class UjwalUomController {

	RestTemplate restTamplate = null;
	
	@RequestMapping(value = "/showAddUom", method=RequestMethod.GET)
	public @ResponseBody ModelAndView uomForm() {
		
		ModelAndView mav = new ModelAndView("masters/addUom");
	
		restTamplate = new RestTemplate();
		List<MUom> muom = restTamplate.getForObject(Constants.url + "/ujwal/getAllMUom", List.class);
		mav.addObject("muomList", muom);
		mav.addObject("title", "Add Unit Of Measurement");
		return mav;
		
	}
	
	@RequestMapping(value = "/insertUom", method=RequestMethod.POST)
	public String newUom(HttpServletRequest req, HttpServletResponse resp) {
		
		int uomId = 0;
		try {
			uomId = Integer.parseInt(req.getParameter("uomId"));
		}catch(Exception e) {
			System.err.println("Null Value");
			System.err.println(e.getMessage());
		}
		
		String uom = req.getParameter("uomName");
		System.out.println("Data "+uomId+" "+uom);
		
		MUom muom = new MUom();
		muom.setUomId(uomId);
		muom.setUomName(uom);
		
		restTamplate = new RestTemplate();
		
		MUom mum = restTamplate.postForObject(Constants.url + "/ujwal/addNewMUom", muom , MUom.class);
		
		if(mum!=null) {
			System.out.println("Sucess");
		}else {
			System.out.println("Fail");
		}
		
		return "redirect:/showAddUom";
		
	}  
	
	@RequestMapping(value = "/editUom/{uomId}", method=RequestMethod.GET)
	public @ResponseBody ModelAndView edituomForm(@PathVariable("uomId") int id) {
		
		ModelAndView mav = new ModelAndView("masters/addUom");
		
		restTamplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		MUom muom = restTamplate.postForObject(Constants.url + "/ujwal/getMUomById", map, MUom.class);
		List<MUom> uom = restTamplate.getForObject(Constants.url + "/ujwal/getAllMUom", List.class);
		mav.addObject("muomList", uom);
		mav.addObject("editUom", muom);
		mav.addObject("title", "Edit Unit Of Measurement");
		return mav;
		
	}
	
	
	@RequestMapping(value = "/deleteRecordofUom", method = RequestMethod.POST)
	public String deleteRecordofUom(HttpServletRequest request, HttpServletResponse response) {
		try {

			String[] uomIds = request.getParameterValues("uomIds");
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < uomIds.length; i++) {
				sb = sb.append(uomIds[i] + ",");
			}
			String items = sb.toString();
			items = items.substring(0, items.length() - 1);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("uomIds", items);
			restTamplate = new RestTemplate();
			Info errMsg = restTamplate.postForObject(Constants.url + "/ujwal/deleteMultiUom", map, Info.class);

		} catch (Exception e) {

			System.err.println("Exception in /deleteRecordofUom @MastContr  " + e.getMessage());
			e.printStackTrace();
			
		}
	return "redirect:/showAddUom";
	}
	
}
