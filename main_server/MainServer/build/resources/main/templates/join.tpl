layout 'layout.tpl', title: '회원가입',
        content: contents {
            div(class: 'container') {
                form(class: "join",action:'/join',method:'post') {
                    p(class:'jointext'){
                        yield "회원가입"
                    }
                    div(class: "join-group") {
                        label(for: "username", class: "control-label join-item") {
                            yield "이름"
                        }
                        div(class: "col-sm-10") {
                            input(type: "text", class: "form-control", id: "inputEmail3",name:'name')
                        }
                    }
                    div(class: "join-group"){
                        label(for: "inputEmail3", class: "control-label join-item") {
                            yield "E-Mail"
                        }
                        div(class: "col-sm-10") {
                            input(type: "email", class: "form-control", id: "inputEmail3",name:'email')
                        }
                    }
                    div(class: "join-group"){
                        label(for: "inputEmail3", class: "control-label join-item") {
                            yield "ID"
                        }
                        div(class: "col-sm-10") {
                            input(type: "id", class: "form-control", id: "inputEmail3",name: 'login_id')
                        }
                    }
                    div(class: "join-group"){
                        label(for: "inputPassword3", class: "control-label join-item") {
                            yield "Password"
                        }
                        div(class: "col-sm-10") {
                            input(type: "password", class: "form-control", id: "inputPassword3",name: 'login_pw')
                        }
                    }
                div(class: "join-bottom") {
                    div{
                        button(type:"button", class:"btn btn-default join-button1"){
                            yield "Cancel"
                        }
                        button(type: "submit", class: "btn btn-default join-button2") {
                            yield "Submit"
                        }
                    }
                }
            }
        }
}