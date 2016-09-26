layout 'settinglist.tpl', loginUser: loginUser, user_id: user_id, group: group,isMe:messages.isMe, user_loginid:messages.user_loginid,user_name:messages.user_name,

        content: contents {
            div(class: 'col-md-8') {
                form(id: 'modify_info', action: '/myPage/setting/withdrawal', method: 'post') {
                    p(class: 'lead text-left', "정말로 탈퇴하시겠습니까?")
                    button(type: "submit", id: "postButton", class: "btn btn-primary", '탈퇴')
                }
            }
        }
