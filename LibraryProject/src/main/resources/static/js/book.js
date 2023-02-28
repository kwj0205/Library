        let total_count = 0;
        let pagenum = 1;
        let is_end = false;
        let sortv = "accuracy";
        let value = "title";

        function check(){
            var v = document.getElementById("b_name");
            value = v.options[v.selectedIndex].value;
        }

        function sortc(){
                var s = document.getElementById("sequence");
                sortv = s.options[s.selectedIndex].value;
            }

        $(document).ready(function(){
            $("#search").keydown(function(event){
                $("#p").html("");
                if(event.keyCode == 13){
                    $("#p").html("");
                    booklist();
                    if(total_count == 0){
                        $("#p").html(`<div id="e">검색된 도서가 없습니다</div>`);
                    }

                }
            });
            $("#more").click(function(){
                if(!is_end){
                    pagenum += 1;
                    booklist();
                }else{
                    window.alert("마지막 페이지입니다");
                }
                });

            $("#submit").click(function(){
                $("#p").html("");
                pagenum = 1;
                booklist();
            });

	        $("#button").click(function(){
                    $("#p").html("");
                    booklist();
                    if(total_count == 0){
                        $("#p").html(`<div id="e">검색된 도서가 없습니다</div>`);
                    }
            });

            function booklist(){

                $.ajax({
                        method: "GET",
                        url: "https://dapi.kakao.com/v3/search/book?",
                        dataType: "JSON",
                        data: {
                            query: $("#content").val().trim(), size:"10", page:pagenum, target: value, sort: sortv,
                        },
                        cache:false,
                        headers: {
                            Authorization: "KakaoAK 2378c22a6b330a2deb9c49bbfb5a8da4"
                        },
                        success:function(data){
                            is_end = data.meta.is_end;
                            total_count = data.meta.total_count;
                            let d = data['documents'];

                            for (let i = 0; i < d.length; i++) {
                                let datetime = d[i]['datetime'].substr(0,10);
                                // if(d[i]['thumbnail'] == ""){
                                //     $("#img").append('<div id="imgx">이미지 없음</div>');
                                // }else if(d[i]['thumbnail'] != ""){
                                //     $("#img").append('<img src="' + d[i]['thumbnail'] + '"/>');
                                //                 }
                                let imgx = `<div id="imgx">이미지 없음</div>`;
                                let html = `<div id="t_img">
                                    <div class=img id="img${i}"><img src="${d[i]['thumbnail']}"/></div>
                                    <div id="t_box">
                                        <div id="t"><form name="form1" action="checkout" method="get">
                                        <input type="hidden" name="isbn" value=${d[i]['isbn']}>
                                        <button type="submit"><b>도서</b>${d[i]['title']}</button></div>
                                                        <div class="c">저자 : ${d[i]['authors']}</div>
                                                        <div class="c">출판사 : ${d[i]['publisher']}</div>
                                                        <div class="c">ISBN : ${d[i]['isbn']}</div>
                                                        <div class="c">출판날짜 : ${datetime}</div>
                                                    </div>
                                                </div>`;

                                $("#p").append(html);

                                if(d[i]['thumbnail'] == ""){
                                    $("#img"+i).append('<img src="/img/imgx.png"/>');

                                }
                            }
                                            // }else if(d[i]['thumbnail'] != ""){
                                            //     $("#img").append('<img src="' + d[i]['thumbnail'] + '"/>');
                                            //     $("#p").append(html);
                            }


                    })
                    .done(function (msg) {
                        console.log(msg);
                    });
                }
            })