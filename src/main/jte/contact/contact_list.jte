@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.Contact
@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.ContactVM

@param ContactVM model

@for(Contact contact : model.contacts())
<ul>
        <li><a href="mailto:${ contact.email() }">${ contact.firstName() }</a></li>
</ul>
@endfor
