html {
    head {
        title title
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css", integrity: "sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7", crossorigin: "anonymous")
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css", integrity: "sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r", crossorigin: "anonymous")
<<<<<<< HEAD
        link(rel: 'stylesheet', type: 'text/css', href: '/css/layout.css')
    }
    body {
        script(type: "text/javascript", src: "../js/script.js") {}
        include template: 'loginForm.tpl'
        include template: 'homeNav.tpl'
        div(class: 'right') {
            div(class: 'container') {
                div(class: 'logins') {
                    if (loginUser == null) {
                        a(class: 'btn btn-primary lo', 'login-in', onclick: "viewsection()")
                    } else {
                        div(class: 'btn lo') {
                            a(href: '/myPage', loginUser.name)
                            span('님 어서오세요')
=======
        script(src: "https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js") {}
    }
    body {
        script(type: "text/javascript", src: "../js/script.js") {}
        section(id: "login_widget") {
            div(id: 'login_background') {}
            div(id: "loginbox") {
                a(id: "x", href: '#', onclick: "hidesection()") {
                    yield "X"
                }
                p(class: "LOGIN") { yield "LOGIN" }
                form(class: "form-horizontal") {
                    div(class: "form-group") {
                        label(for: "inputEmail3", class: "col-sm-2 control-label") {
                            yield "ID"
                        }
                        div(class: "col-sm-10") {
                            input(type: "id", class: "form-control", id: "inputEmail3", placeholder: "ID")
                        }
                    }
                    div(class: "form-group") {
                        label(for: "inputPassword3", class: "col-sm-2 control-label") {
                            yield "Password"
                        }
                        div(class: "col-sm-10") {
                            input(type: "password", class: "form-control", id: "inputPassword3", placeholder: "Password")
                        }
                    }
                    div(class: "form-group") {
                        div(class: "col-sm-offset-2 col-sm-10") {
                            div(class: "checkbox") {
                                label {
                                    input(type: "checkbox") {
                                        yield "Remember me"
                                    }
                                }
                            }
                        }
                    }
                    div(class:"form-group") {
                        div(class: "col-sm-offset-2 col-sm-10") {
                            button(type: "submit", class: "btn btn-default",id:"login_button") {
                                yield "LOGIN"
                            }
>>>>>>> f29efbd... [추가] 마이페이지 초안 완성
                        }
                    }
                }
                div(class: 'contents') {
                    content()
                }
            }
        }
    }
}