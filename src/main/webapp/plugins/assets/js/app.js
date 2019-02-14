$(function() {
    // 侧边菜单
    $('.sidebar-nav-sub-title').on('click', function() {
        $(this).siblings('.sidebar-nav-sub').slideToggle(80)
            .end()
            .find('.sidebar-nav-sub-ico').toggleClass('sidebar-nav-sub-ico-rotate');
    })
    // 读取body data-type 判断是哪个页面然后执行相应页面方法，方法在下面。
    var dataType = $('body').attr('data-type');
    for (key in pageData) {
        if (key == dataType) {
            pageData[key]();
        }
    }
    autoLeftNav();
    $(window).resize(function() {
        autoLeftNav();
    });
    //切换页面
    $('.syLink').on('click', function() {
        var url = $(this).attr('data-url');
        $("#syIframe").attr('src',url);
    })
})
// 页面数据
var pageData = {
    'index': function indexData() {
        $('#example-r').DataTable({

            bInfo: false, //页脚信息
            dom: 'ti'
        });
    }
}
//切换风格
function toggleSkin(){
    var themeColor = storageLoad('SelcetColor').Color;
    if(themeColor.indexOf("black")!=-1){
        themeColor = "theme-white";
        saveSelectColor.Color = "theme-white";
    }else {
        themeColor = "theme-black";
        saveSelectColor.Color = "theme-black";
    }
    $('body').attr('class', themeColor);
    $('body').find('iframe').contents().find('body').attr('class', themeColor);
    storageSave(saveSelectColor);
}

function toggleIframe(url){
    $("#syIframe").attr('src',url);
}

// 风格切换

$('.tpl-skiner-toggle').on('click', function() {
    $('.tpl-skiner').toggleClass('active');
})

$('.tpl-skiner-content-bar').find('span').on('click', function() {
    $('body').attr('class', $(this).attr('data-color'))
    saveSelectColor.Color = $(this).attr('data-color');
    // 保存选择项
    storageSave(saveSelectColor);

})
// 侧边菜单开关
function autoLeftNav() {
    $('.tpl-header-switch-button').on('click', function() {
        if ($('.left-sidebar').is('.active')) {
            if ($(window).width() > 1024) {
                $('.tpl-content-wrapper').removeClass('active');
            }
            $('.left-sidebar').removeClass('active');
        } else {

            $('.left-sidebar').addClass('active');
            if ($(window).width() > 1024) {
                $('.tpl-content-wrapper').addClass('active');
            }
        }
    })

    if ($(window).width() < 1024) {
        $('.left-sidebar').addClass('active');
    } else {
        $('.left-sidebar').removeClass('active');
    }
}



