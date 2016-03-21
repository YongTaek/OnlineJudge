layout 'test.tpl', title: '질문하기', loginUser: loginUser,
        content:
                contents {
                    link(rel: 'stylesheet', href: '/css/table.css')
                    div(class: 'container') {
                        div(class: 'row') {
                            div(class: 'col-md-8 col-xs-12 col-sm-6') {
                                ul(class: 'nav nav-pills') {
                                    li() {
                                        a(href: '/notice', '공지사항')
                                    }
                                    li(class: 'active') {
                                        a(href: '/question', 'Q&A')
                                    }
                                }

                            }
                        }

                        div(class: 'prob-container') {
                            form(id: 'write_question', action: '/question/write', method: 'post') {
                                div(class: 'col-md-12') {
                                    span '제목'
                                    input(type: 'text', value: '', name: 'question_title', id: 'title');

                                }
                                div(class: 'col-md-12') {
                                    span '질문 문제 번호'
                                    input(type: 'text', value: '', name: 'question_problem', id: 'question');
                                }
                                div(class: 'col-md-12') {
                                    span ' 내용'
                                }
                                div(class: 'col-md-12') {
                                    input(type: 'text', value: '', name: 'question_contents', size: '40', style: "height : 100px;, id : 'content")
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