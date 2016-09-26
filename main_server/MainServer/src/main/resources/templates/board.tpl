layout 'layout.tpl', title: '게시판', loginUser: loginUser, user_id : user_id,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/questionList.css')
        },
        content: contents {
            div(class: 'container') {
                form(id: 'search-form', action:'.',class:'form-inline') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(class: 'active') {
                                a(href: '/board/notice', '공지사항')
                            }
                            li() {
                                a(href: '/board/question', 'Q&A')
                            }
                        }
                        div(class: 'head-item') {
                            input(type = "text", class: 'form-control', id: 'exampleInputAmount', name: 'search')
                        }
                        div (class: 'head-item') {
                            button(type: "submit", id: 'search-button', class: "btn btn-primary push-left", '검색')
                        }
                        div(class: 'head-item') {
                            a(class: 'btn btn-primary push-left', href: '/board/notice/write', '글쓰기')
                        }
                    }
                }

                div(id: 'problem-container') {
                    table(class: 'table table-striped') {
                        thead {
                            tr {
                                th(class: 'center', '번호')
                                th(class: 'center', '제목')
                                th(class: 'center', '작성자')
                                th(class: 'center', '날짜')
                            }
                        }
                        tbody {
                            if (messages.empty) {
                                tr { td(colspan: '4', 'No Problem') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td(class: 'center', message.number)
                                        td(class: 'center') {
                                            a(href: '/board/notice/'+message.number, message.title)
                                        }
                                        td(class: 'center', message.user)
                                        td(class: 'center', message.date)
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }