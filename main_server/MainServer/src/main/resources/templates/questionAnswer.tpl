layout 'layout.tpl', title: 'Q&A',loginUser:loginUser, user_id : user_id,
        content: contents {
            link(rel: 'stylesheet', href: '/css/questionAnswer.css')
            div() {
                div(class: 'container') {
                    div(class: 'row') {
                        div(class: 'head-container') {
                            ul(class: 'nav nav-pills head-item-main') {
                                li() {
                                    a(href: '/board/notice', '공지사항')
                                }
                                li(class: 'active') {
                                    a(href: '/board/question', 'Q&A')
                                }
                                li(){
                                    a(href: '/board/question/answerwrite/'+ messages.id, '답변쓰기')
                                }
                            }
                        }
                    }
                    br()
                    div(class: 'prob-container') {
                        div(class: 'col-md-12') {
                            h4(class : 'question') {
                                a(href:'/problem/'+messages.problem_id,messages.problem_id+'번 - '+messages.problem_name)
                                br()

                            }
                            hr(class:'style-eight')
                            div(class : 'panel panel-default') {
                               div(class : 'panel panel-heading') {
                                    h3(class : 'panel-title') {
                                        span(class:'title',messages.title)
                                        span(class:'date', messages.question_date)
                                        span(class:'writer','작성자 : '+messages.question_user)
                                        if(messages.canModifyAndDelete){
                                            a(class:'delete',href: '/board/question/delete/'+ messages.id, '삭제')
                                            a(class:'modify',href: '/board/question/modify/'+ messages.id, '수정')

                                        }

                                    }
                                }
                                div(class: 'panel-body') {
                                    div(class : 'content-post', style : 'line-height : 30px') {
                                        span messages.question_contents
                                    }
                                }
                            }
                            hr(class:'style-answer')
                            if(answers.empty) {
                                span '아직 답변이 없습니다'
                            }
                            else {
                                answers.each { answer ->
                                    div(class: 'panel panel-default') {
                                        div(class: 'panel panel-heading') {
                                            h3(class: 'panel-title') {
                                                span(class:'title', answer.answer_title)
                                                span(class:'date', answer.answer_date)
                                                span(class:'writer','작성자 : '+answer.answer_user)
                                                if(answer.canModifyAndDelete) {
                                                    a(href: '/board/question/answermodify/' + answer.answer_id, '수정')
                                                    a(href: '/board/question/answerdelete/' + answer.answer_id, '삭제')
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