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
import com.ujwal.billsoft.models.MCustomer;
import com.ujwal.billsoft.models.MModelBean;

@Controller
public class UjwalModelController {
	
	RestTemplate rest = new RestTemplate();
	MultiValueMap<String, Object> map = null;
	
	@RequestMapping(value="/showAddModel", method = RequestMethod.GET)
	public ModelAndView showAddModel() {
		ModelAndView mav = new ModelAndView("masters/addModel");
		List<MModelBean> modBean = rest.getForObject(Constants.url+ "/ujwal/getModelByDelStatus", List.class);
		mav.addObject("modelList", modBean);
		
		List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		
		mav.addObject("title","Add Model");
		mav.addObject("comp.custState","Maharashtra");
		return mav;
		
	}
	
	@RequestMapping(value = "/insertModel", method = RequestMethod.POST)
	public String addNewModel(HttpServletRequest req, HttpServletResponse resp) {
	
		
		
		int modelId = 0;
	
		try {
			modelId = Integer.parseInt(req.getParameter("model_id"));
		
			System.out.println("id="+modelId);
		}catch(Exception e) {
			modelId = 0;
			
		}
		int compId = Integer.parseInt(req.getParameter("compId"));
		System.out.println(compId);
		String modelNo = req.getParameter("model_no");
		String model_name = req.getParameter("model_name");
		String productionDate = req.getParameter("production_date");
		
		
		MModelBean mod = new MModelBean();
		mod.setModelId(modelId);
		mod.setModelName(model_name);
		mod.setModelNo(modelNo);
		mod.setProductionDate(productionDate);
		mod.setCompanyId(compId);
		
		map = new LinkedMultiValueMap<>();
		map.add("mod", mod);
		
		MModelBean modb = rest.postForObject(Constants.url + "/ujwal/insertNewModel", mod, MModelBean.class); 
		
		if(modb!=null) {
			System.out.println("Sucess");
		}else {
			System.out.println("Fail");
		}
		return "redirect:/showAddModel";
		
	}
	

@RequestMapping(value="/editModel/{modelId}", method=RequestMethod.GET)
	
	public ModelAndView editCompany(@PathVariable("modelId") int id) {
		
	ModelAndView mav = new ModelAndView("masters/addModel");
		try {
		rest = new RestTemplate();
		MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		
		mav.addObject("title", "Update Model");
		
		List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		
		List<MModelBean> modBean = rest.getForObject(Constants.url+ "/ujwal/getModelByDelStatus", List.class);
		mav.addObject("modelList", modBean);
		
		MModelBean modb = rest.postForObject(Constants.url + "/ujwal/getModelById", map, MModelBean.class);
		mav.addObject("editModel", modb);		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}




@RequestMapping(value="/deleteModel/{modelId}", method=RequestMethod.GET)

public String deleteCustomer(@PathVariable("modelId") int modelId) {
	
	try {
	rest = new RestTemplate();
	MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
	map.add("modelId", modelId);
	Info info = rest.postForObject(Constants.url + "/ujwal/changeModelDelStatus", map, Info.class);
	
	}catch(Exception e){
		System.out.println(e.getMessage());
	}

	return "redirect:/showAddModel";
}



	@RequestMapping(value = "/deleteRecordofModel", method = RequestMethod.POST)
	public String deleteRecordofCustomer(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			RestTemplate rest = new RestTemplate();
			String[] modelIds = request.getParameterValues("modelIds");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < modelIds.length; i++) {
				sb = sb.append(modelIds[i] + ",");

			}
			String items = sb.toString();
			items = items.substring(0, items.length() - 1);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("modelIds", items);

			Info inf = rest.postForObject(Constants.url + "/ujwal/deleteMultiModels", map, Info.class);

		} catch (Exception e) {

			System.err.println("Exception in /deleteRecordofModel @MastContr  " + e.getMessage());
			e.printStackTrace();
		}
	return "redirect:/showAddModel";
	}

	
	@RequestMapping(value="/getUniqueModelNo",method=RequestMethod.GET)
	
	public @ResponseBody List<MModelBean>  getModelById(HttpServletRequest req, HttpServletResponse resp) {
		
		
		int companyId = Integer.parseInt(req.getParameter("companyId"));
		System.out.println("Company No="+companyId);
		

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("companyId", companyId);
		
		List<MModelBean> modelList = rest.postForObject(Constants.url + "/ujwal/getModelByCompanyId", map, List.class);
		System.out.println("Response 1="+modelList);
		System.out.println("Response 2="+modelList.toString());
		
		
		return modelList;
		
	}
	
	
}
