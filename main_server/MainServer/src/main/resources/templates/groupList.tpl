layout 'group.tpl', title: '게시판', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
        },
        tables: contents {
            div(class: 'prob-container') {
                table(class: 'table table-striped') {
                    thead {
                        tr {
                            td(class: 'center', '번호')
                            td(class: 'center', '그룹명')
                            td(class: 'center', '그룹장')
                            td(class: 'center', '가입 제한 여부')
                            td(class: 'center', '가입신청')
                        }
                    }
                    tbody {
                        if (messages.empty) {
                            tr { td(colspan: '5', 'No Problem') }
                        } else {
                            messages.each { message ->
                                tr {
                                    td(class: 'center', message.id)
                                    td(class: 'center') {
                                        a(href: '/group/info'+message.id, message.name)
                                    }
                                    td(class: 'center', message.user)
                                    td(class: 'center', message.isprivate)

                                    td(class: 'center') {
                                        if (!message.isMyGroup) {
                                            a(href: '/group/join/'+message.id, "가입 신청")
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }