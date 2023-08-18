@import org.svenehrke.htmxexamplecontactsspringbootgradlejte.DemoModel

@param DemoModel model

@template.layout.page(
content = @`
    Hello ${model.name()}!
`
)
