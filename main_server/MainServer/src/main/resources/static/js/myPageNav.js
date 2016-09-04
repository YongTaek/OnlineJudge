/**
 * Created by ohyongtaek on 2016. 9. 2..
 */

var myActivity = $('#myActivity')
var setting = $('#setting')
var createProblem = $('#createProblem')

if ($(location).attr('pathname') === '/myPage') {
    if (!myActivity.hasClass('active')) {
        myActivity.addClass('active')
    }
    if (setting.hasClass('active')) {
        setting.removeClass('active')
    }
    if (createProblem.hasClass('active')) {
        createProblem.removeClass('active')
    }
} else if ($(location).attr('pathname').indexOf('/myPage/setting') > -1) {
    if (myActivity.hasClass('active')) {
        myActivity.removeClass('active')
    }
    if (!setting.hasClass('active')) {
        setting.addClass('active')
    }
    if (createProblem.hasClass('active')) {
        createProblem.removeClass('active')
    }
} else if ($(location).attr('pathname') === '/problem/create') {
    if (myActivity.hasClass('active')) {
        myActivity.removeClass('active')
    }
    if (setting.hasClass('active')) {
        setting.removeClass('active')
    }
    if(!createProblem.hasClass('active')) {
        createProblem.addClass('active')
    }
}