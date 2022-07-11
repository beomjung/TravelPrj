<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.dto.UserDTO" %>
<%@ page import="kopo.poly.dto.TravelLocDTO" %>
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
    List<TravelLocDTO> rList = (List<TravelLocDTO>) request.getAttribute("rList");
    String send_onclick = "";

%>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="/assets/img/favicon.png">
    <title>국내 관광지</title>
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
<form name="form" action="/travel/koreaTravel" method="get">
    <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6">
        <div class="container">
            <div class="row border-radius-md pb-4 p-3 mx-sm-0 mx-1 position-relative">
                <div class="col-lg-3 mt-lg-n2 mt-2">
                    <label class="ms-0">지역</label>
                    <select class="form-control" name="location" id="big_city" onchange="chanCity(value)">
                        <option value="1" selected="">서울</option>
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
                </div>
                <div class="col-lg-3 mt-lg-n2 mt-2">
                    <label class="ms-0">시군구</label>
                    <select class="form-control" name="smallcity" id="small_city">
                        <option value="" selected=""></option>
                    </select>
                </div>
                <div class="col-lg-3 mt-lg-n2 mt-2">
                    <label class="ms-0">관광타입</label>
                    <select class="form-control" name="travel_type" id="travel_type" onchange="TravelChoice(value)">
                        <option value="" selected="">전체</option>
                        <option value="12">관광지</option>
                        <option value="14">문화시설</option>
                        <option value="15">축제공연행사</option>
                        <option value="25">여행코스</option>
                        <option value="28">레포츠</option>
                        <option value="32">숙박</option>
                        <option value="38">쇼핑</option>
                        <option value="39">음식점</option>
                    </select>
                </div>

                <div class="col-lg-3 mt-lg-n2 mt-2">
                    <label>&nbsp;</label>
                    <button type="button" class="btn bg-gradient-primary w-100 mb-0" style="display: none;">Search
                    </button>
                </div>

            </div>
        </div>
        <div class="container">
            <div class="row border-radius-md pb-4 p-3 mx-sm-0 mx-1 position-relative">
                <div class="col-lg-3 mt-lg-n2 mt-2">
                    <label class="ms-0">대분류</label>
                    <select class="form-control" name="big_label" id="big_label" onchange="big_type(value)">
                        <option value="">전체</option>
                    </select>
                </div>
                <div class="col-lg-3 mt-lg-n2 mt-2">
                    <label class="ms-0">중분류</label>
                    <select class="form-control" name="middle_label" id="middle_label" onchange="middle_type(value)">
                        <option value="">전체</option>
                    </select>
                </div>
                <div class="col-lg-3 mt-lg-n2 mt-2">
                    <label class="ms-0">소분류</label>
                    <select class="form-control" name="small_label" id="small_label">
                        <option value="">전체</option>
                    </select>
                </div>

                <div class="col-lg-3 mt-lg-n2 mt-2">
                    <label>&nbsp;</label>
                    <button type="submit" class="btn bg-gradient-primary w-100 mb-0">Search</button>
                </div>

            </div>
        </div>
    </div>
</form>
<section class="pt-5 pb-0">
    <div class="container">
        <div class="row">
            <%
                if (rList != null) {
                    for (int i = 0; i < rList.size(); i++) {
                        if (rList.get(i).getDetilaLoc().equals("null")) {
                            rList.get(i).setDetilaLoc("");
                        }
                        if(rList.get(i).getFristimage2().equals("null")){
                            rList.get(i).setFristimage2("/assets/img/noImage.jpeg");
                        }
            %>
            <div class="col-lg-4 col-md-6" style="margin-top: 40px;">
                <div class="card">
                    <div class="card-header p-0 mx-3 mt-n4 position-relative z-index-2">
                        <a class="d-block blur-shadow-image">
                            <img src="<%=rList.get(i).getFristimage2()%>" alt="img-blur-shadow"
                                 class="img-fluid border-radius-lg"
                                 style="width :316px; height: 220px; object-fit: cover;">
                        </a>
                    </div>
                    <div class="card-body pt-3">
                        <p class="text-dark mb-2 text-sm"><%=rList.get(i).getDetilaLoc()%>
                        </p>
                        <a href="javascript:;">
                            <h5>
                                <%=rList.get(i).getTitle()%>
                            </h5>
                        </a>
                        <p>
                            조회수 :  <%=rList.get(i).getReadCnt()%>
                        </p>
                        <%
                            if (rList.get(i).getContentTypeID().equals("12")) {
                                send_onclick = "/travel/koreaTravelDetail12";
                            } else if (rList.get(i).getContentTypeID().equals("14")) {
                                send_onclick = "/travel/koreaTravelDetail14";
                            } else if (rList.get(i).getContentTypeID().equals("15")) {
                                send_onclick = "/travel/koreaTravelDetail";
                            } else if (rList.get(i).getContentTypeID().equals("25")) {
                                send_onclick = "/travel/koreaTravelDetail25";
                            } else if (rList.get(i).getContentTypeID().equals("28")) {
                                send_onclick = "/travel/koreaTravelDetail28";
                            } else if (rList.get(i).getContentTypeID().equals("32")) {
                                send_onclick = "/travel/koreaTravelDetail32";
                            } else if (rList.get(i).getContentTypeID().equals("38")) {
                                send_onclick = "/travel/koreaTravelDetail38";
                            } else if (rList.get(i).getContentTypeID().equals("39")) {
                                send_onclick = "/travel/koreaTravelDetail39";
                            }

                        %>
                        <button type="button" class="btn btn-outline-primary btn-sm mb-0" id="go_detail"
                                onclick="location.href='<%=send_onclick%>?contentId=<%=rList.get(i).getContentID()%>&contenttypeId=<%=rList.get(i).getContentTypeID()%>'">
                            자세히
                        </button>

                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
</section>
</section>
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