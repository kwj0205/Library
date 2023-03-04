$(function(){
    $("button[data-id]").click(function(){
        $("[name='id']").val($(this).attr("data-id"));
        $("[name='frmDelete']").submit();
    });
});