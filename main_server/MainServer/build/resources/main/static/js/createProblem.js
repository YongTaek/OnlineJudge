/**
 * Created by cheonyujung on 2016. 3. 22..
 */
var plus_input = $('#plusButton1');
var minus_input = $('#minusButton');
var count = 0;
var input = "input";
var output = "output";

plus_input.click(function(){

    var temp = input+count;
    console.log(temp);
    $('#testInput').after('<div><input type = text name = temp id="testInput"+count><div>');
    $('#testOutput').after('<div><input type = text name = "output"+count id="testOutput"+count><div>');
    count++;
});

minus_input.click(function(){
    console.log("minus");
    $('#testInput'+count).replacedWith = ' ';
    console.log($('#testOutput'+count));
    count--;
});