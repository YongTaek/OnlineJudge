layout 'contest.tpl', title: '대회', loginUser: loginUser, user_id:user_id,contest_id:messages.contest_id,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/myPage.css')
            link(rel: 'stylesheet', href: '/css/contest.css')
            link(rel: 'stylesheet', href: '/css/problemset.css')
        },
        content: contents {
            div(class: 'container') {
                form(id: 'modify_contest', action: '/contest/setting/' + messages.contest_id, method: 'post', class: 'write_form') {
                    div(class: 'col-md-6') {
                        table(class: 'table', id: 'problem-info') {
                            thead {
                                tr {
                                    th(class: 'center', '이름')
                                    th(class: 'center', '여부')
                                }
                            }
                            tbody {
                                if (Deputies.empty) {
                                    tr {
                                        td(class: 'center', "부관리자가 없습니다")
                                    }
                                } else {
                                    Deputies.each { deputy ->
                                        tr {
                                            td(class: 'center') {
                                                a(href: "/user/info/${deputy.deputy_id}", deputy.deputy_name)
                                            }
                                            td(class: 'center') {
                                                button(class: 'btn btn-primary', type: 'button', value: deputy.deputy_id+"/"+messages.contest_id, name: 'deputy-delete', id: 'deputy-delete', '삭제');
                                            }
                                        }
                                    }
                                }
                                if (requestDeputies.empty) {
                                    tr {
                                        td(class: 'center', "신청한 부관리자가 없습니다")
                                    }
                                } else {
                                    requestDeputies.each { requestDeputy ->
                                        tr {
                                            td(class: 'center') {
                                                a(href: "/user/info/${requestDeputy.requestDeputy_id}", requestDeputy.requestDeputy_name)
                                            }
                                            td(class: 'center') {
                                                button(class: 'btn btn-primary', type: 'button', value: requestDeputy.requestDeputy_id+"/"+messages.contest_id,name: 'requestDeputy-add', id: 'requestDeputy-add', '추가')
                                                button(class: 'btn btn-primary', type: 'button', value: requestDeputy.requestDeputy_id+"/"+messages.contest_id, name: 'requestDeputy-deny', id: 'requestDeputy-deny', '거절')
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    div(class: 'col-md-6') {
                        p("대회 이름")
                        input(id: 'set-name', class: 'form-control input margin', name: 'contestname', value: messages.contest_name, 'required': true)
                        br()
                        p("대회 문제집")
                        input(id: 'set-list', class: 'form-control input margin inline', name: 'contestProblemset', readonly: true, value: messages.contest_problemset)
                        button(class: 'btn btn-primary inline', type: 'button', name: 'problemset', id: 'problemset', '추가')

                    }
                    button(class: 'btn btn-primary modifyButton', type: 'button', value:  messages.contest_id, name: 'delete-contest', id: 'delete-contest', '대회 삭제')
                    button(type: "submit", id: "postButton", class: "btn btn-primary modifyButton", '설정')
                }
            }
            script(type: 'text/javascript', src: '/js/contestModify.js') {}
        }
