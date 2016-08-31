/**
 * Created by ohyongtaek on 2016. 8. 30..
 */
var list = $('#list')
var myGroup = $('#myGroup')
var groupSetting = $('#group_setting')

if($(location).attr('pathname') === '/group/myGroup'){
    if(list.hasClass('active')) {
        list.removeClass('active')
    }
    if(!myGroup.hasClass('active')){
        myGroup.addClass('active')
    }
    if(groupSetting.hasClass('active')) {
        groupSetting.removeClass('active')
    }
} else if($(location).attr('pathname') === '/group/myGroup/setting') {
    if(!groupSetting.hasClass('active')){
        groupSetting.addClass('active')
    }
    if(myGroup.hasClass('active')) {
        myGroup.removeClass('active')
    }
    if(list.hasClass('active')) {
        list.removeClass('active')
    }
} else {
    if(!list.hasClass('active')) {
        list.addClass('active')
    }
    if(myGroup.hasClass('active')){
        myGroup.removeClass('active')
    }
}