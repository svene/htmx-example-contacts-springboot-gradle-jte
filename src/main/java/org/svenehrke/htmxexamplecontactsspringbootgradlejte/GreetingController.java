package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GreetingController {

	@GetMapping("/")
	public RedirectView index() {
		return new RedirectView("/contacts");
	}

	@GetMapping("/contacts")
	public String contacts(Model model, HttpServletResponse response) {
		model.addAttribute("model", new DemoModel("World"));
		return "greeting";
	}
}
