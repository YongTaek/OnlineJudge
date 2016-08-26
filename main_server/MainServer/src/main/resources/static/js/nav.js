/**
 * Created by ohyongtaek on 2016. 8. 24..
 */
$(function () {
    $('[data-toggle="popover"]').popover({
        html: true,

        content: function () {
            return $("#profile-data").html();
        }
    })
})
