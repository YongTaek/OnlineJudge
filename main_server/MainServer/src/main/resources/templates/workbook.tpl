layout 'layout.tpl', title: '문제집',loginUser:loginUser,
        content: contents {
            div() {
                link(rel:'stylesheet', href: '/css/problemset.css')

                div(class: row) {
                    div(class: 'col-md-8 col-xs-12 col-sm-6') {
                        ul(class: 'nav nav-pills') {
                            li(class: 'active', id: 'problem') {
                                a(href: '/problem', '전체')
                            }
                            li(id: 'recent') {
                                a(href: '/problem/recent', '푼 사람이 많은 문제')
                            }
                            li(id: 'ranking') {
                                a(href: '/problem/ranking', '그룹장이 많은 문제')
                            }

                        }
                    }
                }

                div(class: 'prob-container') {
                    table(class: 'prob-table') {
                        thead(class: 'prob-table-head', id: 'test-id') {
                            tr {
                                th(class: 'common-table', id: 'prob-id', '번호')
                                th(class: 'common-table', id: 'prob-author', '만든 사람')
                                th(class: 'common-table', id: 'prob-name', '이름')
                                th(class: 'common-table', id: 'prob-rate', '성취도')
                                th(class: 'common-table', id: 'prob-etc', ' ')
                            }
                        }
                        tbody(id: 'prob-tbody') {
                            if (messages.empty) {
                                tr { td(colspan: '5', 'No ProblemSet') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td(class: 'common-table', message.id)
                                        td(class: 'common-table', message.author)
                                        td(class: 'common-table', message.name)
                                        td(class: 'common-table', message.rate)
                                        td(class: 'common-table', ' ')
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }