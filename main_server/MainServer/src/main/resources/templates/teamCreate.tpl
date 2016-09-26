layout 'layout.tpl', title: '팀 만들기', loginUser: loginUser, user_id : user_id,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/groupCreate.css')
        },
        content: contents {
            div(id: 'content') {
                div(id: 'info-container') {
                    h1(class: 'title', "Team")
                    p(class: 'sub-title', "팀 만들기")
                }
                div(id: 'login-form-container') {
                    h1(id: 'form-title', class: 'title push-bottom', "Team")
                    form(id: 'login-form', action: '/contest/team/create/'+messages.contest_id, method: 'post') {
                        div(class: 'form-group') {
                            label(for: 'group_name', '팀 이름')
                            input(type: 'text', name: 'team_name', class: 'form-control')
                        }
                        button(type: 'submit', class: 'btn', "대회 참가하기")
                    }
                }
            }
            script(src: "/js/login.js") {}
        }