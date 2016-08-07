package pl.spring.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ViewNames;

/**
 * Login controller class
 */
@Controller
public class LoginController {

	/**
	 * Shows a login view.
	 * 
	 * @return view name login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public final String login() {
		return ViewNames.LOGIN;
	}

	/**
	 * Shows a login failed view.
	 * 
	 * @return view name login
	 */
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public final String loginerror(final Model model) {
		model.addAttribute("error", "true");
		return ViewNames.LOGIN;

	}

	/**
	 * Shows a logout view.
	 * 
	 * @return view name logout
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public final String logout(final Model model) {
		return ViewNames.LOGOUT;
	}

	/**
	 * Shows a 403 view.
	 * 
	 * @param user
	 *            user details
	 * 
	 * @return model with a user name object
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public final ModelAndView accesssDenied(final Principal user) {
		ModelAndView model = new ModelAndView();
		model.addObject("userName", user.getName());
		return model;

	}
}
