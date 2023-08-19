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
		List<ContactService.Contact> models = q == null ? contactService.all() : contactService.search(q);
		model.addAttribute("model", new ContactModel(
			contactService.getName(),
			modelsToViewItems(models)
			)
		);
		return "contact";
	}

	private static List<String> modelsToViewItems(List<ContactService.Contact> contacts) {
		return contacts.stream()
			.map(it -> "%s %s".formatted(it.firstName(), it.lastName()))
			.toList();
	}

}
