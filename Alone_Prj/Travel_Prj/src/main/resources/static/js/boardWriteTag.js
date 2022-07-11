
function write_append() {
    const div_list = document.getElementsByClassName('input-image');
    let count = div_list.length +1;

    let warp_main = document.createElement("div");
    warp_main.setAttribute("class", "warp_main");

    let main_005 = document.createElement("div");
    main_005.setAttribute("class", "main_005");

    let ol_001 = document.createElement("ol");
    ol_001.setAttribute("class", "ol_001");

    let li_001 = document.createElement("li");
    li_001.setAttribute("class", "li_001");

    let li_002 = document.createElement("li");
    li_002.setAttribute("class", "li_001");

    let ol_002 = document.createElement("ol");
    ol_002.setAttribute("class", "ol_002");

    let warp_001 = document.createElement("div");
    warp_001.setAttribute("class", "warp_001");

    let postion_sapn = document.createElement("span");
    let postion_title = document.createElement("label");
    postion_title.innerHTML="장소명";

    let postion_addr = document.createElement("input");
    postion_addr.setAttribute("type","text");
    postion_addr.setAttribute("name","addr");
    postion_addr.style.border="none";
    postion_addr.style.display="inline";
    postion_addr.style.marginLeft="10px";

    let warp_002 = document.createElement("div");
    warp_002.setAttribute("class", "warp_002");

    let photo_001 = document.createElement("div");
    photo_001.setAttribute("class", "photo_001");

    let photo_btn = document.createElement("button");
    photo_btn.setAttribute("class", "photo_btn");
    photo_btn.setAttribute("id","photo_btn"+count);
    photo_btn.setAttribute("type","button");
    photo_btn.setAttribute("onclick","photo_event();");

    let photo_002 = document.createElement("div");
    photo_002.setAttribute("class", "photo_002");

    let img_tag = document.createElement("img");

    img_tag.setAttribute("src","");
    img_tag.setAttribute("class", "input-image")
    img_tag.setAttribute("id", "input-image"+count);
    img_tag.style.width = "100%";
    img_tag.style.height = "100%";
    img_tag.style.objectFit = "cover";

    let write_001 = document.createElement("div");
    write_001.setAttribute("class", "write_001");

    let write_002 = document.createElement("div");
    write_002.setAttribute("class", "write_002");

    let title_001 = document.createElement("div");
    title_001.setAttribute("class", "title_001");


    let span_tag = document.createElement("span");

    let text_h4 = document.createElement("h4");
    text_h4.innerHTML = "제목을 적어주세요:)";

    let text_tag = document.createElement("input");
    text_tag.setAttribute("type", "text");
    text_tag.setAttribute("class", "title_002");
    text_tag.setAttribute("name","title");
    text_tag.style.marginTop = "10px";


    let select_span = document.createElement("span");

    let select_star = document.createElement("select");
    select_star.setAttribute("class", "keyword_main");
    select_star.setAttribute("name", "star");
    select_star.style.boxSizing="content-box";
    select_star.style.width="60px";

    let option_main = document.createElement("option");
    option_main.setAttribute("value","");
    option_main.setAttribute("selected", "selected");
    option_main.innerHTML="평점";

    let option_1 = document.createElement("option");
    option_1.setAttribute("value", "1");
    option_1.innerHTML='1';

    let option_2 = document.createElement("option");
    option_2.setAttribute("value", "2");
    option_2.innerHTML='2';

    let option_3 = document.createElement("option");
    option_3.setAttribute("value", "3");
    option_3.innerHTML='3';

    let option_4 = document.createElement("option");
    option_4.setAttribute("value", "4");
    option_4.innerHTML='4';

    let option_5 = document.createElement("option");
    option_1.setAttribute("value", "5");
    option_5.innerHTML='5';



    let detail_001 = document.createElement("textarea");
    detail_001.setAttribute("name","board_content");
    detail_001.setAttribute("class", "detail_001");
    detail_001.setAttribute("placeholder", "사진에대해 설명해 주세요");
    detail_001.setAttribute("row", "6");
    detail_001.style.overflow = "hidden";
    detail_001.style.overflowWrap = "break-word";
    detail_001.style.height = "145px";

    let keyword_001 = document.createElement("div");
    keyword_001.setAttribute("class", "keyword_001");

    let keyword_002 = document.createElement("div");
    keyword_002.setAttribute("class", "keyword_002");
    keyword_002.style.display = "inline-block";

    let keyword_main = document.createElement("input");
    keyword_main.setAttribute("class", "keyword_main");
    keyword_main.setAttribute("placeholder", "#태그");
    keyword_main.setAttribute("name","hashTag")
    keyword_main.style.boxSizing = "content-box";
    keyword_main.style.width = "60px";

    let append_per = document.getElementById("main_001");


    append_per.appendChild(warp_main);
    warp_main.appendChild(main_005);
    warp_main.appendChild(ol_001).appendChild(li_001);
    warp_main.appendChild(ol_002).appendChild(li_002);
    li_002.appendChild(warp_001).appendChild(warp_002);
    warp_001.appendChild(postion_sapn).appendChild(postion_title).appendChild(postion_addr);
    warp_002.appendChild(photo_001).appendChild(photo_btn).appendChild(photo_002).appendChild(img_tag);
    warp_002.appendChild(write_001).appendChild(write_002);
    write_002.appendChild(title_001);
    title_001.appendChild(span_tag).appendChild(text_h4).appendChild(text_tag);
    title_001.appendChild(select_span).appendChild(select_star).appendChild(option_main);
    select_star.appendChild(option_1);
    select_star.appendChild(option_2);
    select_star.appendChild(option_3);
    select_star.appendChild(option_4);
    select_star.appendChild(option_5);
    write_002.appendChild(detail_001);
    write_002.appendChild(keyword_001).appendChild(keyword_002).appendChild(keyword_main);

    return count;
}




