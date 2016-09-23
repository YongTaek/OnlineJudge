layout 'myPageLayout.tpl', loginUser: loginUser,user_id:messages.user_id,group:group,isMe:messages.isMe,user_name: messages.user_name,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/myPage.css')
        },
        content: contents {
            div(class: 'col-md-4') {
                div(id: 'problem-container') {
                    table(class: 'table table-striped') {
                        tbody {
                            if (messages.empty) {
                                tr { td(colspan: '2', 'No Data') }
                            } else {
                                tr {
                                    td(class: 'common-table', "푼 문제")
                                    td(class: 'common-table', messages.solvedProblem)
                                }
                                tr {
                                    td(class: 'common-table', "제출 수")
                                    td(class: 'common-table', messages.submit)
                                }
                                tr {
                                    td(class: 'common-table', "맞은 수")
                                    td(class: 'common-table', messages.correct)
                                }
                                tr {
                                    td(class: 'common-table', "틀린 수")
                                    td(class: 'common-table', messages.incorrect)
                                }
                                tr {
                                    td(class: 'common-table', "소속 그룹")
                                    if (messages.group == null) {
                                        td(class: 'common-table', "가입된 그룹이 없습니다")
                                    } else {
                                        td(class: 'common-table') {
                                            a(href: '/group/myGroup', messages.group)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            div(class: 'col-md-8') {
                div(class: 'panel panel-default') {
                    div(class: 'panel panel-heading') {
                        h3(class: 'panel-title') {
                            span '푼 문제'
                        }
                    }
                    div(class: 'panel-body') {
                        messages.solvedProblemnum.each { message ->
                            span message+" "
                        }
                    }
                }
                div(class: 'panel panel-default') {
                    div(class: 'panel panel-heading') {
                        h3(class: 'panel-title') {
                            span '오답 문제'
                        }
                    }
                    div(class: 'panel-body') {
                        messages.unsolvedProblemnum.each { message ->
                            span message+" "
                        }
                    }
                }
            }
        }
