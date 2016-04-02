/**
 * Created by cheonyujung on 2016. 3. 22..
 */
var plus_input = $('#plusButton1');
var minus_input = $('#minusButton');
var count = 0;

plus_input.click(function(){
    if(!(count<0)) {
        var input_id = 'testInput' + count;
        var output_id = 'testOutput' + count;
        var input = '<div>' + '<input type=text name=' + '"' + input_id + '"' + 'id=' + input_id + '></div>';
        var output = '<div>' + '<input type=text name=' + '"' + output_id + '"' + 'id=' + output_id + '></div>';
        $('#testInput').append(input);
        $('#testOutput').append(output);
        count++;
        $('#testcase_count').val(count);
    }
});

minus_input.click(function(){
    if(count>=0) {
        console.log(count);
        var input = '#testInput'+(count-1);
        var output = '#testOutput' + (count-1);
        $(input).remove();
        $(output).remove();
        count--;
        $('#testcase_count').val(count);
    }
});