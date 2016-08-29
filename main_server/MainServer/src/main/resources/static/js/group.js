/**
 * Created by ohyongtaek on 2016. 8. 30..
 */
var list = $('#list')
var myGroup = $('#myGroup')

if($(location).attr('pathname') === '/group/list') {
    if(!list.hasClass('active')) {
        list.addClass('active')
    }
    if(myGroup.hasClass('active')){
        myGroup.removeClass('active')
    }
}else{
    if(list.hasClass('active')) {
        list.removeClass('active')
    }
    if(!myGroup.hasClass('active')){
        myGroup.addClass('active')
    }
}