 const url = new URL(window.location.href);
        const urlParams = url.searchParams;
        const isbn = urlParams.get('isbn');

        $.ajax({
                method: "GET",
                url: "https://dapi.kakao.com/v3/search/book?target=isbn",
                data: { query: isbn },
                headers: { Authorization: "KakaoAK 2378c22a6b330a2deb9c49bbfb5a8da4" }
            })
            .done(function (msg) {
                console.log(msg);
                if(msg.documents[0].thumbnail == ""){
                    $("#thumbnail").append('<div id="imgx">이미지 없음</div>');
                }
                $("#title").append(msg.documents[0].title);
                $("#authors").append(msg.documents[0].authors);
                $("#datetime").append(msg.documents[0].datetime.substr(0,10));
                $("#publisher").append(msg.documents[0].publisher);
                $("#isbn").append(msg.documents[0].isbn);
                $("#thumbnail").append('<img src="' + msg.documents[0].thumbnail + '" id="img"/>');
                $("#information").append(msg.documents[0].contents + '...');
                $("#translators").append(msg.documents[0].translators);
                $("#url").append('<a href="' + msg.documents[0].url + '">도서정보 상세보기</a>');
            })