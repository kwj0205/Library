$(function(){
    $("button[data-id]").click(function(){
        //alert($(this).attr("data-id"));

        $("[name='id']").val($(this).attr("data-id"));
        $("[name='frmDelete']").submit();
    });
});

$(function(){
    $("button[data-date]").click(function(){
        alert("일주일 연장되었습니다.");

        $("[name='id']").val($(this).attr("data-date"));
        $("[name='frmDate']").submit();
    });
});
