layout 'layout.tpl', title: '팀 정보', loginUser: loginUser, page:list, user_id : user_id,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
            link(rel: 'stylesheet', href: '/css/myPage.css')
            link(rel: 'stylesheet', type: 'text/css', href: '/css/teaminfo.css')
        },
        content: contents{
            div(class:"container") {
                div(class: "row") {
                    div(class: "col-md-12") {
                        p(class: "team-name") {
                            span(style: 'font-size : 35', teamname)
                            span(contestname)
                        }
                    }
                }
                div(class: 'col-md-4') {
                    div(id: 'team-member-table') {
                        table(class: 'table table-striped') {
                            thead() {
                                tr {
                                    th(class: 'center', '팀 원')
                                    th(class: 'center', '수락/거절')
                                }
                            }
                            tbody() {
                                tr(item_id: admin) {
                                    td(class: 'center', admin)
                                    td(class: 'center')
                                }
                                members.each { members ->
                                    tr(item_id: members.name) {
                                        td(class: 'center', members.name)
                                        td()
                                    }
                                }
                                if (isadmin && !request.empty) {
                                    request.each { request ->
                                        tr(item_id: request.requestName) {
                                            td(class: 'center', request.requestName)
                                            td(class: 'center') {
                                                form(id: "accept", class: 'form-inline', method: 'post', action: action + "/accept") {
                                                    input(type: 'hidden', name: 'id', value: request.requestId)
                                                    button(class: 'btn btn-primary btn-custom', type: 'submit', '수락')
                                                }
                                                form(id: "reject", class: 'form-inline', method: 'post', action: action + "/reject") {
                                                    input(type: 'hidden', name: 'id', value: request.requestId)
                                                    button(class: 'btn btn-primary btn-custom', type: 'submit', '거절')
                                                }
                                            }
                                        }
                                    }
                                }
                                if (requestperson) {
                                    tr { td(colspan: '100%', "수락을 기다리는 중입니다.") }
                                }
                            }
                        }
                    }
                }
                div(class: 'col-md-8') {
                    div(class: 'panel panel-default') {
                        div(class: 'panel panel-heading') {
                            h3(class: 'panel-title') {
                                span '대회 결과'
                            }
                        }
                        div(class: 'panel-body') {
                            table(class: "grade-contest") {
                                tbody() {
                                    if (results.empty) {
                                        tr { td(colspan: '100%', "can't get result") }
                                    }
                                    int pnumber = 0;
                                    results.each { results ->
                                        tr {
                                            List list = results.list;
                                            list.each {
                                                pnumber++;
                                                td(class: 'center', pnumber);
                                                if (list) {
                                                    td(class: 'center', 'O');
                                                } else {
                                                    td(class: 'center', "X");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!requestperson && !ismember) {
                    button(id:'request',class: 'rightbtn btn btn-primary', '가입 요청')
                    script(src: '/js/teamInfo.js'){}
                }
                if(requestperson){
                    form(id: "cancel", class: 'form-inline', method: 'post', action: action + "/reject") {
                        input(type: 'hidden', name: 'id', value: user_id)
                        button(class: 'btn btn-primary rightbtn', type: 'submit', '요청 취소')
                    }
                }
            }
        }
