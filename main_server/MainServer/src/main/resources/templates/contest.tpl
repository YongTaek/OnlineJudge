layout 'layout.tpl', title: '대회', loginUser: loginUser,
        custom_head: contents {
//            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
            link(rel: 'stylesheet', href: '/css/myPage.css')
            link(rel: 'stylesheet', href: '/css/contest.css')
            link(rel: 'stylesheet', href: '/css/problemset.css')
        },
        content: contents {
            div(class: 'container') {
                div(class: 'head-container') {
                    ul(class: 'nav nav-pills head-item main') {
                            li(class: 'active', id: 'list') {
                                a(href: "/contest/${message.id}", message.id)
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
                                span(message.admin)
                            }
                            p() {
                                span(message.start +"-"+message.end)
                            }
                        }
                    }
                }
                div(class:'table-div') {
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
                                probleminfo.each { problem->
                                    tr {
                                        td(class: 'center') {
                                            a(href: "/problem/${problem.id}", problem.id)
                                        }
                                        td(class: 'center') {
                                            a(href:"/problem/${problem.id}",problem.name)
                                        }
                                        td()
                                    }
                                }
                            }
                        }
                    }
                }
                    div(class: 'panel panel-default') {
                        div(class: 'panel panel-heading') {
                            h3(class: 'panel-title') {
                                span '대회 운영진'
                            }
                        }
                        div(class: 'panel-body') {
                            span(message.admin)
                            if (message.deputy!=null ) {
                                message.deputy.each { deputy ->
                                    span(deputy.getName()+" ")
                                }
                            }
                            else{
                                p("There is no admins")
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
                            if (message.participant != null) {
                                message.participant.each { participant ->
                                    span(participant.getName())
                                }
                            } else {
                                p('There is no participant')
                            }
                        }
                    }
                div(class:'join-button'){
                    a(class:'btn btn-primary',href: "/contest/join/${message.id}", '참가하기')
                }
                }
            }
