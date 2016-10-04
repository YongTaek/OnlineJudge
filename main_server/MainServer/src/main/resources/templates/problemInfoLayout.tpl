layout 'layout.tpl', title:'문제',loginUser:loginUser, user_id: user_id,
        custom_head: contents {
            custom_head()
        },
        content: contents {
            div('container') {
                div(class: 'head-container') {
                    ul(class: 'nav nav-pills head-item main') {
                        li(class: 'active',id: 'problem-num') {
                            a(href: '#', num)
                        }
                        li(id: 'problem-submit') {
                            a(href: 'submit/'+num, '제출하기')
                        }
                        li(id: 'grade-now') {
                            a(href: '#', '채점 현황')
                        }
                        li(id: 'grade-now-me') {
                            a(href: '#', '내 채점현황')
                        }
                        li(id: 'q&a') {
                            a(href: '/board/question/'+num, 'Q&A')
                        }
                    }
                }
                content()
            }
        }