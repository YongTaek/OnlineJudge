html {
    head {
        title(title)
        link(rel: 'stylesheet', href: '/css/bootstrap.min.css')
        script(src: "https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js") {}
        csrf( disabled:'true')
    }
    body {
        div(class: 'container') {

            if(loginUser == null){
                include template: 'loginForm.tpl'
            }else{
                div(class:'col-md-12'){
                    a(href:'/myPage', loginUser.name)
                    span('님 어서오세요')
                }
            }
            div {
                content()
            }
        }
    }
}
