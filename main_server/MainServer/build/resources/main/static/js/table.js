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