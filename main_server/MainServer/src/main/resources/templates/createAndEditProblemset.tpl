layout 'layout.tpl', title: title, loginUser: loginUser, user_id : user_id,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/problemsetContest.css')
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
        },
        content: contents {
            div(class: 'container') {
                div(id: 'problem-container') {
                    form(id: 'create-problemset', action: action, method: 'post', class: 'write_form') {
                        div(class: 'col-md-6') {
                            span('문제집 이름')
                            br()
                            input(type: 'text', class: 'form-control', value: setName, name: 'problemset_title', id: 'title');
                        }
                        if (flag == 1) {
                            div(class: 'col-md-6') {
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
                        }
                    }
                        if (flag == 2) {
                            div(class: 'col-md-6') {
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
                                                    if (!message.contain) {
                                                        td(class: 'center') {
                                                            form(id: "add", class: 'form-inline', method: 'post', action: action + "/update") {
                                                                input(type: 'hidden', name: 'id', value: message.problem_id)
                                                                button(class: 'btn btn-primary btn-custom', type: 'submit', '추가')
                                                            }
                                                        }
                                                    } else {
                                                        td(class: 'center') {
                                                            form(id: "remove", class: 'form-inline', method: 'post', action: action + "/update") {
                                                                input(type: 'hidden', name: 'id', value: message.problem_id)
                                                                button(class: 'btn btn-primary btn-custom', type: 'submit', '삭제')
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        button(form:"create-problemset",type: "submit", id: "postButton", class: "btn btn-primary", '완료')
                    }
                }
            }