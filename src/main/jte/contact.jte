@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.Contact
@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.ContactVM

@param String initialQ
@param ContactVM model

@template.layout.page(
content = @`
    <form action="/contacts" method="get" class="tool-bar">
        <label for="search">Search Term</label>
        <input id="search" type="search" name="q" value="${ initialQ }"/>
        <input type="submit" value="Search"/>
    </form>

    <br>

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
                <a href="/contacts/${ contact.id() }/edit">Edit</a>
                <a href="/contacts/${ contact.id() }">View</a>
            </td>
        </tr>
        @endfor
        </tbody>

    </table>

    <p>
        <a href="/contacts/new">Add Contact</a>
    </p>

`
)
