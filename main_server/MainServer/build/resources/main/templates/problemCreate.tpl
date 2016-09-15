layout 'layout.tpl', title: '문제만들기',loginUser:loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/createProblem.css')
            script(type: 'text/javascript', src: '/SE2.1.3.O8706/js/HuskyEZCreator.js', charset = "utf-8") {}

//            link(rel: 'stylesheet', href: '/css/table.css')
        },
        content: contents {
            div(class: 'container') {
                div(class: 'prob-container') {
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
                                li() {
                                    a(href: '/myPage', '활동')
                                }
                                li() {
                                    a(href: '/myPage/setting', '설정')
                                }
                                li(class: 'active') {
                                    a(href: '/problem/create', '문제 만들기')
                                }
                            }

                        }
                    }
                    br()

                    form(id : 'write_problem', action:'/problem/create', method:'post', class: 'write_form') {
                        p(class: 'text', '문제 제목')
                        input(class: 'input', type: 'text', value: '', name: 'problem_title', id: 'title')

                        p(class: 'text', '문제 내용')
                        textarea(class: 'form-control', id: 'content', style: 'width 150px height 400px', name: 'problem-contents', type: 'submit') {}
                        script(type = "text/javascript", src: '/js/smartEditor.js') {}
                        br()
                        p(class: 'text', '시간 제한')
                        input(class: 'input',type: 'text', value: '', name: 'problem_timeLimit', id: 'time')

                        p(class: 'text', '메모리 제한')
                        input(class: 'input', type: 'text', value: '', name: 'problem_memoryLimit', id: 'memory')


                        p(class: 'text', '보이는 input값')
                        input(class: 'input', type: 'text', value: "", name: 'problem_visibleInput', id: 'visibleinput')

                        p(class: 'text', '보이는 output값')
                        input(class: 'input', type: 'text', value: "", name: 'problem_visibleOutput', id: 'visibleoutput')
                        br()
                        div(class: 'row col-md-5 testcaseButton') {
                            p(class: 'text', "테스트케이스")
                            p('input값', id: 'testInput')
                        }
                        input(type: 'hidden', name: 'testcase_count', id: 'testcase_count')
                        div() {
                            button(id: "plusButton1", type: 'button', class: "btn btn-primary plusButton", '+')
                            button(id: "minusButton", type: 'button', class: "btn btn-primary", '-')
                            p(class: 'text-left', 'output값', id: 'testOutput')
                        }
                        br()
                        button(type: "submit", id: "postButton", class: "btn btn-primary", '올리기')
                        br()
                    }

                }
                script(type:'text/javascript',src:'../js/createProblem.js'){}

            }
        }