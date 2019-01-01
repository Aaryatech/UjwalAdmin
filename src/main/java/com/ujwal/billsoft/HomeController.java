package com.ujwal.billsoft;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.models.CradentialValidator;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MLocation;
import com.ujwal.billsoft.models.MUser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "login";
	}
	

	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse res) throws IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		ModelAndView mav = new ModelAndView("login");

		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession session = request.getSession();
		try {
			System.out.println("Login Process " + name);

			if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

				mav = new ModelAndView("login");
			} else {
				
						System.out.println("Ok");
						RestTemplate restTemplate = new RestTemplate();

						MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

						map.add("usrMob", name);
						map.add("userPass", password);

						CradentialValidator	userObj = restTemplate.postForObject(Constants.url + "/ujwal/loginUser", map, CradentialValidator.class);
								
						String loginResponseMessage = "";

						if (userObj.isError() == false) {

							session.setAttribute("UserDetail", userObj);
							//CradentialValidator userResponse = (CradentialValidator) session.getAttribute("UserDetail");
							session.setAttribute("userBean", userObj.getMusr());

					
							mav = new ModelAndView("home");
							//mav = new ModelAndView("common/right");
							session.setAttribute("userName", name);

							loginResponseMessage = "Login Successful";
							mav.addObject("loginResponseMessage", loginResponseMessage);

							map = new LinkedMultiValueMap<String, Object>();
							int userId = userObj.getMusr().getUserId();
							map.add("usrId", userId);
							System.out.println("user data" + userObj.toString());
							
							MUser userResponse = (MUser) session.getAttribute("userBean");
							
							System.out.println("User Cred="+userResponse.getUserName()+" "+userResponse.getCompanyId()+" "+userResponse.getUserId());
							
							
							int locationId = userResponse.getLocationId();
							int companyId = userResponse.getCompanyId();
							System.out.println("Compannyy IDSS = "+companyId+" "+locationId);
							
							RestTemplate rest = new RestTemplate();
							
							
							map.add("id", companyId);
							MCompany mComp = rest.postForObject(Constants.url + "/ujwal/getCompanyById", map, MCompany.class);
							System.out.println("Company is="+mComp.getCompName());
							map = new LinkedMultiValueMap<String, Object>();
							map.add("id", locationId);
							MLocation mLoc = rest.postForObject(Constants.url +"/ujwal/getLocationById", map, MLocation.class);
							System.out.println("Data="+mLoc.toString());
							System.out.println("Location is="+mLoc.getLocation_name());
							
							session.setAttribute("companyName", mComp.getCompName());
							session.setAttribute("locationName", mLoc.getLocation_name());

							return mav;
						} else {

							mav = new ModelAndView("login");
							System.out.println("Invalid login credentials");

						}

					}
				} catch (Exception e) {
					System.out.println("HomeController Login API Excep:  " + e.getMessage());
					e.printStackTrace();

				}	
	return mav;
}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("User Logout");

		session.invalidate();
		return "redirect:/";
	}

	/*@ExceptionHandler(LoginFailException.class)
	public String redirectToLogin() {
		System.out.println("HomeController Login Fail Excep:");

		return "login";
	}*/
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getCompLocName(HttpServletRequest request ,HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("common/right");
		HttpSession session = request.getSession();
		MUser userResponse = (MUser) session.getAttribute("userBean");
			
		System.out.println("User Cred="+userResponse.getUserName()+" "+userResponse.getCompanyId()+" "+userResponse.getUserId());
		
		
		int locationId = userResponse.getLocationId();
		int companyId = userResponse.getCompanyId();
		System.out.println("Compannyy IDSS = "+companyId+" "+locationId);
		
		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", companyId);
		map.add("id", locationId);
		
		MCompany mComp = rest.postForObject(Constants.url + "/ujwal/getCompanyById", map, MCompany.class);
		System.out.println("Company is="+mComp.getCompName());
		
		MLocation mLoc = rest.postForObject(Constants.url +"/ujwal/getLocationById", map, MLocation.class);
		System.out.println("Location is="+mLoc.getLocation_name());
		
		mav.addObject("mComp", mComp);
		mav.addObject("mLoc", mLoc);
		
		return mav;
		
	}
*/

	
}
