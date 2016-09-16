/**
 * Created by ohyongtaek on 2016. 9. 15..
 */

$('#button').on('click', function () {

    var text = $('#result').text()
    $(opener.document).find('#admin-list').val(text)
    window.close()
})

$("button[name=addAdminBtn]").on('click', function () {
    var text = $(this).val()
    if ($('#result').text() === '') {
        $('#result').text(text)
    } else {
        $('#result').text($('#result').text() + '/' + text)
    }
    $(this).parent().html('')
})