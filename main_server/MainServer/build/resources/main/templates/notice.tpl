layout 'layout.tpl', title: '공지',loginUser:loginUser,
        content: contents {
            link(rel: 'stylesheet', href: '/css/table.css')
            div() {
                div(class: 'container') {
                    div(class: 'row') {
                        div(class: 'col-md-8 col-xs-12 col-sm-6') {
                            ul(class: 'nav nav-pills') {
                                li(class: 'active') {
                                    a(href: '/board/notice', '공지사항')
                                }

                                li() {
                                    a(href: '/board/question', 'Q&A')
                                }
                            }
                        }
                    }

                    div(class: 'prob-container') {
                        div(class: 'col-md-12') {
                            div(class : 'panel panel-default') {
                                div(class : 'panel panel-heading') {
                                    h3(class : 'panel-title') {
                                        span messages.title
                                        span messages.date
                                        span messages.user
                                        a(href: '/notice/modify/'+ messages.id, '수정')
                                        a(href: '/notice/delete/'+ messages.id, '삭제')
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
        }

