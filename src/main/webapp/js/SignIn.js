$(function () {
    $("#viewCart").click(function () {

       var url = '/ajaxCart';
       $.get(url,fn);
       function fn(data) {
           if(data == 'no'){
               alert("ff");
               layer.open({
                   type:2,
                   title:'your information',
                   shadeClose:true,
                   shade:false,
                   maxmin:true,
                   area:['893px','600px'],
                   content:'/signon',
                   end:function () {
                       alert("end");
                       window.location.reload();
                   }
               });


           }
       }
    });
})