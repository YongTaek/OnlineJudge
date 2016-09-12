layout 'layout.tpl', title: 'MyTeam', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/myTeamList.css')
        },
        content: contents {
            div(class: 'container') {
                form(id: 'search-form', action:'.',class:'form-inline') {
                    div(class: 'head-container') {
                        ul(class: 'nav nav-pills head-item main') {
                            li(class: 'active', )
                        }
                        div(class: 'head-item') {
                            input(type = "text", class: 'form-control', id: 'exampleInputAmount', name: 'search')
                        }
                        div (class: 'head-item') {
                            button(type: "submit", id: 'search-button', class: "btn btn-primary push-left", '검색')
                        }
                    }
                }

                div(id: 'problem-container') {
                    table(class: 'table table-striped') {
                        thead {
                            tr {
                                th(class: 'center', '팀 이름')
                                th(class: 'center', '팀 장')
                                th(class: 'center', '출전 대회')
                            }
                        }
                        tbody {
                            if (messages.empty) {
                                tr { td(colspan: '4', 'No Team') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td(class: 'center', message.name)
                                        td(class: 'center', message.admin)
                                        td(class: 'center') {
                                            a(href: '/contest/'+message.contest_id, message.contest)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }