@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.ContactModel

@param ContactModel model

@template.layout.page(
content = @`
    <h3>Hello ${model.name()}!</h3>

    <div>Contacts:</div>
    <ul>
    @for(String entry : model.contacts())
        <li>${entry}</li>
    @endfor
    </ul>

`
)
