layout 'myPageLayout.tpl',user_loginid:user_loginid,group:group,loginUser:loginUser,user_id : user_id,isMe:isMe,user_name:user_name,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/setting.css')
        },
        content: contents {
            div(class: 'col-md-4') {
                ul(class: 'list-group sidebar-nav-v1') {
                    li(id: 'info', class: 'list-group-item active') {
                        a(href: '/user/setting', style: "color: rgb(255,255,255)", '정보 변경')
                    }
                    li(id: 'password', class: 'list-group-item') {
                        a(href: '/user/setting/password', '비밀번호 변경')
                    }
                    li(id: 'withdrawal', class: 'list-group-item') {
                        a(href: '/user/setting/withdrawal', '회원 탈퇴')
                    }
                    li(class: 'list-group-item') {
                        a(href: '/group/list', '그룹 설정')
                    }
                    li(class: 'list-group-item') {
                        a(href: '/contest/team/myTeam/list', '팀 설정')
                    }
                }
            }
            content()
            script(src:'/js/mySetting.js')
        }
