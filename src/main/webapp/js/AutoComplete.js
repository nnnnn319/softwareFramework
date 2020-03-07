$(function () {
    $("#keyword").keyup(function () {
        var keywords = document.getElementById("keyword").value;
        if(keywords != ""){
            var url = "/searchAutoComplete";
            var params = "ajax_searchName=" + keywords;
            function fn(data) {
                if(data.length > 0){
                    $("#searchResult").html("");
                    $.each(data,function (index,ele) {
                        $("#searchResult").append('<option>' +ele+ '</option>');
                    });
                }
                else{

                }

            }
            $.post(url,params,fn,"json");
        }
        else{
            $("#searchResult").hide();
        }
    });
});