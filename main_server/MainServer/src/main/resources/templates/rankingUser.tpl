layout 'layout.tpl', title: '랭킹', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/rankingUser.css')
        },
        content: contents {
            div(class: 'container') {
                form(id: 'search-form', action:'.',class:'form-inline') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(class: 'active') {
                                a(href: '/ranking/user', '회원')
                            }
                            li() {
                                a(href: '/ranking/group', '그룹')
                            }
                        }
                    }
                }

                div(id: 'problem-container') {
                    table(class: 'table table-striped') {
                        thead {
                            tr {
                                th(class: 'center', '순위')
                                th(class: 'center', '아이디')
                                th(class: 'center', '이름')
                                th(class: 'center', '맞춘 갯수')
                            }
                        }
                        tbody {
                            if (messages.empty) {
                                tr { td(colspan: '4', 'No Ranking') }
                            } else {
                                messages.each { message ->
                                    tr {
//                                        td(class: 'center', message.number)
//                                        td(class: 'center') {
//                                            a(href: '/notice/'+message.number, message.title)
//                                        }
//                                        td(class: 'center', message.user)
//                                        td(class: 'center', message.date)
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }