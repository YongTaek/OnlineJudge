layout 'layout.tpl', title: '문제집 만들기', loginUser: loginUser, user_id : user_id,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/problemsetContest.css')
        },
        content: contents {
            div(class: 'container') {
                div(id: 'problem-container') {
                    form(id : 'create-problemset', action:'/problemset/create/contest', method:'post', class: 'write_form') {
                        div(class: 'col-md-6') {
                            span('문제집 이름')
                            br()
                            input(type: 'text',class: 'form-control', value: '', name: 'problemset_title', id: 'title');
                        }
                        div(class: 'col-md-6') {
                            p(class : 'center', "공개 여부가 비공개인 문제만 등록 가능")
                            table(class: 'table table-striped') {
                                thead {
                                    tr {
                                        th(class: 'center', '제목')
                                        th(class: 'center', '공개여부')
                                        th(class: 'center', '선택')
                                    }
                                }
                                tbody {
                                    if (messages.empty) {
                                        tr { td(colspan: '3', 'No Problem') }
                                    } else {
                                        messages.each { message ->
                                            tr {
                                                td(class: 'center') {
                                                    a(href: '/problem/' + message.problem_id, message.problem_name)
                                                }
                                                td(class: 'center', message.problem_isOpen)
                                                td(class: 'center') {
                                                    input(type: 'checkbox', name: 'selectedProblem', value: message.problem_id)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        button(type: "submit", id: "postButton", class: "btn btn-primary", '올리기')
                    }
                }
            }

        }