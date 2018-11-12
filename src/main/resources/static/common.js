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
    var month = (d.getMonth()+1)>9?(d.getMonth()+1):'0'+(d.getMonth()+1);
    var date = d.getDate()>9?d.getDate():'0'+d.getDate();
    var hours = d.getHours()>9?d.getHours():'0'+d.getHours();
    var minutes = d.getMinutes()>9?d.getMinutes():'0'+d.getMinutes();
    var seconds = d.getSeconds()>9?d.getSeconds():'0'+d.getSeconds();
    return year + '-' + month+ '-' + date + ' ' + hours + ':' + minutes + ':' + seconds;
}

function dateFormat(time){
    var d = new Date(time);
    var year = d.getFullYear();
    var month = (d.getMonth()+1)>9?(d.getMonth()+1):'0'+(d.getMonth()+1);
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

function confirm(message,callback){
    bootbox.confirm({
        buttons: {
            confirm: {
                label: '确认',
                className: 'btn-myStyle'
            },
            cancel: {
                label: '取消',
                className: 'btn-default'
            }
        },
        title: "提示信息",
        message: message,
        callback: callback,
    });
}

function error(data) {
    bootboxalert("服务器异常，请联系管理员");
}

bootbox.setDefaults("locale","zh_CN");







