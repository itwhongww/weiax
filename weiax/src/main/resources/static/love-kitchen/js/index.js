define(function (require,exports,module) {

    var global=require("./common/global")

    var index = {
        init:function(){
            //改变div的高度
            $(".container").height($(window).height());
            $(".dialog").hide();
            $(".masking").hide();
            $(".contentImage").hide();
            $(".contentModule").hide();
        }
    }

    imgIndex = 0;
    imgs = [];

    module.exports= index;

    var tableMain = $("#tableMain").bootstrapTable({
        method:"get",
        url:global.urlGetTable,
        dataType:"json",
        editable: true,
        sidePagination:"server",
        striped: true,
        responseHandler:function (res) {
            var rt = {};
            if(res.code == global.successCode){
                rt['rows'] = res.data;
                rt['total'] = 0;
            }else if(res.code == global.errorCode){
                layer.msg(res.message);
            }else{
                layer.msg('服务器异常');
                return;
            }
            return rt;
        },
        columns:[
            {field:'menuId',valign:'middle',visible:false},
            {field:'menuName',title:'菜单',valign:'middle',cellStyle:function(){
                return {classes: 'menuName'};
            }}
        ],
    })

    $('body').on('click-cell.bs.table',function(field,value,row,$element){
        if(value == 'menuName'){
            var data = {'id':$element.menuId};
            data = JSON.stringify(data);
            $(".contentImage").show();
            $(".contentModule").show();
            $.ajax({
                type: 'post',
                url: global.urlDetailMenu,
                data: data,
                dataType: 'json',
                processData: false,
                contentType: 'application/json',
                async: false,
                success: function(res){
                    if(res.code == global.successCode){
                        var result = res.data;
                        $(".title h3").text(result.menuName);
                        $(".content h4").text(result.content);
                        imgs = result.imgPath;
                        $("#imageBigImg").attr('src',imgs[0]);
                        $(".thumbnail").empty();
                        $.each(imgs,function(i,obj){
                            if(i<3){
                                $(".thumbnail").append("<image src='"+obj+"' class='thumbnailImg'/>");
                            }
                        })
                        $(".thumbnail").append("<div class='thumbnailLeft'><image/></div><div class='thumbnailRight'><image/></div>")
                        $(".thumbnailLeft").hide();
                        imgIndex = 0;
                        if(imgs.length<=3){
                            $(".thumbnailRight").hide();
                        }
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
        }
    })

    $('body').on('click','.thumbnailImg',function(e){
        $('#imageBigImg').attr('src',e.target.src);
    })



    $('body').on('click','.thumbnailLeft',function(e){
        imgIndex = imgIndex - 1;
        $("#imageBigImg").attr('src',imgs[imgIndex]);
        $(".thumbnail").empty();
        $.each(imgs,function(i,obj){
            if(i<imgIndex + 3 && i>=imgIndex){
                $(".thumbnail").append("<image src='"+obj+"' class='thumbnailImg'/>");
            }
        })
        $(".thumbnail").append("<div class='thumbnailLeft'><image/></div><div class='thumbnailRight'><image/></div>")
        $(".thumbnailRight").show();
        if(imgIndex == 0){
            $(".thumbnailLeft").hide();
        }
    })

    $('body').on('click','.thumbnailRight',function(e){
        imgIndex = imgIndex + 1;
        $("#imageBigImg").attr('src',imgs[imgIndex]);
        $(".thumbnail").empty();
        $.each(imgs,function(i,obj){
            if(i<imgIndex + 3 && i>=imgIndex){
                $(".thumbnail").append("<image src='"+obj+"' class='thumbnailImg'/>");
            }
        })
        $(".thumbnail").append("<div class='thumbnailLeft'><image/></div><div class='thumbnailRight'><image/></div>")
        $(".thumbnailLeft").show();
        if(imgs.length<=imgIndex + 3){
            $(".thumbnailRight").hide();
        }
    })

})
