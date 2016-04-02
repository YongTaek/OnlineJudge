layout 'layout.tpl', title: '문제만들기',loginUser:loginUser,
        content: contents {
            div(class: 'container') {
                div(class: 'prob-container') {
                    div(class : 'row') {
                        div(class: 'col-md-12') {
                            p() {
                                span(style: 'font-size : 35', messages.user_id)
                                span(messages.user_name)
                            }
                        }
                    }
                    div(class: 'row') {
                        div(class: 'col-md-8 col-xs-12 col-sm-6') {
                            ul(class: 'nav nav-tabs') {
                                li() {
                                    a(href: '/myPage', '활동')
                                }
                                li() {
                                    a(href: '/myPage/setting', '설정')
                                }
                                li(class : 'active'){
                                    a(href: '/problem/create', '문제 만들기')
                                }
                            }

                        }
                    }
                    hr(class : 'half-rule')
                    form(id : 'write_problem', action:'/problem/create', method:'post') {
                        div(class: 'col-md-12') {
                            div(class : 'col-md-4') {
                                p(class : 'lead text-center','문제 제목')
                            }
                            div(class : 'col-md-8') {
                                input(type: 'text', value: '', name: 'problem_title', id: 'title')
                            }
                        }
                        div(class: 'col-md-12') {
                            div(class : 'col-md-4') {
                                p(class : 'lead text-center', '문제 내용')
                            }
                            div(class : 'col-md-8') {
                                textarea(class: 'form-control', style:  'width 200px height 200px',name: 'problem-contents', type : 'submit'){

                                }
                                //input(type: 'text', value: '', name: 'problem_contents', size: '40', style: "height : 100px;, id : 'content")
                            }
                        }
                        div(class :'col-md-12') {
                            div(class : 'col-md-4') {
                                p(class : 'lead text-center', '시간 제한')
                            }
                            div(class : 'col-md-8') {
                                input(type: 'text', value: '', name: 'problem_timeLimit', id : 'time')
                            }
                        }
                        div(class :'col-md-12') {
                            div(class : 'col-md-4') {
                                p(class : 'lead text-center','메모리 제한')
                            }
                            div(class : 'col-md-8'){
                                input(type: 'text', value: '', name: 'problem_memoryLimit', id : 'memory')
                            }
                        }
                        div(class : 'col-md-12') {
                            div(class: 'col-md-4') {
                                p(class: 'lead text-center', '보이는 input값')
                            }
                            div(class : 'col-md-8'){
                                input(type: 'text', value: "", name: 'problem_visibleInput', id: 'visibleinput')
                            }
                        }
                        div(class : 'col-md-12') {
                            div(class: 'col-md-4') {
                                p(class: 'lead text-center', '보이는 output값')
                            }
                            div(class : 'col-md-8'){
                                input(type: 'text', value: "", name: 'problem_visibleOutput', id: 'visibleoutput');                            }
                        }
                        div(class : 'row') {
                            div(class: 'col-md-4') {
                                p(class : 'lead text-center','테스트 케이스 input값', id : 'testInput')
                            }
                            div(class: 'col-md-8') {
                                p(class : 'lead text-left','테스트 케이스 output값', id : 'testOutput')
                            }
                            button(id: "plusButton1", type: 'button', class: "btn btn-primary", '+')
                            button(id: "minusButton", type: 'button', class: "btn btn-primary", '-')
                        }
                        div(class: 'col-md-12') {
                            div(class: 'row') {
                                button(type : "submit", id : "postButton", class : "btn btn-primary", '올리기')
                                a(href : '/question',class:"btn btn-primary", '취소')
                            }
                        }
                    }
                }
                script(type:'text/javascript',src:'../js/createProblem.js'){}

            }
        }