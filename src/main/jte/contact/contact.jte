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

    <form x-data="{ selected: [] }">
        <template x-if="selected.length > 0">
            <div class="box info tool-bar">
                <slot x-text="selected.length"></slot>
                contacts selected

                <button type="button" class="bad bg color border">Delete</button>
                <hr aria-orientation="vertical">
                <button type="button" @click="selected = []">Cancel</button>
            </div>
        </template>

        <table>
            <thead>
            <tr>
                <th>Select</th> <th>First</th> <th>Last</th> <th>Phone</th> <th>Email</th> <th></th>
            </tr>
            </thead>
            <tbody>
            @template.contact.contact_rows(model = model)
            </tbody>

        </table>
        <button hx-delete="/contact"
                hx-confirm="Are you sure you want to delete these contacts?"
                hx-target="body">
            Delete Selected Contacts
        </button>
    </form>

    <p>
        <a href="/contact/new">Add Contact</a>
        <span hx-get="/contact/count" hx-trigger="revealed">
            <img style="height: 20px" id="spinner" class="htmx-indicator" src="/spinning-circles.svg"/>
        </span>

    </p>

`
)
