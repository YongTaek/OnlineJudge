layout 'layout.tpl',title:'문제집 수정',loginUser:loginUser,userGroup:userGroup,
        custom_head:contents{
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
            link(rel: 'stylesheet', type: 'text/css', href: '/css/requiredProblemset.css')
        },
        content:contents{
            div(class:'container'){
                table(class: 'table table-striped') {
                    thead {
                        tr {
                            th(class: 'center', id: 'require-set', '이름')
                            th(class: 'center', id: 'set-rate', '문제 갯수')
                            th(class: 'center', '설정')
                        }
                    }
                    tbody() {
                        if (addProblemsets.empty && removeProblemsets.empty) {
                            tr { td(colspan: '100%', 'No required problem set') }
                        } else {
                            if(!addProblemsets.empty) {
                                addProblemsets.each { addProblemset ->
                                    tr {
                                        td(class: 'center', addProblemset.name)
                                        td(class: 'center', addProblemset.count)
                                        td(class: 'centers') {
                                            form(class: 'form-inline', action: '/group/setting/requiredProblem', method: 'post') {
                                                input(type: 'hidden', name: 'id', addProblemset.id)
                                                button(class: 'btn btn-primary btn-custom', type: 'submit', '강퇴')
                                            }
                                        }
                                    }
                                }
                            }
                            if(!removeProblemsets.empty) {
                                removeProblemsets.each { removeProblemset ->
                                    tr {
                                        td(class: 'center', removeProblemset.name)
                                        td(class: 'center', removeProblemset.count)
                                        td(class: 'centers') {
                                            form(class: 'form-inline', action: '/group/setting/requiredProblem', method: 'post') {
                                                input(type: 'hidden', name: 'id', removeProblemset.id)
                                                button(class: 'btn btn-primary btn-custom', type: 'submit', '강퇴')
                                            }
                                        }
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
                button(class:'btn btn-primary btn-complete',id: 'button', '완료')
            }
            script(src:'/js/RequiredProblemset.js'){}
        }