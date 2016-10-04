/**
 * Created by ohyongtaek on 2016. 9. 2..
 */
var myInfo = $('#info')
var myPassword = $('#password')
var withdrawal = $('#withdrawal')

if ($(location).attr('pathname').indexOf('/user/info') > -1) {
    if (!myInfo.hasClass('active')) {
        myInfo.addClass('active')
    }
    if (myPassword.hasClass('active')) {
        myPassword.removeClass('active')
    }
    if (withdrawal.hasClass('active')) {
        withdrawal.removeClass('active')
    }
} else if ($(location).attr('pathname').indexOf('/user/setting') > -1) {
    if (myInfo.hasClass('active')) {
        myInfo.removeClass('active')
    }
    if (!myPassword.hasClass('active')) {
        myPassword.addClass('active')
    }
    if (withdrawal.hasClass('active')) {
        withdrawal.removeClass('active')
    }
} else if ($(location).attr('pathname') === '/problem/create') {
    if (myInfo.hasClass('active')) {
        myInfo.removeClass('active')
    }
    if (myPassword.hasClass('active')) {
        myPassword.removeClass('active')
    }
    if(!withdrawal.hasClass('active')) {
        withdrawal.addClass('active')
    }
}