layout 'problemList.tpl', title: '문제', loginUser: loginUser, page:ranking, user_id : user_id,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
            link(rel: 'stylesheet', type: 'text/css', href: '/css/recentProblems.css')
        },
        tables: contents {
            div(id: 'problem-container') {
                table(class: 'table table-striped') {
                    thead() {
                        tr {
                            th(class: 'center', id: 'prob-ranking', '순위')
                            th(class: 'center', id: 'prob-id', '문제번호')
                            th(class: 'center', id: 'prob-name', '제목')
                            th(class: 'center', id: 'prob-success-count', '정답 수')
                            th(class: 'center', id: 'prob-success-rate', '정답 비율')
                            th(class: 'center', id: 'prob-user-result', 'O/X')
                        }
                    }
                    tbody() {
                        if (messages.empty) {
                            tr { td(colspan: '100%', 'No Problem') }
                        } else {
                            messages.each { message ->
                                tr {
                                    td(class: 'center', message.rank)
                                    td(class: 'center', message.id)
                                    td(class: 'center', message.name)
                                    td(class: 'center', message.count)
                                    td(class: 'center', message.rate)
                                    if (message.result == true) {
                                        td(class: 'center', 'O')
                                    } else {
                                        td(class: 'center', 'X')
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
                                a(href: '?page='+(page-1), page)
                            }

                        }
                    }
                }
            }
        }
