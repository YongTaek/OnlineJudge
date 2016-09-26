layout 'problemList.tpl', title: '문제', loginUser: loginUser, page:list, user_id : user_id,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
            link(rel: 'stylesheet', type: 'text/css', href: '/css/allProblems.css')
        },
        tables: contents {
            div(id: 'problem-container') {
                table(class: 'table table-striped') {
                    thead() {
                        tr {
                            th(class: 'center', '문제번호')
                            th(class: 'center', '제목')
                            th(class: 'center', '정답 수')
                            th(class: 'center', '정답 비율')
                            th(class: 'center', 'O/X')
                        }
                    }
                    tbody() {
                        if (messages.empty) {
                            tr { td(colspan: '100%', 'No Problem') }
                        } else {
                            messages.each { message ->
                                tr(item_id: message.id) {
                                    td(class: 'center', message.id)
                                    td(class: 'center') {
                                        a(href: "/problem/${message.id}", message.name)
                                    }
                                    td(class: 'center', message.count)
                                    td(class: 'center', message.rate)
                                    if (message.result != null) {
                                        if (message.result == true) {
                                            td(class: 'center', 'O')
                                        } else {
                                            td(class: 'center', 'X')
                                        }
                                    } else {
                                        td(class: 'center')
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
