/**
 * Created by ohyongtaek on 2016. 3. 19..
 */
window.onload = function() {
    var editor  = CodeMirror.fromTextArea(document.getElementById("code"),{
        mode:"/mode/javascript",
        styleActiveLine: true,
        lineNumbers: true,
        lineWrapping:true

    }
    );
};