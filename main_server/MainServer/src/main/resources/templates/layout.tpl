html {
    head {
        title("이것저것")
        link(rel: 'stylesheet', type: 'text/css', href: '/css/layout.css')
        link(rel: 'stylesheet', type: 'text/css', href: '/css/home.css')
        link(rel: 'stylesheet', type: 'text/css', href: '/css/loginwidget.css')
        link(rel: 'stylesheet', type: 'text/css', href: '/css/join.css')
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css", integrity: "sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7", crossorigin: "anonymous")
        link(rel: "stylesheet", href ="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css", integrity: "sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r", crossorigin: "anonymous")
    }
    body {
        script (src:"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"){}
        script (src:"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js", integrity:"sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" ,crossorigin:"anonymous"){}
        script(type: "text/javascript", src: "../js/layout.js") {}
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
                        }
                    }
                }
                div(class:"find_id") {
                    a(href: "#") {
                        yield "ID/PW 찾기"
                    }
                    p (id:"bar"){ yield " | " }
                    a(href: "#") {
                        yield "회원가입"
                    }
                }
            }
        }
        nav(class: 'navbar') {
            ul(class: 'menu') {
                li(class: 'llist') {
                    a(class:"problem",href: '#',title:"Problems") {
                        yield img(class: "icon", src: '/ img / tool.png ')
                    }
                }
                li(class: 'llist') {
                    a(class:"board",href: '#',title:"Board") {
                        yield img(class: "icon", src: '/img/editing.png')
                    }
                }
                li(class: 'list') {
                    a(class:"ranking",href: '#',title:"Ranking") {
                        yield img(class: "icon", src: '/img/three.png')
                    }
                }
                li(class: 'list') {
                    a(class:"contest",href: '#',title:"Contest") {
                        yield img(class: "icon", src: '/img/shapes.png')
                    }
                }
            }
        }
        div(class: 'right') {
            ul(class:"loginbar") {
                li(class:'lo') {
                    a(class: 'loandjo', 'Join-in',onclick:"hidesection()")
                }
                li(class:'lo') {
                    a(class: 'loandjo', 'Login',onclick: "viewsection()")
                }
            }
            content()
        }
    }
}