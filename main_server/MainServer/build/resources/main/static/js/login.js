/**
 * Created by jdekim43 on 2016. 4. 2..
 */

$(function () {
    $("#join-button").click(function () {
        $("#login-form").attr("action", "/join");
        $("#content").css("height", "410px");
        $("#form-title").text("Sign Up");
        $(".join-form").removeClass("hide");
        $("button[type=submit]").text("Sign up");
        $(this).addClass("hide");
    });
});