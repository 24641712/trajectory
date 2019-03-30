/**
 * Created by lichunting on 2017/12/17.
 */

var adds=[];

$(function () {
    $("#upload").click(function () {
        var formData = new FormData();
        formData.append("upfile", document.getElementById("upfile").files[0]);
        formData.append("uid",$("#uid").val());
        $.ajax({
            url: "/upload/plt.do",
            type: "POST",
            data: formData,
            /**
             *必须false才会自动加上正确的Content-Type
             */
            contentType: false,
            /**
             * 必须false才会避开jQuery对 formdata 的默认处理
             * XMLHttpRequest会对 formdata 进行正确的处理
             */
            processData: false,
            success: function (data) {
                console.log(data);
                var obj = JSON.parse(data);
                if(obj.status=="success"){
                    alert("导入数据成功");
                    document.getElementById("form1").reset();
                }else{
                    alert("导入数据失败")
                    document.getElementById("form1").reset();
                }
            },
            error: function () {

            }
        });
    });
});


