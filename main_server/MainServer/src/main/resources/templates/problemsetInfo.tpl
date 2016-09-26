layout 'layout.tpl', title: '문제집', loginUser: loginUser, user_id : user_id,
        custom_head:contents{
            custom_head()
            link(rel: 'stylesheet', href: '/css/problemsetInfo.css')
        },
        content: contents {
            div(class: 'container') {
                div(class: row) {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(class: 'active', id: 'problem') {
                                a(href: '/problemset/'+problemset.problemset_id, '문제집')
                            }
                        }
                    }
                }
                div(class: 'row') {
                        h1 (class: 'inrow', problemset.problemset_name)
                        span (problemset.problemset_author)
                }
                div(id: 'problem-container') {
                    table(class: 'table table-striped') {
                        thead(id: 'test-id') {
                            tr {
                                th(class: 'center', id: 'prob-id', '문제번호')
                                th(class: 'center', id: 'prob-author', '제목')
                                th(class: 'center', id: 'prob-answercount', '정답수')
                                th(class: 'center', id: 'prob-rate', '정답 비율')
                                th(class: 'center', id: 'prob-etc', 'O/X')
                            }
                        }
                        tbody(id: 'prob-tbody') {
                            if (messages.empty) {
                                tr { td(colspan: '5', 'No Problem') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td(class: 'center', message.problem_id)
                                        td(class: 'center'){
                                            a(href: '/problem/'+message.problem_id, message.problem_name)
                                        }
                                        td(class: 'center', message.problem_clear)
                                        td(class: 'center', message.problem_rate)
                                        td(class: 'center', message.problem_I)
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }