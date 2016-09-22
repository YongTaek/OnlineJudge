layout 'layout.tpl', loginUser: loginUser, title: '대회 만들기',
        custom_head: contents {
        },
        content: contents {
            label(id: 'result') {}
            table(class: 'table table-striped') {
                thead {
                    tr {
                        th(class: 'center', '선택')
                        th(class: 'center', id: 'require-set', '이름')
                        th(class: 'center', id: 'set-count', '문제 갯수')
                    }
                }
                tbody() {
                    if (addProblemSets.empty) {
                        tr { td(colspan: '100%', 'No required problem set') }
                    } else {
                        addProblemSets.each { addProblemSet ->
                            tr {
                                td(class: 'center') {
                                    input(type: 'radio', name:'problemset',value:addProblemSet.id,class: 'btn btn-primary btn-custom', '추가')
                                }
                                td(class: 'center', addProblemSet.name)
                                td(class: 'center', addProblemSet.count)
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
            script(type: 'text/javascript', src: '/js/contestCreateInsertSet.js') {}
        }