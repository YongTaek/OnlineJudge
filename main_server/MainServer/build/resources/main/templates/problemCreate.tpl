layout 'myPageLayout.tpl', title: '문제만들기', loginUser: loginUser,user_id:messages.user_id,group:group,
        custom_head: contents {
            link(rel: 'stylesheet', href: '/css/createProblem.css')
            script(type: 'text/javascript', src: '/SE2.1.3.O8706/js/HuskyEZCreator.js', charset = "utf-8") {}

//            link(rel: 'stylesheet', href: '/css/table.css')
        },
        content: contents {

                br()
                form(id : 'write_problem', action:'/problem/create', method:'post', class: 'write_form') {
                    p(class: 'text', '문제 제목')
                    input(class: 'input', type: 'text', value: '', name: 'problem_title', id: 'title')

                    p(class: 'text', '문제 내용')
                    textarea(class: 'form-control', id: 'content', style: 'width 150px height 400px', name: 'problem-contents', type: 'submit') {}
                    script(type = "text/javascript", src: '/js/smartEditor.js') {}
                    br()

                    p(class: 'text', '공개 여부')
                    input(type: 'radio', name : 'isOpen', value: 'O', checked: 'checked')
                    label(class : 'forCheckBox', 'O')

                    input(type: 'radio', name : 'isOpen', value: 'X')
                    label(class : 'forCheckBox', 'X')

                    p(class: 'text', '시간 제한')
                    input(class: 'input',type: 'text', value: '', name: 'problem_timeLimit', id: 'time')

                    p(class: 'text', '메모리 제한')
                    input(class: 'input', type: 'text', value: '', name: 'problem_memoryLimit', id: 'memory')

                    p(class: 'text', '보이는 input값')
                    input(class: 'input', type: 'text', value: "", name: 'problem_visibleInput', id: 'visibleinput')

                    p(class: 'text', '보이는 output값')
                    input(class: 'input', type: 'text', value: "", name: 'problem_visibleOutput', id: 'visibleoutput')
                    div(class: 'row col-md-5 testcaseButton') {
                        p(class: 'text', "테스트케이스")
                        p('input값', id: 'testInput')
                    }
                input(type: 'hidden', name: 'testcase_count', id: 'testcase_count')
                div() {
                    button(id: "plusButton1", type: 'button', class: "btn btn-primary plusButton", '+')
                    button(id: "minusButton", type: 'button', class: "btn btn-primary", '-')
                    p(class: 'text-left', 'output값', id: 'testOutput')
                }
                button(type: "submit", id: "postButton", class: "btn btn-primary", '올리기')
            }

            script(type: 'text/javascript', src: '../js/createProblem.js') {}
        }
