$(function(){
   // 글 [삭제] 버튼
   $("#btnDel").click(function(){
        let answer = confirm("삭제하시겠습니까?");
        if(answer){
            $("form[name='frmDelete']").submit();
        }
   });

   //현재 글의 id값
   const id = $("input[name='id']").val().trim();

   //해당 글의 댓글 로딩
   loadComment(id);
   // 댓글 작성 버튼 누르면 댓글 등록 하기.
   // 1. 어느글에 대한 댓글인지? --> 위에 id 변수에 담겨있다
   // 2. 어느 사용자가 작성한 댓글인지? --> logged_uid 값
   // 3. 댓글 내용은 무엇인지?  --> 아래 content
    //작성 버튼
   $("#btn_comment").click(function(){
           // 입력한 댓글
           const content = $("#input_comment").val().trim();

           // 검증
           if(!content){
               alert("댓글 입력을 하세요");
               $("#input_comment").focus();
               return;
           }

           // 전달할 parameter 들 준비 Controller 와 같아야함
           const data = {
               "request_id": id,
               "user_id": logged_id,
               "content": content,
           };

           $.ajax({
               url: conPath + "/comment/rebook",
               type: "POST",
               data: data,
               cache: false,
               success: function(data, status, xhr){
                   if(status == "success"){
                       if(data.status !== "OK"){
                           alert(data.status);
                           return;
                       }
                       loadComment(id);   // 댓글 목록 다시 업데이트
                       $("#input_comment").val('');  // 입력칸 리셋
                   }
               },
           });


       });

});
//매개변수는 현재 글의 아이디(request_id) 의 댓글 목록 읽어오기
//data는 json으로 변환된것을 받아옴
function loadComment(request_id){
    $.ajax({
        url: conPath+"/comment/list?id="+request_id,
        type: "GET",
        cache: false,
        success: function(data,status,xhr){
            if(status == "success"){
//                alert(xhr.responseText); //response 결과 확인용

                //서버쪽에 에러 메시지 있는 경우
                if(data.status != "OK"){
                    alert(data.status);
                    return;
                    }

                    buildComment(data); //화면 랜더링

                    // ★댓글목록을 불러오고 난뒤에 삭제에 대한 이벤트 리스너를 등록해야 한다
                    addDelete();
            }
        },
    })

}

function buildComment(result){
    //댓글의 총 개수 저장
    $("#cmt_cnt").text(result.count);

    const out=[];

    //댓글에 대한 json 값 정보들을 가져옴
    result.data.forEach(comment => {
        let id = comment.id; //댓글의 아이디
        let content = comment.content.trim();
        let regdate = comment.regdate;

        let user_id = parseInt(comment.user.id);
        let username = comment.user.username;
        let name = comment.user.name;

        //삭제버튼 여부 : 작성자 본인인 경우만 보이게 함, 클릭시 아이디를 삭제하는 아이콘 버튼
        const delBtn = (logged_id !== user_id) ? '' : `
                                <i class="btn fa-solid fa-delete-left text-danger" data-bs-toggle="tooltip"
                                    data-cmtdel-id="${id}" title="삭제"></i>
                            `;

        const row=`
            <tr>
            <td><span><strong>${username}</strong><br><small class="text-secondary">(${name})</small></span></td>
            <td>
              <span>${content}</span>${delBtn}
            </td>
            <td><span><small class="text-secondary">${regdate}</small></span></td>
              </tr>

        `;

        out.push(row);
    });

    $("#cmt_list").html(out.join("\n"));
}//end buildComment();

// 댓글 삭제버튼이 눌렸을때. 해당 댓글 삭제하는 이벤트를 삭제버튼에 등록
function addDelete(){
    const id = $("input[name='id']").val().trim();

    $("[data-cmtdel-id]").click(function(){
            if(!confirm("댓글을 삭제하시겠습니까?")) return;

        //삭제할 댓글의 comment_id ,this 는 삭제할 아이콘을 가리킴
        const comment_id = $(this).attr("data-cmtdel-id");

        $.ajax({
            url:conPath + "/comment/delete",
            type: "POST",
            cache: false,
            data: {"id":comment_id},
            success: function(data,status,xhr){
                if(status == "success"){
                    if(data.status !== "OK"){
                        alert(data.status);
                        return;
                    }
                    //삭제후에도 다시 목록을 불러와야함
                    loadComment(id);
                }
            },
        });
    });
}
