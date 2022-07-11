<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.Board_PostDTO" %>
<%@ page import="kopo.poly.dto.UserDTO" %>
<%@ page import="kopo.poly.dto.BookMarkDTO" %>
<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">
<%
    List<Board_PostDTO> rList = (List<Board_PostDTO>) request.getAttribute("pList");
    UserDTO pDTO = (UserDTO) request.getAttribute("qDTO");
    int login_user_seq = (int) request.getAttribute("login_user_seq");

    if (pDTO == null) {
        pDTO = new UserDTO();
    }

    BookMarkDTO rDTO = (BookMarkDTO) request.getAttribute("rDTO");

%>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="/assets/img/favicon.png">
    <title>게시판 상세보기</title>
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
    <link href="/assets/css/boardWrite.css" rel="stylesheet"/>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <style>
    </style>
</head>

<body class="about-us bg-gray-200">
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
<header class="bg-gradient-dark">
    <div class="page-header min-vh-75" style="background-image: url('static/assets/img/bg9.jpg');">
        <span class="mask bg-gradient-dark opacity-6"></span>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8 text-center mx-auto my-auto">
                    <h5 class="text-white"><%=pDTO.getUser_nickname()%>님, 어디든 떠날 수 있어요</h5>
                    <h5 class="text-white">다른 회원들이 작성한 여행게시물을 확인해 보세요 :)</h5>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- -------- END HEADER 7 w/ text and video ------- -->
<!-- Section with four info areas left & one card right with image and waves -->
<div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4">
    <section class="py-sm-7 py-5 position-relative">
        <div class="container">
            <div class="row">
                <div class="col-12 mx-auto">
                    <div class="mt-n8 mt-md-n9 text-center">
                        <img class="avatar avatar-xxl shadow-xl position-relative z-index-2"
                             src="<%=pDTO.getFile_detail_path()%>"
                             alt="bruce" loading="lazy">
                        <br>
                    </div>
                    <div class="row py-5">
                        <div class="col-lg-7 col-md-7 z-index-2 position-relative px-md-2 px-sm-5 mx-auto">

                            <div class="d-flex justify-content-between align-items-center mb-2">
                                <span><h3 class="mb-0" style="display: inline-block;"></h3></span>
                                <div class="top_006" style="">
                                    <div id="post_div" class="post_div">
                                        <button type="button" class="btn btn-info w-auto me-2" id="postChg"
                                                onclick="location.href='/board/boardChg?user_seq=<%=rList.get(0).getUser_seq()%>&board_seq=<%=rList.get(0).getBoard_seq()%>'">
                                            게시글
                                            수정
                                        </button>
                                        <form action="/board/boardDelete" method="post">
                                        <button type="submit" class="btn btn-info w-auto me-2" id="postDelete">
                                            게시글 삭제
                                            <input type="hidden" name="file_seq" value="<%=rList.get(0).getFile_seq()%>">
                                            <input type="hidden" id="user_seq" name="user_seq" value="<%=rList.get(0).getUser_seq()%>">
                                            <input type="hidden" id="board_seq" name="board_seq" value="<%=rList.get(0).getBoard_seq()%>">
                                            <input type="hidden" id="bookmark_seq" name="bookmark_seq" value="<%=rDTO.getBookmark_seq()%>">

                                        </button>
                                        </form>
                                    </div>
                                    <div id="follow_div" class="follow_div">
                                        <button type="button" class="btn btn-info w-auto me-2" id="follow"
                                                onclick="followProc();">팔로우 하기
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-4">
                            <h3 class="mb-0" style="display: inline;"><%=pDTO.getUser_nickname()%></h3>
                            <div class="col-auto">
                                <span class="h6"><%=pDTO.getBoard_cnt()%></span>
                                <span>Posts</span>
                            </div>
                            <div class="col-auto">
                                <span class="h6" id=""><%=pDTO.getFollower()%></span>
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

<div class="container">

    <section class="py-5 bg-gray-100">
        <div class="col-auto" style="text-align: center;">
            <span class="h5">출발일 : <%=rList.get(0).getBoard_start_day()%></span>
            <span class="h5">도착일 : <%=rList.get(0).getBoard_end_day()%></span>
        </div>
        <br/>
        <br/>
            <%
            for (int i = 0; i < rList.size(); i++) {
        %>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto text-center">
                    <div class="card card-blog card-plain">
                        <div class="card-header p-0 position-relative z-index-2">
                            <a class="d-block blur-shadow-image">
                                <img src="<%=rList.get(i).getFile_detail_path()%>" alt="img-blur-shadow"
                                     class="img-fluid border-radius-lg" style="width:820px; height:420px; object-fit:cover">
                            </a>
                            <p class="text-gradient text-primary text-gradient font-weight-bold text-sm text-uppercase"
                               style="display: inline;">#<%=rList.get(i).getBoard_post_hashTag()%>
                            </p>
                        </div>
                        <div class="card-body px-0 pt-4">
                            <p class="text-gradient text-primary text-gradient font-weight-bold text-sm text-uppercase"
                               style="display: inline;">평점 : <%=rList.get(i).getBoard_post_star()%>
                            </p>
                            <a href="javascript:;">
                                <h4>
                                    <%=rList.get(i).getBoard_post_title()%>
                                </h4>
                            </a>
                            <p>
                                <%=rList.get(i).getBoard_post_content()%>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <%
            }
        %>
        <section class="py-7 mt-3">
            <div class="container">
                <div class="row justify-space-between text-center py-2">
                    <div class="col-12 mx-auto">
                        <button class="btn bg-gradient-primary btn-icon btn-lg" id="book_btn" type="button" value="" style="display: inline-block;" onclick="insertbookMark();">
                            <div class="d-flex align-items-center">
                                BOOKMARK
                            </div>
                            <input type="hidden" name="book_seq" id="book_seq" value="">
                        </button>
                    </div>
                </div>
            </div>
        </section>
    </div>
</section>
</section>

<!-- END Blogs w/ 3 rows w/ image on left & title, text, author on end-->
</div>
</div>
<!-- -------- START Testimonials w/ 3 cards & brand logos  -------- -->

<!-- -------- END Testimonials w/ 3 cards & brand logos  -------- -->

<!--   Core JS Files   -->
<script src="/assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<script src="/assets/js/plugins/perfect-scrollbar.min.js"></script>
<!--  Plugin for TypedJS, full documentation here: https://github.com/inorganik/CountUp.js -->
<script src="/assets/js/plugins/countup.min.js"></script>
<!--  Plugin for Parallax, full documentation here: https://github.com/wagerfield/parallax  -->
<script src="/assets/js/plugins/parallax.min.js"></script>
<!-- Control Center for Material UI Kit: parallax effects, scripts for the example pages etc -->
<!--  Google Maps Plugin    -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDTTfWur0PDbZWPr7Pmq8K3jiDp0_xUziI"></script>
<script src="/assets/js/material-kit.min.js?v=3.0.0" type="text/javascript"></script>
<script type="text/javascript">
    let login_seq = <%=login_user_seq%>;
    let user_seq = <%=rList.get(0).getUser_seq()%>;

    if (login_seq == user_seq) {
        document.getElementsByClassName("follow_div")[0].style.display = "none";
        document.getElementById("book_btn").style.display="none";
    }
    if (login_seq != user_seq) {
        document.getElementsByClassName("post_div")[0].style.display = "none";
    }

    let login_user_seq = <%=login_user_seq%>;
    let board_user_seq = <%=rList.get(0).getUser_seq()%>;

    console.log("login_user_seq" + login_user_seq);
    console.log("board_user_seq" + board_user_seq);

    let auth = <%=pDTO.getAuth_follow()%>;
    let count = 1;
    let book_auth = <%=rDTO.getBookmark_seq()%>;
    let cnt = 0;

    if (auth != count) {
        $("#follow").removeAttr("onclick");
        $("#follow").attr("onclick", "followProc();");
        $("#follow").text("팔로우 하기");
    } else {
        $("#follow").removeAttr("onclick");
        $("#follow").attr("onclick", "followDeleteProc();");
        $("#follow").text("팔로우 취소");
    }


    if (book_auth > cnt) {
        let book_res = "<%=rDTO.getBookmark_seq()%>";
        $("#book_btn").css("background-color", "red");
        $("#book_btn").removeAttr("onclick");
        $("#book_btn").attr("onclick", "bookMarkDelete();");
        $("#book_btn").text("북마크 취소")
    } else {
        $("#bookmark_seq").attr("value", "");
        $("#book_btn").css("background-color", "gray");
        $("#book_btn").removeAttr("onclick");
        $("#book_btn").attr("onclick", "insertbookMark();");
        $("#book_btn").text("북마크")
    }


    function followProc() {
        let sendData = {
            "login_user_seq": login_user_seq,
            "board_user_seq": board_user_seq
        }
        console.log(sendData);
        $.ajax({
            type: "POST",
            url: "/followProc",
            data: sendData,
            success: function (data) {
                if (data == 1) {
                    $("#follow").removeAttr("onclick");
                    $("#follow").attr("onclick", "followDeleteProc();");
                    $("#follow").text("팔로우 취소");
                    document.location.href = document.location.href
                }
            }
        });
    }

    function followDeleteProc() {
        let sendData = {
            "login_user_seq": login_user_seq,
            "board_user_seq": board_user_seq
        }
        $.ajax({
            type: "POST",
            url: "/followDeleteProc",
            data: sendData,
            success: function (data) {
                if (data == 1) {
                    $("#follow").removeAttr("onclick");
                    $("#follow").attr("onclick", "followProc();");
                    $("#follow").text("팔로우 하기");
                    document.location.href = document.location.href
                }
            }
        });
    }

    function insertbookMark(){
        let login_user_seq = <%=login_user_seq%>;
        let board_user_seq = <%=rList.get(0).getUser_seq()%>;
        let board_seq = <%=rList.get(0).getBoard_seq()%>;

        let bookData={
            "user_seq" : login_user_seq,
            "board_user_seq" : board_user_seq,
            "board_seq" : board_seq
        }
        $.ajax({
            type: "POST",
            url: "/main/bookMarkProc",
            data: bookData,
            success: function (data) {
                if (data >= 1) {
                   let result = data;
                   console.log(data);
                   $("#bookmark_seq").removeAttr("value");
                    $("#bookmark_seq").attr("value", result);
                    $("#book_btn").css("background-color", "red");
                    $("#book_btn").removeAttr("onclick");
                    $("#book_btn").attr("onclick", "bookMarkDelete");
                    $("#book_btn").text("북마크 취소")
                    document.location.href = document.location.href

                }
            }
        });
    }

    function bookMarkDelete(){
        let bookmark_seq = $("#bookmark_seq").val();

        console.log("북마크는?:", bookmark_seq);
        let bookData={
            "bookmark_seq" : bookmark_seq
        }
        $.ajax({
            type: "POST",
            url: "/main/bookDeleteProc",
            data: bookData,
            success: function (data) {
                console.log(data);
                if (data == 0) {
                    let result = data;
                    console.log(result);
                    $("#bookmark_seq").attr("value", "");
                    $("#book_btn").css("background-color", "gray");
                    $("#book_btn").removeAttr("onclick");
                    $("#book_btn").attr("onclick", "insertbookMark");
                    $("#book_btn").text("북마크")
                    document.location.href = document.location.href
                }
            }
        });
    }
</script>

</body>

</html>