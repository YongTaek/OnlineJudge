/**
 * Created by ohyongtaek on 2016. 2. 18..
 */

function sort_table(tbody, col, asc) {
    var rows = tbody.rows,
        rlen = rows.length,
        arr = new Array();
    for (i = 0; i < rlen; ++i) {
        cells = rows[i].cells;
        clen = cells.length;
        arr[i] = new Array();
        for (j = 0; j < clen; ++j) {
            arr[i][j] = cells[j].innerHTML;
        }
    }
    if (asc == 1) {
        arr.sort(function (a, b) {
            return a[col] - b[col];
        });
    } else {
        arr.sort(function (a, b) {
            return b[col] - a[col];
        })
    }
    for (i = 0; i < rlen; ++i) {
        rows[i].innerHTML = "<td class=\"common-table\">" + arr[i].join("</td><td class=\"common-table\">") + "</td>";
    }
}

var prob_id = $('#prob-id');
var prob_tbody = $('#prob-tbody');
asc1 = asc2 = asc3 = 1;
prob_id.click(function(){
    sort_table(prob_tbody, 0, asc1);
    asc1 *= -1;
    asc2 = 1;
    asc3 = 1;
})

$('#click-etc').click(function(){
    $('#etc').slideToggle('fast',function(){});
});

var problem = $('#problem');
var recent = $('#recent');
var prob_ranking = $('#ranking');

if (jQuery(location).attr('pathname') ==='/problem'){
    if(!problem.hasClass('active')) {
        problem.addClass('active');
    }
    if(recent.hasClass('active')){
        recent.removeClass('active');
    }
    if(prob_ranking.hasClass('active')){
        prob_ranking.removeClass('active');
    }
}else if(jQuery(location).attr('pathname') === '/problem/recent'){
    if(problem.hasClass('active')){
        problem.removeClass('active');
    }
    if(!recent.hasClass('active')){
        recent.addClass('active');
    }
    if(prob_ranking.hasClass('active')){
        prob_ranking.removeClass('active');
    }
}else if(jQuery(location).attr('pathname') === '/problem/ranking') {
    if(problem.hasClass('active')){
        problem.removeClass('active');
    }
    if(recent.hasClass('active')){
        recent.removeClass('active');
    }
    if(!prob_ranking.hasClass('active')){
        prob_ranking.addClass('active');
    }
    $('#prob-ranking').css('width', '5%');
    $('#prob-name').css('width','50%');
};
$('#searchButton').click(function(){
    $.ajax({
        url:'./search',
        type:'post',
        dataType:'json',
        data:$('#searchForm').serialize(),
        success:function(data){
            var result = "";
            if(data == null){
                result = "<tr><td colspan='5'>No Problem</td></tr> ";
            }else {
                for (i = 0; i < data.length; ++i) {
                    result += "<tr>";
                    result += "<td class=\"common-table\">" + data[i]['id'] + "</td>";
                    result += "<td class=\"common-table\">" + data[i]['name'] + "</td>";
                    result += "<td class=\"common-table\">" + data[i]['count'] + "</td>";
                    result += "<td class=\"common-table\">" + data[i]['rate'] + "</td>";
                    result += "</tr>";
                }
            }
            prob_tbody.html(result);
        }
    })
});