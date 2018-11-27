
function transformToJson(formData) {
    var obj = {}
    for (var i in formData) {
        obj[formData[i].name] = formData[i]['value'];
    }
    return obj;
}

/*简单封装bootbox.js中的bootbox.alert*/
function bootboxalert(message) {
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
    for (var i = 0; i < arr1.length; i++) {
        var arr2 = arr1[i].split('=');
        if (arr2[0] == key) {
            return decodeURI(arr2[1]);
        }
    }
}

function timeFormat(time) {
    // return time.replace('T',' ').substring(0,19);
    var d = new Date(time);
    var year = d.getFullYear();
    var month = (d.getMonth() + 1) > 9 ? (d.getMonth() + 1) : '0' + (d.getMonth()+1);
    var date = d.getDate() > 9 ? d.getDate() : '0' + d.getDate();
    var hours = d.getHours() > 9 ? d.getHours() : '0' + d.getHours();
    var minutes = d.getMinutes() > 9 ? d.getMinutes() : '0' + d.getMinutes();
    var seconds = d.getSeconds() > 9 ? d.getSeconds() : '0' + d.getSeconds();
    return year + '-' + month + '-' + date + ' ' + hours + ':' + minutes + ':' + seconds;
}

function dateFormat(time) {
    // return time.substring(0,10);
    var d = new Date(time);
    var year = d.getFullYear();
    var month = (d.getMonth() + 1) > 9 ? (d.getMonth() + 1) : '0' + (d.getMonth()+1);
    var date = d.getDate() > 9 ? d.getDate() : '0' + d.getDate();
    return year + '-' + month + '-' + date;
}

/**
 * 前台输入判断是否为非负整数
 * @param input
 * @returns {boolean}
 */
function isInteger(input) {
    var tt = /^\d+$/g;
    return tt.test(input);
}

/**
 * 非负浮点数
 * @param input
 * @returns {boolean}
 */
function isNumber(input){
    var regPos = /^\d+(\.\d+)?$/;
    if(regPos.test(input)){
        return true;
    }else{
        return false;
    }
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

function confirm(message, callback) {
    bootbox.confirm({
        buttons: {
            confirm: {
                label: '确认',
                className: 'btn-primary'
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

bootbox.setDefaults("locale", "zh_CN");

/**
 * 日期选择器初始化
 */
function initDatePicker(){
    $('.date-picker').datepicker({
        language: 'zh-CN',
        autoclose: true,
        todayHighlight: true
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
}

/**
 * 生成表格中带颜色的文字
 * @param text
 * @param color
 * @returns {string}
 */
function generateSpan(text,color){
    return "<span style='color:#"+color+"'>"+text+"</span>";
}

/**
 * 获得图片路径
 * @param url
 * @returns {string}
 */
function getImageUrl(url) {
    return '/static/' + url
}

/**
 * 获得功能按钮
 * @param style 按钮样式
 * @param func 功能
 * @param name 按钮名称
 * @returns {string}
 */
function getFunctionButton(style, func, name) {
    return "<a class='btn btn-minier " + style + "' href='javascript:" + func + "'>" + name + "</a>&nbsp;";
}

/**
 * 获得跳转按钮
 * @param style 按钮样式
 * @param url 跳转地址
 * @param name 按钮名称
 * @returns {string}
 */
function getRedirectButton(style, url, name) {
    return "<a class='btn btn-minier " + style + "' href='" + url + "'>" + name + "</a>&nbsp;";
}

function success(data) {
    if ('error' in data) {
        bootboxalert(data.error.message);
    } else {
        $("#modal-form").modal('hide');
        jQuery(grid_selector).setGridParam({}).trigger("reloadGrid");
    }
}

/**
 * 初始化列表
 * @param grid_selector grid选择器
 * @param pager_selector pager选择器
 * @param options 参数
 */
function initTable(grid_selector, pager_selector, options) {
    var url = options.hasOwnProperty("url") ? options.url : "";
    var caption = options.hasOwnProperty("caption") ? options.caption : "列表";
    var colNames = options.hasOwnProperty("colNames") ? options.colNames : ['', '操作'];
    var colModel = options.hasOwnProperty("colModel") ? options.colModel : [
        {name: 'id', index: 'id', sortable: false, hidden: true},
        {name: '_action', index: '_action', width: 100, sortable: false}
    ]


    var postData = options.hasOwnProperty("postData") ? options.postData : "";
    var rownumbers = options.hasOwnProperty("rownumbers") ? options.rownumbers : true;
    var datatype = options.hasOwnProperty("datatype") ? options.datatype : "json";
    var mtype = options.hasOwnProperty("mtype") ? options.mtype : "GET";
    var hidegrid = options.hasOwnProperty("hidegrid") ? options.hidegrid : false;//收缩列表按钮
    var prmNames = options.hasOwnProperty("prmNames") ? options.prmNames : {
        page: "pageNum",
        rows: "pageSize"
    };
    var width = options.hasOwnProperty("width") ? options.width : "100%";
    var autowidth = options.hasOwnProperty("autowidth") ? options.autowidth : true;
    var shrinkToFit = options.hasOwnProperty("shrinkToFit") ? options.shrinkToFit : true;
    var autoScroll = options.hasOwnProperty("autoScroll") ? options.autoScroll : false;
    var height = options.hasOwnProperty("height") ? options.height : 350;
    var rowNum = options.hasOwnProperty("rowNum") ? options.rowNum : 10;
    var rowList = options.hasOwnProperty("rowList") ? options.rowList : [10, 20, 30];
    var altRows = options.hasOwnProperty("altRows") ? options.altRows : true;
    var viewrecords = options.hasOwnProperty("viewrecords") ? options.viewrecords : true;
    var multiselect = options.hasOwnProperty("multiselect") ? options.multiselect : false;
    var emptyrecords = options.hasOwnProperty("emptyrecords") ? options.emptyrecords : "0条数据";
    var loadComplete = options.hasOwnProperty("loadComplete") ? options.loadComplete : function (data) {
        var table = this;
        setTimeout(function () {
            $("#jqgh_grid-table_rn").empty().append("序号");//第一列加上列名
            updatePagerIcons(table);//美化【首页，下一页，上一页，末页】
        }, 0);
    };
    var jsonReader = options.hasOwnProperty("jsonReader") ? options.jsonReader : { // jsonReader来跟服务器端返回的数据做对应
        root: "data", // 包含实际数据的数组
        page: "paging.currPage", // 当前页
        total: "paging.totalPage",// 总页数
        records: "paging.total", // 查询出的记录数
        repeatitems: false
    };

    jQuery(grid_selector).jqGrid({
        url: url,
        postData: postData,
        rownumbers: rownumbers,
        datatype: datatype,
        mtype: mtype,
        hidegrid: hidegrid,//收缩列表按钮
        prmNames: prmNames,// 重新定义分页信息
        width: width,
        autowidth: autowidth,
        shrinkToFit: shrinkToFit,
        autoScroll: autoScroll,
        height: height,
        colNames: colNames,
        colModel: colModel,
        rowNum: rowNum,
        rowList: rowList,
        pager: pager_selector,
        altRows: altRows,
        viewrecords: viewrecords,
        multiselect:multiselect,
        emptyrecords: emptyrecords,
        loadComplete: loadComplete,
        jsonReader: jsonReader,
        caption: caption
    });
}








