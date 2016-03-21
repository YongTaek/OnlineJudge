layout 'test.tpl', title: '문제', loginUser:loginUser,
        content:contents{
            div(class:'container'){
                div(class:'row') {
                    div(class: 'col-md-12'){
                        ul(class: 'nav nav-pills'){
                            li(id:'problem-num'){
                                a(href:'#', messages.num)
                            }
                            li(id:'problem-submit'){
                                a(href: 'submit/'+messages.num, '제출하기')
                            }
                            li(id: 'grade-now'){
                                a(href: '#', '채점 현황')
                            }
                            li(id: 'grade-now-me'){
                                a(href: '#','내 채점현황')
                            }
                            li(id: 'q&a'){
                                a(href: '../question/'+messages.num,'Q&A')
                            }
                        }
                    }
                    div(class: 'col-md-12'){
                        div(class:'page-header'){
                            h1{
                                span(id:'problem-title', messages.title)
                                if(messages.label!=null) {
                                    span(id: 'problem-label', messages.label)
                                }
                            }
                        }
                    }
                    div(class:'col-md-12'){
                        div(class:'table-responsive'){
                            table(class:'table', id: 'problem-info'){
                                thead{
                                    tr{
                                        th('시간 제한')
                                        th('메모리 제한')
                                        th('제출')
                                        th('정답')
                                        th('맞은 사람')
                                        th('정답 비율')
                                    }
                                }
                                tbody{
                                    tr{
                                        th(messages.time)
                                        th(messages.memory)
                                        th(messages.submit)
                                        th(messages.success_count)
                                        th(messages.success_user_count)
                                        th(messages.rate)
                                    }
                                }
                            }
                        }
                    }
                    div(class:'problem-body'){
                        div(class:'col-md-12'){
                            section(id: 'description'){
                                div(class:'headline'){
                                    h2('문제')
                                }
                                div(class:'problem-description'){
                                    p(messages.content)
                                }
                            }
                        }
                        div(class:'col-md-12'){
                            section(id: 'test-input'){
                                div(class:'headline'){
                                    h2('입력')
                                }
                                div(class:'problem-input'){
                                    p(messages.input)
                                }
                            }
                        }
                        div(class:'col-md-12'){
                            section(id:'test-output'){
                                div(class:'headline'){
                                    h2('출력')
                                }
                                div(class:'problem-output'){
                                    p(messages.output)
                                }
                            }
                        }
                        div(class:'col-md-12') {
                            div(class: 'row') {
                                div(class: 'col-md-6') {
                                    section(id: 'sampleinput') {
                                        div(class: 'headline'){
                                            h2('예제 입력')
                                        }
                                        pre(class:'sampledata',messages.inputData)
                                    }
                                }
                                div(class: 'col-md-6') {
                                    section(id: 'sampleinput') {
                                        div(class: 'headline'){
                                            h2('예제 출력')
                                        }
                                        pre(class:'sampledata',messages.outputData)
                                    }
                                }
                            }
                        }
                        div(class:'col-md-12'){
                            section(id: 'source'){
                                div(class:'headline'){
                                    h2('출처')
                                }
                                ul{
                                    messages.sources.each { source ->
                                        li(source)
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }