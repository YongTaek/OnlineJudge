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
                        li(class : 'list-group-item'){
                            a(href: '/myPage/setting', '정보 변경')
                        }
                        li(class : 'list-group-item active'){
                            a(href: '/myPage/setting/password', style : "color: rgb(255,255,255)", '비밀번호 변경')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/myPage/setting/withdrawal', '회원 탈퇴')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/myPage/setting/group', '그룹 설정')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/myPage/setting/team', '팀 설정')
                        }
                    }
                }
                div(class : 'col-md-8') {
                    form(id: 'modify_info', action: '/myPage/setting/password', method: 'post') {
                        div(class: 'form-group row') {
                            div(class: 'col-md-3 margin') {
                                p("기존 비밀번호")
                            }
                            div(class: 'col-md-9 margin') {
                                input(type: 'password', value: "", name: 'origin_password', id: 'origin_password')
                            }
                        }
                        div(class: 'from-group row') {
                            div(class: 'col-md-3 margin') {
                                p("새 비밀번호")
                            }
                            div(class: 'col-md-9 margin') {
                                input(type: 'password', value: "", name: 'new_password', id: 'new_password')
                            }
                        }
                        div(class: 'form-group row'){
                            div(class : 'col-md-3 margin'){
                                p("새 비밀번호 재입력")
                            }
                            div(class: 'col-md-9 margin') {
                                input(type: 'password', value: "", name: 'new_password1', id: 'new_password1')
                            }
                        }
                        button(type: "submit", id: "postButton", class: "btn btn-primary", '수정')
                    }
                }
            }
        }