<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.dto.UserDTO" %>
<%@ page import="kopo.poly.dto.UserDTO" %>
<%@ page import="kopo.poly.util.EncryptUtil" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">
<%
    UserDTO pDTO = (UserDTO) request.getAttribute("pDTO");
    List<UserDTO> rList = (List<UserDTO>) request.getAttribute("rList");
    String user_file_path = (String) request.getAttribute("user_file_path");
%>


<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="/assets/img/favicon.png">
    <title>회원정보 수정</title>
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css"
        href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
    <!-- Nucleo Icons -->
    <link href="/assets/css/nucleo-icons.css" rel="stylesheet" />
    <link href="/assets/css/nucleo-svg.css" rel="stylesheet" />
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
    <!-- CSS Files -->
    <link id="pagestyle" href="/assets/css/material-kit.css" rel="stylesheet" />
    <script src="/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#photo_chg').click(function (e) {
                e.preventDefault();
                $('#file').click();
            });
        });


        //파일보내기
       function uploadFile() {
            var imageInput  = $("#file")[0];
            console.log("결과는? "+imageInput);

            if(imageInput.files.length === 0){
                alert("사진을 선택해주세요");
                return;
            }

            var form = $('#photo_send')[0];
            let data = new FormData(form);
            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "/img/imgUpload",
                data:data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    console.log(data);
                    alert('등록이 성공하였습니다.');
                    let user_file_seq = data.user_file_seq;

                    $("#user_file_seq").attr('value',user_file_seq);
                },
                error: function () {

                    alert('등록이 실패하였습니다.');
                }
            });
        }

    </script>

</head>

<body class="sign-in-basic">
    <div class="page-header align-items-start min-vh-100">
        <span class="mask bg-gradient-dark opacity-6"
            style="background-image: linear-gradient(195deg, #ffffff 0%, #ffffff 100%);"></span>
        <div class="container my-auto">
            <div class="row">
                <div class="col-lg-4 col-md-8 col-12 mx-auto">
                    <div class="card z-index-0 fadeIn3 fadeInBottom">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1"
                                style="background-image:  linear-gradient(195deg, #49a3f1 0%, #49a3f1 100%); box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(245 236 239 / 40%) !important; color:#42424a !important">
                                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">회원정보 수정</h4>
                            </div>
                        </div>
                        <div class="card-body">
                            <form class="text-start" id="photo_send" enctype="multipart/form-data">
                                <div style="text-align: center;" >
                                    <img class="avatar avatar-xxl shadow-xl position-relative z-index-2"
                                        src="<%=user_file_path%>" alt="bruce" loading="lazy" id="input-image"><br>
                                        <br>
                                    <a id="photo_chg" onclick=""><h7>대표사진 변경</h7></a>
                                    <button type="button" class="btn bg-gradient-primary w-100 my-4 mb-2" id="send_my" onclick="uploadFile();"
                                            style="background-image:  linear-gradient(195deg, #49a3f1 0%, #49a3f1 100%); box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(245 236 239 / 40%) !important;">대표사진 변경 확인</button>
                                    <div>
                                        <input type="file" name="fileUpload" id="file" style="display: none;">
                                        <input type="hidden" name="file_code" value="myPhoto">
                                    </div>
                                </div>
                            </form>
                            <form action="/main/userInfoChgProc" method="post">
                                <br>
                                <div class="input-group input-group-outline mb-3">
                                    <input type="text" class="form-control" placeholder="<%=pDTO.getUser_name()%>" readonly>
                                </div>
                                <div class="input-group input-group-outline mb-3">
                                    <input type="email" class="form-control" placeholder="<%=EncryptUtil.decAES128CBC(pDTO.getUser_email())%>" readonly>
                                </div>
                                <div class="input-group input-group-outline mb-3">
                                    <input type="text" class="form-control" name="nick_name" value="<%=pDTO.getUser_nickname()%>">
                                </div>
                                <div class="input-group input-group-outline mb-3">
                                    <input type="text" class="form-control" name="user_age" value="<%=pDTO.getUser_age()%>">
                                </div>
                                <div class="input-group input-group-outline mb-3" id="hidden_per">
                                    <input type="text" class="form-control" name="my_info" value="<%=pDTO.getUser_introducation()%>">
                                </div>
                                <input type="hidden" name="user_file_seq" id="user_file_seq" value="">
                                <hr>
                                <h8>관심사</h8>
                                <div class="row justify-space-between text-center py-2">
                                    <div class="col-12">
                                        <%
                                            for(int i = 0; i< rList.size(); i++){
                                        %>
                                      <span class="badge rounded-pill bg-info"><%=rList.get(i).getUser_interest_content()%></span>
                                        <%
                                            }
                                        %>
                                    </div>
                                  </div>
                                <div>
                                    <button type="submit" class="btn bg-gradient-primary w-100 my-4 mb-2"
                                        style="background-image:  linear-gradient(195deg, #49a3f1 0%, #49a3f1 100%); box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(245 236 239 / 40%) !important;">확인</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--   Core JS Files   -->
    <script src="/assets/js/core/popper.min.js" type="text/javascript"></script>
    <script src="/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
    <script src="./assets/js/plugins/perfect-scrollbar.min.js"></script>
    <!--  Plugin for Parallax, full documentation here: https://github.com/wagerfield/parallax  -->
    <script src="/assets/js/plugins/parallax.min.js"></script>
    <!-- Control Center for Material UI Kit: parallax effects, scripts for the example pages etc -->
    <!--  Google Maps Plugin    -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDTTfWur0PDbZWPr7Pmq8K3jiDp0_xUziI"></script>
    <script src="/assets/js/material-kit.min.js?v=3.0.0" type="text/javascript"></script>

    <script type="text/javascript">

        document.getElementById('file').addEventListener('change', readURL, true);
        function readURL(){
            var file = document.getElementById("file").files[0];
            var reader = new FileReader();
            reader.onloadend = function(){
                document.getElementById('input-image').src = reader.result;
            }
            if(file){
                reader.readAsDataURL(file);
            }else{
            }
        }


    </script>
</body>


</html>