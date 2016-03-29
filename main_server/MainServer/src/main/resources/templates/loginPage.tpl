layout 'layout.tpl',title:'로그인',
        content: contents {
            div(id: "login_widget") {
                div() {
                    a(id: "x", href: '#', onclick: "hidesection()") {
                        yield "X"
                    }
                    p(class: "LOGIN") { yield "LOGIN" }
                    form(class: "form-horizontal", action: '/login', method: 'post') {
                        div(class: "form-group") {
                            label(for: "inputEmail3", class: "col-sm-2 control-label") {
                                yield "ID"
                            }
                            div(class: "col-sm-10") {
                                input(type: "id", class: "form-control", id: "inputEmail3", placeholder: "ID", name: 'login_id')
                            }
                        }
                        div(class: "form-group") {
                            label(for: "inputPassword3", class: "col-sm-2 control-label") {
                                yield "Password"
                            }
                            div(class: "col-sm-10") {
                                input(type: "password", class: "form-control", id: "inputPassword3", placeholder: "Password", name: 'login_pw')
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
                        div(class: "form-group") {
                            div(class: "col-sm-offset-2 col-sm-10") {
                                button(type: "submit", class: "btn btn-default", id: "login_button") {
                                    yield "LOGIN"
                                }
                            }
                        }
                    }
                    div(class: "find_id") {
                        a(href: "#") {
                            yield "ID/PW 찾기 "
                        }
                        p(id: "bar") { yield "|" }
                        a(href: "/join") {
                            yield " 회원가입"
                        }
                    }
                }
            }
        }