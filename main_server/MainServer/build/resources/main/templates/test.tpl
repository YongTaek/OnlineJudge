html {
    head {
        title(title)
        link(rel: 'stylesheet', href: '/css/bootstrap.min.css')
        script(src: "https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js") {}
        csrf( disabled:'true')
    }
    body {
        div(class: 'container') {

            if(!session){
                div(id: 'login', class: 'login-form') {
                    form(class: "form-inline", id: 'loginForm',action:'/login', method:'post') {
                        div(class: "form-group") {
                            span 'ID'
                            div(class: 'input-group') {
                                input(type: "text", class: 'form-control', id: 'login_id', name: 'login_id')
                            }
                            span 'PW'
                            div(class: 'input-group') {
                                input(type: "password", class: 'form-control', id: 'login_pw', name: 'login_pw')
                            }
                        }
                        button(type: "submit", id: 'loginButton', class: "btn btn-primary", 'login')
                    }
                }
            }else{
                div{
                    p 'asdf'
                }
            }
            div { content() }
        }
    }
}