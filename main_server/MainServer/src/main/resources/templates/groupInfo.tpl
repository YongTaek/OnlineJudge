layout 'layout.tpl', title: '문제', loginUser: loginUser,
        content: contents {
            div() {
                link(rel: 'stylesheet', href: '/css/groupInfo.css')
                div('row') {
                    div(class: 'col-md-12') {
                        ul(class: 'nav nav-pills') {
                            li {
                                a(href: '/group', 'Group 목록')
                            }
                            li(class: 'active') {
                                a(href: '/group/info', '내 Group')
                            }
                        }
                    }
                }
                div('group-container') {
                    div(class: 'col-md-6') {
                        div(class: 'prob-set-container') {
                            table(class: 'prob-set-table') {
                                thead {
                                    tr {
                                        th(class: 'common-table', id: 'require-set', '필수 문제집')
                                        th(class: 'common-table', id: 'set-rate', '성취도')
                                    }
                                }
                                tbody(class: 'prob-set-table-body') {
                                    if (messages.empty) {
                                        td 'No require problem set'
                                    } else {
                                        messages.each { message ->
                                            tr {
                                                td(class: 'common-table', message.name)
                                                td(class: 'common-table', message.rate)
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }

                    div(class: 'col-md-6') {
                        table(class: 'group-member-table') {
                            thead(class: 'group-member-table-head') {
                                tr {
                                    th(class: 'common-table', id: 'usr-name', '닉네임')
                                    th(class: 'common-table', id: 'suc-count', '푼문제')
                                    th(class: 'common-table', id: 'suc-rate', '비율')
                                }
                            }
                            tbody(class: 'group-member-table-body') {
                                if (members.empty) {
                                    h2 'No group member'
                                } else {
                                    waitMembers.each { waitMember ->
                                        tr {
                                            td(class: 'common-table',waitMember.name)
                                            td(class: 'common-table',waitMember.prob_count)
                                            td(class: 'common-table',waitMember.rate)
                                        }
                                    }
                                    members.each { member ->
                                        tr {
                                            td(class: 'common-table', member.name)
                                            td(class: 'common-table', member.prob_count)
                                            td(class: 'common-table', member.rate)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }