<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.dto.UserDTO" %>
<%@ page import="kopo.poly.dto.FollowDTO" %>
<%@ page import="java.util.List" %>
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

<%
    UserDTO pDTO = (UserDTO) request.getAttribute("pDTO");
    //팔로잉 유저 조회
    List<FollowDTO> rList = (List<FollowDTO>) request.getAttribute("rList");

    //팔로워 유저 조회
    List<FollowDTO> pList = (List<FollowDTO>) request.getAttribute("pList");
%>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="/assets/img/favicon.png">
    <title>팔로잉,팔로워 유저 조회</title>
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
<!-- -------- END HEADER 4 w/ search book a ticket form ------- -->
<div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4">
    <!-- START Testimonials w/ user image & text & info -->
    <section class="py-sm-7 py-5 position-relative">
        <div class="container">
            <div class="row">
                <div class="col-12 mx-auto">
                    <div class="mt-n8 mt-md-n9 text-center">
                        <img class="avatar avatar-xxl shadow-xl position-relative z-index-2"
                             src="<%=pDTO.getFile_detail_path()%>"
                             alt="회원정보를 수정해 이미지를 추가해주세요" loading="lazy">
                        <br>
                    </div>
                    <div class="row py-5">
                        <div class="col-lg-7 col-md-7 z-index-2 position-relative px-md-2 px-sm-5 mx-auto">
                            <div class="d-flex justify-content-between align-items-center mb-2">
                                <h3 class="mb-0"><%=pDTO.getUser_nickname()%>
                                </h3>

                            </div>
                            <div class="row mb-4">
                                <div class="col-auto">
                                    <span class="h6"><%=pDTO.getBoard_cnt()%></span>
                                    <span>Posts</span>
                                </div>
                                <div class="col-auto">
                                    <span class="h6"><%=pDTO.getFollower()%></span>
                                    <span>Followers</span>
                                </div>
                                <div class="col-auto">
                                    <span class="h6"><%=pDTO.getFollowing()%></span>
                                    <span>Following</span>
                                </div>
                            </div>
                            <p class="text-lg mb-0"><%=pDTO.getUser_introducation()%>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- END Testimonials w/ user image & text & info -->
    <!-- START Blogs w/ 4 cards w/ image & text & link -->
    <!-- START Blogs w/ 4 cards w/ image & text & link -->
    <section class="py-3">
        <!-- START Teams w/ 4 cards in a row w/ avatar on bottom end-->
        <section class="py-8">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 mx-auto text-center">
                        <h4 class="text-gradient text-primary">팔로잉</h4>
                    </div>
                </div>
                <div class="row mt-lg-5 mt-4">
                    <%
                        if(rList != null){
                            for(int i = 0; i<rList.size(); i++){

                    %>
                    <div class="col-lg-4 col-sm-6 mb-4" style="margin-top: 10px;">
                        <div class="card card-body">
                            <div class="row">
                                <div class="col-lg-5 text-end my-auto">
                                    <img class="avatar avatar-xxl border-radius-lg mt-n5" src="<%=rList.get(i).getUser_file_path()%>">
                                </div>
                                <div class="col-lg-7">
                                    <h5 class="mb-2"><%=rList.get(i).getUser_nickname()%></h5>
                                    <h7><%=rList.get(i).getUser_introducation()%></h7>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                        }
                    %>
                </div>
            </div>
        </section>
        <section class="py-8">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 mx-auto text-center">
                        <h4 class="text-gradient text-primary">팔로워</h4>
                    </div>
                </div>
                <div class="row mt-lg-5 mt-4">
                    <%
                        if(pList != null){
                            for(int i=0; i<pList.size(); i++){
                    %>
                    <div class="col-lg-4 col-sm-6 mb-4" style="margin-top: 10px;">
                        <div class="card card-body">
                            <div class="row">
                                <div class="col-lg-5 text-end my-auto">
                                    <img class="avatar avatar-xxl border-radius-lg mt-n5" src="<%=pList.get(i).getUser_file_path()%>">
                                </div>
                                <div class="col-lg-7">
                                    <h5 class="mb-2"><%=pList.get(i).getUser_nickname()%></h5>
                                    <h7><%=pList.get(i).getUser_introducation()%></h7>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                        }
                    %>
                </div>
            </div>
        </section>
        <!-- END Teams w/ 4 cards in a row w/ avatar on bottom end-->
    </section>
    <!-- END Blogs w/ 4 cards w/ image & text & link -->
</div>
<!--   Core JS Files   -->
<script src="/assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<script src="/assets/js/plugins/perfect-scrollbar.min.js"></script>
<!--  Plugin for Parallax, full documentation here: https://github.com/wagerfield/parallax  -->
<script src="/assets/js/plugins/parallax.min.js"></script>
<!-- Control Center for Material UI Kit: parallax effects, scripts for the example pages etc -->
<!--  Google Maps Plugin    -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDTTfWur0PDbZWPr7Pmq8K3jiDp0_xUziI"></script>
<script src="/assets/js/material-kit.min.js?v=3.0.0" type="text/javascript"></script>
</body>

</html>