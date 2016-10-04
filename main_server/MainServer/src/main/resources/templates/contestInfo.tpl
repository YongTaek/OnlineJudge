layout 'contest.tpl', title: '대회', loginUser: loginUser, user_id:user_id,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/myPage.css')
            link(rel: 'stylesheet', href: '/css/contest.css')
            link(rel: 'stylesheet', href: '/css/problemset.css')
        },
        content: contents {
            div(class: 'container') {
                div() {
                    div(class: "row") {
                        div(class: "col-md-12") {
                            p(class: "team-name") {
                                span(style: 'font-size : 35', message.name)
                                span(message.admin)
                            }
                            p() {
                                span(message.start +" - "+message.end)
                            }
                            button(id: 'deputy-request', class:'btn btn-primary',type:'button', '부관리자 신청')
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
                        span(message.admin+" ")
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
                                a(href:"/contest/team/info/${participant.getId()}",participant.getName()+" ")
                            }
                        } else {
                            p('There is no participant')
                        }
                    }
                }
                div(class:'join-button'){
                    a(class:'btn btn-primary',href: "/contest/team/create/${message.id}", '참가하기')
                    a(class:'btn btn-primary', href:"/contest/setting/${message.id}", "설정")
                }
            }
            script(src:'/js/contestInfo.js'){}
        }
