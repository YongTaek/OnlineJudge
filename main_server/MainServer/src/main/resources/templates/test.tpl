html {
    head {
        title(title)
        link(rel:'stylesheet', href:'/css/bootstrap.min.css')
        link(rel:'stylesheet', href: '/css/table.css')
        script (src:"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"){}
    }
    body {
        div(class:'container') {
            div { content() }
        }
        script(type:'text/javascript',src:'js/table.js'){}

    }
}