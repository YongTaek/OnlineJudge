layout 'contest.tpl', loginUser: loginUser, title: '대회 만들기',
        custom_head: contents {
        },
        content: contents {
            label(id: 'result', class:'hidden') {}
            table(class: 'table table-striped') {
                thead {
                    tr {
                        th(class: 'center', '선택')
                        th(class: 'center', id: 'require-set', '이름')
                    }
                }
                tbody() {
                    if (addAdmins.empty) {
                        tr { td(colspan: '100%', 'No required problem set') }
                    } else {
                        if (!addAdmins.empty) {
                            addAdmins.each { addAdmin ->
                                tr {
                                    td(class: 'center') {
                                        button(name:'addAdminBtn',type: 'button',value:addAdmin.id, class: 'btn btn-primary btn-custom', '추가')
                                    }
                                    td(class: 'center', addAdmin.name)
                                }
                            }
                        }
                    }
                }
            }

            div(class: 'prob-nav') {
                ul(class: 'pagination') {
                    if (pages.empty) {

                    } else {
                        pages.each { page ->
                            li {
                                a(href: '?page='+(page-1), page)
                            }

                        }
                    }
                }
            }
            button(class: 'btn btn-primary btn-complete', id: 'button', '완료')
            script(type: 'text/javascript', src: '/js/contestCreateInsertAdmins.js') {}
        }