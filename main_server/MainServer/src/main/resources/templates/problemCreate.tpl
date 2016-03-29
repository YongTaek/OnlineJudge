layout 'test.tpl', title: '문제만들기',loginUser:loginUser,
        content: contents {
            div(class: 'container') {
                div(class: 'prob-container') {
                    form(id : 'write_problem', action:'/problem/create', method:'post') {
                        div(class: 'col-md-12') {
                            span '문제 제목'
                            input(type: 'text', value: '', name: 'problem_title', id : 'title')

                        }
                        div(class: 'col-md-12') {
                            span ' 내용'
                        }
                        div(class: 'col-md-12') {
                            input(type: 'text', value: '', name: 'problem_contents', size : '40', style : "height : 100px;, id : 'content")
                        }
                        div(class :'col-md-12') {
                            span '시간 제한'
                            input(type: 'text', value: '', name: 'problem_timeLimit', id : 'time')

                        }
                        div(class :'col-md-12') {
                            span '메모리 제한'
                            input(type: 'text', value: '', name: 'problem_memoryLimit', id : 'memory')
                        }
                        div(class : 'row') {
                            div(class: 'col-md-6') {
                                span '보이는 input값'
                                input(type: 'text', value: "", name: 'problem_visibleInput', id: 'visibleinput')
                            }
                            div(class: 'col-md-6') {
                                span '보이는 output값'
                                input(type: 'text', value: "", name: 'problem_visibleOutput', id: 'visibleoutput');
                            }
                        }
                        div(class : 'row') {
                            div(class: 'col-md-6') {
                                span('테스트 케이스 input값', id : 'testInput')
                            }
                            div(class: 'col-md-6') {
                                span('테스트 케이스 output값', id : 'testOutput')
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