layout 'test.tpl', title: '공지',
        content: contents {
            div(class: 'container') {
                div(class: 'prob-nav') {
                    ul(){
                        li(){
                            a(href : '/notice', '공지사항')
                        }
                        li(){
                            a(href : '/question', 'Q&A')
                        }
                    }
                    form(id: 'search-form', action = '', method = 'post') {
                        input(type: 'text', value: '', name: 'search')
                        input(type: 'button', value: '검색')
                    }
                }
            }
            div(class: 'prob-container') {

                    div(class:'col-md-12'){
                        h2 messages.title
                    }
                    div(class:'col-md-2'){
                        span messages.user
                    }
                    div(class:'col-md-2'){
                        span messages.date
                    }
                    div(class:'col-md-12'){
                        p messages.contents
                    }
            }
        }

