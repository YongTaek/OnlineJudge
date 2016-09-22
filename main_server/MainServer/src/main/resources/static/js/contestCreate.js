/**
 * Created by ohyongtaek on 2016. 9. 15..
 */

var startCalendar = '#start-date';
var endCalendar = '#end-date';
YUI().use('calendar', 'datatype-date', 'cssbutton', function(Y) {
    var calendar = new Y.Calendar( {
        contentBox: startCalendar,
        width: '340px',
        showPreMonth: true,
        showNextMonth: true,
        date: new Date()
    }).render();

    var dtdate = Y.DataType.Date;

    calendar.on("selectionChange", function(ev) {
        var newDate = ev.newSelection[0];

        $('#startdate').val(dtdate.format(newDate));
    })

})

YUI().use('calendar', 'datatype-date', 'cssbutton', function(Y) {
    var calendar = new Y.Calendar( {
        contentBox: endCalendar,
        width: '340px',
        showPreMonth: true,
        showNextMonth: true,
        date: new Date()
    }).render();

    var dtdate = Y.DataType.Date;

    calendar.on("selectionChange", function(ev) {
        var newDate = ev.newSelection[0];

        $('#enddate').val(dtdate.format(newDate));
    })

})

$('#admin-input').on('click',function() {
    window.open("/contest/create/insert-admin","운영진 추가","width=800px, height=600px")
})

$('#problemset').on('click',function() {
    window.open("/contest/create/insert-set","대회 문제집 추가","width=800px, height=600px")
})