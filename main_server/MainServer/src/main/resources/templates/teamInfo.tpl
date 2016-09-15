layout 'layout.tpl', title: '문제', loginUser: loginUser, page:list,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
            link(rel: 'stylesheet', href: '/css/myPage.css')
            link(rel: 'stylesheet', type: 'text/css', href: '/css/teaminfo.css')
        },
        content: contents{
            div(class:"container"){
                div(class: "row"){
                    div(class: "col-md-12"){
                        p(class: "team-name"){
                            span(style: 'font-size : 35', "TeamName")
                            span("ContestName")
                        }
                    }
                }
                div(class : 'col-md-4') {
                    div(id: 'team-member-table') {
                        table(class: 'table table-striped') {
                            thead() {
                                tr {
                                    th(class: 'center', '팀 원')
                                    th(class: 'center', '삭 제')
                                }
                            }
                            tbody() {
                                if (members.empty) {
                                    tr { td(colspan: '100%', 'No Team Members') }
                                } else {
                                    members.each { members ->
                                        tr(item_id: members.name) {
                                            td(class: 'center', members.name)
                                            td(class: 'center', 'x')
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                    div(class : 'col-md-8'){
                        div(class : 'panel panel-default') {
                            div(class: 'panel panel-heading') {
                                h3(class: 'panel-title') {
                                    span '대회 결과'
                                }
                            }
                            div(class: 'panel-body'){
                                table(class: "grade-contest"){
                                    tbody(){
                                        if(members.empty){
                                            tr{td(colspan: '100%', "can't get result")}
                                        }
                                        messages.each{ message ->
                                            tr(){
                                                td()
                                            }
                                        }
                                    }
                                }
                            }
                            }
                        }
                }
        }