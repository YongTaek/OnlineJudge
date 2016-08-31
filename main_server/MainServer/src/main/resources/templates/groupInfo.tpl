layout 'group.tpl', title: '문제', loginUser: loginUser,userGroup:userGroup,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
        },
        join: contents {
            div(class:"head-item") {
                if (isJoin) {
                    a(class: 'btn btn-primary push-left', href: "/group/join/$id", '가입 가능')
                }
            }
        },
        tables: contents {
            div('problem-container') {
                div(class: 'col-md-8') {
                    table(class: 'table table-striped') {
                        thead {
                            tr {
                                th(class: 'center', id: 'require-set', '필수 문제집')
                            }
                        }
                        tbody() {
                            if (messages.empty) {
                                tr { td(colspan: '100%', 'No required problem set') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td(class: 'center', message.name)
                                    }

                                }
                            }
                        }
                    }

                }
                div(class: 'col-md-4') {
                    table(class: 'table table-striped') {
                        thead() {
                            tr {
                                th(class: 'center', id: 'usr-name', '닉네임')
                                th(class: 'center', id: 'suc-count', '푼문제')
                                th(class: 'center', id: 'suc-rate', '비율')
                            }
                        }
                        tbody() {
                            if (!members) {
                                tr { td(colspan: '100%', 'No group member') }
                            } else {
                                waitMembers.each { waitMember ->
                                    tr {
                                        td(class: 'center', waitMember.name)
                                        td(class: 'center', waitMember.prob_count)
                                        td(class: 'center', waitMember.rate)
                                    }
                                }
                                members.each { member ->
                                    tr {
                                        td(class: 'center', member.name)
                                        td(class: 'center', member.prob_count)
                                        td(class: 'center', member.rate)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
