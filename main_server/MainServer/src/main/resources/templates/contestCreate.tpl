layout 'contest.tpl', loginUser: loginUser,title:'대회 만들기',
        custom_head: contents {
          script(src: 'http://yui.yahooapis.com/3.18.1/build/yui/yui-min.js'){}
        },
        content: contents {

            form(id: 'write_problem', action: '/contest/create', method: 'post', class: 'write_form') {
                p(class: 'text', '대회 제목 제목')
                input(class: 'input', type: 'text', value: '', name: 'contest_title', id: 'title')

                p(class: 'text', '대회 시작 날짜')
                input(class: 'input', type: 'text', value: '', name: 'contest_start_time', id: 'startdate')
                div(id: 'start-date',class:'yui3-skin-sam yui3-g') {}
                p(class: 'text', '대회 종료 날짜')
                input(class: 'input', type: 'text', value: '', name: 'contest_end_time', id: 'enddate')
                div(id: 'end-date',class:'yui3-skin-sam yui3-g') {}

                p(class: 'text', '대회 운영진')
                span(id: 'admins'){}
                input(id:'admin-list', name: 'admins', readonly:true){}
                button(class: 'btn btn-primary',type:'button', value: "", name: 'admin-list', id: 'admin-input','추가')

                p(class: 'text', '대회 문제집(추후 설정 가능)')
                input(id:'set-list', name:'problemset',readonly:true){}
                button(class: 'btn btn-primary',type:'button', value: "", name: 'problemset', id: 'problemset','추가')

                button(type: "submit", id: "postButton", class: "btn btn-primary", '올리기')
            }
            script(type: 'text/javascript', src: '/js/contestCreate.js') {}
        }