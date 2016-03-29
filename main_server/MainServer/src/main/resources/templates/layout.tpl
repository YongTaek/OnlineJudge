html {
    head {
        title title
        link(rel: 'stylesheet', type: 'text/css', href: '/css/home.css')
        link(rel: 'stylesheet', type: 'text/css', href: '/css/loginwidget.css')
        link(rel: 'stylesheet', type: 'text/css', href: '/css/join.css')
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css", integrity: "sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7", crossorigin: "anonymous")
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css", integrity: "sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r", crossorigin: "anonymous")
        link(rel: 'stylesheet', type: 'text/css', href: '/css/layout.css')
        script(src: "https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js") {}
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