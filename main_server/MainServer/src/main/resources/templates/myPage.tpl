layout 'layout.tpl', title: '마이페이지',loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/myPage.css')
//            link(rel: 'stylesheet', href: '/css/table.css')
        },
        content: contents {
            div(class: 'container') {
                div(class : 'row') {
                    div(class: 'col-md-12') {
                        p(class : 'username') {
                            span(style: 'font-size : 35', messages.user_id)
                            span(messages.user_name)
                        }
                    }
                }
                div(class: 'row') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(class : 'active') {
                                a(href: '/myPage', '활동')
                            }
                            li() {
                                a(href: '/myPage/setting', '설정')
                            }
                            li(){
                                a(href: '/problem/create', '문제 만들기')
                            }
                            if(!messages.group){
                                li() {
                                    a(href: '/group/list', '그룹')
                                }
                                li() {
                                    a(href: '/group/create','그룹 만들기')
                                }
                            }
                        }

                    }
                }
                br()
                div(class : 'col-md-4') {
                    div(id: 'problem-container') {
                        table(class: 'table table-striped') {
                            tbody {
                                if (messages.empty) {
                                    tr { td(colspan: '2', 'No Data') }
                                } else {
                                    tr{
                                       td(class : 'common-table', "푼 문제")
                                       td(class : 'common-table', messages.solvedProblem)
                                    }
                                    tr{
                                       td(class : 'common-table', "제출 수")
                                       td(class : 'common-table', messages.submit)
                                    }
                                    tr{
                                       td(class : 'common-table', "맞은 수")
                                       td(class : 'common-table', messages.correct)
                                    }
                                    tr{
                                       td(class : 'common-table', "틀린 수")
                                       td(class : 'common-table', messages.incorrect)
                                    }
                                    tr{
                                        td(class : 'common-table', "소속 그룹")
                                       if(messages.group == null){
                                           td(class : 'common-table')
                                       }else{
                                           td(class : 'common-table'){
                                               a(href: '/group/myGroup',messages.group)
                                           }
                                       }
                                    }
                                }
                            }
                        }
                    }
                }
                div(class : 'col-md-8'){
                    div(class : 'panel panel-default') {
                        div(class: 'panel panel-heading') {
                            h3(class: 'panel-title') {
                                span '푼 문제'
                            }
                        }
                        div(class: 'panel-body'){
                            messages.solvedProblemnum.each { message ->
                                span message+" "
                            }
                        }
                    }
                    div(class : 'panel panel-default') {
                        div(class: 'panel panel-heading') {
                            h3(class: 'panel-title') {
                                span '오답 문제'
                            }
                        }
                        div(class: 'panel-body'){
                            messages.unsolvedProblemnum.each { message ->
                                span message+" "
                            }
                        }
                    }
                }
            }
        }