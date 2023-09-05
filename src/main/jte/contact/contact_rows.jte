@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.Contact
@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.ContactVM

@param ContactVM model

@for(Contact contact : model.contacts())
        <tr>
                <td><input type="checkbox" name="selected_contact_ids" value="${ contact.id() }"></td>
                <td>${ contact.firstName() }</td>
                <td>${ contact.lastName() }</td>
                <td>${ contact.phone() }</td>
                <td>${ contact.email() }</td>
                <td>
                        <div data-overflow-menu>
                                <button type="button" aria-haspopup="menu"
                                        aria-controls="contact-menu-${contact.id()}"
                                >Options</button>
                                <div role="menu" hidden id="contact-menu-${contact.id()}">
                                        <a role="menuitem" href="/contact/${contact.id()}/edit">Edit</a>
                                        <a role="menuitem" href="/contact/${contact.id()}">View</a>
                                        <a href="#"
                                           hx-delete="/contact/${ contact.id() }"
                                           hx-swap="outerHTML swap:1s"
                                           hx-confirm="Are you sure you want to delete this contact?"
                                           hx-target="closest tr"
                                        >Delete Contact
                                        </a>
                                </div>
                        </div>
                </td>
        </tr>
@endfor

