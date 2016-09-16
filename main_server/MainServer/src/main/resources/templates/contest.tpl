layout 'layout.tpl', loginUser:loginUser,title:title,
        custom_head: contents {
          custom_head()
        },
        content: contents{
            div(class:'container') {
                content()
            }
        }