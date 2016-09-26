layout 'layout.tpl', loginUser: loginUser,title:'대회 만들기', user_id : user_id,
        custom_head: contents {
            link(rel:'stylesheet', href:'//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css')
            script(src: 'http://yui.yahooapis.com/3.18.1/build/yui/yui-min.js'){}
            script(src: '//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js'){}
            link(rel: 'stylesheet', href: '/css/createContest.css')

        },
        content: contents {
            form(id: 'write_problem', action: '/contest/create', method: 'post', class: 'write_form') {
                p(class: 'text', '대회 제목')
                input(class: 'input', type: 'text', value: '', name: 'contest_title', id: 'title', 'required':true)
                div(class : 'inrow') {
                    div(class : 'inrow marginRight') {
                        p(class: 'text', '대회 시작 날짜')
                        input(class: 'time ui-timepicker-input input margin', type: 'text', value: '', name: 'contest_start_date', id: 'startdate', 'required':true)
                        input(class: 'time ui-timepicker-input input', type: 'text', value: '', name: 'contest_start_time', id: 'starttime', 'required':true)

                        div(id: 'start-date', class: 'yui3-skin-sam yui3-g') {}
                    }
                    div(class: 'inrow') {
                        p(class: 'text', '대회 종료 날짜')
                        input(class: 'input margin', type: 'text', value: '', name: 'contest_end_date', id: 'enddate', 'required':true)
                        input(class: 'input', type: 'text', value: '', name: 'contest_end_time', id: 'endtime', 'required':true)

                        div(id: 'end-date', class: 'yui3-skin-sam yui3-g') {}
                    }
                }
//                p(class : 'text', '참가 인원 수')
//                input(class : 'input', id : 'numberPerson', name : 'number_person',type: 'text')

//                p(class: 'text', '대회 운영진')
//                span(id: 'admins'){}
//                input(id:'admin-list', name: 'admins', readonly:true){}
//                button(class: 'btn btn-primary',type:'button', value: "", name: 'admin-list', id: 'admin-input','추가')
                p(class: 'text', '대회 문제집(추후 설정 가능)')
                input(id:'set-list', class: 'input margin',name:'problemset',readonly:true){}
                button(class: 'btn btn-primary',type:'button', value: "", name: 'problemset', id: 'problemset','추가')
                br()
                button(type: "submit", id: "postButton", class: "btn btn-primary", '올리기')
            }
            script(type: 'text/javascript', src: '/js/contestCreate.js') {}
        }