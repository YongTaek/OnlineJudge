layout 'layout.tpl', title: '질문하기', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/writeNotice.css')
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

                        div(class: 'prob-container') {
                            form(id: 'write_question', action: '/question/write', method: 'post') {
                                div(class: 'col-md-12') {
                                    span '제목'
                                    input(type: 'text',class: 'form-title', value: '', name: 'question_title', id: 'title');

                                }
                                div(class: 'col-md-12') {
                                    span '질문 문제 번호'
                                    input(type: 'text', class: 'form-title',value: '', name: 'question_problem', id: 'question');
                                }
                                div(class: 'col-md-12') {
                                    span ' 내용'
                                }
                                div(class: 'col-md-12') {
                                    input(type: 'text', class: 'form-control', value: '', name: 'question_contents', size: '40', style: "height : 100px;, id : 'content")
                                }
                                div(class: 'col-md-12') {
                                    div(class: 'row') {
                                        button(type: "submit", id: "postButton", class: "btn btn-primary", '올리기')
                                        a(href: '/question', class: "btn btn-primary", '취소')
                                    }
                                }
                            }
                        }
                    }
                }