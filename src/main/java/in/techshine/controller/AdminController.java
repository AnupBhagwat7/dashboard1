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
public class AdminController {
	
	@RequestMapping(value= "/admin/dashboard" , method = RequestMethod.GET)
	public String loadAdminPage() {
		
		return "/admin/homepage1";
	}

}
