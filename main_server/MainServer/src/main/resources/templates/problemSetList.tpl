layout 'layout.tpl', title: '문제집',loginUser:loginUser, user_id : user_id,
        custom_head:contents{
            custom_head()
            link(rel: 'stylesheet', href: '/css/problemset.css')

        },
        content: contents {
            div(class: 'container') {
                div(class: row) {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
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
                        div (class: 'head-item') {
                            a(class: 'btn btn-primary push-left', href: '/problemset/create', '문제집 만들기')
                        }

                    }
                }

                div(id: 'problem-container') {
                    table(class: 'table table-striped') {
                        thead(id: 'test-id') {
                            tr {
                                th(class: 'center', id: 'prob-id', '번호')
                                th(class: 'center', id: 'prob-author', '만든 사람')
                                th(class: 'center', id: 'prob-name', '이름')
                                th(class: 'center', id: 'prob-rate', '성취도')
                                th(class: 'center', id: 'prob-etc', '푼 사람수')
                            }
                        }
                        tbody(id: 'prob-tbody') {
                            if (messages.empty) {
                                tr { td(colspan: '5', 'No ProblemSet') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td(class: 'center', message.id)
                                        td(class: 'center', message.author)
                                        td(class: 'center'){
                                            a(href: '/problemset/'+message.id, message.name)
                                        }
                                        td(class: 'center', message.rate)
                                        td(class: 'center', ' ')
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }