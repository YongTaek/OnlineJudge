layout 'layout.tpl', title: '문제', loginUser: loginUser,
        custom_head: contents {
            link(rel: 'stylesheet', type: 'text/css', href: '/css/problemList.css')
        },
        content: contents {
            div(class: 'container') {
                div(class: 'head-container') {
                    ul(class: 'nav nav-pills head-item main') {
                        li(id: 'problem-num') {
                            a(href: '#', messages.num)
                        }
                        li(id: 'problem-submit') {
                            a(href: 'submit/'+messages.num, '제출하기')
                        }
                        li(id: 'grade-now') {
                            a(href: '#', '채점 현황')
                        }
                        li(id: 'grade-now-me') {
                            a(href: '#', '내 채점현황')
                        }
                        li(id: 'q&a') {
                            a(href: '../question/'+messages.num, 'Q&A')
                        }
                    }
                }
                div() {
                    div(class: 'page-header') {
                        h1 {
                            span(id: 'problem-title', messages.title)
                            if (messages.label != null) {
                                span(id: 'problem-label', messages.label)
                            }
                        }
                    }
                }
                div() {
                    div(class: 'table-responsive') {
                        table(class: 'table', id: 'problem-info') {
                            thead {
                                tr {
                                    th(class:'center', '시간 제한')
                                    th(class:'center','메모리 제한')
                                    th(class:'center', '제출')
                                    th(class:'center', '정답')
                                    th(class:'center', '맞은 사람')
                                    th(class:'center', '정답 비율')
                                }
                            }
                            tbody {
                                tr {
                                    td(class:'center',messages.time)
                                    td(class:'center',messages.memory)
                                    td(class:'center',messages.submit)
                                    td(class:'center',messages.success_count)
                                    td(class:'center',messages.success_user_count)
                                    td(class:'center',messages.rate)
                                }
                            }
                        }
                    }
                }
                div(class: 'problem-body') {
                    div() {
                        section(id: 'description') {
                            div(class: 'headline') {
                                h2('문제')
                            }
                            div(class: 'problem-description') {
                                p(messages.content)
                            }
                        }
                    }
                    div() {
                        section(id: 'test-input') {
                            div(class: 'headline') {
                                h2('입력')
                            }
                            div(class: 'problem-input') {
                                p(messages.input)
                            }
                        }
                    }
                    div() {
                        section(id: 'test-output') {
                            div(class: 'headline') {
                                h2('출력')
                            }
                            div(class: 'problem-output') {
                                p(messages.output)
                            }
                        }
                    }
                    div() {
                        div(class: 'row') {
                            div(class: 'col-md-6') {
                                section(id: 'sampleinput') {
                                    div(class: 'headline') {
                                        h2('예제 입력')
                                    }
                                    pre(class: 'sampledata', messages.inputData)
                                }
                            }
                            div(class: 'col-md-6') {
                                section(id: 'sampleinput') {
                                    div(class: 'headline') {
                                        h2('예제 출력')
                                    }
                                    pre(class: 'sampledata', messages.outputData)
                                }
                            }
                        }
                    }
                    div() {
                        section(id: 'source') {
                            div(class: 'headline') {
                                h2('출처')
                            }
                            ul {
                                messages.sources.each { source ->
                                    li(source)
                                }
                            }
                        }
                    }
                }

            }

        }