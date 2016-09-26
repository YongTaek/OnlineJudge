layout 'group.tpl', title: '문제', loginUser: loginUser,userGroup:userGroup, user_id : user_id,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
        },
        jjang_head: contents {
            if(jjang) {
                li(id: 'group_setting') {
                    a(href: '/group/myGroup/setting', '그룹 설정')
                }
            }
        },
        tables: contents {
            if (!userGroup) {
                div() {
                    h2(class:'center', '그룹이 아직 없습니다')
                }
            } else {
                div('problem-container') {
                    div(class: 'col-md-8') {
                        table(class: 'table table-striped') {
                            thead {
                                tr {
                                    th(class: 'center', id: 'require-set', '필수 문제집')
                                    th(class: 'center', id: 'set-rate', '성취도')
                                }
                            }
                            tbody() {
                                if (messages.empty) {
                                    tr { td(colspan: '100%', 'No required problem set') }
                                } else {
                                    messages.each { message ->
                                        tr {
                                            td(class: 'center', message.name)
                                            td(class: 'centers', message.rate)
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
        }