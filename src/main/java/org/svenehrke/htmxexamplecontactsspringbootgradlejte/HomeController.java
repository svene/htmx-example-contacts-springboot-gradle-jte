package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

	@GetMapping("/")
	public RedirectView index() {
		return new RedirectView("/contact");
	}

}
