layout 'test.tpl', title: '문제',
        content: contents {
            div(class: 'container') {
                div(class: 'row') {
                    div(class: 'col-md-8 col-xs-12 col-sm-6') {
                        ul(class: 'nav nav-pills') {
                            li(class: 'active') {
                                a(href: '#', '문제')
                            }
                            li {
                                a(href: '#', '추가된 문제')
                            }
                            li {
                                a(href: '#', '문제순위')
                            }
                            li {
                                a(id:'click-etc',href: '#','기타',role:'button')
                                div(id:'etc'){
                                    p 'asdf'
                                }
                            }
                        }
                    }
                    div(class: 'col-md-4') {
                        form(class: "form-inline") {
                            div(class: "form-group") {
                                div(class: 'input-group') {
                                    input(type = "text", class: 'form-control', id: 'exampleInputAmount')
                                }
                            }
                            button(type: "submit", class: "btn btn-primary", '검색')
                        }
                    }
                }
                div(class: 'prob-container') {
                    table(class: 'prob-table') {
                        thead(class: 'prob-table-head', id:'test-id') {
                            tr {
                                th(class: 'common-table', id: 'prob-id', '문제번호')
                                th(class: 'common-table', id: 'prob-name', '제목')
                                th(class: 'common-table', id: 'prob-success-count', '정답 수')
                                th(class: 'common-table', id: 'prob-success-rate', '정답 비율')
                                th(class: 'common-table', id: 'prob-user-result', 'O/X')
                            }
                        }
                        tbody(id:'prob-tbody') {
                            if (messages.empty) {
                                tr { td(colspan: '5', 'No Problem') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td(class: 'common-table', message.id)
                                        td(class: 'common-table', message.name)
                                        td(class: 'common-table', message.count)
                                        td(class: 'common-table', message.rate)
                                        td(class: 'common-table', message.result)
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