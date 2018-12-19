function initImgControl(file_selector, img_selector, input_selector) {
    $(file_selector).ace_file_input({
        style: 'well',
        btn_choose: '把图片拖到这里或点击选择图片',
        btn_change: null,
        no_icon: 'ace-icon fa fa-cloud-upload',
        droppable: true,
        thumbnail: 'large',
        allowExt: ['jpg', 'jpeg', 'png', 'gif', 'tif', 'tiff', 'bmp'],
        allowMime: ['image/jpg', 'image/jpeg', 'image/png', 'image/gif', 'image/tif', 'image/tiff', 'image/bmp'],
        before_remove: function () {
            $(img_selector).attr('hidden', 'hidden');
            return true;
        }
    }).on('change', function () {
        var image = $(file_selector)[0].files[0]
        if (!image) {
            return;
        }
        var formData = new FormData();
        formData.append('file', image);

        $.ajax({
            url: "/api/upload/uploadimage",    //请求的url地址
            dataType: "json",
            type: 'POST',
            async: true,
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false,// 告诉jQuery不要去设置Content-Type请求头
            data: formData,
            cache: false,
            success: function (data) {
                if ('error' in data) {
                    bootboxalert(data.error.message);
                } else {
                    $(input_selector).val(data.data.url)
                    $(img_selector).attr('hidden', 'hidden');
//                    alert('文件上传成功!' + data.data.url);
                }
            },
            error: error
        });
    });
}