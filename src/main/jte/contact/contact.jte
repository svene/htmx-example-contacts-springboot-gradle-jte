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

    <div class="tool-bar">
        <label for="search">Search Term</label>
        <input id="search" type="search" name="search" value="${ initialQ }"/>
        <button hx-post="/contact_list" hx-target="#main" hx-include="#search">
            Search The Contacts
        </button>
    </div>

    <br>

    <div id="main"></div>

    <p><a href="/contact/new">Add Contact</a></p>

`
)
