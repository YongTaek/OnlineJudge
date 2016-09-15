layout 'layout.tpl', title: '답변하기', loginUser:loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/writeNotice.css')
            script(type: 'text/javascript', src: '/SE2.1.3.O8706/js/HuskyEZCreator.js', charset = "utf-8") {}
        },
        content: contents {
            div(class : 'container') {
                div(class:'row') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li() {
                                a(href: '/board/notice', '공지사항')
                            }
                            li() {
                                a(href: '/board/question', 'Q&A')
                            }
                            li(class : 'active'){
                                a(href: '/board/question/answerwrite/'+messages.id, '답변쓰기')

                            }
                        }

                    }
                }
                br()
                div(class: 'prob-container') {
                    form(id : 'write', action:'/question/answermodify/'+messages.id, method:'post') {
                        div(class: 'margin') {
                            span '답변 제목'
                            input(type: 'text', value: messages.title, name: 'answer_title', id : 'title');
                        }
                        div(class: 'col-md-12') {
                            span ' 내용'
                        }
                        div(class: 'col-md-12') {
                            textarea(type: 'text', value: messages.contents, name: 'smartEditor', size: '40', style: "height : 100px;", id : 'smartEditor') {
                            }
                            script(type = "text/javascript", src: '/js/smartEditor.js'){}
                        }
                        div(class: 'col-md-12') {
                            div(class: 'row') {
                                button(type : "submit", id : "postButton", class : "btn btn-primary", '올리기')
                                a(href : '/question/'+messages.question_id,class:"btn btn-primary", '취소')
                            }
                        }
                    }
                }
            }
        }