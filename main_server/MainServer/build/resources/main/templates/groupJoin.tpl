layout 'layout.tpl', title: '그룹 가입 신청', loginUser: loginUser,
        content: contents {

            div(class: 'prob-container') {
                form(id: 'write_question', action: '/group/join', method: 'post') {
                    \

                    h2 'Group - 가입 신청'
                    div(class: 'col-md-12') {
                        span '한 마디'
                    }
                    div(class: 'col-md-12') {
                        input(type: 'text', value: '', name: 'comment', size: '40', style: "height : 100px;, id : 'comment")
                    }
                    div(class: 'col-md-12') {
                        div(class: 'row') {
                            button(type: "submit", id: "postButton", class: "btn btn-primary", '신청하기')
                            a(href: '/group', class: "btn btn-primary", '취소')
                        }
                    }
                }
            }

        }