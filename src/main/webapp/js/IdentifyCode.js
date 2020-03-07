$(function () {
    $("#newId").on( "blur",function () {
        alert("fff");
       var username = $("#newId").val();
       var url ="/newAccount";
       var params = 'username='+username;
       function fn(data) {
           if(data == 'ok'){
               alert('ok');
               $("#newAcc").text('already exist');
               // $("#newAcc").style.color = 'green';
               $("#newAcc").attr("style","color:red;");
           }
           else{
               alert('ok');
               $("#newAcc").text('success');
               $("#newAcc").attr("style","color:green;");
               // $("#newAcc").style.color = 'red';
           }
       }
        $.post(url,params,fn,"text");
    });
});