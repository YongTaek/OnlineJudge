allProblems(class: 'navbar') {
    ul(class: 'menu') {
        li(class: 'llist') {
            a(href: '/problem/list') {
                yield img(class: "icon", src: '/img/tool.png ')
            }
        }
        li(class: 'llist') {
            a(href: '/notice') {
                yield img(class: "icon", src: '/img/editing.png')
            }
        }
        li(class: 'list') {
            a(href: '/ranking') {
                yield img(class: "icon", src: '/img/three.png')
            }
        }
        li(class: 'list') {
            a(href: '#') {
                yield img(class: "icon", src: '/img/shapes.png')
            }
        }
    }
}