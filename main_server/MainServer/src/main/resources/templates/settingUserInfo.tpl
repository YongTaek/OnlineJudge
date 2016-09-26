layout 'settinglist.tpl', loginUser: loginUser, user_id: user_id, group: group, user_loginid:messages.user_loginid,isMe:messages.isMe,
        content: contents {
            div(class: 'col-md-8') {
                form(id: 'modify_info', action: '/myPage/setting', method: 'post') {
                    div(class: 'form-group') {
                        div(class: 'row') {
                            div(class: 'col-md-3 margin') {
                                p("이름")
                                p("이메일")
                            }
                            div(class: 'col-md-5 margin') {
                                input(type: 'text', value: messages.name, name: 'user_name', id: 'name')
                                input(type: 'text', value: messages.email, name: 'user_email', id: 'email')
                            }
                        }

                        button(type: "submit", id: "postButton", class: "btn btn-primary", '수정')
                    }
                }
            }
        }