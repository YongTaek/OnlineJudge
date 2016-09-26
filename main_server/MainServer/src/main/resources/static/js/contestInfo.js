/**
 * Created by cheonyujung on 2016. 9. 29..
 */


$('#deputy-request').on('click', function() {
    console.log($(location).attr('path'))
    $.ajax({
        type: 'POST',
        url: '/contest/info/deputy',
        data: {contest_id: $(location).attr('pathname').split('/')[3]},
        dataType: 'json',
        success: function(data) {
            console.log(data);
        },
        error: function(data) {
            console.log(data);
        }
    })
    var requestDeputyBtn = document.getElementById('deputy-request');
    requestDeputyBtn.remove();
})