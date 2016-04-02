html {
    head {
        title title
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css")
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css")
        link(rel: 'stylesheet', type: 'text/css', href: '/css/layout.css')
        script(src: "https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js") {}
    }
    body {
        div(id: 'background') {}
        div(id: 'background-overlap') {}
        nav(id: 'nav') {
            p("Hanyang Online Judge")
            ul() {
                li() {
                    a(href: "/problem/list", "대회")
anges
                }
                li() {
                    a(href: "/problem/list", "랭킹")
                }
                li() {
                    a(href: "/problem/list", "게시판")
                }
                li() {
                    a(href: "/problem/list", "문제 풀러 가기")
                }
            }
        }
        div(id: 'main-container', class: 'container') {
            content()
        }
    }
}