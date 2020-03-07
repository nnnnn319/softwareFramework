$(function () {
    $(".numChange").on("input propertychange",function () {
        // var $change = $("#numChange").val();
        var $change = $(this).val();
        if($change>9999){
            alert('not enough');
            $change=9999;
        }
        var $itemId = $(this).parent().siblings("#getHref").children().attr("href");
        var $list = $(this).parent().siblings("#listPrice");
        var $t = $(this).parent().siblings("#totalPrice");
        var $sub = $("#subTotal");
        var $listPriceStr = $list.html().slice(1);
        var $listPrice = parseFloat($listPriceStr);
        var $totalPrice = $listPrice*parseInt($change);
        var subPrice = document.getElementsByClassName('cul');
        var $culSubP = 0;
        var s = subPrice.length;
        // for(var i=0;i<subPrice.length;i++){
        //     $culSubP = $culSubP + parseFloat(subPrice[i].html().slice(1));
        // }
        var url = "/cartChange";
        var params = {change:$change,itemId:$itemId};
        $.post(url,params,fn,"text");
        function fn(data) {
            if(data != null){
                $t.html("$"+$totalPrice.toString());
                $sub.html("Sub Total:$"+ data);
            }
        }
    });
});