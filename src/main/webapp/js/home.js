/**
 * Created by kiah on 2018/11/10.
 */
$(".roomNum").on('click', function() {
    var url = $(this).attr("data-url");
    window.parent.toggleIframe(url);
})