layout 'test.tpl', title: '게시판',
        content: contents {
            link(rel: 'stylesheet', href: '/css/table.css')
            div(class: 'container') {
                div(class: 'row') {
                    div(class: 'col-md-8 col-xs-12 col-sm-6') {
                        ul(class: 'nav nav-pills') {
                            li(class: 'active') {
                                a(href: '/notice', '공지사항')
                            }
                            li() {
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
                            a(href: '/notice/write', '공지 작성')
                        }
                    }
                }

                div(class: 'prob-container') {
                    table(class: 'table table-striped') {
                        thead {
                            tr {
                                td (class : 'common-table','번호')
                                td (class : 'common-table','제목')
                                td (class : 'common-table','작성자')
                                td (class : 'common-table','날짜')
                            }
                        }
                        tbody {
                            if (messages.empty) {
                                tr { td(colspan: '4', 'No Problem') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td (class : 'common-table',message.number)
                                        td (class : 'common-table'){
                                            a(href: '/notice/' + message.number, message.title)
                                        }
                                        td (class : 'common-table', message.user)
                                        td (class : 'common-table', message.date)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }