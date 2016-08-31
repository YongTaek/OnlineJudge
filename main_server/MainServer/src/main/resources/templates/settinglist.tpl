layout 'layout.tpl', title: '마이페이지',loginUser:loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/setting.css')
//            link(rel: 'stylesheet', href: '/css/table.css')
        },
        content: contents {
            div(class: 'container') {
                div(class : 'row') {
                    div(class: 'col-md-12') {
                        p(class : 'username') {
                            span(style: 'font-size : 35', messages.user_id)
                            span(messages.user_name)
                        }
                    }
                }
                div(class: 'row') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li() {
                                a(href: '/myPage', '활동')
                            }
                            li(class: 'active') {
                                a(href: '/myPage/setting', '설정')
                            }
                            li() {
                                a(href: '/problem/create', '문제 만들기')
                            }
                        }

                    }
                }
                br()
                div(class : 'col-md-4'){
                    ul(class : 'list-group sidebar-nav-v1'){
                        li(class : 'list-group-item active'){
                            a(href: '/myPage/setting', style : "color: rgb(255,255,255)",'정보 변경')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/myPage/setting/password', '비밀번호 변경')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/myPage/setting/withdrawal', '회원 탈퇴')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/group/list', '그룹 설정')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/myPage/setting/team', '팀 설정')
                        }
                    }
                }
                div(class : 'col-md-8') {
                    form(id: 'modify_info', action: '/myPage/setting', method: 'post') {
                        div(class: 'form-group') {
                            div(class : 'row') {
                                div(class: 'col-md-3 margin') {
                                    p("이름")
                                    p("이메일")
                                }
<<<<<<< HEAD
                                div(class: 'col-md-5 margin') {
                                    input(type: 'text', value: messages.name, name: 'user_name', id: 'name')
                                    input(type: 'text', value: messages.email, name: 'user_email', id: 'email')
=======
                                div(class: 'col-md-5') {
                                    input(type: 'text', value: messages.name, name: 'name', id: 'name')
                                    input(type: 'text', value: messages.email, name: 'email', id: 'email')
>>>>>>> 3e9c5c9... [수정] 그룹 페이지 수정
                                }
                            }

                            button(type: "submit", id: "postButton", class: "btn btn-primary", '수정')
                        }
                    }
                }
            }
        }