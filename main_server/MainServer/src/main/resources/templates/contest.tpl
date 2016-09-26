layout 'layout.tpl', title: '대회', loginUser: loginUser, user_id:user_id,
        custom_head:contents{
            custom_head()
        },
        content: contents {
            div(class: 'container') {
                form(id: 'search-form', action: page, class: 'form-inline') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(class: 'active', id: 'list') {
                                a(href: '#', '대회')
                            }
                            li {
                                a(href: '/contest/create', '대회 만들기')
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
                content()
            }
            script(src: '../js/table.js') {}
        }