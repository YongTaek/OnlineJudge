function viewsection() {
    var widgetbackground = document.getElementById("login_background");
    var widget = document.getElementById("loginbox");
    widget.style.display = "block";
    widgetbackground.style.display = "block";

}




function hidesection() {
    var widgetbackground = document.getElementById("login_background");
    var widget = document.getElementById("loginbox");
    widget.style.display = "none";
    widgetbackground.style.display = "none";
}

$(document).ready(function(){
    $('.problem').popover({html:true,placement:'right',trigger:'click',content: '<ul><li><a href="#" class="popovercontent">"문제집"</a></li><li><a href="#" class="popovercontent">"문제"</a></li></ul>' });
});
