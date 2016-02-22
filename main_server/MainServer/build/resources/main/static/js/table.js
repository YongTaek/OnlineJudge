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


var prob_id = document.getElementById('prob-id');
var prob_tbody = document.getElementById('prob-tbody');
asc1 = asc2 = asc3 = 1;
prob_id.addEventListener('click',function() {
    sort_table(prob_tbody, 0, asc1);
    asc1 *= -1;
    asc2 = 1;
    asc3 = 1;
}, false);
$('#click-etc').click(function(){
    $('#etc').slideToggle('fast',function(){});
});

var problem = document.getElementById('problem');
var recent = document.getElementById('recent');
var prob_ranking = document.getElementById('ranking');

if (jQuery(location).attr('pathname') ==='/'){
    problem.outerHTML = "<li class='active' id='problem'>"+"<a href='/'>문제</a>"+"</li>";
    recent.outerHTML =  "<li id='recent'>"+"<a href='/recent'>추가된 문제</a>"+"</li>";
    prob_ranking.outerHTML = "<li id='prob-ranking'>"+"<a href='/prob/ranking'>문제 순위</a>"+"</li>";
}else if(jQuery(location).attr('pathname') === '/recent'){
    problem.outerHTML = "<li  id='problem'>"+"<a href='/'>문제</a>"+"</li>";
    recent.outerHTML =  "<li class='active' id='recent'>"+"<a href='/recent'>추가된 문제</a>"+"</li>";
    prob_ranking.outerHTML = "<li id='prob-ranking'>"+"<a href='/prob/ranking'>문제 순위</a>"+"</li>";
}else if(jQuery(location).attr('pathname') === '/prob/ranking') {
    problem.outerHTML = "<li  id='problem'>" + "<a href='/'>문제</a>" + "</li>";
    recent.outerHTML = "<li  id='recent'>" + "<a href='/recent'>추가된 문제</a>" + "</li>";
    prob_ranking.outerHTML = "<li class='active' id='ranking'>" + "<a href='/prob/ranking'>문제 순위</a>" + "</li>";
    document.getElementById('prob-ranking').style.setProperty('width', '5%');
    document.getElementById('prob-name').style.setProperty('width','50%');
};
var table = document.getElementById('prob-tbody');
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
                console.log(result);
                alert("asdf");
            }
            $('#prob-tbody').html(result);
        }
    })
});