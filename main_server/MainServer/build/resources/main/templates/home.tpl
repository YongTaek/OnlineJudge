html {
    head {
        link(rel: 'stylesheet', type: 'text/css', href: '/css/home.css')
        link(rel: 'stylesheet', type: 'text/css', href: '/css/nav.css')
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css")
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css")
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css")
        link(rel: "stylesheet", href: "https://fonts.googleapis.com/css?family=Raleway:100")
        script(src: "https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js") {}
        script(src: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js") {}
        script(src: "/js/nav.js") {}
    }
    body {
        div(id: 'background') {}
        div(id: 'background-overlap') {}
        ul(id: 'nav') {
            li() {
                a(href: "/problem/list", "문제 풀러 가기")
            }
            li() {
                a(href: "/board/notice", "게시판")
            }
            li() {
                a(href: "/ranking/user", "랭킹")
            }
            li() {
                a(href: "/problem/list", "대회")
            }
            if (loginUser != null) {
                li() {
                    a(href: "#", "data-toggle": "popover", "data-placement": "bottom", "data-trigger":"focus", "data-container": "body", "$loginUser")
                }
                ul(class: "hidden", id: "profile-data") {
                    li() {
                        a(class: "profile-link", href: "/myPage", "My Page")
                    }
                    li() {
                        form(id: 'form-id', action: '/logout', method: 'post') {
                            a(id: 'logout', class: "profile-link", href: '#', onclick: "document.getElementById('form-id').submit()", "Logout")
                        }
                    }
                }
            } else {
                li() {
                    a(href: "/login", "로그인")
                }
            }
        }
        div(id: 'content') {
            p(class: 'title', "Online Judge")
            p(class: 'sub-title', "of Hanyang University")
        }
    }
}