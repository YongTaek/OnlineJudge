layout 'contest.tpl', title: '문제', loginUser: loginUser, page:list, user_id:user_id,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
            link(rel: 'stylesheet', type: 'text/css', href: '/css/allContest.css')
        },
        content: contents {
            div(id: 'problem-container') {
                table(class: 'table table-striped') {
                    thead() {
                        tr {
                            th(class: 'center', '대회')
                            th(class: 'center', '우승')
                            th(class: 'center', '준우승')
                            th(class: 'center', '대회 시작')
                            th(class: 'center', '주최자')
                            th(class: 'center', '참가하기')
                        }
                    }
                    tbody() {
                        if (messages.empty) {
                            tr { td(colspan: '100%', 'No Contents') }
                        } else {
                            messages.each { message ->
                                if(message.isgoing){
                                    tr(class: "ongoing",item_id: message.id) {
                                        td() {
                                            a(href: "/contest/info/${message.id}", message.name)
                                            if(message.isjoin){
                                                span(class:"isjoin","참가")
                                            }
                                        }
                                        if(message.winner !=null){
                                            td(class: 'center', message.winner)
                                        }
                                        else{
                                            td(class: 'center')
                                        }
                                        if(message.subwinner !=null){
                                            td(class: 'center', message.subwinner)
                                        }
                                        else{
                                            td(class: 'center')
                                        }
                                        td(class: 'center',message.startTime)
                                        td(class: 'center',admin)
                                        td(class: 'center') {
                                            if(!message.isjoin) {
                                                a(class: "btn-custom btn btn-primary",href: "/contest/join/${message.id}", '참가하기')
                                            }
                                        }
                                    }
                                }
                                else{
                                    tr(item_id: message.id) {
                                        td() {
                                            a(href: "/contest/info/${message.id}", message.name)
                                            if(message.isjoin){
                                                span(class:"isjoin","참가")
                                            }
                                        }
                                        if(message.winner !=null){
                                            td(class: 'center', message.winner)
                                        }
                                        else{
                                            td(class: 'center')
                                        }
                                        if(message.subwinner !=null){
                                            td(class: 'center', message.subwinner)
                                        }
                                        else{
                                            td(class: 'center')
                                        }
                                        td(class: 'center',message.startTime)
                                        td(class: 'center',admin)
                                        td(class: 'center')
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
        }
