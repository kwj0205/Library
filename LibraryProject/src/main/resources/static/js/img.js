function showimg(){
        var img = document.getElementById("img_bg");
        let sel = document.forms['select'];
        let result;
        let val = `${sel['img'].value}`;
        if(val == "1"){
           img.setAttribute("src", "/img/1.jpg")
           result = "도서관";
        }else if(val == "2"){
            img.setAttribute("src", "/img/2.jpg")
            result = "도서관";
        }else if(val == "3"){
            img.setAttribute("src", "/img/3.jpg")
            result = "도서관";
        }else if(val == "4"){
            img.setAttribute("src", "/img/4.jpg")
            result = "도서관";
        }else if(val == "5"){
            img.setAttribute("src", "/img/5.jpg")
            result = "도서관";
        }else if(val == "6"){
            img.setAttribute("src", "/img/6.jpg")
            result = "도서관";
        }else if(val == "7"){
            img.setAttribute("src", "/img/7.jpg")
            result = "도서관";
        }else if(val == "8"){
            img.setAttribute("src", "/img/8.jpg")
            result = "도서관";
        }else if(val == "9"){
            img.setAttribute("src", "/img/9.jpg")
            result = "도서관";
        }
        document.getElementById("text").innerHTML = result;
    }