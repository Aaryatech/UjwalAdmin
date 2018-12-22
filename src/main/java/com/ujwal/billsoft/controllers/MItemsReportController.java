package com.ujwal.billsoft.controllers;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ujwal.billsoft.commons.DateConvertor;
import com.ujwal.billsoft.commons.ExportToExcel;
import com.ujwal.billsoft.models.BillDetails;
import com.ujwal.billsoft.models.BillHeader;
import com.ujwal.billsoft.models.ItemBean;
import com.ujwal.billsoft.models.MPart;

@Controller
public class MItemsReportController {
	
	RestTemplate rest = null;
	
	/***********************************Item Report**************************************/
	@RequestMapping(value="/showItemsReport", method=RequestMethod.GET)
	
	public ModelAndView ShowItemForm() {
		
		ModelAndView mav = new ModelAndView("report/ItemReport");
		try {
			rest = new RestTemplate();
		List<MPart> partList = rest.getForObject(Constants.url + "/ujwal/getAllPart", List.class);
		mav.addObject("partList", partList);
		mav.addObject("title", "Items Report");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	} 
	
	@RequestMapping(value="/getItemListBetweenDate", method = RequestMethod.GET)
	
	public @ResponseBody List<ItemBean>  itemReport(HttpServletRequest request, HttpServletResponse response) {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		String fromDate =  request.getParameter("fromDate");
		String toDate =  request.getParameter("toDate");
		System.out.println("Item Data = "+ itemId+" "+fromDate+" "+toDate);
			
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("itemId", itemId);
		map.add("fromDate", DateConvertor.convertToYMD(fromDate));
		map.add("toDate", DateConvertor.convertToYMD(toDate));
		
		List<ItemBean>  getList = rest.postForObject(Constants.url + "/ujwal/getItemsBetweenDate", map,List.class);
		
		System.out.println("Response List  "+getList);
		return getList;
		
	}
	
}
