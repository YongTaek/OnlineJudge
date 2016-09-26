layout 'layout.tpl', title: '문제', loginUser: loginUser, user_id : user_id,
        custom_head:contents{
            custom_head()
        },
        content: contents {
            div(class: 'container') {
                form(id: 'search-form', action: page, class: 'form-inline') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(class: 'active', id: 'list') {
                                a(href: '/problem/list', '문제')
                            }
                            li(id: 'recent') {
                                a(href: '/problem/recent', '추가된 문제')
                            }
                            li(id: 'ranking') {
                                a(href: '/problem/ranking', '문제순위')
                            }
                            li {
                                a(id: 'click-etc', href: '#', '기타', role: 'button')
                            }

                        }
                        div(class: 'head-item') {
                            input(type: "text", class: 'form-control', name: 'search')
                        }
                        div(class: 'head-item') {
                            button(type: "submit", id: 'search-button', class: "btn btn-primary push-left", '검색')
                        }
                    }
                }
                tables()
            }
            script(src: '../js/table.js') {}
        }