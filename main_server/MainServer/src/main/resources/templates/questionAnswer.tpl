layout 'layout.tpl', title: 'Q&A',
        content: contents {
            link(rel: 'stylesheet', href: '/css/table.css')
            div() {
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
                                li(){
                                    a(href: '/question/answerwrite/'+ messages.id, '답변쓰기')
                                }
                            }
                        }
                    }

                    div(class: 'prob-container') {
                        div(class: 'col-md-12') {
                            h3(clss : 'col-md-12') {
                                span '질문 문제 : '
                                span messages.problem_id
                                span messages.problem_name
                            }
                            div(class : 'panel panel-default') {
                                div(class : 'panel panel-heading') {
                                    h3(class : 'panel-title') {
                                        span messages.title
                                        span messages.question_date
                                        span messages.question_user
                                        if(messages.canModifyAndDelete){
                                            a(href: '/question/modify/'+ messages.id, '수정')
                                            a(href: '/question/delete/'+ messages.id, '삭제')
                                        }

                                    }
                                }
                                div(class: 'panel-body') {
                                    div(class : 'content-post', style : 'line-height : 30px') {
                                        span messages.question_contents
                                    }
                                }
                            }
                            if(answers.empty) {
                                span '아직 답변이 없습니다'
                            }
                            else {
                                answers.each { answer ->
                                    div(class: 'panel panel-default') {
                                        div(class: 'panel panel-heading') {
                                            h3(class: 'panel-title') {
                                                span answer.answer_title
                                                span answer.answer_date
                                                span answer.answer_user
                                                if(answer.canModifyAndDelete) {
                                                    a(href: '/question/answermodify/' + answer.answer_id, '수정')
                                                    a(href: '/question/answerdelete/' + answer.answer_id, '삭제')
                                                }
                                            }
                                        }
                                        div(class: 'panel-body') {
                                            span answer.answer_contents
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }