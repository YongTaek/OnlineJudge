layout 'group.tpl',loginUser:loginUser,userGroup:userGroup,
        custom_head:contents{
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
        },
        tables:contents{
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
                    button(class: 'btn btn-primary',id: 'requiredProblemAdd', '필수 문제집 설정')
                }
                div(class: 'col-md-4') {
                    table(class: 'table table-striped') {
                        thead() {
                            tr {
                                th(class: 'center', id: 'usr-name', '닉네임')
                                th(class: 'center', id: 'suc-count', '푼문제')
                                th(class: 'center', id: 'suc-rate', '비율')
                                th(class: 'center', id: 'force-delete', '강퇴/요청취소')
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
                                        td(class: 'center') {
                                            a(class:'btn btn-primary',href: '#', '취소')
                                        }
                                    }
                                }
                                members.each { member ->
                                    tr {
                                        td(class: 'center', member.name)
                                        td(class: 'center', member.prob_count)
                                        td(class: 'center', member.rate)
                                        td(class: 'center') {
                                            if(me != member.name) {
                                                a(class: 'btn btn-primary btn-custom', href: '#', '강퇴')
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            script(src:'/js/groupSetting.js')
        }