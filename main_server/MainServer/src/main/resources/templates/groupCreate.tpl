layout 'layout.tpl', title: '그룹 만들기', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/groupCreate.css')
        },
        content: contents {
            div(id: 'content') {
                div(id: 'info-container') {
                    h1(class: 'title', "Group")
                    p(class: 'sub-title', "Group create")
                }
                div(id: 'login-form-container') {
                    h1(id: 'form-title', class: 'title push-bottom', "Group")
                    form(id: 'login-form', action: '/group/create', method: 'post') {
                        div(class: 'form-group') {
                            label(for: 'group_name', '그룹 이름')
                            input(type: 'text', name: 'group_name', class: 'form-control')
                        }
                        div(class: 'form-group') {
                            label(for: 'group_private', '가입 제한 여부')
                            div(class: 'radio') {
                                input(type: 'radio', name: 'isprivate', id: 'group_private', value: 1)
                                p('O')


                                input(type: 'radio', name: 'isprivate', id: 'group_public', value: 2)
                                p('X')
                            }
                        }
                        button(type: 'submit', class: 'btn', "Create")
                    }
                }
            }
            script(src: "/js/login.js") {}
        }