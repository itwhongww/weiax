define(function (require,exports,module) {

    var global=require("./common/global")
    var nowImage = null;
    var datafiles = {};

    var newMenu = {
        init:function(){

        }
    }

    $('body').on('click','#newMenu',function(){
        $(".masking").show();
        $('.dialog').show();
        initNewMenuTable();
        initNewMenuTable_new();
        nowImage = null;
        datafiles = {};

    })

    function initNewMenuTable(){

        $(".dialog").append("<div class='newMenu_font'></div>\
                                <div class='newMenu_img'></div>");
    }
    function initNewMenuTable_new(){
        for(var i=0;i<6;i++){
            $(".newMenu_img").append("<div class='newMenuImgDiv' >\
                                        <image class='newMenuImg' id='newMenuImg"+i+"'></image>\
                                        <div class='delImgBT' id='delImgBT"+i+"'></div>\
                                    </div>");
            $(".newMenuImg").attr("src","img/add.jpg");
        }
        $(".newMenu_font").append("<form enctype='multipart/form-data'>\
            <input type='file' id='file' style='display:none'/> </form>");
        $(".newMenu_font").append("<input type='text' class='newMenuInput'/>");
        $(".newMenu_font").append("<textarea class='newMenuTextarea'/>");
        $(".newMenu_font").append("<input type='button' class='newMenuGoBT' value='确认'/>");
        $(".newMenu_font").append("<input type='button' class='newMenuCancelBT' value='取消'/>");
    }
    $('body').on('click','.newMenuImg',function(e){
        nowImage = e.target;
        $("#file").trigger("click");
    })
    $('body').on('change','#file',function(e){
        getPath(nowImage,this,'transImg');
        var imgId = $(nowImage).attr('id');
        var btId = imgId.replace("newMenuImg","delImgBT");
        $("#"+btId).show();
    })
    function getPath(obj,fileQuery,transImg){
        if(window.navigator.userAgent.indexOf("MSIE")>=1){
            obj.select();
            var path=document.selection.createRange().text;
            obj.removeAttribute("src");
            obj.setAttribute("src",transImg);
            obj.style.filter=
            "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+path+"', sizingMethod='scale');";
        }
        else{
            var file =fileQuery.files[0];
            var reader = new FileReader();
            reader.onload = function(e){
                obj.setAttribute("src",e.target.result)

                datafiles[$(obj).attr('id')] = file;
            }
            reader.readAsDataURL(file);
        }
    }
    $('body').on('click','.newMenuGoBT',function(){
        var formData = new FormData();
        $.each(datafiles,function(key,value){
            formData.append("files",value);
        })
        var data = {
            'menuName':$(".newMenuInput").val(),
            'content':$(".newMenuTextarea").val()
        };
        formData.append("comeInfo",JSON.stringify(data).toString());
        $.ajax({
            type: 'post',
            url: global.urlAddMenu,
            data: formData,
            dataType: 'json',
            processData: false,
            contentType: false,
            async: false,
            success: function(res){
                if(res.code == global.successCode){
                    layer.msg("上传成功");
                    cancelBT();
                    window.location.reload();
                }else if(res.code == global.errorCode){
                    layer.msg(res.message);
                }else{
                    layer.msg('服务器正忙，请稍后重试！');
                    return;
                }
            },
            error: function(){
                layer.msg('服务器正忙，请稍后重试！');
                return;
            }
        })
    })
    $('body').on('click','.newMenuCancelBT',function(){
        cancelBT();
    })
    function cancelBT(){
        $('.dialog').empty();
        $('.dialog').hide();
        $(".masking").hide();
    }
    $('body').on('click','.delImgBT',function(e){
        var btId = $(e.target).attr('id');
        $(e.target).hide();
        var imgId = btId.replace("delImgBT","newMenuImg");
        $("#"+imgId).attr("src","img/add.jpg");
    })
})
