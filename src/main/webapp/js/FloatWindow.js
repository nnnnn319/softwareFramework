$(function () {
   $(".float").bind('mouseenter mouseleave',function (event) {
       if (event.type == 'mouseenter') {
           var leave = false;
           var $category = $(this).attr("href");
           var url = "/floatWindow";
           var params = "category=" + $category;
           $.post(url, params, fn, "json");
           function fn(data) {
                $.each(data,function (n,value) {
                    var app_str = '<p class="float_p">' + value.name + value.description +'</p>';
                    $('#float').append(app_str);
                });
               $("#float").css({display: 'initial', left: event.pageX, top: event.pageY + 10});
           }
       }
       $("#float").mouseleave(function () {
           $('.float_p').remove();
           $("#float").css({display: 'none'});
           leave = true;
       });
   });
});