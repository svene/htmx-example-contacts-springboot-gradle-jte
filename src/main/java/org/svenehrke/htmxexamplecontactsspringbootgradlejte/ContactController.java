package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
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
	public String contact(HttpServletRequest request, @RequestParam(required = false) String query_prefix, Model model) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			Object id = inputFlashMap.get("id");
			model.addAttribute("insertId", id);
		}
		// Input handling:
		var initialQ = query_prefix == null ? "" : query_prefix;

		// Service calls:
		List<Contact> rows = query_prefix == null ? contactService.all() : contactService.search(query_prefix);

		// View model population:
		model.addAttribute("initialQ", initialQ);
		model.addAttribute("model", new ContactVM(rows));

		// view name: // TODO: factor into to methods using 'headers' in the @GetMapping():
		boolean justRows = "input-search".equalsIgnoreCase(request.getHeader("HX-Trigger"));
		return justRows ? "contact/contact_rows" : "contact/contact";

	}

	@GetMapping(value = "/contact/count", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String get_contact_count() throws InterruptedException {
		int count = contactService.count();
		Thread.sleep(3000);
		return "(" + count + " total Contacts)";
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

	@GetMapping("/contact/{id}")
	public String contact(@PathVariable BigInteger id, Model model) {
		model.addAttribute("contact", contactService.byId(id));
		return "contact/show_contact";
	}

	@GetMapping("/contact/{id}/edit")
	public String editContact(@PathVariable BigInteger id, Model model) {
		model.addAttribute("contact", contactService.byId(id));
		return "contact/edit_contact";
	}

	@RequestMapping(
		value = "/contact/{id}/edit",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	public RedirectView editContact_post(
		@RequestBody MultiValueMap<String, String> formData,
		RedirectAttributes redirectAttributes,
		@PathVariable BigInteger id
	) {
		Contact contact = ContactBuilder.from(formDataToModel(formData)).withId(id);
		contactService.updateContact(contact);
		return new RedirectView("/contact");
	}

	@RequestMapping(
		value = "/contact/{id}/delete",
		method = RequestMethod.POST
	)
	public RedirectView deleteContact_post(@PathVariable BigInteger id) {
		contactService.deleteContact(id);
		return new RedirectView("/contact");
	}

	@DeleteMapping(value = "/contact/{id}", headers = {"HX-Trigger!=delete-button"})
	@ResponseBody
	public String delete_contact_inline(@PathVariable BigInteger id) {
		contactService.deleteContact(id);
		return "";

	}

	@DeleteMapping(value = "/contact/{id}", headers = {"HX-Trigger=delete-button"})
	public String delete_contact_redirect_to_list(HttpServletRequest request, @PathVariable BigInteger id) {
		contactService.deleteContact(id);
		request.setAttribute(
			View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.SEE_OTHER);
		return "redirect:/contact";

	}

	@DeleteMapping("/contact")
	public String delete_bulk(HttpServletRequest request, @RequestParam List<String> selected_contact_ids) {
		request.setAttribute(
			View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.SEE_OTHER);
		// TODO: add bulk-delete to service to improve performance:
		selected_contact_ids.forEach(it -> contactService.deleteContact(new BigInteger(it)));
		return "redirect:/contact";
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
