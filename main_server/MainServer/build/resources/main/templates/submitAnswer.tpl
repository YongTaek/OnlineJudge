layout 'layout.tpl', title: '문제', loginUser: loginUser, user_id : user_id,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/submitAnswer.css')
            link(rel: 'stylesheet', href: '/css/codemirror.css')
            link(rel: 'stylesheet', href: '/css/createProblem.css')
            script(src: '/js/codemirror.js') {}
            script(type: 'text/javascript', src: '/mode/javascript/javascript.js'){}
            script(src: '/mode/xml/xml.js'){}
            script(src: '/js/submitAnswer.js') {}
        },

        content: contents {
            div(class: 'container') {
                div(class: 'header'){
                    ul(class: 'nav nav-pills head-item main') {
                        li(id: 'problem-num') {
                            a(href: '#', problem.id)
                        }
                        li(class: 'active', id: 'problem-submit') {
                            a(href: 'submit/'+problem.id, '제출하기')
                        }
                        li(id: 'grade-now') {
                            a(href: '#', '채점 현황')
                        }
                        li(id: 'grade-now-me') {
                            a(href: '#', '내 채점현황')
                        }
                        li(id: 'q&a') {
                            a(href: '/board/question/'+problem.id, 'Q&A')
                        }
                    }
                }
                form(id: 'submit-answer-form',action:'/problem/submit', class: 'form-horizontal',method:'post') {
                    legend problem.name
                    input(type: 'hidden', value: problem.id, name: 'problem_id')
                    div(class: 'form-group') {
                        label(class: 'col-md-2 control-label', for: 'language', '언어')
                        select (name:'language') {
                            option(value: 'C',selected:'selected', 'C')
                            option(value: 'java', 'Java')
                        }
                    }
                    div(class: 'form-group') {
                        label(class: 'col-md-2 control-label', for: 'source', '소스 코드')
                        div(class: 'col-md-10') {
                            textarea(id: 'code', autofocus: 'autofocus', name:'code',class: 'code-java'){}
                            button(type: "submit", id: "postButton", class: "submitAns btn btn-primary", '제출하기')
                        }
                    }

                }
            }
        }