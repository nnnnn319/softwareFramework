$(function () {
    $('#frame_check').click(function () {
        var url = '/frameOrder';
        $.get(url,fn);
        function fn(data){
            alert("a");
            layer.open({
                type:2,
                title:'your information',
                shadeClose:true,
                shade:false,
                maxmin:true,
                area:['893px','600px'],
                content:'/newOrder_form',
                end:function () {
                 alert("end");
                 window.location.reload();
                }
            });
        }
    });
});