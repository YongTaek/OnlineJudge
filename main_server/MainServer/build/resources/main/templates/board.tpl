layout 'layout.tpl', title: '게시판', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/problemList.css')
        },
        content: contents {
            div(class: 'container') {
                form(id: 'search-form', action:'.',class:'form-inline') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(class: 'active') {
                                a(href: 'notice', '공지사항')
                            }
                            li() {
                                a(href: 'question', 'Q&A')
                            }
                        }
                        div(class: 'head-item') {

                            input(type = "text", class: 'form-control', id: 'exampleInputAmount', name: 'search')
                        }
                        div (class: 'head-item') {
                            button(type: "submit", id: 'search-button', class: "btn btn-primary push-left", '검색')
                        }
                        div(class: 'head-item') {
                            a(class: 'btn btn-primary push-left', href: '/notice/write', '글쓰기')
                        }
                        di
                    }
                }

                div(id: 'problem-container') {
                    table(class: 'table table-striped') {
                        thead {
                            tr {
                                td(class: 'common-table', '번호')
                                td(class: 'common-table', '제목')
                                td(class: 'common-table', '작성자')
                                td(class: 'common-table', '날짜')
                            }
                        }
                        tbody {
                            if (messages.empty) {
                                tr { td(colspan: '4', 'No Problem') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td(class: 'common-table', message.number)
                                        td(class: 'common-table') {
                                            a(href: '/notice/'+message.number, message.title)
                                        }
                                        td(class: 'common-table', message.user)
                                        td(class: 'common-tbale', message.date)
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }