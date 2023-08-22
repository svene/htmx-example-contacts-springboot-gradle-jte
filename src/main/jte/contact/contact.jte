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
        <input id="input-search" type="search" name="query_prefix" value="${ initialQ }"
               hx-get="/contact"
               hx-target="tbody"
               hx-trigger="my-event, keyup delay:200ms changed"
               hx-push-url="true"
               hx-indicator="#spinner"
        >
        <img style="height: 20px" id="spinner" class="htmx-indicator" src="/spinning-circles.svg"/>
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
        @template.contact.contact_rows(model = model)
        </tbody>

    </table>

    <p><a href="/contact/new">Add Contact</a></p>

`
)
