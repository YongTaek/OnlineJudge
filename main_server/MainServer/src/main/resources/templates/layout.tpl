html {
    head {
        title title
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css")
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css")
        link(rel: 'stylesheet', type: 'text/css', href: '/css/layout.css')
        link(rel: 'stylesheet', type: 'text/css', href: '/css/nav.css')
        script(src: "https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js") {}
        script(src: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js") {}
        script(src: "/js/nav.js") {}
        custom_head()
    }
    body {
        div(id: 'background') {}
        div(id: 'background-overlap') {}
        nav(id: 'nav') {
            a(href: "/", "Hanyang Online Judge")
            ul() {
                if (loginUser != null) {
                    li() {
                        a(href: "#", "data-toggle": "popover", "data-placement": "bottom", "data-container": "body", "$loginUser")
                    }
                    ul(class: "hidden", id: "profile-data") {
                        li() {
                            a(class: "profile-link", href: "/myPage", "My Page")
                        }
                        li() {
                            a(class: "profile-link", href: "/login?logout", "Logout")
                        }
                    }


                } else {
                    li() {
                        a(href: "/login", "로그인")
                    }
                }
                li() {
                    a(href: "/problem/list", "대회")
                }
                li() {
                    a(href: "/problem/list", "랭킹")
                }
                li() {
                    a(href: "/board/notice", "게시판")
                }
                li() {
                    a(href: "/problem/list", "문제 풀러 가기")
                }



            }
        }
        div(id: 'content-area') {
            content()
        }
    }
}