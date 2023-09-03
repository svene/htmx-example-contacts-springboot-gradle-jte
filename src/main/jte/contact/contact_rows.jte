@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.Contact
@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.ContactVM

@param ContactVM model

@for(Contact contact : model.contacts())
        <tr>
                <td>${ contact.firstName() }</td>
                <td>${ contact.lastName() }</td>
                <td>${ contact.phone() }</td>
                <td>${ contact.email() }</td>
                <td>
                        <a href="/contact/${ contact.id() }">View</a>
                        <a href="/contact/${ contact.id() }/edit">Edit</a>
                        <a href="#"
                           hx-delete="/contact/${ contact.id() }"
                           hx-swap="outerHTML swap:1s"
                           hx-confirm="Are you sure you want to delete this contact?"
                           hx-target="closest tr"
                        >Delete Contact
                        </a>
                </td>
        </tr>
@endfor

