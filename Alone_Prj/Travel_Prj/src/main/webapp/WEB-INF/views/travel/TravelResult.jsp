<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.dto.TravelLocDTO" %>
<%@ page import="kopo.poly.dto.TravelReInfoDTO" %>
<%@ page import="kopo.poly.dto.TravelManyInfoDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.UserDTO" %>

<%
    UserDTO uDTO = (UserDTO) request.getAttribute("uDTO");
    List<TravelLocDTO> tList = (List<TravelLocDTO>) request.getAttribute("tList");
    List<TravelLocDTO> rList = (List<TravelLocDTO>) request.getAttribute("pList");
%>

<!--
=========================================================
* Material Kit 2 - v3.0.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-kit
* Copyright 2021 Creative Tim (https://www.creative-tim.com)
* Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->
<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="/assets/img/favicon.png">
    <title>회원 여행지 선택</title>
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
    <link id="pagestyle" href="/assets/css/material-kit.css?v=3.0.0" rel="stylesheet"/>
    <script src="/js/city.js"></script>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <script src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xx34359a5f37ea4a94a022196151b0c4e3"></script>

</head>

<body class="blog-author bg-gray-200">
<!-- Navbar Transparent -->
<!-- Navbar Transparent -->
<div class="container position-sticky z-index-sticky top-0">
    <div class="row">
        <div class="col-12">
            <nav class="navbar navbar-expand-lg  blur border-radius-xl top-0 z-index-fixed shadow position-absolute my-3 py-2 start-0 end-0 mx-4">
                <div class="container-fluid px-0">
                    <a class="navbar-brand font-weight-bolder ms-sm-3" onclick="location.href='/main/home'"
                       rel="tooltip" title="Designed and Coded by Creative Tim" data-placement="bottom" target="_blank">
                        HOME
                    </a>
                    <button class="navbar-toggler shadow-none ms-2" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navigation" aria-controls="navigation" aria-expanded="false"
                            aria-label="Toggle navigation">
      <span class="navbar-toggler-icon mt-2">
        <span class="navbar-toggler-bar bar1"></span>
        <span class="navbar-toggler-bar bar2"></span>
        <span class="navbar-toggler-bar bar3"></span>
      </span>
                    </button>
                    <div class="collapse navbar-collapse pt-3 pb-2 py-lg-0 w-100" id="navigation">
                        <ul class="navbar-nav navbar-nav-hover ms-auto">
                            <li class="nav-item dropdown dropdown-hover mx-2">
                                <a class="nav-link ps-2 d-flex cursor-pointer align-items-center" id="dropdownMenuPages"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="material-icons opacity-6 me-2 text-md">dashboard</i>
                                    myPage
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-animation dropdown-md dropdown-md-responsive p-3 border-radius-lg mt-0 mt-lg-3"
                                    aria-labelledby="dropdownMenuBlocks">
                                    <div class="d-none d-lg-block">
                                        <li class="nav-item dropdown dropdown-hover dropdown-subitem">
                                            <a class="dropdown-item py-2 ps-3 border-radius-md"
                                               onclick="location.href='/main/myPage2'">
                                                <div class="w-100 d-flex align-items-center justify-content-between">
                                                    <div>
                                                        <h6 class="dropdown-header text-dark font-weight-bolder d-flex justify-content-cente align-items-center p-0">
                                                            myPage</h6>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="nav-item dropdown dropdown-hover dropdown-subitem">
                                            <a class="dropdown-item py-2 ps-3 border-radius-md"
                                               onclick="location.href='/user/userDelete'">
                                                <div class="w-100 d-flex align-items-center justify-content-between">
                                                    <div>
                                                        <h6 class="dropdown-header text-dark font-weight-bolder d-flex justify-content-cente align-items-center p-0">
                                                            Withdrawal</h6>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="nav-item dropdown dropdown-hover dropdown-subitem">
                                            <a class="dropdown-item py-2 ps-3 border-radius-md"
                                               onclick="location.href='/user/passwordChg'">
                                                <div class="w-100 d-flex align-items-center justify-content-between">
                                                    <div>
                                                        <h6 class="dropdown-header text-dark font-weight-bolder d-flex justify-content-cente align-items-center p-0">
                                                            PassChg</h6>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </div>
                                </ul>
                            </li>
                            <li class="nav-item dropdown dropdown-hover mx-2">
                                <a class="nav-link ps-2 d-flex cursor-pointer align-items-center" id="dropdownMenuPages"
                                   data-bs-toggle="dropdown" aria-expanded="false"
                                   onclick="location.href='/main/bookMark'">
                                    <i class="material-icons opacity-6 me-2 text-md">dashboard</i>
                                    BookMark
                                </a>
                            </li>

                            <li class="nav-item dropdown dropdown-hover mx-2">
                                <a class="nav-link ps-2 d-flex cursor-pointer align-items-center"
                                   id="dropdownMenuBlocks" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="material-icons opacity-6 me-2 text-md">view_day</i>
                                    Post
                                    <img src="/assets/img/down-arrow-dark.svg" alt="down-arrow"
                                         class="arrow ms-auto ms-md-2">
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-animation dropdown-md dropdown-md-responsive p-3 border-radius-lg mt-0 mt-lg-3"
                                    aria-labelledby="dropdownMenuBlocks">
                                    <div class="d-none d-lg-block">
                                        <li class="nav-item dropdown dropdown-hover dropdown-subitem">
                                            <a class="dropdown-item py-2 ps-3 border-radius-md"
                                               onclick="location.href='/board/boardWrite'">
                                                <div class="w-100 d-flex align-items-center justify-content-between">
                                                    <div>
                                                        <h6 class="dropdown-header text-dark font-weight-bolder d-flex justify-content-cente align-items-center p-0">
                                                            PostdWrite</h6>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="nav-item dropdown dropdown-hover dropdown-subitem">
                                            <a class="dropdown-item py-2 ps-3 border-radius-md"
                                               onclick="location.href='/board/boardList'">
                                                <div class="w-100 d-flex align-items-center justify-content-between">
                                                    <div>
                                                        <h6 class="dropdown-header text-dark font-weight-bolder d-flex justify-content-cente align-items-center p-0">
                                                            PostList</h6>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </div>
                                </ul>
                            </li>
                            <li class="nav-item dropdown dropdown-hover mx-2">
                                <a class="nav-link ps-2 d-flex cursor-pointer align-items-center"
                                   id="dropdownMenuBlocks" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="material-icons opacity-6 me-2 text-md">view_day</i>
                                    Follow
                                    <img src="/assets/img/down-arrow-dark.svg" alt="down-arrow"
                                         class="arrow ms-auto ms-md-2">
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-animation dropdown-md dropdown-md-responsive p-3 border-radius-lg mt-0 mt-lg-3"
                                    aria-labelledby="dropdownMenuBlocks">
                                    <div class="d-none d-lg-block">
                                        <li class="nav-item dropdown dropdown-hover dropdown-subitem">
                                            <a class="dropdown-item py-2 ps-3 border-radius-md"
                                               onclick="location.href='/main/Follow'">
                                                <div class="w-100 d-flex align-items-center justify-content-between">
                                                    <div>
                                                        <h6 class="dropdown-header text-dark font-weight-bolder d-flex justify-content-cente align-items-center p-0">
                                                            FolloList</h6>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="nav-item dropdown dropdown-hover dropdown-subitem">
                                            <a class="dropdown-item py-2 ps-3 border-radius-md"
                                               onclick="location.href='/main/FollowPost'">
                                                <div class="w-100 d-flex align-items-center justify-content-between">
                                                    <div>
                                                        <h6 class="dropdown-header text-dark font-weight-bolder d-flex justify-content-cente align-items-center p-0">
                                                            FollowPost</h6>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </div>
                                </ul>
                            </li>
                            <li class="nav-item dropdown dropdown-hover mx-2">
                                <a class="nav-link ps-2 d-flex cursor-pointer align-items-center"
                                   id="dropdownMenuBlocks" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="material-icons opacity-6 me-2 text-md">view_day</i>
                                    Travel
                                    <img src="/assets/img/down-arrow-dark.svg" alt="down-arrow"
                                         class="arrow ms-auto ms-md-2">
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-animation dropdown-md dropdown-md-responsive p-3 border-radius-lg mt-0 mt-lg-3"
                                    aria-labelledby="dropdownMenuBlocks">
                                    <div class="d-none d-lg-block">
                                        <li class="nav-item dropdown dropdown-hover dropdown-subitem">
                                            <a class="dropdown-item py-2 ps-3 border-radius-md"
                                               onclick="location.href='/travel/koreaTravel'">
                                                <div class="w-100 d-flex align-items-center justify-content-between">
                                                    <div>
                                                        <h6 class="dropdown-header text-dark font-weight-bolder d-flex justify-content-cente align-items-center p-0">
                                                            KoreaTravel</h6>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="nav-item dropdown dropdown-hover dropdown-subitem">
                                            <a class="dropdown-item py-2 ps-3 border-radius-md"
                                               onclick="location.href='/travel/TravelRecommend'">
                                                <div class="w-100 d-flex align-items-center justify-content-between">
                                                    <div>
                                                        <h6 class="dropdown-header text-dark font-weight-bolder d-flex justify-content-cente align-items-center p-0">
                                                            RecommendTravel</h6>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="nav-item dropdown dropdown-hover dropdown-subitem">
                                            <a class="dropdown-item py-2 ps-3 border-radius-md"
                                               onclick="location.href='/travel/userTravelChoice'">
                                                <div class="w-100 d-flex align-items-center justify-content-between">
                                                    <div>
                                                        <h6 class="dropdown-header text-dark font-weight-bolder d-flex justify-content-cente align-items-center p-0">
                                                            UserChoiceTravel</h6>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </div>
                                </ul>
                            </li>

                            <li class="nav-item ms-lg-auto">

                            </li>
                            <li class="nav-item my-auto ms-3 ms-lg-0">

                                <a onclick="location.href='/user/logOutProc'"
                                   class="btn btn-sm  bg-gradient-primary  mb-0 me-1 mt-2 mt-md-0">로그아웃</a>

                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- End Navbar -->
        </div>
    </div>
</div>
<!-- -------- START HEADER 4 w/ search book a ticket form ------- -->
<header>
    <div class="page-header min-height-400"
         style="background-image: url('../../../../resources/static/assets/img/city-profile.jpg');"
         loading="lazy">
        <span class="mask bg-gradient-dark opacity-8"></span>
    </div>
</header>
<!-- START Blogs w/ 4 cards w/ image & text & link -->
<!-- START Blogs w/ 4 cards w/ image & text & link -->
<section class="py-5">
    <div class="container">
        <div class="row">
            <h3>선택한 여행</h3>
            <%
                for (int i = 0; i < rList.size(); i++) {
                    if(rList.get(i).getFristimage().equals("null")){
                        rList.get(i).setFristimage("/assets/img/noImage.jpeg");
                    }
                    if(rList.get(i).getReadCnt().equals("null")){
                        rList.get(i).setReadCnt("");
                    }
                    if(rList.get(i).getTitle().equals("null")){
                        rList.get(i).setTitle("");
                    }
            %>
            <div class="col-lg-3 col-sm-6">
                <div class="card card-plain card-blog mt-4">
                    <div class="card-header p-0 position-relative z-index-2">
                        <a class="d-block blur-shadow-image">
                            <img src="<%=rList.get(i).getFristimage()%>" alt="img-blur-shadow"
                                 class="img-fluid border-radius-lg" style="object-fit: cover;">
                        </a>
                    </div>
                    <div class="card-body px-0">
                        <p class="text-uppercase font-weight-bold text-info text-xs">조회수
                            : <%=rList.get(i).getReadCnt()%>
                        </p>
                        <h5>
                            <a href="javascript:;" class="text-dark font-weight-bold"><%=rList.get(i).getTitle()%>
                            </a>
                        </h5>
                    </div>
                </div>
            </div>
            <%
                }
            %>

        </div>
    </div>
</section>
<!-- START Blogs w/ big image on left -->
<div class="container">
    <button type="button" id="btn_select" class="btn bg-gradient-info w-auto me-2" style="display: inline; margin-left: 30px;">경로 및 요금확인
    </button>
    <p id="result"></p>
    <div id="map_div"></div>
</div>
<hr/>
<section class="py-4 position-relative">
    <div id="carousel-testimonial-pricing" class="carousel slide carousel-team" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="container">
                    <div class="row align-items-center">
                        <%
                            if(tList.get(0).getFristimage().equals("null")){
                                tList.get(0).setFristimage("/assets/img/noImage.jpeg");
                            }
                            if(tList.get(0).getTitle().equals("null")){
                                tList.get(0).setTitle("");
                            }
                        %>
                        <div class="col-md-5 p-lg-5 ms-lg-auto">
                            <div class="p-3">
                                <img class="w-100 border-radius-md shadow-lg" src="<%=tList.get(0).getFristimage()%>"
                                     alt="First slide" style="width:450px; height:600px;object-fit: cover;">
                            </div>
                        </div>
                        <div class="col-lg-5 col-md-7 me-lg-auto position-relative">
                            <h3 class="text-dark"><%=tList.get(0).getTitle()%>
                            </h3>
                            <p class="my-4">
                                <i><%=tList.get(0).getOverview()%>
                                </i>
                            </p>
                            <div class="w-25 ms-auto opacity-2 mt-n8">
                                <svg width="110px" height="110px" viewBox="0 0 270 270" version="1.1"
                                     xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                    <title>quote-down</title>
                                    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                        <path d="M107.000381,49.033238 C111.792099,48.01429 115.761022,48.6892564 116.625294,50.9407629 C117.72393,53.8028077 113.174473,58.3219079 107.017635,60.982801 C107.011653,60.9853863 107.00567,60.9879683 106.999688,60.990547 C106.939902,61.0219589 106.879913,61.0439426 106.820031,61.0659514 C106.355389,61.2618887 105.888177,61.4371549 105.421944,61.5929594 C88.3985192,68.1467602 80.3242628,76.9161885 70.3525495,90.6906738 C60.0774843,104.884196 54.9399518,119.643717 54.9399518,134.969238 C54.9399518,138.278158 55.4624127,140.716309 56.5073346,142.283691 C57.2039492,143.328613 57.9876406,143.851074 58.8584088,143.851074 C59.7291771,143.851074 61.0353294,143.241536 62.7768659,142.022461 C68.3497825,138.016927 75.4030052,136.01416 83.9365338,136.01416 C93.8632916,136.01416 102.658051,140.063232 110.320811,148.161377 C117.983572,156.259521 121.814952,165.88151 121.814952,177.027344 C121.814952,188.695638 117.417572,198.970703 108.622813,207.852539 C99.828054,216.734375 89.1611432,221.175293 76.6220807,221.175293 C61.9931745,221.175293 49.3670351,215.166992 38.7436627,203.150391 C28.1202903,191.133789 22.8086041,175.024577 22.8086041,154.822754 C22.8086041,131.312012 30.0359804,110.239421 44.490733,91.6049805 C58.2196377,73.906272 74.3541002,59.8074126 102.443135,50.4450748 C102.615406,50.3748509 102.790055,50.3058192 102.966282,50.2381719 C104.199241,49.7648833 105.420135,49.3936487 106.596148,49.1227802 L107,49 Z M233.000381,49.033238 C237.792099,48.01429 241.761022,48.6892564 242.625294,50.9407629 C243.72393,53.8028077 239.174473,58.3219079 233.017635,60.982801 C233.011653,60.9853863 233.00567,60.9879683 232.999688,60.990547 C232.939902,61.0219589 232.879913,61.0439426 232.820031,61.0659514 C232.355389,61.2618887 231.888177,61.4371549 231.421944,61.5929594 C214.398519,68.1467602 206.324263,76.9161885 196.352549,90.6906738 C186.077484,104.884196 180.939952,119.643717 180.939952,134.969238 C180.939952,138.278158 181.462413,140.716309 182.507335,142.283691 C183.203949,143.328613 183.987641,143.851074 184.858409,143.851074 C185.729177,143.851074 187.035329,143.241536 188.776866,142.022461 C194.349783,138.016927 201.403005,136.01416 209.936534,136.01416 C219.863292,136.01416 228.658051,140.063232 236.320811,148.161377 C243.983572,156.259521 247.814952,165.88151 247.814952,177.027344 C247.814952,188.695638 243.417572,198.970703 234.622813,207.852539 C225.828054,216.734375 215.161143,221.175293 202.622081,221.175293 C187.993174,221.175293 175.367035,215.166992 164.743663,203.150391 C154.12029,191.133789 148.808604,175.024577 148.808604,154.822754 C148.808604,131.312012 156.03598,110.239421 170.490733,91.6049805 C184.219638,73.906272 200.3541,59.8074126 228.443135,50.4450748 C228.615406,50.3748509 228.790055,50.3058192 228.966282,50.2381719 C230.199241,49.7648833 231.420135,49.3936487 232.596148,49.1227802 L233,49 Z"
                                              fill="#48484A" fill-rule="nonzero"
                                              transform="translate(135.311778, 134.872794) scale(-1, -1) translate(-135.311778, -134.872794) "></path>
                                    </g>
                                </svg>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%
                for (int i = 1; i < tList.size(); i++) {
                    if(tList.get(i).getFristimage().equals("null")){
                        tList.get(i).setFristimage("/assets/img/noImage.jpeg");
                    }
                    if(tList.get(i).getTitle().equals("null")){
                        tList.get(i).setTitle("");
                    }
            %>
            <div class="carousel-item">

                <div class="container">

                    <div class="row align-items-center">
                        <div class="col-md-5 p-lg-5 ms-lg-auto">
                            <div class="p-3">
                                <img class="w-100 border-radius-md shadow" src="<%=tList.get(i).getFristimage()%>"
                                     alt="First slide" style="width:450px; height:600px;object-fit: cover;">
                            </div>
                        </div>
                        <div class="col-lg-5 col-md-7 me-lg-auto">
                            <h3 class="text-dark"><%=tList.get(i).getTitle()%>
                            </h3>
                            <p class="my-4">
                                <i><%=tList.get(i).getOverview()%>
                                </i>
                            </p>
                            <div class="w-25 ms-auto opacity-2 mt-n8">
                                <svg width="110px" height="110px" viewBox="0 0 270 270" version="1.1"
                                     xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                    <title>quote-down</title>
                                    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                        <path d="M107.000381,49.033238 C111.792099,48.01429 115.761022,48.6892564 116.625294,50.9407629 C117.72393,53.8028077 113.174473,58.3219079 107.017635,60.982801 C107.011653,60.9853863 107.00567,60.9879683 106.999688,60.990547 C106.939902,61.0219589 106.879913,61.0439426 106.820031,61.0659514 C106.355389,61.2618887 105.888177,61.4371549 105.421944,61.5929594 C88.3985192,68.1467602 80.3242628,76.9161885 70.3525495,90.6906738 C60.0774843,104.884196 54.9399518,119.643717 54.9399518,134.969238 C54.9399518,138.278158 55.4624127,140.716309 56.5073346,142.283691 C57.2039492,143.328613 57.9876406,143.851074 58.8584088,143.851074 C59.7291771,143.851074 61.0353294,143.241536 62.7768659,142.022461 C68.3497825,138.016927 75.4030052,136.01416 83.9365338,136.01416 C93.8632916,136.01416 102.658051,140.063232 110.320811,148.161377 C117.983572,156.259521 121.814952,165.88151 121.814952,177.027344 C121.814952,188.695638 117.417572,198.970703 108.622813,207.852539 C99.828054,216.734375 89.1611432,221.175293 76.6220807,221.175293 C61.9931745,221.175293 49.3670351,215.166992 38.7436627,203.150391 C28.1202903,191.133789 22.8086041,175.024577 22.8086041,154.822754 C22.8086041,131.312012 30.0359804,110.239421 44.490733,91.6049805 C58.2196377,73.906272 74.3541002,59.8074126 102.443135,50.4450748 C102.615406,50.3748509 102.790055,50.3058192 102.966282,50.2381719 C104.199241,49.7648833 105.420135,49.3936487 106.596148,49.1227802 L107,49 Z M233.000381,49.033238 C237.792099,48.01429 241.761022,48.6892564 242.625294,50.9407629 C243.72393,53.8028077 239.174473,58.3219079 233.017635,60.982801 C233.011653,60.9853863 233.00567,60.9879683 232.999688,60.990547 C232.939902,61.0219589 232.879913,61.0439426 232.820031,61.0659514 C232.355389,61.2618887 231.888177,61.4371549 231.421944,61.5929594 C214.398519,68.1467602 206.324263,76.9161885 196.352549,90.6906738 C186.077484,104.884196 180.939952,119.643717 180.939952,134.969238 C180.939952,138.278158 181.462413,140.716309 182.507335,142.283691 C183.203949,143.328613 183.987641,143.851074 184.858409,143.851074 C185.729177,143.851074 187.035329,143.241536 188.776866,142.022461 C194.349783,138.016927 201.403005,136.01416 209.936534,136.01416 C219.863292,136.01416 228.658051,140.063232 236.320811,148.161377 C243.983572,156.259521 247.814952,165.88151 247.814952,177.027344 C247.814952,188.695638 243.417572,198.970703 234.622813,207.852539 C225.828054,216.734375 215.161143,221.175293 202.622081,221.175293 C187.993174,221.175293 175.367035,215.166992 164.743663,203.150391 C154.12029,191.133789 148.808604,175.024577 148.808604,154.822754 C148.808604,131.312012 156.03598,110.239421 170.490733,91.6049805 C184.219638,73.906272 200.3541,59.8074126 228.443135,50.4450748 C228.615406,50.3748509 228.790055,50.3058192 228.966282,50.2381719 C230.199241,49.7648833 231.420135,49.3936487 232.596148,49.1227802 L233,49 Z"
                                              fill="#48484A" fill-rule="nonzero"
                                              transform="translate(135.311778, 134.872794) scale(-1, -1) translate(-135.311778, -134.872794) "></path>
                                    </g>
                                </svg>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
            <%
                }
            %>
        </div>
        <a class="carousel-control-prev text-dark text-lg" href="#carousel-testimonial-pricing" role="button"
           data-bs-slide="prev">
            <i class="fas fa-chevron-left"></i>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next text-dark text-lg" href="#carousel-testimonial-pricing" role="button"
           data-bs-slide="next">
            <i class="fas fa-chevron-right"></i>
            <span class="sr-only">Next</span>
        </a>
    </div>
</section>

<!-- END Blogs w/ big image on left -->
<!-- END Blogs w/ 4 cards w/ image & text & link -->
<!-- END Blogs w/ 4 cards w/ image & text & link -->
<hr/>

<!-- END Blogs w/ big image on left -->

</div>
<!--   Core JS Files   -->
<script src="/assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<script src="/assets/js/plugins/perfect-scrollbar.min.js"></script>
<!--  Plugin for Parallax, full documentation here: https://github.com/wagerfield/parallax  -->
<script src="/assets/js/plugins/parallax.min.js"></script>
<!-- Control Center for Material UI Kit: parallax effects, scripts for the example pages etc -->
<!--  Google Maps Plugin    -->
<script src="/assets/js/material-kit.min.js?v=3.0.0" type="text/javascript"></script>
<!--지도 띄우기-->
<script>

    //시작 값
    var start_x = <%=rList.get(0).getMapx()%>;
    var start_x1 = String(<%=rList.get(0).getMapx()%>);
    var start_y =  <%=rList.get(0).getMapy()%>;
    var start_y1 = String(<%=rList.get(0).getMapy()%>);
    //끝 값
    var end_x =  <%=rList.get(3).getMapx()%>;
    var end_y = <%=rList.get(3).getMapy()%>;;

    var end_x1 =  String(<%=rList.get(3).getMapx()%>);
    var end_y1 = String(<%=rList.get(3).getMapy()%>);

    //경유지 1
    var arry_x =  <%=rList.get(1).getMapx()%>;
    var arry_y = <%=rList.get(1).getMapy()%>;;

    var arry_x1 =  String(<%=rList.get(1).getMapx()%>);
    var arry_y1 = String(<%=rList.get(1).getMapy()%>);

    //경유지 2
    var arry2_x =  <%=rList.get(2).getMapx()%>;
    var arry2_y = <%=rList.get(2).getMapy()%>;;

    var arry2_x1 =  String(<%=rList.get(2).getMapx()%>);
    var arry2_y1 = String(<%=rList.get(2).getMapy()%>);

    var lon, lat;

    var map;
    var marker_s, marekr_e;

    let user_addr = "<%=uDTO.getUser_addr()%>";
    //경로그림정보
    var drawInfoArr = [];
    console.table(arry_x, arry_y);

    window.onload=function initTmap() {

        $("#btn_select").click(function () {
            // 2. API 사용요청
            var fullAddr = user_addr;
            $.ajax({
                method: "GET",
                url: "https://apis.openapi.sk.com/tmap/geo/fullAddrGeo?version=1&format=json&callback=result",
                async: false,
                data: {
                    "appKey": "l7xx34359a5f37ea4a94a022196151b0c4e3",
                    "coordType": "WGS84GEO",
                    "fullAddr": fullAddr
                },
                success: function (response) {

                    var resultInfo = response.coordinateInfo; // .coordinate[0];
                    console.log(resultInfo);

                    // 3.마커 찍기
                    // 검색 결과 정보가 없을 때 처리
                    if (resultInfo.coordinate.length == 0) {
                        $("#result").text(
                            "요청 데이터가 올바르지 않습니다.");
                    } else {
                        var resultCoordinate = resultInfo.coordinate[0];
                        if (resultCoordinate.lon.length > 0) {
                            // 구주소
                            lon = resultCoordinate.lon;
                            lat = resultCoordinate.lat;
                        } else {
                            // 신주소
                            lon = resultCoordinate.newLon;
                            lat = resultCoordinate.newLat
                        }
                    }
                    console.log(lat, lon);
                    // 1. 지도 띄우기
                    map = new Tmapv2.Map("map_div", {
                        center: new Tmapv2.LatLng(37.56701114710962, 126.9973611831669),
                        width: "100%",
                        height: "400px",
                        zoom: 15,
                        zoomControl: true,
                        scrollwheel: true
                    });

                    // 시작, 도착 심볼찍기
                    // 시작
                    marker_s = new Tmapv2.Marker({
                        position: new Tmapv2.LatLng(lat, lon),
                        icon: "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png",
                        iconSize: new Tmapv2.Size(24, 38),
                        map: map
                    });

                    // 도착
                    marker_e = new Tmapv2.Marker({
                        position: new Tmapv2.LatLng(end_y, end_x),
                        icon: "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png",
                        iconSize: new Tmapv2.Size(24, 38),
                        map: map
                    });

                    marker = new Tmapv2.Marker({
                        position: new Tmapv2.LatLng(start_y, start_x),
                        icon: "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_1.png",
                        iconSize: new Tmapv2.Size(24, 38),
                        map: map
                    });

                    marker = new Tmapv2.Marker({
                        position: new Tmapv2.LatLng(arry_y, arry_x),
                        icon: "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_2.png",
                        iconSize: new Tmapv2.Size(24, 38),
                        map: map
                    });

                    marker = new Tmapv2.Marker({
                        position: new Tmapv2.LatLng(arry2_y, arry2_x),
                        icon: "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_3.png",
                        iconSize: new Tmapv2.Size(24, 38),
                        map: map
                    });
                    var headers = {};
                    headers["appKey"] = "l7xx34359a5f37ea4a94a022196151b0c4e3";

                    $.ajax({
                        type: "POST",
                        headers: headers,
                        url: "https://apis.openapi.sk.com/tmap/routes/routeOptimization10?version=2&format=json",//
                        async: false,
                        contentType: "application/json",
                        data: JSON.stringify({
                            "reqCoordType": "WGS84GEO",
                            "resCoordType": "EPSG3857",
                            "startName": "출발",
                            "startX": lon,
                            "startY": lat,
                            "startTime": "202206160203",
                            "endName": "도착",
                            "endX": end_x1,
                            "endY": end_y1,
                            "searchOption": "0",
                            "viaPoints": [
                                {
                                    "viaPointId": "test01",
                                    "viaPointName": "test01",
                                    "viaX": start_x1,
                                    "viaY": start_y1,
                                },
                                {
                                    "viaPointId": "test02",
                                    "viaPointName": "test02",
                                    "viaX": arry_x1,
                                    "viaY": arry_y1,
                                },
                                {
                                    "viaPointId": "test03",
                                    "viaPointName": "test03",
                                    "viaX": arry2_x1,
                                    "viaY": arry2_y1,
                                }
                            ]
                        }),
                        success: function (response) {
                            console.log("성공?");
                            console.log(response);
                            var resultData = response.properties;
                            var resultFeatures = response.features;

                            // 결과 출력
                            var tDistance = "총 거리 : " + (resultData.totalDistance / 1000).toFixed(1) + "km,  ";
                            var tTime = "총 시간 : " + (resultData.totalTime / 60).toFixed(0) + "분,  ";
                            var tFare = "총 요금 : " + resultData.totalFare + "원";

                            $("#result").text(tDistance + tTime + tFare);

                            for (var i in resultFeatures) {
                                console.log(i);
                                var geometry = resultFeatures[i].geometry;
                                var properties = resultFeatures[i].properties;
                                console.log("properties", properties);
                                var polyline_;

                                drawInfoArr = [];

                                if (geometry.type == "LineString") {
                                    for (var j in geometry.coordinates) {
                                        // 경로들의 결과값(구간)들을 포인트 객체로 변환
                                        var latlng = new Tmapv2.Point(geometry.coordinates[j][0], geometry.coordinates[j][1]);
                                        // 포인트 객체를 받아 좌표값으로 변환
                                        var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(latlng);
                                        // 포인트객체의 정보로 좌표값 변환 객체로 저장
                                        var convertChange = new Tmapv2.LatLng(convertPoint._lat, convertPoint._lng);

                                        drawInfoArr.push(convertChange);
                                    }

                                    polyline_ = new Tmapv2.Polyline({
                                        path: drawInfoArr,
                                        strokeColor: "#FF0000",
                                        strokeWeight: 4,
                                        map: map
                                    });
                                }
                            }
                        },
                        error: function (request, status, error) {
                            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                        }
                    });

                },
                error: function (request, status, error) {
                    console.log(request);
                    console.log("code:" + request.status + "\n message:" + request.responseText + "\n error:" + error);
                    // 에러가 발생하면 맵을 초기화함
                    // markerStartLayer.clearMarkers();
                    // 마커초기화
                    map.setCenter(new Tmapv2.LatLng(37.570028, 126.986072));
                    $("#result").html("");

                }
            });
        });

    }


</script>

</body>

</html>