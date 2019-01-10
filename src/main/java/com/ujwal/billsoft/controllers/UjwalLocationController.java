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

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.models.Info;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MLocComp;
import com.ujwal.billsoft.models.MLocation;

@Controller
public class UjwalLocationController {

	RestTemplate restTamplate = null;
	
	@RequestMapping(value="/showAddLocation", method=RequestMethod.GET)
public ModelAndView addShowCompanyForm() {
		
		ModelAndView mav = new ModelAndView("masters/addLocation");
		try {
		restTamplate = new RestTemplate();
		
		List<MLocation> locList = restTamplate.getForObject(Constants.url + "/ujwal/getAllLocationsByDel", List.class);
		List<MCompany> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		List<MLocComp> locCompList = restTamplate.getForObject(Constants.url + "/ujwal/getCompLoc", List.class);
		
		mav.addObject("compList", compList);
		//mav.addObject("locList", locList);
		mav.addObject("locComp", locCompList);
		mav.addObject("title", "Add Location");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}
	
	
	@RequestMapping(value="/insertLocation", method=RequestMethod.POST)
	public String newLocation(HttpServletRequest req, HttpServletResponse resp) {
		int locid = 0;
		System.out.println("Hellooo");
		try {
			
		 locid = Integer.parseInt(req.getParameter("loc_id"));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		String locName = req.getParameter("loc_name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mob_no");
		int compId = Integer.parseInt(req.getParameter("compId"));
		String fax = req.getParameter("fax");
		
		System.out.println("Data = "+locid+" "+locName+" "+address+" "+email+" "+mobile+" "+compId+" "+fax);
		
		MLocation mLoc = new MLocation();
		mLoc.setLocationId(locid);
		mLoc.setLocation_name(locName);
		mLoc.setLocationAddress(address);
		mLoc.setEmail(email);
		mLoc.setPhoneNo(mobile);
		mLoc.setFaxNo(fax);
		mLoc.setCompId(compId);
			
		restTamplate = new RestTemplate();
		MLocation loc = restTamplate.postForObject(Constants.url + "/ujwal/addNewLocation", mLoc, MLocation.class); 
		
		if(loc!=null) {
			System.out.println("Sucess");
		}else {
			System.out.println("Fail");
		}
		
		
		return "redirect:/showAddLocation";
	}
	
	@RequestMapping(value="/editLocation/{locId}", method=RequestMethod.GET)
	public ModelAndView editCompany(@PathVariable("locId") int id) {
		
		ModelAndView mav = new ModelAndView("masters/addLocation");
		try {
		restTamplate = new RestTemplate();
		MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		
		List<MLocation> locList = restTamplate.getForObject(Constants.url + "/ujwal/getAllLocationsByDel", List.class);
		MLocation loc = restTamplate.postForObject(Constants.url + "/ujwal/getLocationById", map, MLocation.class);
		List<MCompany> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		List<MLocComp> locCompList = restTamplate.getForObject(Constants.url + "/ujwal/getCompLoc", List.class);
		
		
		mav.addObject("compList", compList);
		mav.addObject("editLoc", loc);
		mav.addObject("locList", locList);
		mav.addObject("locComp", locCompList);
		mav.addObject("title", "Edit Location");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}

@RequestMapping(value="/deleteLocation/{locId}", method=RequestMethod.GET)

public String deleteCompany(@PathVariable("locId") int id) {
	
	
	try {
	restTamplate = new RestTemplate();
	MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
	map.add("id", id);
	Info info = restTamplate.postForObject(Constants.url + "/ujwal/deleteLocation", map, Info.class);
	//mav.addObject("editComp", compList);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}

	return "redirect:/showAddLocation";
}


@RequestMapping(value = "/deleteRecordofLocation", method = RequestMethod.POST)
public String deleteRecordofLocation(HttpServletRequest request, HttpServletResponse response) {
	try {

		String[] LocIds = request.getParameterValues("LocIds");
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < LocIds.length; i++) {
			sb = sb.append(LocIds[i] + ",");
		}
		String items = sb.toString();
		items = items.substring(0, items.length() - 1);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("LocIds", items);
		restTamplate = new RestTemplate();
		Info errMsg = restTamplate.postForObject(Constants.url + "/ujwal/deleteMultiLocation", map, Info.class);

	} catch (Exception e) {

		System.err.println("Exception in /deleteRecordofLocation @MastContr  " + e.getMessage());
		e.printStackTrace();
		
	}
	return "redirect:/showAddLocation";
}


	@RequestMapping(value = "/getLocation", method = RequestMethod.GET)
	public @ResponseBody List<MLocation> getLocation(HttpServletRequest req, HttpServletResponse resp){
		
		int companyId = 0;
		try {
			companyId = Integer.parseInt(req.getParameter("companyId"));
			System.out.println("IDss="+companyId);
		}catch(Exception e) {
			 companyId = 0;
		}
		restTamplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("companyId", companyId);
		List<MLocation> locList = restTamplate.postForObject(Constants.url + "/ujwal/getAllLocations", map, List.class);
		System.out.println("Response Loc="+locList);
		return locList;
	}

}
