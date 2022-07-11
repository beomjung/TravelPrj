<%@ page import="java.io.FileReader" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.Board_PostDTO" %>
<!DOCTYPE html>
<%
    List<Board_PostDTO> pList = (List<Board_PostDTO>) request.getAttribute("pList");
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>여행 게시글 수정</title>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <script src="/js/boardWriteTag.js"></script>
    <script type="text/javascript" src="http://form-serialize.googlecode.com/svn/trunk/serialize-0.2.min.js"></script>
    <link href="/assets/css/boardWrite.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
    <script type="text/javascript">

        window.onload = function () {
            //여기는 동적 태그 생성
            $(function () {
                let count = 0;
                $(".btn_001").click(function () {
                    count = write_append();

                });
                let click_id="";
                //img-btn클릭시 input file 클릭
                $(document).on("click", ".photo_btn", function photo_event(e) {
                    click_id= $(this).attr('id');
                    e.preventDefault();
                    $('#file').click();
                });

                // 파일 객체에 변화가 일어났을때 발생하는 이벤트
                document.getElementById('file').addEventListener('change', readURL, true);
                // 파일 배열에 담을 변수
                let self_file = [];
                let auth_board = [];
                //파일 객체 읽어와서 배열에 담아줌
                function readURL(e) {
                    console.log(click_id);
                    let sub_result = click_id.substr(9);
                    console.log(sub_result);
                    let files = document.getElementById("file").files[0];
                    console.log(files);
                    let reader = new FileReader();
                    reader.onloadend = function () {
                        //자동으로 생성되는 객체 id값 지정 순서대로 증가
                        let test = "input-image" + sub_result;
                        console.log(test);
                        //자동으로 생성된 아이디에 src 읽어온 파일 url로 교체 preview
                        $('#' + test).attr('src', reader.result);
                    }
                    //파일 정보 배열에 담기
                    self_file.push(files);
                    auth_board.push(sub_result);
                    console.log(self_file);

                    if (files) {
                        reader.readAsDataURL(this.files[0]);
                    } else {
                    }
                }

                //파일 전송
                $(document).on("click", "#send_form", function () {
                    let formData = new FormData($("#uploadFile")[0]);
                    if (self_file.length >= 0) {
                        self_file.forEach(function (f) {
                            formData.append("self_file", f);
                        });
                        formData.append("auth_board", auth_board);
                        console.log(formData);
                        $.ajax({
                            url: "/board/boardChgProc",
                            data: formData,
                            type: 'POST',
                            enctype: 'multipart/form-data',
                            processData: false,
                            contentType: false,
                            dataType: 'json',
                            cache: false,
                            success: function (res) {
                                console.table(res);
                                let url = res["moveUrl"]+"?msg="+res["msg"]+"&url="+res["url"];
                                location.replace(url);
                                console.log(url);
                            }, error: function (res) {
                                console.log("fail : " + res);
                            }
                        });
                    }
                });
            });
        }


    </script>
</head>
<body>
<div class="top_001" style="position: static; top: 0px; left: 0px; right: 0px;">
    <form action="/board/boardChgProc" id="uploadFile" method="post" enctype="multipart/form-data">
        <div class="top_002">
            <div class="top_003" style="height: 80px;">
                <div class="top_004"
                     style="position: fixed; transition: top 0.1s ease 0s; top: 0px; width: 1880px;">
                    <div class="top_005">
                        <a class="a_001">
                            <svg class="icon"></svg>
                        </a>
                        <div class="top_006">
                            <button type="button" class="btn_002"
                                    style="background-color: rgb(53,197,240); margin-right: 60px" id="send_form">
                                <span class="sp_001">올리기</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="main_001" id="main_001">
            <div class="main_002">

                <div class="main_003">
                    <div class="main_004">
                        <label>출발일</label>
                        <input type="date" class="section_001" id="start_day" name="start_day" value="<%=pList.get(0).getBoard_start_day()%>">

                    </div>
                    <div class="main_004">
                        <label>도착일</label>
                        <input type="date" class="section_001" id="end_day" name="end_day" value="<%=pList.get(0).getBoard_end_day()%>">
                        <span class="sp_002">
                                <svg class="icon" width="10" height="10"></svg>
                            </span>
                    </div>
                    <div class="main_004">
                        <span>
                                <label>지역</label>
                                   <select class="section_001" name="location" id="big_city"
                                           onchange="chanCity(value)" style="display: inline" disabled>
                                       <option value="<%=pList.get(0).getAreacode()%>">지역코드 : <%=pList.get(0).getAreacode()%></option>
                                       <option value="1">서울</option>
                                       <option value="2">인천</option>
                                       <option value="3">대전</option>
                                       <option value="4">대구</option>
                                       <option value="5">광주</option>
                                       <option value="6">부산</option>
                                       <option value="7">울산</option>
                                       <option value="31">경기도</option>
                                       <option value="33">충청북도</option>
                                       <option value="34">충청남도</option>
                                       <option value="35">경상북도</option>
                                       <option value="36">경상남도</option>
                                       <option value="37">전라북도</option>
                                       <option value="38">전라남도</option>
                                       <option value="39">제주도</option>
                                   </select>
                            </span>
                    </div>
                    <div class="main_004">
                              <span>
                                  <label class="ms-0">시군구</label>
                                     <select class="section_001" name="smallcity" id="small_city" style="display: inline;" disabled>
                                        <option value="<%=pList.get(0).getDetail_areacode()%>" selected>시 코드 : <%=pList.get(0).getDetail_areacode()%></option>
                                    </select>
                            </span>
                    </div>
                    <div class="main_004" style="margin-left: 400px;">
                        <button type="button" class="btn_001"
                                style="background-color: rgb(53,197,240); margin-right: 60px; display: none;">
                            <span class="sp_001">게시글 추가하기</span>
                        </button>
                    </div>
                    <div>
                        <input type="file" multiple="multiple" name="file" class="file_01" id="file" style="display: none;"
                               onChange="readURL(e);">
                    </div>
                </div>
            </div>

            <%
                for(int i = 0; i<pList.size(); i++){
            %>
            <div class="warp_main">
                <div class="main_005"></div>
                <ol class="ol_001">
                    <li class="li_001"></li>
                </ol>
                <ol class="ol_002">
                    <li class="li_001">
                        <div class="warp_001">
                            <div class="warp_002">
                                <div class="photo_001">
                                    <button class="photo_btn" id="photo_btn<%=pList.get(i).getBoard_post_seq()%>"onclick="photo_event();">
                                        <div class="photo_002" id="">
                                            <img src="<%=pList.get(i).getFile_detail_path()%>" style="width: 100%; height: 100%; object-fit: cover;"
                                                 id="input-image<%=pList.get(i).getBoard_post_seq()%>">
                                        </div>
                                    </button>
                                </div>
                                <div class="write_001">
                                    <div class="write_002">
                                        <div class="title_001">
                                            <span><h4>제목을 적어주세요:)</h4> <input type="text" class="title_002" name="title"
                                                                              style="margin-top: 10px;" value="<%=pList.get(0).getBoard_post_title()%>"></span>
                                            <span><select class="keyword_main"
                                                          style="box-sizing : content-box; width:60px;" name="star">
                                                <option value="<%=pList.get(i).getBoard_post_star()%>" selected>평점:<%=pList.get(i).getBoard_post_star()%></option>
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                            </select></span>
                                        </div>
                                        <textarea class="detail_001" placeholder="사진에대해 설명해 주세요" rows="6"
                                                  style="overflow: hidden; overflow-wrap: break-word; height: 145px;"
                                                  name="board_content"><%=pList.get(0).getBoard_post_content()%></textarea>
                                        <div class="keyword_001">
                                            <div class="keyword_002" style="display: inline-block;">
                                                <input class="keyword_main" placeholder="#태그" value="<%=pList.get(0).getBoard_post_hashTag()%>"
                                                       style="box-sizing : content-box; width:60px;" id="keyMain"
                                                       onkeyup="enterkey();" name="hashTag">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <span>
                            <h5>위치
                         <input type="text" style="border:none; display: inline; margin-left: 10px;" name="addr" value="<%=pList.get(i).getBoard_post_postion()%>">
                            </h5>
                                </span>
                        </div>
                    </li>
                </ol>
            </div>
            <input type="hidden", name="board_seq" value="<%=pList.get(i).getBoard_seq()%>">
            <input type="hidden" name="board_post_seq" value="<%=pList.get(i).getBoard_post_seq()%>">
            <%
                }
            %>
        </div>
    </form>
</div>
</body>

</html>