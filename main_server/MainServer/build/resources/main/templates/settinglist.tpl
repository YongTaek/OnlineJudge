layout 'layout.tpl', title: '마이페이지',loginUser:loginUser,
        content: contents {
            link(rel: 'stylesheet', href: '/css/table.css')
            div(class: 'container') {
                div(class : 'row') {
                    div(class: 'col-md-12') {
                        p() {
                            span(style: 'font-size : 35', messages.user_id)
                            span(messages.user_name)
                        }
                    }
                }
                div(class: 'row') {
                    div(class: 'col-md-8 col-xs-12 col-sm-6') {
                        ul(class: 'nav nav-tabs') {
                            li() {
                                a(href: '/myPage', '활동')
                            }
                            li(class : 'active') {
                                a(href: '/myPage/setting', '설정')
                            }
                        }

                    }
                }
                hr(class : 'half-rule')
                div(class : 'col-md-4'){
                    ul(class : 'list-group sidebar-nav-v1'){
                        li(class : 'list-group-item active'){
                            a(href: '/mypage/setting', style : "color: rgb(255,255,255)",'정보 변경')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/mypage/setting/password', '비밀번호 변경')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/mypage/setting/withdrawal', '회원 탈퇴')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/mypage/setting/group', '그룹 설정')
                        }
                        li(class : 'list-group-item'){
                            a(href: '/mypage/setting/team', '팀 설정')
                        }
                    }
                }
                form(id : 'modify_info', action:'/mypage/setting', method:'post') {
                    div(class: 'col-md-8') {
                        div(class: 'form-group row') {
                            div(class: 'col-md-3') {
                                label(class : 'right', "이름")
                            }
                            div(class: 'col-md-9') {
                                input(type: 'text', value: messages.name, name: 'user_name', id: 'name')
                            }
                        }
                        div(class: 'from-group row') {
                            div(class: 'col-md-3') {
                                label( "이메일")
                            }
                            div(class: 'col-md-9') {
                                input(type: 'text', value: messages.email, name: 'user_email', id: 'email')
                            }
                        }
                        button(type : "submit", id : "postButton", class : "btn btn-primary", '수정')
                    }
                }
            }
        }