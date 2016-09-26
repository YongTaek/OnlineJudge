layout 'myPageLayout.tpl',user_loginid:user_loginid,group:group,loginUser:loginUser,user_id : user_id,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/setting.css')
        },
        content: contents {
            div(class: 'col-md-4') {
                ul(class: 'list-group sidebar-nav-v1') {
                    li(id: 'info', class: 'list-group-item active') {
                        a(href: '/myPage/setting', style: "color: rgb(255,255,255)", '정보 변경')
                    }
                    li(id: 'password', class: 'list-group-item') {
                        a(href: '/myPage/setting/password', '비밀번호 변경')
                    }
                    li(id: 'withdrawal', class: 'list-group-item') {
                        a(href: '/myPage/setting/withdrawal', '회원 탈퇴')
                    }
                    li(class: 'list-group-item') {
                        a(href: '/group/list', '그룹 설정')
                    }
                    li(class: 'list-group-item') {
                        a(href: '/myPage/setting/team', '팀 설정')
                    }
                }
            }
            content()
        }
