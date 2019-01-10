package com.ujwal.billsoft.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.models.Info;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MLocation;
import com.ujwal.billsoft.models.MUser;
import com.ujwal.billsoft.models.UserBean;

@Controller	
public class UjwalUserController {

RestTemplate restTamplate = null;
	
	@RequestMapping(value="/addNewUser", method=RequestMethod.GET)
	public ModelAndView addShowCompanyForm() {
		
		ModelAndView mav = new ModelAndView("masters/addUser");
		try {
		restTamplate = new RestTemplate();
	   //List<MLocation> locList = restTamplate.getForObject(Constants.url + "/ujwal/getAllLocations", List.class);
	    //mav.addObject("locList", locList);
	    
	    List<MCompany> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
	    mav.addObject("compList", compList);
	    
	    //List<MUser> userList = restTamplate.getForObject(Constants.url + "/ujwal/getAllUsers", List.class);
		
	    List<UserBean> userList = restTamplate.getForObject(Constants.url + "/ujwal/getAllUsersByDel", List.class);
	    mav.addObject("userList", userList);
			
		mav.addObject("title", "Add User");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}
	
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String addNewUser(HttpServletRequest req, HttpServletResponse resp) {
		
		String path = null; 
		restTamplate = new RestTemplate();
		int user_id = 0;
		
		try {
			user_id = Integer.parseInt(req.getParameter("user_id"));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		int userType = Integer.parseInt(req.getParameter("userType"));
		String user_name = req.getParameter("user_name");
		String mob_no = req.getParameter("mob_no");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		int compId = Integer.parseInt(req.getParameter("compId"));
		int locId = Integer.parseInt(req.getParameter("locId"));
		
		MUser musr = new MUser();
		musr.setUserId(user_id);
		musr.setUserName(user_name);
		musr.setUserMobile(mob_no);
		musr.setUserEmail(email);
		musr.setUserPwd(pass);
		musr.setCompanyId(compId);
		musr.setLocationId(locId);
		musr.setEx_int(userType);
		MUser muser = restTamplate.postForObject(Constants.url + "/ujwal/insertNewUser", musr, MUser.class);
		if(muser!=null) {
			System.out.println("sucess");
		
		}else{
			System.out.println("Fail");
			
		}

		return "redirect:/addNewUser";
		
	}
	
	@RequestMapping(value="/editUser/{userId}", method=RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("userId") int id ) {
		
		ModelAndView mav = new ModelAndView("masters/addUser");
		restTamplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		MUser usr= restTamplate.postForObject(Constants.url + "/ujwal/getUserById", map, MUser.class);	
		mav.addObject("editLoc", usr);
		mav.addObject("locationId", usr.getLocationId());
	
		
		//List<MLocation> locList = restTamplate.getForObject(Constants.url + "/ujwal/getAllLocations", List.class);
		//mav.addObject("locList", locList);
		
		List<MCompany> compList = restTamplate.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		
		List<MUser> userList = restTamplate.getForObject(Constants.url + "/ujwal/getAllUsers", List.class);
		mav.addObject("userList", userList);	
		
		List<UserBean> user = restTamplate.getForObject(Constants.url + "/ujwal/getAllUsersByDel", List.class);
		mav.addObject("userList", user);
			
		mav.addObject("title", "Edit User");
		return mav;
	}
	
	
	@RequestMapping(value="/deleteUser/{userId}", method=RequestMethod.GET)

	public String deleteCompany(@PathVariable("userId") int id) {
			
		try {
		restTamplate = new RestTemplate();
		MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		Info info = restTamplate.postForObject(Constants.url + "/ujwal/deleteUser", map, Info.class);
		//mav.addObject("editComp", compList);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return "redirect:/addNewUser";
		}
	

	@RequestMapping(value = "/deleteRecordofUser", method = RequestMethod.POST)
	public String deleteRecordofCompany(HttpServletRequest request, HttpServletResponse response) {
		try {

				String[] userIds = request.getParameterValues("userIds");
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < userIds.length; i++) {
				sb = sb.append(userIds[i] + ",");
			}
		String items = sb.toString();
		items = items.substring(0, items.length() - 1);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("userIds", items);
		restTamplate = new RestTemplate();
		Info errMsg = restTamplate.postForObject(Constants.url + "/ujwal/deleteMultiUser", map, Info.class);

		} catch (Exception e) {

			System.err.println("Exception in /deleteRecordofUser @MastContr  " + e.getMessage());
			e.printStackTrace();
		}
			return "redirect:/addNewUser";
	}
}
