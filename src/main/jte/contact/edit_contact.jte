@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.Contact

@param Contact contact

@template.layout.page(
content = @`
    <h1>new contact</h1>

    <form action="/contact/${ contact.id() }/edit" method="post">
        <fieldset>
            <legend>Contact Values</legend>
            <p>
                <label for="email">Email</label>
                <input name="email" id="email" type="text" placeholder="Email" value="${ contact.email() }">
<%--                <span class="error">${ contact.errors['email'] }</span>--%>
            </p>
            <p>
                <label for="first_name">First Name</label>
                <input name="first_name" id="first_name" type="text" placeholder="First Name"
                       value="${ contact.firstName() }">
<%--                <span class="error">${ contact.errors['first'] }</span>--%>
            </p>
            <p>
                <label for="last_name">Last Name</label>
                <input name="last_name" id="last_name" type="text" placeholder="Last Name"
                       value="${ contact.lastName() }">
<%--                <span class="error">${ contact.errors['last'] }</span>--%>
            </p>
            <p>
                <label for="phone">Phone</label>
                <input name="phone" id="phone" type="text" placeholder="Phone" value="${ contact.phone() }">
<%--                <span class="error">${ contact.errors['phone'] }</span>--%>
            </p>
            <button>Save</button>
        </fieldset>
    </form>

<button
    id="delete-button"
    hx-delete="/contact/${contact.id() }"
    hx-target="body"
    hx-push-url="true"
    hx-confirm="Are you sure you want to delete this contact?"
>Delete Contact</button>

    <p>
        <a href="/contact">Back</a>
    </p>

`
)
