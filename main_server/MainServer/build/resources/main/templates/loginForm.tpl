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