package com.ujwal.billsoft.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.commons.VpsImageUpload;
import com.ujwal.billsoft.models.Info;
import com.ujwal.billsoft.models.MCompany;

@Controller
public class UjwalCompanyController {

	RestTemplate restTamplate = null;
	
	@RequestMapping(value="/showAddCompany", method=RequestMethod.GET)
	public ModelAndView addShowCompanyForm() {
		
		ModelAndView mav = new ModelAndView("masters/addcompany");
		try {
		restTamplate = new RestTemplate();
		List<MCompany> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		mav.addObject("title", "Add Company");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}
	
	@RequestMapping(value="/insertCompany", method=RequestMethod.POST)
	public String newCompany(HttpServletRequest req, HttpServletResponse resp,@RequestParam("file") List<MultipartFile> file) {
		int compId = 0;
		try {
		 compId = Integer.parseInt(req.getParameter("comp_id"));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		String compName = req.getParameter("comp_name");
		String address = req.getParameter("comp_add");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mob_no");
		String pan = req.getParameter("pan_no");
		String gstNo = req.getParameter("gst_no");
		VpsImageUpload upload = new VpsImageUpload();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		 
		System.out.println(sdf.format(cal.getTime()));

		/*String curTimeStamp = sdf.format(cal.getTime());*/
		String curTimeStamp = "Img";

		try {
			
			upload.saveUploadedFiles(file, Constants.COMPANY_IMAGE_TYPE, curTimeStamp + "-" + file.get(0).getOriginalFilename());
			
			System.out.println("upload method called " + file.toString());
			
		} catch (IOException e) {
			
			System.out.println("Exce in File Upload In Item Insert " + e.getMessage());
			e.printStackTrace();
		}
	
		MCompany mComp = new MCompany();
		mComp.setCompId(compId);
		mComp.setCompName(compName);
		mComp.setAddress(address);
		mComp.setEmail(email);
		mComp.setPhoneNo(mobile);
		mComp.setPanNo(pan);
		mComp.setGstid(gstNo);
		mComp.setLogo(curTimeStamp + "-" + file.get(0).getOriginalFilename());
		
		restTamplate = new RestTemplate();
		MCompany company = restTamplate.postForObject(Constants.url + "/ujwal/addNewCompany", mComp, MCompany.class); 
		
		if(company!=null) {
			System.out.println("Sucess");
		}else {
			System.out.println("Fail");
		}
		
		
		return "redirect:/showAddCompany";
		
	}
	
	
	
@RequestMapping(value="/editCompany/{compId}", method=RequestMethod.GET)
	
	public ModelAndView editCompany(@PathVariable("compId") int id) {
		
		ModelAndView mav = new ModelAndView("masters/addcompany");
		try {
		restTamplate = new RestTemplate();
		MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		List<MCompany> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		MCompany editComp = restTamplate.postForObject(Constants.url + "/ujwal/getCompanyById", map, MCompany.class);
		mav.addObject("editComp", editComp);
		mav.addObject("title", "Edit Company");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}



@RequestMapping(value="/deleteCompany/{compId}", method=RequestMethod.GET)

public String deleteCompany(@PathVariable("compId") int id) {
	
	
	try {
	restTamplate = new RestTemplate();
	MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
	map.add("id", id);
	Info info = restTamplate.postForObject(Constants.url + "/ujwal/deleteCompany", map, Info.class);
	//mav.addObject("editComp", compList);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}

	return "redirect:/showAddCompany";
	}


@RequestMapping(value = "/deleteRecordofCompany", method = RequestMethod.POST)
public String deleteRecordofCompany(HttpServletRequest request, HttpServletResponse response) {
	try {

		String[] companyIds = request.getParameterValues("companyIds");
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < companyIds.length; i++) {
			sb = sb.append(companyIds[i] + ",");
		}
		String items = sb.toString();
		items = items.substring(0, items.length() - 1);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("companyIds", items);
		restTamplate = new RestTemplate();
		Info errMsg = restTamplate.postForObject(Constants.url + "/ujwal/deleteMultiCompany", map, Info.class);

	} catch (Exception e) {

		System.err.println("Exception in /deleteRecordofCompany @MastContr  " + e.getMessage());
		e.printStackTrace();
	}
return "redirect:/showAddCompany";
}
}
