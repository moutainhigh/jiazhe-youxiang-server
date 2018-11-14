/*将form通过$('#operatorSearchForm').serializeArray()方式获得的formData【形如：
[{"name":"user","value":"hpc"},{"name":"pwd","value":"123"},{"name":"sex","value":"M"},{"name":"age","value":"100"}]】格式
转为{"user":"hpc","pwd":"123","sex":"M","age":"100"}格式*/
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
    var d = new Date(time);
    var year = d.getFullYear();
    var month = (d.getMonth() + 1) > 9 ? (d.getMonth() + 1) : '0' + (d.getMonth());
    var date = d.getDate() > 9 ? d.getDate() : '0' + d.getDate();
    var hours = d.getHours() > 9 ? d.getHours() : '0' + d.getHours();
    var minutes = d.getMinutes() > 9 ? d.getMinutes() : '0' + d.getMinutes();
    var seconds = d.getSeconds() > 9 ? d.getSeconds() : '0' + d.getSeconds();
    return year + '-' + month + '-' + date + ' ' + hours + ':' + minutes + ':' + seconds;
}

function dateFormat(time) {
    var d = new Date(time);
    var year = d.getFullYear();
    var month = (d.getMonth() + 1) > 9 ? (d.getMonth() + 1) : '0' + (d.getMonth());
    var date = d.getDate() > 9 ? d.getDate() : '0' + d.getDate();
    return year + '-' + month + '-' + date;
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
    var url = options.url ? options.url : "";
    var caption = options.caption ? options.caption : "列表";
    var colNames = options.colNames ? options.colNames : ['', '操作'];
    var colModel = options.colModel ? options.colModel : [
        {name: 'id', index: 'id', sortable: false, hidden: true},
        {name: '_action', index: '_action', width: 100, sortable: false}
    ]


    var postData = options.postData ? options.postData : "";
    var rownumbers = options.rownumbers ? options.rownumbers : true;
    var datatype = options.datatype ? options.datatype : "json";
    var mtype = options.mtype ? options.mtype : "GET";
    var hidegrid = options.hidegrid ? options.hidegrid : false;//收缩列表按钮
    var prmNames = options.prmNames ? options.prmNames : {
        page: "pageNum",
        rows: "pageSize"
    };
    var width = options.width ? options.width : "100%";
    var autowidth = options.autowidth ? options.autowidth : true;
    var height = options.height ? options.height : 350;
    var rowNum = options.rowNum ? options.rowNum : 10;
    var rowList = options.rowList ? options.rowList : [10, 20, 30];
    var altRows = options.altRows ? options.altRows : true;
    var viewrecords = options.viewrecords ? options.viewrecords : true;
    var emptyrecords = options.emptyrecords ? options.emptyrecords : "0条数据";
    var loadComplete = options.loadComplete ? options.loadComplete : function (data) {
        var table = this;
        setTimeout(function () {
            $("#jqgh_grid-table_rn").empty().append("序号");//第一列加上列名
            updatePagerIcons(table);//美化【首页，下一页，上一页，末页】
        }, 0);
    };
    var jsonReader = options.jsonReader ? options.jsonReader : { // jsonReader来跟服务器端返回的数据做对应
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
        height: height,
        colNames: colNames,
        colModel: colModel,
        rowNum: rowNum,
        rowList: rowList,
        pager: pager_selector,
        altRows: altRows,
        viewrecords: viewrecords,
        emptyrecords: emptyrecords,
        loadComplete: loadComplete,
        jsonReader: jsonReader,
        caption: caption
    });
}







