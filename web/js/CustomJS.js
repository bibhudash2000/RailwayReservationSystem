$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

$(window).ready(function () {
    var $temp = $("<input>");
    var $url = $(location).attr('href');
    $('#shareNotice').click(function () {
//        alert();
        $("body").append($temp);
        $temp.val($url).select();
        document.execCommand("copy");
        $temp.remove();
        
        $('#snackbar').html('Link copied');
        $('#snackbar').addClass('show');
        setTimeout(function () {
            $('#snackbar').removeClass("show");
        }, 3000);

    });
});