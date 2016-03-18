layout 'test.tpl', title: '답변하기',
        content: contents {
            link(rel:'stylesheet', href: '/css/table.css')
            div(class: 'container') {
                div(class:'row') {
                    div(class: 'col-md-8 col-xs-12 col-sm-6') {
                        ul(class: 'nav nav-pills') {
                            li() {
                                a(href: '/notice', '공지사항')
                            }
                            li() {
                                a(href: '/question', 'Q&A')
                            }
                            li(class : 'active'){
                                a(href: '/question/answerwrite/'+messages.id, '답변쓰기')

                            }
                        }

                    }
                }

                div(class: 'prob-container') {
                    form(id : 'write_question', action:'/question/answerwrite/'+messages.id, method:'post') {
                        div(class: 'col-md-12') {
                            span '답변 제목'
                            input(type: 'text', value: '', name: 'answer_title', id : 'title');

                        }
                        div(class: 'col-md-12') {
                            span ' 내용'
                        }
                        div(class: 'col-md-12') {
                            input(type: 'text', value: '', name: 'answer_contents', size : '40', style : "height : 100px;, id : 'content")
                        }
                        div(class: 'col-md-12') {
                            div(class: 'row') {
                                button(type : "submit", id : "postButton", class : "btn btn-primary", '올리기')
                                a(href : '/question',class:"btn btn-primary", '취소')
                            }
                        }
                    }
                }
            }
        }