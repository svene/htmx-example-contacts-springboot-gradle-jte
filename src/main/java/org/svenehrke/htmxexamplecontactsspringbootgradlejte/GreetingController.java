package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

	@GetMapping("/")
	public String index(Model model, HttpServletResponse response) {
		model.addAttribute("model", new DemoModel("World"));
		return "greeting";
	}
}
