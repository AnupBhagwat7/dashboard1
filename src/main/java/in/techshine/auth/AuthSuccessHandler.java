/**
 * 
 */
package in.techshine.auth;

/**
 * @author anup
 *
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		// Get the role of logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();

		String targetUrl = "";
		if (role.contains("ROLE_ADMIN")) {
			targetUrl = "/admin/dashboard";
		}else if (role.contains("ROLE_EMPLOYER")) {
			targetUrl = "/employer/dashboard";
		}else if (role.contains("ROLE_USER")) {
			targetUrl = "/welcome";
		}
		return targetUrl;
	}
}
