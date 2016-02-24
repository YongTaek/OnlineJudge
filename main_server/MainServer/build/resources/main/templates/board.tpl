layout 'test.tpl', title: '게시판',
        content: contents {
            div(class: 'container') {
                div(class: 'prob-nav') {
                    input(type: 'button', value : '공지')
                    input(type: 'button', value : 'Q&A')
                    form(id: 'search-form', action = '', method = 'post') {
                        input(type: 'text', value: '', name: 'search')
                        input(type: 'button', value: '검색')
                    }
                }
            }
        }