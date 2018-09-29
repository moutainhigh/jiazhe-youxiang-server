/**
 * Created by TU on 2018/7/31.
 */
/*document.write("<script language='javascript' src='/static/assets/js/jquery-1.11.1.min.js'></script>");
document.write("<script language='javascript' src='/static/assets/bootstrap/js/bootstrap.min.js'></script>");
document.write("<script language='javascript' src='/static/jquery-confirm-master/jquery-confirm.min.js'></script>");
document.write("<script language='javascript' src='/static/easyui-1.5/jquery.easyui.min.js'></script>");*/

/*ace admin js*/
document.write("<script language='javascript' src='/static/ace-master/js/ace-extra.min.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/html5shiv.min.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/respond.min.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/jquery-2.1.4.min.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/jquery-1.11.3.min.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/bootstrap.min.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/ace-elements.min.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/ace.min.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/bootbox.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/bootstrap-datepicker.min.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/bootstrap-datepicker.zh-CN.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/jquery.jqGrid.min.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/grid.locale-cn.js'></script>");
document.write("<script language='javascript' src='/static/ace-master/js/tree.min.js'></script>");

/*将form通过$('#operatorSearchForm').serializeArray()方式获得的formData【形如：
[{"name":"user","value":"hpc"},{"name":"pwd","value":"123"},{"name":"sex","value":"M"},{"name":"age","value":"100"}]】格式
转为{"user":"hpc","pwd":"123","sex":"M","age":"100"}格式*/
function transformToJson(formData){
    var obj={}
    for (var i in formData) {
        obj[formData[i].name]=formData[i]['value'];
    }
    return obj;
}

/*简单封装bootbox.js中的bootbox.alert*/
function bootboxalert(message){
    bootbox.alert({
        buttons: {
            ok: {
                label: '确定',
                className: 'btn-myStyle'
            }
        },
        message: message,
        title: "提示信息",
    });
}

//获取cookie值
function getCookie(key) {
    var arr1 = document.cookie.split('; ');
    for (var i=0; i<arr1.length; i++) {
        var arr2 = arr1[i].split('=');
        if ( arr2[0] == key ) {
            return decodeURI(arr2[1]);
        }
    }
}

//将获取的json格式的city转为options标签，需登录，无权限限制
function citysToOptions(fatherId){
    var html = "<option value=''>  </option>";
    $.ajax({
        url:"/city/findNextCitys",    //请求的url地址
        dataType:"json",
        async:false,
        data:{
            "fatherId":fatherId
        },
        type:"GET",
        success:function(result){
            switch (result.code){
                case '000000':
                    var data = result.data;
                    for(var i=0;i<data.length;i++){
                        html = html + "<option value='"+data[i].id+"'>" +data[i].name+ "</option>"
                    }
                    break;
                case "000001":
                    parent.location.href = "/system/index";
                    break;
                case "000002":
                    window.location.href = "/system/403";
                    break;
                default :
                    bootboxalert(result.msg);
                    break;
            }
        },
        error:function(){
            bootboxalert("服务器异常，请联系管理员");
        }
    });
    return html;
}




