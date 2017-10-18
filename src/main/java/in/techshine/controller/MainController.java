/**
 * 
 */
package in.techshine.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.techshine.beans.User;
import in.techshine.service.SecurityService;
import in.techshine.service.UserService;
import in.techshine.validator.UserValidator;

/**
 * @author anup
 *
 */
@Controller
public class MainController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	private static final Logger logger = Logger.getLogger(MainController.class); 
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		
		logger.debug("Loading registration page");
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		
		logger.debug(userForm.getUsername() +" started registering ");
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			logger.debug(userForm.getUsername() +" has some validation errors while registering ");
			return "registration";
		}

		userService.save(userForm);
		logger.debug(userForm.getUsername() +" registration is successful . Autologin started. ");
		
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		logger.debug(" registration is successful . Redirecting to homepage. ");
		return "redirect:homepage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout, HttpServletRequest request) {
		if (error != null){
			logger.debug("Username and password is invalid error occured");
			model.addAttribute("error", "Your username and password is invalid.");
		}
		
		if (logout != null){
			logger.debug("User is logging out");
			model.addAttribute("message", "You have been logged out successfully.");
			
			HttpSession session = request.getSession(false);
			 SecurityContextHolder.clearContext();
			 session = request.getSession(false);
			 
			 if (session != null) { 
			 session.invalidate();
			 session = null;
			 }
			 
			 for (Cookie cookie : request.getCookies()) {
			 cookie.setMaxAge(0);
			 }	
		}
		return "login";
	}
	
	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		
		logger.debug("Loading dashboard page");
		return "/user/homepage";
	}
}
