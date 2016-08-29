layout 'layout.tpl', title: '문제', loginUser: loginUser,
        custom_head: contents {
            custom_head()
        },
        content: contents {
            div(class: 'container') {
                form(class: "form-inline", id: 'searchForm') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(id: 'list'){
                                a(href: '/group/list','Group 목록')
                            }
                            li(class: 'active', id: 'myGroup') {
                                a(href: '/group/info', '내 Group')
                            }
                        }
                        div(class: "head-item") {
                            input(type = "text", class: 'form-control', name: 'search')
                        }
                        div(class: "head-item") {
                            button(type: "submit", id: 'searchButton', class: "btn btn-primary", '검색')
                        }
                        if(!userGroup) {
                            div(class: "head-item") {
                                a(class: 'btn btn-primary push-left', href: '/group/create', '그룹 만들기')
                            }
                        }
                    }
                }
                tables()
            }
            script(src: '/js/group.js'){}
        }