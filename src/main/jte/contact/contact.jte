@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.Contact
@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.ContactVM
@import java.math.BigInteger

@param String initialQ
@param ContactVM model
@param BigInteger insertId

@template.layout.page(
content = @`

    @if(insertId != null)
        <h2>new contact with id ${insertId} added</h2>
    @endif


    <form action="/contact" method="get" class="tool-bar">
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
        <a href="/contact/new">Add Contact</a>
    </p>

`
)
