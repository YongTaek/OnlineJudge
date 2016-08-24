loginUser: loginUser
html {
    head {
        link(rel: 'stylesheet', type: 'text/css', href: '/css/login.css')
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css")
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css")
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css")
        link(rel: "stylesheet", href: "https://fonts.googleapis.com/css?family=Raleway:100")
        script(src: "https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js") {}
        script(src: "/js/login.js") {}
    }
    body {
        div(id: 'background') {}
        div(id: 'background-overlap') {}
        div(id: 'content') {
            div(id: 'info-container') {
                h1(class: 'title', "Online Judge")
                p(class: 'sub-title', "at. Hanyang University")
            }
            div(id: 'login-form-container') {
                h1(id: 'form-title', class: 'title push-bottom', "Login")
                form(id:'login-form', action: '/login', method: 'post') {
                    div(class: 'form-group') {
                        label(for: 'login_id', 'ID')
                        input(type: 'text', name:'login_id', class: 'form-control')
                    }
                    div(class: 'form-group') {
                        label(for: 'login_pw', 'PW')
                        input(type: 'password', name:'login_pw', class: 'form-control')
                    }
                    div(class: 'form-group hide join-form') {
                        label(for: 'name', 'Name')
                        input(type: 'text', name:'name', class: 'form-control')
                    }
                    div(class: 'form-group hide join-form') {
                        label(for: 'email', 'Email')
                        input(type: 'email', name:'email', class: 'form-control')
                    }
                    button(type: 'submit', class: 'btn', "Login")
                    a(id: 'join-button', href: '#', "처음 오셨나요?")
                }
            }
        }
    }
}