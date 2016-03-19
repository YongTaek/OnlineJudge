layout 'test.tpl', title: '그룹 만들기',
        content: contents {
            div(class: 'container') {
                div(class: 'prob-container') {
                    form(id : 'write_question', action:'/group/create', method:'post') {\
                        h2 'Group - 만들기'
                        div(class: 'col-md-12') {
                            span '그룹 이름'
                            input(type: 'text', value: '', name: 'group_name', id : 'group_name')
                        }
                        div(class: 'col-md-12') {
                            span '가입 제한 여부'
                        }
                        div(class: 'col-md-12') {
                            div(class : 'row'){
                                input(type: 'radio', name : 'isprivate', id : 'group_private', value : 1,'O')
                                input(type: 'radio', name : 'isprivate', id : 'group_public', value : 2,'X')

                            }
                        }
                        div(class: 'col-md-12') {
                            div(class: 'row') {
                                button(type : "submit", id : "postButton", class : "btn btn-primary", '만들기')
                                a(href : '/group',class:"btn btn-primary", '취소')
                            }
                        }
                    }
                }
            }
        }