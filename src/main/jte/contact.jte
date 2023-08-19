@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.ContactModel

@param String initialQ
@param ContactModel model

@template.layout.page(
content = @`
    <form action="/contacts" method="get" class="tool-bar">  (3)
        <label for="search">Search Term</label>
        <input id="search" type="search" name="q" value="${ initialQ }"/> (4)
        <input type="submit" value="Search"/>
    </form>

    <div>Contacts:</div>
    <ul>
    @for(String entry : model.contacts())
        <li>${entry}</li>
    @endfor
    </ul>

`
)
