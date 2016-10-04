
$('#request').on('click', function() {
    var teamId = $(location).attr("pathname").split("/")[4];
    $.ajax({
        url: "/contest/team/info/request",
        type: "POST",
        data: {team_id:teamId},
        dataType: 'json',
        complete: function(data) {
            console.log(data);
            alert(data.responseText);
            window.location.href="/contest/team/info/"+teamId;
        }
    });
})