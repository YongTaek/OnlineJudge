layout 'layout.tpl', title: '대회', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
            link(rel: 'stylesheet', href: '/css/myPage.css')
        },
        content: contents {
            div(class: 'container') {
                div(class: 'head-container') {
                    ul(class: 'nav nav-pills head-item main') {
                        li(class: 'active', id: 'list') {
                            a(href: '#', messages.num)
                        }
                        li(id: 'recent') {
                            a(href: '#', '진행 중 대회')
                        }
                        li(id: 'ranking') {
                            a(href: '#', '마감한 대회')
                        }
                        li {
                            a(id: 'click-etc', href: '#', '기타', role: 'button')
                        }
                    }
                }
                div() {
                    div(class: "row") {
                        div(class: "col-md-12") {
                            p(class: "team-name") {
                                span(style: 'font-size : 35', "ContestName")
                                a(herf = '#', admin) {}
                            }
                            p() {
                                span("date of contest")
                            }
                        }
                    }
                }
                div() {
                    div(class: 'table-responsive') {
                        table(class: 'table', id: 'problem-info') {
                            thead {
                                tr {
                                    th(class: 'center', '번호')
                                    th(class: 'center', '제목')
                                    th(class: 'center', '비고')
                                }
                            }
                            tbody {
                                tr {
                                    td(class: 'center', messages.number)
                                    td(class: 'center', messages.title)
                                    td()
                                }
                            }
                        }
                    }
                }
                div(class: 'problem-body') {
                    div(class: 'panel panel-default') {
                        div(class: 'panel panel-heading') {
                            h3(class: 'panel-title') {
                                span '대회 운영진'
                            }
                        }
                        div(class: 'panel-body') {
                            a(href = '#') {
                                admin
                            }
                            if (!admins.empty) {
                                admins.each { admins ->
                                    a(herf = '#') {
                                        admins.deputy
                                    }
                                }
                            }
                        }
                    }
                    div(class: 'panel panel-default') {
                        div(class: 'panel panel-heading') {
                            h3(class: 'panel-title') {
                                span '대회 참가자'
                            }
                        }
                        div(class: 'panel-body') {
                            if (!participant.empty) {
                                participant.each { participant ->
                                    a(herf = '#') {
                                        joins.name
                                    }
                                }
                            } else {
                                p('There is no participant')
                            }
                        }
                    }
                }
            }
        }
