/**
 * Created by ohyongtaek on 2016. 3. 19..
 */
//var myCodeArea = document.getElementById('source');
//var myCodeMirror = CodeMirror.fromTextArea(myCodeArea);
window.onload = function() {
    var textArea = document.getElementById("source");
    var editor = CodeMirror.fromTextArea(textArea, {
        mode: "javascript",
        lineNumbers: true,
        lineWrapping: true,
        theme: '3024-night'
    });
};