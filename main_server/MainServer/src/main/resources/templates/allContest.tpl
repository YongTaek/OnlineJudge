layout 'contestList.tpl', title: '문제', loginUser: loginUser, page:list,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
            link(rel: 'stylesheet', type: 'text/css', href: '/css/allContest.css')
        },
        tables: contents {
            div(id: 'problem-container') {
                table(class: 'table table-striped') {
                    thead() {
                        tr {
                            th(class: 'center', '번호')
                            th(class: 'center', '대회')
                            th(class: 'center', '우승')
                            th(class: 'center', '준우승')
                            th(class: 'center', '참여')
                            th(class: 'center', '진행 중')
                            th(class: 'center', '개최자')
                        }
                    }
                    tbody() {
                        if (messages.empty) {
                            tr { td(colspan: '100%', 'No Contents') }
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
