@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.Contact
@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.ContactVM

@param ContactVM model

<table>
        <thead>
        <tr>
                <th>First</th> <th>Last</th> <th>Phone</th> <th>Email</th> <th></th>
        </tr>
        </thead>
        <tbody>
        @for(Contact contact : model.contacts())
                <tr>
                        <td>${ contact.firstName() }</td>
                        <td>${ contact.lastName() }</td>
                        <td>${ contact.phone() }</td>
                        <td>${ contact.email() }</td>
                        <td>
                                <a href="/contact/${ contact.id() }">View</a>
                                <a href="/contact/${ contact.id() }/edit">Edit</a>
                        </td>
                </tr>
        @endfor
        </tbody>

</table>

