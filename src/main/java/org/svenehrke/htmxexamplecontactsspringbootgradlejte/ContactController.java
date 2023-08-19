package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@Slf4j
public class ContactController {

	private final ContactService contactService;

	@GetMapping("/contact")
	public String contact(HttpServletRequest request, @RequestParam(required = false) String q, Model model) {

		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			Object id = inputFlashMap.get("id");
			model.addAttribute("insertId", (BigInteger)id);
			System.out.println(id.toString());
		}

		log.info("query param q: {}", q);
		var initialQ = q == null ? "" : q;
		List<Contact> models = q == null ? contactService.all() : contactService.search(q);
		model.addAttribute("initialQ", initialQ);
		model.addAttribute("model", new ContactVM(models));
		return "contact/contact";
	}

	@GetMapping("/contact/new")
	public String contact_new(Model model) {
		Contact contact = new Contact(BigInteger.ZERO, "", "", "", "");
		model.addAttribute("contact", contact);
		return "contact/new_contact";
	}

	@RequestMapping(
		value = "/contact/new",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	public RedirectView contact_new_post(
		@RequestBody MultiValueMap<String, String> formData,
		RedirectAttributes redirectAttributes
	) {
		Contact result = contactService.insertContact(formDataToModel(formData));
		System.out.println("result.id: " + result.id());
		redirectAttributes.addFlashAttribute("id", result.id());
		return new RedirectView("/contact");
	}

	private static Contact formDataToModel(MultiValueMap<String, String> formData) {
		return new Contact(
			null,
			formData.getFirst("first_name"),
			formData.getFirst("last_name"),
			formData.getFirst("phone"),
			formData.getFirst("email")
		);
	}

}
