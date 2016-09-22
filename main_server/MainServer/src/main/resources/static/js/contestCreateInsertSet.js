/**
 * Created by ohyongtaek on 2016. 9. 16..
 */
$('#button').on('click', function () {
    $(opener.document).find('#admin-list').val()
    var selectedSet = $("input[name=problemset]");
    var text = $('#result')
    for (var i = 0; i< selectedSet.length; i++) {
        if(selectedSet[i].checked){
            text.text(selectedSet[i].value)
            break
        }
    }
    $(opener.document).find('#set-list').val(text)
    window.close()
})
