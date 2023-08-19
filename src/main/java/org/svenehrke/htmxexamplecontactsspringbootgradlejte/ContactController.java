package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class ContactController {

	private final ContactService contactService;

	@GetMapping("/contacts")
	public String contacts(@RequestParam(required = false) String q, Model model) {
		log.info("query param q: {}", q);
		var initialQ = q == null ? "" : q;
		List<Contact> models = q == null ? contactService.all() : contactService.search(q);
		model.addAttribute("initialQ", initialQ);
		model.addAttribute("model", new ContactVM(models));
		return "contact/contact";
	}

	@GetMapping("/contacts/new")
	public String contacts_new(@RequestParam(required = false) String q, Model model) {
//		log.info("query param q: {}", q);
//		var initialQ = q == null ? "" : q;
//		List<Contact> models = q == null ? contactService.all() : contactService.search(q);
//		model.addAttribute("initialQ", initialQ);
//		model.addAttribute("model", new ContactVM(models));
		return "contact/new_contact";
	}

}
