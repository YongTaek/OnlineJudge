layout 'layout.tpl', title: '마이페이지', loginUser: loginUser,
        custom_head: contents {
            custom_head()
        },
        content: contents {
            div(class: 'container') {
                div(class: 'row') {
                    div() {
                        p(class: 'username') {
                            span(style: 'font-size : 35', user_id)
                            span(loginUser)
                        }
                    }
                }
                div(class: 'row') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(id:'myActivity', class: 'active') {
                                a(href: '/myPage', '활동')
                            }
                            li(id:'setting') {
                                a(href: '/myPage/setting', '설정')
                            }
                            li(id: 'createProblem') {
                                a(href: '/problem/create', '문제 만들기')
                            }
                            if (!group) {
                                li(id: 'group') {
                                    a(href: '/group/list', '그룹')
                                }
                                li(id: 'groupCreate') {
                                    a(href: '/group/create', '그룹 만들기')
                                }
                            }
                        }

                    }
                }
                br()
                content()
            }
            script(src:'/js/myPageNav.js'){}
        }