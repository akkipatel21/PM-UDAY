package com.controller.adminPage;
a
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AdminRpt_DAO;
import com.dao.AdminRpt_DAOImpl;
import com.dao.CreateUserDAO;
import com.dao.CreateUserDAOImpl;
import com.dao.DeskVeri_DAO;
import com.dao.DeskVeri_DAOImpl;
import com.dao.UserLoginDAO;
import com.dao.UserLoginDAOImpl;
import com.models.AdminLogin;
import com.models.ApplicationFormDDANew;
import com.models.AssignAssembly;
import com.models.Colony_Master_Model;
import com.models.UserLogin;
import com.models.desk_deficiency;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class AdminController {	
	 public CreateUserDAO createUserDAO = new CreateUserDAOImpl();
	 public UserLoginDAO userLoginDAO = new UserLoginDAOImpl();
	 public AdminRpt_DAO adminDao = new AdminRpt_DAOImpl();
	 
	@RequestMapping(value="/adminPage")
	public ModelAndView adminPage(ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session){
		System.out.println("Admin Page MODULE");
		int role = Integer.parseInt(session.getAttribute("role").toString());
		if(role != 1){
			model.put("msg","You are not Authorise User for this Module!");
			return new ModelAndView("redirect:login");
		}
		model.put("role",role);
		model.put("msg",msg);
		return new ModelAndView("adminPageMiduleTile");
	}
	
	@RequestMapping(value="/createUserForm")
	public ModelAndView createUserForm(ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session){
		System.out.println("Create UserForm MODULE");
		int role = Integer.parseInt(session.getAttribute("role").toString());
		if(role != 1){
			model.put("msg","You are not Authorise User for this Module!");
			return new ModelAndView("redirect:login");
		}
		model.put("msg",msg);
		return new ModelAndView("createUserFormTile","userLogin",new UserLogin());
	}
	
	
	
	@RequestMapping(value = "/admin/locationGisModule", method = RequestMethod.GET)
	public ModelAndView locationGisModulePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("locationGisModulePage");
		return model;
	}
	
	@RequestMapping(value = "/Vision", method = RequestMethod.GET)
	public ModelAndView Vision() {
		ModelAndView model = new ModelAndView();
		model.setViewName("VisionTile");
		return model;
	}

	
	
	@RequestMapping(value = "/HelpDesk", method = RequestMethod.GET)
	public ModelAndView Helpdesk() {
		ModelAndView model = new ModelAndView();
		model.setViewName("HelpDeskTile");
		return model;
	}
	
	
	@RequestMapping(value = "/Processing_Center", method = RequestMethod.GET)
	public ModelAndView Processing_Center() {
		ModelAndView model = new ModelAndView();
		model.setViewName("ProcessingCenterTile");
		return model;
	}
	@RequestMapping(value = "/Instructions", method = RequestMethod.GET)
	public ModelAndView Instructions() {
		ModelAndView model = new ModelAndView();
		model.setViewName("instructionsTile");
		return model;
	}
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public ModelAndView createUser(@RequestParam(value="msg",required =false)String msg,ModelMap modelMap) {
		ModelAndView model = new ModelAndView();
		modelMap.put("msg",msg);
//		model.setViewName("createUserTile");
		return new ModelAndView("createUserTile");
	}
	@RequestMapping(value = "/colonyAssignment", method = RequestMethod.GET)
	public ModelAndView colonyAssginment() {
		ModelAndView model = new ModelAndView();
		model.setViewName("colonyAssignmentTile");
		return model;
	}

	
	@RequestMapping(value = "/saveCreateUser", method = RequestMethod.POST)
	public ModelAndView saveCreateUser (@ModelAttribute("createUser")AdminLogin adminLogin,ModelMap model) {
		Session session11 = HibernateUtil.getSessionFactory().openSession();
		session11.beginTransaction();
		session11.save(adminLogin);
		session11.getTransaction().commit();
		session11.close();
		String msg = ("New "+adminLogin.getType()+" created");
		model.put("msg", msg);
		return new ModelAndView("redirect:createUser");
	}
	
	@RequestMapping(value = "/getUserNames")
	public @ResponseBody List<AdminLogin> getUserNames (String userType) {
		return adminDao.getUserNamesByUserType(userType);
	}
	@RequestMapping(value = "/getAssemblyList")
	public @ResponseBody List<Colony_Master_Model> getAssemblyList (String userType) {
		return adminDao.getAssemblyList(userType);
	}
	
	@RequestMapping(value = "/assignAssembly",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView assignAssembly(HttpServletRequest request,
	@ModelAttribute("colonyAssignment") AssignAssembly assignAssembly, ModelMap model,@RequestParam(value = "msg", required = false) String msg, HttpSession session) throws ParseException, IOException  {
		
		int userId = adminDao.getUserIdByUserName(assignAssembly.getUsername());
		System.out.println(assignAssembly.getUsername());
		System.out.println(assignAssembly.getType());
		String[] defcount=request.getParameterValues("fields_count");
		int v=Integer.parseInt(defcount[0]);
		for (int i=1;i<=v;i++)
		{
			String[] assembly = request.getParameterValues("assembly"+i);
			String abc = assembly[0];
			List<Colony_Master_Model> list = adminDao.getColoniesByAssemblyName(abc);
			for (Colony_Master_Model colony_Master_Model : list) {
			assignAssembly.setAssembly(abc);
			assignAssembly.setType(assignAssembly.getType());
			assignAssembly.setUser_id(userId);
			assignAssembly.setColony_reg_no(colony_Master_Model.getReg_no());
			assignAssembly.setAssembly_no(colony_Master_Model.getAssembly_no());
			assignAssembly.setDistrict(colony_Master_Model.getDistrict());
			Session session1 = HibernateUtilNA.getSessionFactory().openSession(); 
			session1.beginTransaction(); 
			session1.save(assignAssembly);
			session1.getTransaction().commit();
			session1.close();
			}
		}
		
		return new ModelAndView("redirect:colonyAssignment");
	}
	
	


	//server path
	
//  private String server = "10.247.102.61";
	private String server = "192.168.1.196"; 
	private int port = 8080;
		
				@RequestMapping("/wms")
				@ResponseBody
				public String mirrorRest(@RequestBody String body, HttpMethod method, HttpServletRequest request,
				    HttpServletResponse response) throws URISyntaxException
				{
					//System.out.println("sadasdsadhsdsd"+request.getQueryString());
				//	String temp = new String(request.getQueryString());
				String req =	request.getQueryString().replace("%2C", ",").replace("%3A", ":").replace("%2F", "/").replace("%3D", "=").replace("%5B", "[").replace("%5D","]").replace("%27", "'").replace("%20", " ").replace("%3C", "<").replace("%3E", ">");
					

					
				    URI uri = new URI("http", null, server, port,"/geoserver/cite/wms",req , null);
				
				  //  System.out.println(uri);
				    RestTemplate restTemplate = new RestTemplate();
				    ResponseEntity<String> responseEntity =
				        restTemplate.exchange(uri, method, new HttpEntity<String>(body), String.class);

				    return responseEntity.getBody();
				}
				
				
				@RequestMapping("/ows")
				@ResponseBody
				public String mirrorRestOWS(@RequestBody String body, HttpMethod method, HttpServletRequest request,
				    HttpServletResponse response) throws URISyntaxException
				{
					//System.out.println("sadasdsadhsdsd"+request.getQueryString());
				//	String temp = new String(request.getQueryString());
				String req =	request.getQueryString().replace("%2C", ",").replace("%3A", ":").replace("%2F", "/").replace("%27", "'").replace("%20", " ");
					

					
				    URI uri = new URI("http", null, server, port,"/geoserver/cite/ows",req , null);
				
				  //  System.out.println(uri);
				    RestTemplate restTemplate = new RestTemplate();
				    ResponseEntity<String> responseEntity =
				        restTemplate.exchange(uri, method, new HttpEntity<String>(body), String.class);

				    return responseEntity.getBody();
				}
				
				
				@RequestMapping(value="/createDistrictUserForm")
				public ModelAndView createDistrictUserForm(ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session){
					System.out.println("Create District UserForm MODULE");
					int role = Integer.parseInt(session.getAttribute("role").toString());
					String state = session.getAttribute("state").toString();
					if(role != 3){
						model.put("msg","You are not Authorise User for this Module!");
						return new ModelAndView("redirect:login");
					}
					System.out.println("state==" + state);
					model.put("state",state);
					model.put("msg",msg);
					return new ModelAndView("createDistrictUserFormTile","userLogin",new UserLogin());
				}
				
				
				@RequestMapping(value = "/adminModule3", method = RequestMethod.GET)
				public ModelAndView adminModule3() {
					ModelAndView model = new ModelAndView();
					model.setViewName("adminModule3Tile");
					return model;
				}

}
