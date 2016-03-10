layout 'test.tpl', title: '질문하기',
        content: contents {
            link(rel:'stylesheet', href: '/css/table.css')
            div(class: 'container') {
                div(class:'row') {
                    div(class: 'col-md-8 col-xs-12 col-sm-6') {
                        ul(class: 'nav nav-pills') {
                            li(class: 'active') {
                                a(href: '/notice', '공지사항')
                            }
                            li() {
                                a(href: '/question', 'Q&A')
                            }
                        }

                    }
                    div(class: 'col-md-4') {
                        form(class: "form-inline",id: 'searchForm') {
                            div(class: "form-group") {
                                div(class: 'input-group') {
                                    input(type = "text", class: 'form-control', id: 'exampleInputAmount',name:'search')
                                }
                            }
                            button(type: "submit",id: 'searchButton' , class: "btn btn-primary", '검색')

                        }
                    }
                }

                div(class: 'prob-container') {

                    div(class: 'col-md-12') {
                        span '제목'
                        input(type: 'text', value: '', name: 'question_title');
                    }
                    div(class: 'col-md-12') {
                        span '질문 문제'
                        input(type: 'text', value: '', name: 'question_problem')
                    }
                    div(class: 'col-md-12') {
                        span ' 내용'
                    }
                    div() {
                        input(tyoe: 'text', value: '', name: 'qustion_contents')
                    }
                    div(class: 'col-md-12') {
                        div(class: 'row') {
                            form(id: 'write_question', action = '', method = 'post') {
                                a(href: '/question', '올리기')
                            }
                            a(href: '/question', '취소')
                        }
                    }
                }
            }
        }