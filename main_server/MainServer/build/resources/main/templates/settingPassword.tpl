layout 'settinglist.tpl', loginUser: loginUser, user_id:user_id, group: group,isMe:messages.isMe,user_loginid:messages.user_loginid,user_name:messages.user_name,
        content: contents {
            div(class: 'col-md-8') {
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
                    div(class: 'form-group row') {
                        div(class: 'col-md-3 margin') {
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
