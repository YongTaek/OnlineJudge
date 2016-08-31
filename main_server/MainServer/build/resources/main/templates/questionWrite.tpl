layout 'layout.tpl', title: '질문하기', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/writeNotice.css')
            script(type: 'text/javascript', src: '/SE2.1.3.O8706/js/HuskyEZCreator.js', charset = "utf-8") {}
        },
        content: contents {
                    div(class: 'container') {
                        div(class: 'head-container') {
                            div(class: 'nav nav-pills head-item main') {
                                ul(class: 'nav nav-pills') {
                                    li(id: 'notice') {
                                        a(href: '/board/notice', '공지사항')
                                    }
                                    li(class: 'active', id: 'question') {
                                        a(href: '/board/question', 'Q&A')
                                    }
                                }

                            }
                        }
                        br()
                        div(class: 'prob-container') {
                            form(id: 'write_question', action: '/question/write', method: 'post') {
                                div(class: 'row') {
                                    div(class:'margin') {
                                        span(class : 'marginTitle', '제목')
                                        input(type: 'text',class: 'form-title', value: '', name: 'question_title', id: 'title');
                                    }
                                    div(class:'margin'){
                                        span(class : 'marginNumber' ,'질문 문제 번호')
                                        input(type: 'text', class: 'form-title', value: '', name: 'question_problem', id: 'question');
                                    }

                                }
                                br()
                                div(class: 'col-md-12') {
                                    span ' 내용'
                                }
                                br()
                                div(class: 'col-md-12') {
                                    textarea(type: 'text', value: '', name: 'question_contents', size: '40', style: "height : 100px;", id : 'content') {
                                    }
                                    script(type = "text/javascript", src: '/js/smartEditor.js'){}
                                }
                                br()
                                div(class: 'col-md-12') {
                                    div(class: 'row') {
                                        button(type: "submit", id: "postButton", class: "btn btn-primary submit", '올리기')
                                        a(href: '/question', class: "btn btn-primary", '취소')
                                    }
                                }
                            }
                        }
                    }
                }