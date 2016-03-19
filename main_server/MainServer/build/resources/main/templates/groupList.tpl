layout 'test.tpl', title: '게시판',
        content: contents {
            link(rel: 'stylesheet', href: '/css/table.css')
            div(class: 'container') {
                div(class: 'row') {
                    div(class: 'col-md-8 col-xs-12 col-sm-6') {
                        ul(class: 'nav nav-pills') {
                            li(class: 'active') {
                                a(href: '/group', 'Group 목록')
                            }
                            li() {
                                a(href: '/group/my', '내 Group')
                            }
                        }

                    }
                    div(class: 'col-md-4') {
                        form(class: "form-inline", id: 'searchForm') {
                            div(class: "form-group") {
                                div(class: 'input-group') {
                                    input(type = "text", class: 'form-control', id: 'exampleInputAmount', name: 'search')
                                }
                            }
                            button(type: "submit", id: 'searchButton', class: "btn btn-primary", '검색')
                            a(href: '/group/create', '그룹 만들기')
                        }
                    }
                }
                div(class: 'prob-container') {
                    table(class: 'table table-striped') {
                        thead {
                            tr {
                                td (class : 'common-table','번호')
                                td (class : 'common-table','그룹명')
                                td (class : 'common-table','그룹장')
                                td (class : 'common-table','가입 제한 여부')
                                td (class : 'common-table','가입신청')
                            }
                        }
                        tbody {
                            if (messages.empty) {
                                tr { td(colspan: '5', 'No Problem') }
                            } else {
                                messages.each { message ->
                                    tr {
                                        td (class : 'common-table',message.id)
                                        td (class : 'common-table'){
                                            a(href: '/group/' + message.id, message.name)
                                        }
                                        td (class : 'common-table', message.user)
                                        td (class : 'common-table', message.isprivate)
                                        td(class: 'common-table'){
                                            a(href: '/group/join/'+ message.id, "가입 신청")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }