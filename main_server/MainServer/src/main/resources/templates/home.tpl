html {
    head {
        link(rel: 'stylesheet', type: 'text/css', href: '/css/home.css')
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css")
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css")
        link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css")
        link(rel: "stylesheet", href: "https://fonts.googleapis.com/css?family=Raleway:100")
        script(src: "https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js") {}
    }
    body {
        div(id: 'background') {}
        div(id: 'background-overlap') {}
        ul(id: 'nav') {
            li() {
                a(href: "/login", "로그인")
            }
            li() {
                a(href: "/problem/list", "문제 풀러 가기")
            }
            li() {
                a(href: "/problem/list", "게시판")
            }
            li() {
                a(href: "/problem/list", "랭킹")
            }
            li() {
                a(href: "/problem/list", "대회")
            }
        }
        div(id: 'content') {
            p(class: 'title', "Online Judge")
            p(class: 'sub-title', "of Hanyang University")
        }
    }
}