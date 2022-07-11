<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="/assets/assets/img/favicon.png">
    <title>비밀번호 변경</title>
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700"/>
    <!-- Nucleo Icons -->
    <link href="/assets/assets/css/nucleo-icons.css" rel="stylesheet"/>
    <link href="/assets/assets/css/nucleo-svg.css" rel="stylesheet"/>
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
    <!-- CSS Files -->
    <link id="pagestyle" href="/assets/css/material-kit.css" rel="stylesheet"/>
    <script src="/js/jquery-3.6.0.min.js"></script>

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
                            <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">비밀번호 변경</h4>
                        </div>
                    </div>
                    <div class="card-body">
                        <form role="form" action="/user/passwordChgProc" method="post">
                            <div class="input-group input-group-outline mb-3">
                                <input type="password" name="password" id="password1" class="form-control" placeholder="새 비밀번호">
                            </div>
                            <div class="input-group input-group-outline mb-3">
                                <input type="password" name="password2" id="password2" class="form-control" placeholder="새 비밀번호 확인">
                            </div>
                            <div id="auth_res"></div>
                            <div class="text-center">
                                <button type="submit" id="password_send" class="btn bg-gradient-primary w-100 my-4 mb-2"
                                        style="background-image:  linear-gradient(195deg, #49a3f1 0%, #49a3f1 100%); box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(245 236 239 / 40%) !important;">
                                    확인
                                </button>
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
<script src="/assets/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<script src="/assets/assets/js/plugins/perfect-scrollbar.min.js"></script>
<!--  Plugin for Parallax, full documentation here: https://github.com/wagerfield/parallax  -->
<script src="/assets/assets/js/plugins/parallax.min.js"></script>
<!-- Control Center for Material UI Kit: parallax effects, scripts for the example pages etc -->
<!--  Google Maps Plugin    -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDTTfWur0PDbZWPr7Pmq8K3jiDp0_xUziI"></script>
<script src="/assets/assets/js/material-kit.min.js?v=3.0.0" type="text/javascript"></script>
<script>
    $("#password2").on("propertychange change keyup paste input", function() {
        if ($("#password1").val() == $("#password2").val()) {
            $("#auth_res").text("비밀번호가 일치합니다!");
            $("#auth_res").css('color', 'blue');
            $("#password_send").attr("type","submit");
        } else {
            $("#auth_res").text("비밀번호가 일치하지 않습니다");
            $("#auth_res").css('color', 'red');
            $("#password_send").attr("type","button");
        }
    });
</script>
</script>
</body>

</html>