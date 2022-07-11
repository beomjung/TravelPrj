<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="/assets/img/favicon.png">
    <title>회원가입</title>
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700"/>
    <!-- Nucleo Icons -->
    <link href="/assets/css/nucleo-icons.css" rel="stylesheet"/>
    <link href="/assets/css/nucleo-svg.css" rel="stylesheet"/>
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
    <!-- CSS Files -->
    <link id="pagestyle" href="/assets/css/material-kit.css" rel="stylesheet"/>
    <!--jquery 사용-->
    <script src="/js/jquery-3.6.0.min.js"></script>
    <!--사용자 입력 확인-->
    <script type="text/javascript">
        function userRegCheck(f) {
            if (f.user_id.value == "") {
                alert("아이디를 입력하세요");
                f.user_id.focus();
                return false;
            }
            if (f.user_pwd.value == "") {
                alert("비밀번호를 입력하세요");
                f.user_pwd.focus();
                return false;
            }
            if (f.user_pwd2.value == "") {
                alert("비밀번호 확인을 입력하세요");
                f.user_pwd2.focus();
                return false;
            }
            if (f.user_pwd.value != f.user_pwd2.value) {
                alert("비밀번호가 다릅니다 다시 입력하세요");
                f.user_pwd.focus();
                return false;
            }
            if (f.user_email.value == "") {
                alert("이메일을 입력해주세요");
                f.user_email.focus();
                return false;
            }
            if (f.user_name.value == "") {
                alert("이름을 입력해주세요");
                f.user_name.focus();
                return false;
            }
            if (f.user_nickname.value == "") {
                alert("닉네임을 입력해주세요");
                f.user_nickname.focus();
                return false;
            }
            if (f.user_age.value == "") {
                alert("나이를 입력하세요");
                f.user_age.focus();
                return false;
            }
            if (f.gender.value === "") {
                alert("성별을 입력해 주세요");
                f.gender.focus();
                return false;
            }
            if (f.user_addr.value === "") {
                alert("주소를 입력해주세요");
                f.user_addr.focus();
                return false;
            }
        }
    </script>

    <!--아이디 중복 확인-->
    <script type="text/javascript">
        function userID_Check() {
            $.ajax({
                type: "POST",
                url: "/user/IdCheckProc",
                data: $("#user_id"),
                success(data) {
                    if (data == 1) {
                        $("#id_result").text("중복된 아이디가 있습니다.");
                        $("#id_result").css('color', 'red');
                        $("#btn_reg").attr("type","button");
                    } else {
                        $("#id_result").text('사용가능한 아이디 입니다.');
                        $("#id_result").css('color', 'blue');
                        $("#btn_reg").attr("type","submit");
                    }
                }
            })
        }

    </script>
    <!--인증메일 전송-->
    <script>
        function email_send() {
            $.ajax({
                type: "POST",
                url: "/user/emailSendProc",
                data: $("#user_email"),
                success(data) {
                    console.log(data);
                    let Random_Pin = data.Random_Pin;
                    let result = data.result;
                    let emailCheck = data.emailCheck;
                    console.log(Random_Pin);
                    console.log(result);
                    console.log(emailCheck);

                    if (emailCheck == 0) {
                        $("#email_text").text("이미 가입된 이메일입니다");
                        $("#email_text").css('color', 'red');
                    }
                    if (emailCheck == 1) {
                        if (result == 1) {
                            $("#email_text").text("입력하신 이메일로 인증번호를 발송했습니다");
                            $("#email_text").css('color', 'blue');
                            $("#btn_reg").attr("type","submit");
                        } else {
                            $("#email_text").text("이메일 발송해 실패하였습니다 다시 확인해 주세요");
                            $("#email_text").css('color', 'red');
                            $("#btn_reg").attr("type","button");
                        }
                    }
                    $("#auth_email").on("propertychange change keyup paste input", function () {
                        if (Random_Pin == $("#auth_email").val()) {
                            $("#auth_res").text("인증번호가 일치 합니다");
                            $("#auth_res").css('color', 'blue');
                            $("#btn_reg").attr("type","submit");
                        } else {
                            $("#auth_res").text("인증번호를 다시 확인해 주세요");
                            $("#auth_res").css('color', 'red');
                            $("#btn_reg").attr("type","button");

                        }
                    });
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
                            <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">회원가입</h4>
                        </div>
                    </div>
                    <div class="card-body">
                        <form role="form" class="text-start" onsubmit="return userRegCheck(this)"
                              action="/user/User_RegProc" method="post">
                            <div class="input-group input-group-outline my-3">
                                <input type="text" class="form-control" name="user_id" id="user_id" placeholder="아이디">
                            </div>
                            <div id="id_result"></div>
                            <button type="button" class="btn bg-gradient-primary w-100 my-4 mb-2"
                                    style="background-image:  linear-gradient(195deg, #49a3f1 0%, #49a3f1 100%); box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(245 236 239 / 40%) !important;"
                                    onclick="userID_Check();">아이디 중복 확인
                            </button>
                            <div class="input-group input-group-outline mb-3">
                                <input type="password" class="form-control" id="user_pwd" name="user_pwd"
                                       placeholder="비밀번호">
                            </div>
                            <div class="input-group input-group-outline mb-3">
                                <input type="password" class="form-control" id="user_pwd2" name="user_pwd2"
                                       placeholder="비밀번호 확인">
                            </div>
                            <div id="pwd_res"></div>
                            <div class="input-group input-group-outline mb-3">
                                <input type="email" class="form-control" id="user_email" name="user_email"
                                       placeholder="이메일">
                            </div>
                            <div id="email_text"></div>
                            <button type="button" class="btn bg-gradient-primary w-100 my-4 mb-2"
                                    style="background-image:  linear-gradient(195deg, #49a3f1 0%, #49a3f1 100%); box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(245 236 239 / 40%) !important;"
                                    onclick="email_send();">
                                인증번호 전송
                            </button>
                            <div class="input-group input-group-outline mb-3">
                                <input type="text" class="form-control" id="auth_email" placeholder="인증번호">
                            </div>
                            <div id="auth_res"></div>
                            <div class="input-group input-group-outline mb-3">
                                <input type="text" class="form-control" id="user_addr" name="user_addr"
                                       placeholder="주소">
                            </div>
                            <div class="input-group input-group-outline mb-3">
                                <input type="text" class="form-control" id="user_name" name="user_name"
                                       placeholder="이름">
                            </div>
                            <div class="input-group input-group-outline mb-3">
                                <input type="text" class="form-control" id="user_nickname" name="user_nickname"
                                       placeholder="닉네임">
                            </div>
                            <div class="input-group input-group-outline mb-3">
                                <input type="text" class="form-control" id="user_age" name="user_age" placeholder="나이">
                            </div>
                            <div>
                                <div class="input-group input-group-outline mb-3" id="gender">
                                    <span style="margin:auto;"><input type="radio" name="user_gender"
                                                                      value="남">남자</span>
                                    <span style="margin: auto;"><input type="radio" name="user_gender"
                                                                       value="여">여자</span>
                                    <div class="text-center">
                                    </div>
                                </div>
                                <button type="submit" class="btn bg-gradient-primary w-100 my-4 mb-2" id="btn_reg"
                                        style="background-image:  linear-gradient(195deg, #49a3f1 0%, #49a3f1 100%); box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(245 236 239 / 40%) !important;">
                                    가입하기
                                </button>
                            </div>
                            <p class="mt-4 text-sm text-center">
                                이미 가입하셨나요?
                                <a href="/user/login" class="text-primary text-gradient font-weight-bold">로그인</a>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--   Core JS Files   -->
<!--   Core JS Files   -->
<script src="/assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<script src="/assets/js/plugins/perfect-scrollbar.min.js"></script>
<!--  Plugin for Parallax, full documentation here: https://github.com/wagerfield/parallax  -->
<script src="/assets/js/plugins/parallax.min.js"></script>
<!-- Control Center for Material UI Kit: parallax effects, scripts for the example pages etc -->
<!--  Google Maps Plugin    -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDTTfWur0PDbZWPr7Pmq8K3jiDp0_xUziI"></script>
<script src="/assets/js/material-kit.min.js" type="text/javascript"></script>

<script>
    $("#user_pwd2").on("propertychange change keyup paste input", function () {
        if ($("#user_pwd").val() == $("#user_pwd2").val()) {
            $("#pwd_res").text("비밀번호가 일치합니다!");
            $("#pwd_res").css('color', 'blue');
        } else {
            $("#pwd_res").text("비밀번호가 일치하지 않습니다");
            $("#pwd_res").css('color', 'red');
        }
    });
</script>
</body>

</html>