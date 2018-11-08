/**
 * Created by TU on 2018/7/31.
 */
/*document.write("<script language='javascript' src='/static/assets/js/jquery-1.11.1.min.js'></script>");
document.write("<script language='javascript' src='/static/assets/bootstrap/js/bootstrap.min.js'></script>");
document.write("<script language='javascript' src='/static/jquery-confirm-master/jquery-confirm.min.js'></script>");
document.write("<script language='javascript' src='/static/easyui-1.5/jquery.easyui.min.js'></script>");*/

/*ace admin js*/
document.write("<script language='javascript' src='../static/ace-master/js/ace-extra.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/html5shiv.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/respond.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/jquery-2.1.4.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/jquery-1.11.3.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/bootstrap.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/ace-elements.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/ace.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/bootbox.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/bootstrap-datepicker.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/bootstrap-datepicker.zh-CN.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/jquery.jqGrid.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/grid.locale-cn.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/tree.min.js'></script>");
document.write("<script language='javascript' src='../static/ace-master/js/select2.min.js'></script>");

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
                className: 'btn-primary'
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

function timeFormat(time){
    var d = new Date(time);
    var year = d.getFullYear();
    var month = (d.getMonth()+1)>9?(d.getMonth()+1):'0'+(d.getMonth());
    var date = d.getDate()>9?d.getDate():'0'+d.getDate();
    var hours = d.getHours()>9?d.getHours():'0'+d.getHours();
    var minutes = d.getMinutes()>9?d.getMinutes():'0'+d.getMinutes();
    var seconds = d.getSeconds()>9?d.getSeconds():'0'+d.getSeconds();
    return year + '-' + month+ '-' + date + ' ' + hours + ':' + minutes + ':' + seconds;
}

function dateFormat(time){
    var d = new Date(time);
    var year = d.getFullYear();
    var month = (d.getMonth()+1)>9?(d.getMonth()+1):'0'+(d.getMonth());
    var date = d.getDate()>9?d.getDate():'0'+d.getDate();
    return year + '-' + month+ '-' + date;
}

//replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
    var replacement =
        {
            'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
            'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
            'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
            'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
        };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
        if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
    })
}




