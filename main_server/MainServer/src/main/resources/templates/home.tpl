layout 'layout.tpl', title: 'OnlineJudge',
        content: contents {
            div(id: 'backimg') {
                    ul(class:"loginbar") {
                        li(class:'lo') {
                            a(class: 'loandjo', 'Join-in',onclick:"hidesection()")
                        }
                        li(class:'lo') {
                            a(class: 'loandjo', 'Login',onclick: "viewsection()")
                        }
                    }
                }
            }