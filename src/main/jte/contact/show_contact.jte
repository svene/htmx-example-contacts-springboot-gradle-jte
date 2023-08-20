@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.Contact
@import java.math.BigInteger

@param Contact contact

@template.layout.page(
content = @`
    <h1>${contact.firstName()} ${contact.lastName()}</h1>

    <div>
        <div>Phone: ${contact.phone()}</div>
        <div>Email: ${contact.email()}</div>
    </div>

    <p>
        <a href="/contact/${contact.id()}/edit">Edit</a>
        <a href="/contact">Back</a>
    </p>


`
)
