package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@Slf4j
public class ContactController {

	private final ContactService contactService;
	private final Something something;

	@GetMapping("/contacts")
	public String contacts(@RequestParam(required = false) String q, Model model) {
		log.info("query param q: {}", q);
		if (q != null) {
			contactService.all();
		}
		model.addAttribute("model", new ContactModel(
			contactService.getName() + " " + something.name(),
			q == null ? contactService.all() : contactService.search(q)
			)
		);
		return "contact";
	}
}
