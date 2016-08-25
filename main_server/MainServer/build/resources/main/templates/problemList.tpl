layout 'layout.tpl', title: '문제', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
        },
        content: contents {
            div(class: 'container') {
                form(id: 'search-form', action: '.', class: 'form-inline') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(class: 'active', id: 'problem') {
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
                        div (class: 'head-item') {
                            input(type: "text", class: 'form-control', name: 'search')
                        }
                        div (class: 'head-item') {
                            button(type: "submit", id: 'search-button', class: "btn btn-primary push-left", '검색')
                        }
                    }
                }
                div(id: 'problem-container') {
                    table(class: 'table table-striped') {
                        thead() {
                            tr {
                                th(class: 'center', '문제번호')
                                th(class: 'center', '제목')
                                th(class: 'center', '정답 수')
                                th(class: 'center', '정답 비율')
                                th(class: 'center', 'O/X')
                            }
                        }
                        tbody() {
                            if (messages.empty) {
                                tr { td(colspan: '100%', 'No Problem') }
                            } else {
                                messages.each { message ->
                                    tr(item_id: message.id) {
                                        td(class: 'center', message.id)
                                        td{
                                            a(href: "/problem/${message.id}", message.name)
                                        }
                                        td(class: 'center', message.count)
                                        td(class: 'center', message.rate)
                                        if (message.result != null) {
                                            if (message.result == true) {
                                                td(class: 'center', 'O')
                                            } else {
                                                td(class: 'center', 'X')
                                            }
                                        } else {
                                            td(class: 'center')
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
                div(class: 'prob-nav') {
                    ul(class: 'pagination') {
                        if (pages.empty) {

                        } else {
                            pages.each { page ->
                                li {
                                    a(href: '?page=' + (page - 1), page)
                                }

                            }
                        }
                    }
                }
            }
        }