layout 'test.tpl', title: '문제', loginUser: loginUser,
        content:contents{
            div(class:'container'){

                link(rel:'stylesheet', href: '/css/submitAnswer.css')
                link(rel:'stylesheet', href: '/css/codemirror.css')



                div(class:'col-md-12'){
                    form(id:'submit-answer-form',class:'form-horizontal'){
                        legend problem.name
                        input(type:'hidden',value:problem.id,name:'problem_id')
                        div(class:'form-group'){
                            label(class:'col-md-2 control-label',for:'language','언어')
                            select{
                                option(value:1,'C')
                                option(value:2,'Java')
                            }
                        }

                        div(class:'form-group'){
                            label(class:'col-md-2 control-label',for:'source','소스 코드')
                            div(class:'col-md-10'){
                                textarea (id:'source',autofocus:'autofocus',class:'code-java')

                            }
                        }

                    }
                }
                script(type:'text/javascript',src:'../js/codemirror.js'){}
                script(type: 'text/javascript',src:'../js/submitAnswer.js'){}
            }
        }