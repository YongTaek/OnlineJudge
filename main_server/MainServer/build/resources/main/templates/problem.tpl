layout 'test.tpl', title: '문제',
        content: contents {
            div(class:'container'){
                div(class:'prob-nav'){
                    ul(){
                        li '문제'
                        li '추가된 문제'
                        li '문제순위'
                        li '기타'
                    }
                    form(id:'search-form', action='', method='post'){
                        input(type:'text', value:'', name:'search')
                        input(type:'button', value:'검색')
                    }
                }
                div(class:'prob-container'){
                    table(class:'prob-table'){
                        thead {
                            tr {
                                td '문제번호'
                                td '제목'
                                td '정답 수'
                                td '정답 비율'
                                td 'O/X'
                            }
                        }
                        tbody {
                            if(problems.empty) { tr {td(colspan:'5', 'No Problem')}}
                            else {
                                problems.each { problem ->
                                    tr {
                                        td problem.id
                                        td problem.name
                                        td problem.count
                                        td problem.rate
                                        td problem.result
                                    }
                                }
                            }
                        }
                    }
                }
                div(class:'prob-footer'){

                }
            }
        }