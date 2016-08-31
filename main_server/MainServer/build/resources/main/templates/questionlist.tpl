layout 'layout.tpl', title: '질문게시판',loginUser:loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/questionList.css')
        },
        content: contents {
                div(class: 'container') {
                    form(id: 'search-form', action: '.', class: 'form-inline') {
                        div(class: 'head-container') {
                            ul(class: 'nav nav-pills head-item main') {
                                li(id: 'notice') {
                                    a(href: '/board/notice', '공지사항')
                                }
                                li(class: 'active', id: 'question') {
                                    a(href: '/board/question', 'Q&A')
                                }

                            }
                            div(class: 'head-item') {
                                input(type: "text", class: 'form-control', name: 'search')
                            }
                            div(class: 'head-item') {
                                button(type: "submit", id: 'search-button', class: "btn btn-primary push-left", '검색')
                            }
                            div(class: 'head-item') {
                                button(class: "btn btn-primary push-left") {
                                    a(href: '/question/write', '글쓰기')
                                }
                            }
                        }
                    }

                    div(id: 'problem-container') {
                        table(class: 'table table-striped') {
                            thead() {
                                tr {
                                    th(class: 'center', '번호')
                                    th(class: 'center', '제목')
                                    th(class: 'center', '문제')
                                    th(class: 'center', '작성자')
                                    th(class: 'center', '날짜')
                                }
                            }
                            tbody() {
                                if (messages.empty) {
                                    tr { td(colspan: '100%', 'No Problem') }
                                } else {
                                    messages.each { message ->
                                        tr(item_id: message.id) {
                                            td(class: 'center', message.number)
                                            td(class: 'center') {
                                                a(href: '/question/' + message.number, message.title)
                                            }
                                            td(class: 'center', message.question)
                                            td(class: 'center', message.user)
                                            td(class: 'center', message.date)
                                        }
                                    }
                                }
                            }

                        }
                    }
                    div(class: 'prob-nav') {
                        ul(class: 'pagination') {
//                        if (pages.empty) {
//
//                        } else {
//                            pages.each { page ->
//                                li {
//                                    a(href: '?page=' + (page - 1), page)
//                                }
//
//                            }
//                        }
                        }
                    }
                }
        }