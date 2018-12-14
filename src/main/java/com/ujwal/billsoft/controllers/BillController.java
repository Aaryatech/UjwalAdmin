package com.ujwal.billsoft.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.models.BillDetails;
import com.ujwal.billsoft.models.MCustomer;
import com.ujwal.billsoft.models.MGetPart;
import com.ujwal.billsoft.models.MPart;
@Controller
@Scope("session")
public class BillController {
	
	List<MCustomer> custList;
	RestTemplate rest = new RestTemplate();
	
	
	@RequestMapping(value = "/getCustById", method = RequestMethod.GET)
	public @ResponseBody MCustomer getCustById(HttpServletRequest request, HttpServletResponse response) {

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		int custId = Integer.parseInt(request.getParameter("custId"));

		map.add("id", custId);

		MCustomer custArray = rest.postForObject(Constants.url + "/ujwal/getCustomerById", map, MCustomer.class);

		System.err.println("Ajax custList List " + custArray.toString());

		return custArray;

	}
	@RequestMapping(value = "/getPartListById", method = RequestMethod.GET)
	public @ResponseBody MPart getPartById(HttpServletRequest request, HttpServletResponse response) {

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		int partId = Integer.parseInt(request.getParameter("partId"));

		map.add("id", partId);

		MPart custArray = rest.postForObject(Constants.url + "/ujwal/getPartById", map, MPart.class);

		System.err.println("Ajax custList List " + custArray.toString());

		return custArray;

	}
	List<BillDetails> detailList = new ArrayList<BillDetails>();

	@RequestMapping(value = "/addPartDetail", method = RequestMethod.GET)
	public @ResponseBody List<BillDetails> addPartDetail(HttpServletRequest request,
			HttpServletResponse response) {

		try {
				int partId = Integer.parseInt(request.getParameter("partId"));

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("id", partId);
				MGetPart parttax = rest.postForObject(Constants.url + "GetPartInfo", map, MGetPart.class);
				
				float qty = Float.parseFloat(request.getParameter("qty"));
				float partMrp = Float.parseFloat(request.getParameter("partMrp"));
				float disc =Float.parseFloat(request.getParameter("disc"));
				String remark = request.getParameter("remark");
				BillDetails bill = new BillDetails();
				bill.setPartId(partId);
				bill.setQty(qty);
				bill.setDiscPer(disc);
				bill.setRemark(remark);
				bill.setMrp(partMrp);
				bill.setUomName(parttax.getUomName());
				detailList.add(bill);
				
				//parttax.getExtVar1();
				System.out.println(parttax.toString());
				
				
				System.out.println("Part Name: "+parttax.getPartName());
		} catch (Exception e) {
			System.err.println("Exce In " + e.getMessage());
			e.printStackTrace();
		}
	//	System.err.println("  part List " + detailList.toString());

		return detailList;

	}

}
