/**
 * 
 */
package in.techshine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author anup
 *
 */
@Controller
public class EmployerController {
	
	@RequestMapping(value= "/employer/dashboard" , method = RequestMethod.GET)
	public String loadEmployerPage() {
		
		return "/employer/homepage2";
	}

}
