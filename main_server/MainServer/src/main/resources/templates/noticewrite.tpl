layout 'layout.tpl', title: '질문하기',loginUser:loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/questionList.css')
            script(type: 'text/javascript', src: '/SE2.1.3.O8706/js/HuskyEZCreator.js', charset = "utf-8") {}
        },
        content: contents {
            div(class: 'container') {
                div(class: 'row') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(class: 'active') {
                                a(href: '/board/notice', '공지사항')
                            }
                            li() {
                                a(href: '/board/question', 'Q&A')
                            }
                        }
                    }
                }
                br()
                div(class: 'prob-container') {
                    form(id: 'write_question', action: '/board/notice/write', method: 'post') {
                        div(class: 'col-md-12 margin') {
                            span '제목'
                            input(type: 'text', value: '', name: 'notice_title', id: 'title');
                        }
                        div(class: 'col-md-12') {
                            span ' 내용'
                        }
                        br()
                        div(class: 'col-md-12') {
                            textarea(type: 'text', value: '', name: 'notice_contents', size: '40', style: "height : 100px;", id : 'content') {
                            }
                            script(type = "text/javascript", src: '/js/smartEditor.js'){}

//                            input(type: 'text', value: '', name: 'notice_contents', size : '40', style : "height : 100px;, id : 'content")
                            }
                            br()
                            div(class: 'col-md-12') {
                                div(class: 'row') {
                                    button(type: "submit", id: "postButton", class: "btn btn-primary submit", '올리기')
                                    a(href: '/board/notice', class: "btn btn-primary", '취소')
                                }
                            }
                        }
                    }


            }
        }