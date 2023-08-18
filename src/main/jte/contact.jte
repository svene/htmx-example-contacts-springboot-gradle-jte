@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.ContactModel

@param ContactModel model

@template.layout.page(
content = @`
    Hello ${model.name()}! ${model.contacts().size()}
`
)
