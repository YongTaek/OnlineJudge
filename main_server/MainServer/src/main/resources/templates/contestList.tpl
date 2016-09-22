layout 'layout.tpl', title: '대회', loginUser: loginUser,
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
                            li(id: 'recent') {
                                a(href: '#', '진행 중 대회')
                            }
                            li(id: 'ranking') {
                                a(href: '#', '마감한 대회')
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