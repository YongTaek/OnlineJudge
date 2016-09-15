layout 'layout.tpl', title: '공지',loginUser:loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/questionAnswer.css')
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
                        div(class: 'col-md-12') {
                            div(class : 'panel panel-default') {
                                div(class : 'panel panel-heading') {
                                    h3(class : 'panel-title') {
                                        span(class:'title', messages.title)
                                        span(class:'date', messages.date)
                                        span(class:'writer', messages.user)
                                        a(class:'delete', href: '/board/notice/delete/'+ messages.id, '삭제')
                                        a(class:'modify', href: '/board/notice/modify/'+ messages.id, '수정')
                                    }
                                }
                                div(class: 'panel-body') {
                                    div(class : 'content-post', style : 'line-height : 30px') {
                                        span messages.contents
                                    }
                                }
                            }
                        }
                    }
                }

        }

