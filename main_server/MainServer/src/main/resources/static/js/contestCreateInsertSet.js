/**
 * Created by ohyongtaek on 2016. 9. 16..
 */
$('#button').on('click', function () {
    $(opener.document).find('#admin-list').val()
    var selectedSet = $("input[name=problemset]");
    var text = $('#result').text()
    for (var i = 0; i< selectedSet.length; i++) {
        if(selectedSet[i]){
            text.html(selectedSet[i])
            break
        }
    }
    $(opener.document).find('#admin-list').val(text)
    window.close()
})
