/**
 * Created by cheonyujung on 2016. 8. 31..
 */

var oEditors = [];

nhn.husky.EZCreator.createInIFrame({

    oAppRef: oEditors,

    elPlaceHolder: "content", //textarea에서 지정한 id와 일치해야 합니다.

    sSkinURI: "/SE2.1.3.O8706/SmartEditor2Skin.html",

    fCreator: "createSEditor2"

});
