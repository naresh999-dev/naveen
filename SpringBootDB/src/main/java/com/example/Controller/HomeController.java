package com.example.Controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Dto.User;
import com.example.Service.MailUserService;
import com.example.Service.SMSUserService;
import com.example.Service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	@Autowired
	SMSUserService SMSUserService;
	@Autowired
	MailUserService mailUserService;

	@RequestMapping("/")
	public String Login(Model model ,@RequestHeader() Map<String,String> headers) {
		
		System.out.println("12122"+headers.entrySet());
		/*
		 * List<MediaType> x=headers.getAccept(); for (MediaType mediaType : x) {
		 * System.out.println(mediaType); }
		 */
		User user=new User();
		model.addAttribute("user",user);
		return "Login.jsp";
	}
	
	@RequestMapping("/home")
	public String home(Model model,@ModelAttribute("user") User user) {
		model.addAttribute("user",user);
		return "Home.jsp";
	}
	
	@RequestMapping("/signUp")
	public String signUp(Model model) {
		User user=new User();
		model.addAttribute("user",user);
		return "Signup.jsp";
	}
	
	@RequestMapping("/signIn")
	public String signIn(@ModelAttribute("user") User user,Model model,RedirectAttributes redirectAttributes,@RequestHeader("Accept") String Type) {
		System.out.println("callingg"+user.getUsername());
	user=userService.getUserByUersName(user.getUsername());
	if (user != null /* && user.isMobileActivation() */) {
		redirectAttributes.addAttribute("user",user);
		return "redirect:/home";
	}else {
		return "Login.jsp";
	}
		
	}
	
	

	

	@RequestMapping("/addUser")
	public String addUser(@ModelAttribute("user") User user,final RedirectAttributes redirectAttributes) {
		String message="Welcome ";
		System.out.println(user.getUname());
		user.setMobileNumber("+91"+user.getMobileNumber());
		user.setEmailActivationCode(getCode());
		user.setMobileActivationCode(getCode());
	    user=userService.addUser(user);
		message=message+user.getUname()+" "+"Actovation Code :"+user.getMobileActivationCode();
		SMSUserService.sendSMS(user.getMobileNumber(),message);
		message=message+user.getUname()+" "+"Actovation Code :"+user.getEmailActivationCode();
		mailUserService.snedMail(user.getEmial(),message);
		redirectAttributes.addAttribute("user", user);
		return "redirect:/verification";
	}

	public int getCode() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
	
	@RequestMapping("/verification")
	public String VerificationPage(HttpServletRequest request,ModelMap model,@ModelAttribute("user") User user) {
		model.addAttribute("id",user.getId());
		return "Verfication.jsp";
	}
	
	@RequestMapping("/verificationCode")
	public String Verification(HttpServletRequest request,final RedirectAttributes redirectAttributes,Model model) {
		User user=userService.getUser(Integer.parseInt(request.getParameter("id")));
		System.out.println(user.getMobileActivationCode());
		if(user.getMobileActivationCode()==Integer.parseInt(request.getParameter("otp"))) {
			model.addAttribute("user",new User());
			user.setMobileActivation(true);
			userService.addUser(user);
			return "Login.jsp";
		}
		redirectAttributes.addAttribute("id", user.getId());
		return "redirect:/verification";
	}
	@RequestMapping("/forGotPassword")
	public String ForGotPassword(@ModelAttribute("user") User user,Model model) {
		String message="Hi ";
	user=userService.findByMobileNumber("+91"+user.getMobileNumber());
	user.setPassword(String.valueOf(getCode()));
	userService.addUser(user);
	message=message+user.getUsername()+" this your new Password "+user.getPassword();
	SMSUserService.sendSMS(user.getMobileNumber(),message);
	model.addAttribute(new User());
		return "Login.jsp";
	}
	
	@RequestMapping("/forGotPasswordPage")
	public String ForGotPasswordPage(Model model) {
	model.addAttribute(new User());
		return "ForGotPassword.jsp";
	}
	
	
	@GetMapping(value = "/getAllUser",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUserJson(@RequestHeader(name="Accept")  String name) {
		System.out.println("json...............");
		System.out.println(name);
		List<User> users=userService.getAllUsers();
		// HttpHeaders headers = new HttpHeaders();
			/*
			 * if (type.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
			 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); return new
			 * ResponseEntity<>(users,headers,HttpStatus.OK); }else {
			 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML)); return new
			 * ResponseEntity<>(users,headers,HttpStatus.OK); }
			 */
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllUser",produces =MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<User>> getAllUserXml() {
		System.out.println("xmlllll...............");
		List<User> users=userService.getAllUsers();
		// HttpHeaders headers = new HttpHeaders();
			/*
			 * if (type.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
			 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); return new
			 * ResponseEntity<>(users,headers,HttpStatus.OK); }else {
			 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML)); return new
			 * ResponseEntity<>(users,headers,HttpStatus.OK); }
			 */
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	@GetMapping(value = "/getAllUser")//consumes =MediaType.APPLICATION_XML_VALUE
	public ResponseEntity<List<User>> getAllUser(@RequestHeader(name="Accept")  String name) {
		System.out.println(".....................");
		System.out.println(name);
		List<User> users=userService.getAllUsers();
		// HttpHeaders headers = new HttpHeaders();
			/*
			 * if (type.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
			 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); return new
			 * ResponseEntity<>(users,headers,HttpStatus.OK); }else {
			 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML)); return new
			 * ResponseEntity<>(users,headers,HttpStatus.OK); }
			 */
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@RequestMapping("/getUser/{id}")
	public String getUser(@PathVariable int id) {
		User user=userService.getUser(id);
		System.out.println("HomeController"+user.getUname());
		return "home.jsp";
	}
	
	

	@RequestMapping("/deleteByID")
	public String delteById(@PathVariable int id) {
		userService.delteById(id);
		return "home.jsp";
	}

	@RequestMapping("/deleteAllUsers")
	public String deleteAllUsers() {
		userService.deleteAllUsers();
		return "home.jsp";
	}
	
	@RequestMapping("/updateUser")
	@Transactional
	public String updateUser(HttpServletRequest req) {
		User user=userService.getUser(Integer.parseInt(req.getParameter("t1")));
		user.setUname(req.getParameter("t2"));
	    user=userService.updateUser(user);
		System.out.println("Finally update"+user.getUname());
		return "home.jsp";
	}

}
