<!--
=========================================================
* Material Kit 2 - v3.0.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-kit
* Copyright 2021 Creative Tim (https://www.creative-tim.com)
* Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="refresh" content="3; url='/user/login'"> <!--메타 태그 이용해서 페이지 이동-->
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="/assets/img/favicon.png">
    <title>index</title>
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
    <script src="/js/jquery-3.6.0.min.js"></script>


</head>

<body class="index-page bg-gray-200" onload="setInterval(getYear, 1000);">
<header class="header-2">
    <div class="page-header min-vh-75 relative"
         style="background-image: url('/assets/img/travel_1.jpg')">
        <span class="mask bg-gradient-primary opacity-4"></span>
        <div class="container">
            <div class="row">
                <div class="col-lg-7 text-center mx-auto">
                    <h1 class="text-white pt-3 mt-n5">TRAVEL</h1>
                    <!-- <p class="lead text-white mt-3">Free & Open Source Web UI Kit built over Bootstrap 5. <br/> Join over 1.6 million developers around the world. </p> -->
                </div>
            </div>
        </div>
    </div>
</header>
<div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6">
    <section class="pt-3 pb-4" id="count-stats">
        <div class="container">
            <div class="row">
                <div class="col-lg-9 mx-auto py-3">
                    <div class="row">
                        <h1 style="text-align: center;">Spring</h1>
                        </br> </br> </br>
                        <h2 style="text-align: center;" id="clock"></h2>
                    </div>
                </div>
            </div>
        </div>

    </section>
</div>
<!--   Core JS Files   -->
<script src="/assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<script src="/assets/js/plugins/perfect-scrollbar.min.js"></script>
<!--  Plugin for TypedJS, full documentation here: https://github.com/inorganik/CountUp.js -->
<script src="/assets/js/plugins/countup.min.js"></script>
<script src="/assets/js/plugins/choices.min.js"></script>
<script src="/assets/js/plugins/prism.min.js"></script>
<script src="/assets/js/plugins/highlight.min.js"></script>
<!--  Plugin for Parallax, full documentation here: https://github.com/dixonandmoe/rellax -->
<script src="/assets/js/plugins/rellax.min.js"></script>
<!--  Plugin for TiltJS, full documentation here: https://gijsroge.github.io/tilt.js/ -->
<script src="/assets/js/plugins/tilt.min.js"></script>
<!--  Plugin for Selectpicker - ChoicesJS, full documentation here: https://github.com/jshjohnson/Choices -->
<script src="/assets/js/plugins/choices.min.js"></script>
<!--  Plugin for Parallax, full documentation here: https://github.com/wagerfield/parallax  -->
<script src="/assets/js/plugins/parallax.min.js"></script>
<!-- Control Center for Material UI Kit: parallax effects, scripts for the example pages etc -->
<!--  Google Maps Plugin    -->

<script src="/assets/js/material-kit.min.js" type="text/javascript"></script>
<script type="text/javascript">

    const clock = document.getElementById("clock");

    function getClock() {
        const date = new Date()
        const year = date.getFullYear();
        const month = date.getMonth();
        const day = date.getDay();
        const hour = String(date.getHours()).padStart(2, "0");
        const minutes = String(date.getMinutes()).padStart(2, "0");
        const second = String(date.getSeconds()).padStart(2, "0");//number이기 때문에 padStart 붙일 수 없음. String 변환해주어야한다.
        clock.innerText = year + "년 " + month + "월 " + day + "일 " + hour + "시 " + minutes + "분 " + second + "초"
    }

    getClock();
    setInterval(getClock, 1000);
</script>

</body>

</html>