/**
 * Created by cheonyujung on 2016. 9. 28..
 */
$('#problemset').on('click',function() {
    window.open("/contest/create/insert-set","대회 문제집 추가","width=800px, height=600px")
})

$('#requestDeputy-add').on('click',function() {
    console.log($(location).attr('path'))
    var buttonValue = document.getElementById('requestDeputy-add').value;
    $.ajax({
        type: 'POST',
        url: '/contest/add/deputy',
        data: {contest_id: $(location).attr('pathname').split('/')[3], deputy_id:buttonValue},
        dataType: 'json',
        success: function(data) {
            console.log(data);
        },
        error: function(data) {
            console.log(data);
        }
    })
    location.reload();
})

$('#requestDeputy-deny').on('click',function() {
    console.log($(location).attr('path'))
    var buttonValue = document.getElementById('requestDeputy-deny').value;
    $.ajax({
        type: 'POST',
        url: '/contest/delete/requestDeputy',
        data: {contest_id: $(location).attr('pathname').split('/')[3], deputy_id:buttonValue},
        dataType: 'json',
        success: function(data) {
            console.log(data);
        },
        error: function(data) {
            console.log(data);
        }
    })
    location.reload();
})

$('#deputy-delete').on('click',function() {
    console.log($(location).attr('path'))
    var buttonValue = document.getElementById('deputy-delete').value;
    $.ajax({
        type: 'POST',
        url: '/contest/delete/deputy',
        data: {contest_id: $(location).attr('pathname').split('/')[3], deputy_id:buttonValue},
        dataType: 'json',
        success: function(data) {
            console.log(data);
        },
        error: function(data) {
            console.log(data);
        }
    })
    location.reload();
})

$('#delete-contest').on('click',function() {
    console.log($(location).attr('path'))
    $.ajax({
        type: 'POST',
        url: '/contest/delete',
        data: {contest_id: $(location).attr('pathname').split('/')[3]},
        dataType: 'json',
        success: function(data) {
            console.log(data);
        },
        error: function(data) {
            console.log(data);
        }
    })
    location.reload("/contest/list");
})