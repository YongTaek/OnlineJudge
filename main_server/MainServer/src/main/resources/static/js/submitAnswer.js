/**
 * Created by ohyongtaek on 2016. 3. 19..
 */

$(document).ready(function() {
    var textarea = document.getElementById('code')
    CodeMirror.fromTextArea(textarea, {
        lineNumbers: true,
        lineWrapping: true,
        theme: "eclipse",
        val: textarea.value,
        mode: "text/javascript"
    })

})
