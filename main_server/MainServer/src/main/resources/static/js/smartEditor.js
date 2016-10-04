    /**
 * Created by cheonyujung on 2016. 8. 31..
 */

$(function() {

    var oEditors = [];

    nhn.husky.EZCreator.createInIFrame({

        oAppRef: oEditors,

        elPlaceHolder: "smartEditor", //textarea에서 지정한 id와 일치해야 합니다.

        sSkinURI: "/SE2.1.3.O8706/SmartEditor2Skin.html",

        fCreator: "createSEditor2"

    });

    $("#postButton").click(function () {
        //id가 smarteditor인 textarea에 에디터에서 대입
        oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);

        // 이부분에 에디터 validation 검증

        //폼 submit
        $("#write").submit();
    })


    $("#modifyButton").click(function (){
        jQuery.ajax({
            url: "${createLink(controller: 'WebBoardController', action: 'getData')}",
            data: "ret=" + ret,
            success: function (data) {
                alert(data);
                var content = data;
                var sHTML = "<span style='color:#FF0000;'>"+content+"<\/span>";

                oEditors.getById["smartEditor"].exec("PASTE_HTML", [sHTML]);
            }
        });
        //var content = document.getElementById("modifyButton").value;


    })

})