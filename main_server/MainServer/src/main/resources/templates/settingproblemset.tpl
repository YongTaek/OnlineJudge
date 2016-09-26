layout 'layout.tpl', title: '문제집 만들기', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/problemsetContest.css')
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
            link(rel: 'stylesheet', type: 'text/css', href: '/css/settingProblemset.css')
        },
        content: contents {
            div(class: 'container') {
                div(id: 'problem-container') {
                    form(id: 'create-problemset', method: 'post', class: 'write_form') {
                        div(class: 'col-md-6') {
                            span('문제집 이름')
                            br()
                            input(type: 'text', class: 'readonly form-control', value: setName , name: 'problemset_title', id: 'title',readonly: true);
                        }
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
                                                if(!message.contain){
                                                    td(class: 'center') {
                                                        form(class: 'form-inline', method: 'post') {
                                                            input(type: 'hidden', action: '/problemset/setting', name: 'id', value: message.problem_id)
                                                            button(class: 'btn btn-primary btn-custom', type: 'submit', '추가')
                                                        }
                                                    }
                                                }
                                                else{
                                                    td(class: 'center') {
                                                        form(class: 'form-inline',action: '/problemset/setting', method: 'post') {
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
                    a(class:'btn btn-primary',href: "/problemset", '완료')
                }
            }
        }