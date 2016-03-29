layout 'layout.tpl', title: '질문게시판',loginUser:loginUser,
        content: contents {
            link(rel: 'stylesheet', href: '/css/table.css')
            div() {
                div(class: 'row') {
                    div(class: 'col-md-8 col-xs-12 col-sm-6') {
                        ul(class: 'nav nav-pills') {
                            li() {
                                a(href: '/notice', '공지사항')
                            }
                            li(class: 'active') {
                                a(href: '/question', 'Q&A')
                            }
                        }

                    }
                    div(class: 'col-md-4') {
                        form(class: "form-inline", id: 'searchForm') {
                            div(class: "form-group") {
                                div(class: 'input-group') {
                                    input(type = "text", class: 'form-control', id: 'exampleInputAmount', name: 'search')
                                }
                            }
                            button(type: "submit", id: 'searchButton', class: "btn btn-primary", '검색')
                            a(href: '/question/write', '글쓰기')
                        }
                    }
                }

                div(class: 'prob-container') {
                    table(class: 'table table-striped') {
                        thead {
                            tr {
                                td (class : 'common-table', '번호')
                                td (class : 'common-table', '제목')
                                td (class : 'common-table', '문제')
                                td (class : 'common-table', '작성자')
                                td (class : 'common-table', '날짜')
                            }
                        }
                        tbody {
                            if (messages.empty) {
                                tr { td(colspan: '6', 'No Question') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td (class : 'common-table', message.number)
                                        td (class : 'common-table'){
                                            a(href: '/question/'+ message.number, message.title)
                                        }
                                        td (class : 'common-table', message.question)
                                        td (class : 'common-table', message.user)
                                        td (class : 'common-table',message.date)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }