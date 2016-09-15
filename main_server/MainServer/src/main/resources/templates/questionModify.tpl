layout 'layout.tpl', title: '질문수정',loginUser:loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/questionList.css')
            script(type: 'text/javascript', src: '/SE2.1.3.O8706/js/HuskyEZCreator.js', charset = "utf-8") {}
        },
        content: contents {
            div(class: 'container') {
                div(class: 'row') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li() {
                                a(href: '/board/notice', '공지사항')
                            }
                            li(class: 'active') {
                                a(href: '/board/question', 'Q&A')
                            }
                        }
                    }
                }
                br()
                div(class: 'prob-container') {
                    form(id : 'write', action:'/board/question/modify/'+messages.id, method:'post') {
                        div(class: 'col-md-12 margin') {
                            span '제목'
                            input(type: 'text', value: messages.title, name: 'question_title', id : 'title');

                        }
                        div(class: 'col-md-12 margin') {
                            span '질문 문제 번호'
                            input(type: 'text', value: messages.problem_id, name: 'question_problem', id : 'question');
                        }
                        div(class: 'col-md-12 margin') {
                            span ' 내용'
                            a(href: '#', id: "modifyButton", class: "btn btn-primary", '본문 내용 가져오기', value: messages.contents)
                        }
                        div(class: 'col-md-12') {
                            textarea(type: 'text', value: '', name: 'smartEditor', size: '40', style: "height : 100px;", id : 'smartEditor') {
                            }
                            script(type = "text/javascript", src: '/js/smartEditor.js'){}
                        }
                        br()
                        div(class: 'col-md-12') {
                            div(class: 'row') {
                                button(type : "submit", id : "postButton", class : "btn btn-primary", '올리기')
                                a(href : '/board/question',class:"btn btn-primary", '취소')
                            }
                        }
                    }
                }
            }
        }