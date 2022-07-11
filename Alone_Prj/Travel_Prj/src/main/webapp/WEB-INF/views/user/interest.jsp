<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="/assets/img/favicon.png">
    <title>관심정보 등록</title>
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
    <link id="pagesvyle" href="/assets/css/material-kit.css" rel="stylesheet"/>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        let i = 0;
        function btn_append() {
            let text = document.getElementById("search_interest").value;
            let newSpan = document.createElement("span");
            newSpan.innerHTML = "# " + text;
            newSpan.setAttribute("class", "badge rounded-pill bg-info");
            newSpan.setAttribute("id","sp_"+i);
            newSpan.style.margin = "5px 5px 5px 5px";

            let hidenInput = document.createElement("input");
            hidenInput.setAttribute('type','hidden');
            hidenInput.setAttribute('name','interest');
            hidenInput.setAttribute('value', text);

            let append_per = document.getElementById("btn-append");
            append_per.appendChild(newSpan)

            newSpan.appendChild(hidenInput);
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
                            <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">관심사 설정</h4>
                        </div>
                    </div>
                    <form name="sendForm" action="/user/interestproc" method="post">
                        <div class="card-body">
                            <h6>무엇에 관심을 가지고 계신가요?</h6>
                            <h6>좋아하는 단어를 골라주세요 :)</h6>

                            <div class="container">
                                <div class="row justify-space-between py-2">
                                    <div class="col-lg-4 mx-auto">
                                        <div class="input-group input-group-dynamic mb-4">
                                            <span class="input-group-text"><i class="fas fa-search"
                                                                              aria-hidden="true"></i></span>

                                            <input class="form-control" placeholder="Search" type="text"
                                                   id="search_interest"/>
                                            <span><button type="reset" class="btn bg-gradient-primary w-100 my-4 mb-2"
                                                          style="background-image:  linear-gradient(195deg, #49a3f1 0%, #49a3f1 100%); box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(245 236 239 / 40%) !important;"
                                                          id="append_text" onclick="btn_append();">
                                                추가하기
                                                </button>
                                            </span>

                                        </div>

                                    </div>
                                </div>
                            </div>
                            <hr>


                            <div class="row justify-space-between text-center py-2">
                                <div class="col-12" id="btn-append">
                                </div>
                            </div>

                            <div class="text-center">
                                <button type="submit" id="submit" class="btn bg-gradient-primary w-100 my-4 mb-2"
                                        style="background-image:  linear-gradient(195deg, #49a3f1 0%, #49a3f1 100%); box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(245 236 239 / 40%) !important;">
                                    시작하기
                                </button>

                                <button type="button" class="btn bg-gradient-primary w-100 my-4 mb-2"
                                        style="background-image:  linear-gradient(195deg, #49a3f1 0%, #49a3f1 100%); box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(245 236 239 / 40%) !important;" onclick="location.href='/main/home'">
                                    건너뛰기
                                </button>
                            </div>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
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

<script>
</script>
</body>

</html>