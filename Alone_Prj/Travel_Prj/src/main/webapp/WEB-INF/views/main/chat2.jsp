<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.util.DateUtil" %>
<%@ page import="kopo.poly.dto.UserDTO" %>
<%@ page import="kopo.poly.dto.ChatDTO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<%
    String user_id = (String) session.getAttribute("user_id");
    UserDTO pDTO = (UserDTO) request.getAttribute("pDTO");
    String roomkey = "";
    if (user_id.equals("admin")) {
        roomkey = (String) request.getAttribute("roomkey");
    } else {
        roomkey = pDTO.getRoomkey();
    }

    List<ChatDTO> rList = (List<ChatDTO>) request.getAttribute("rList");
%>
<head>
    <title>Chatting</title>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link
            href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700"
            rel="stylesheet">
    <link
            href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap"
            rel="stylesheet">
    <link
            href="https://fonts.googleapis.com/css?family=Abril+Fatface&display=swap"
            rel="stylesheet">
    <link rel="stylesheet"
          href="/andrea-master/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="/andrea-master/css/animate.css">
    <link rel="stylesheet" href="/andrea-master/css/owl.carousel.min.css">
    <link rel="stylesheet"
          href="/andrea-master/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/andrea-master/css/magnific-popup.css">
    <link rel="stylesheet" href="/andrea-master/css/aos.css">
    <link rel="stylesheet" href="/andrea-master/css/ionicons.min.css">
    <link rel="stylesheet"
          href="/andrea-master/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/andrea-master/css/jquery.timepicker.css">
    <link rel="stylesheet" href="/andrea-master/css/flaticon.css">
    <link rel="stylesheet" href="/andrea-master/css/icomoon.css">
    <link rel="stylesheet" href="/andrea-master/css/style.css">

    <!-- profile -->
    <link rel="stylesheet" href="/css/profile.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <!-- profile -->
    <!-- chatting -->
    <style type="text/css">
        .chat_window {

            width: 100%;
            max-width: 800px;
            height: 600px;
            border-radius: 10px;
            background-color: #fff;

            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
            background-color: #f8f8f8;
            overflow: hidden;
        }

        .top_menu {
            background-color: #fff;
            width: 100%;
            padding: 20px 0 15px;
            box-shadow: 0 1px 30px rgba(0, 0, 0, 0.1);
        }

        .top_menu .buttons {
            margin: 3px 0 0 20px;
            position: absolute;
        }

        .top_menu .buttons .button {
            width: 16px;
            height: 16px;
            border-radius: 50%;
            display: inline-block;
            margin-right: 10px;
            position: relative;
        }

        .top_menu .buttons .button.close {
            background-color: #f5886e;
        }

        .top_menu .buttons .button.minimize {
            background-color: #fdbf68;
        }

        .top_menu .buttons .button.maximize {
            background-color: #a3d063;
        }

        .top_menu .title {
            text-align: center;
            color: #bcbdc0;
            font-size: 20px;
        }

        .messages {
            position: relative;
            list-style: none;
            padding: 20px 10px 0 10px;
            margin: 0;
            height: 440px;
            overflow: scroll;
        }

        .messages .message {
            clear: both;
            overflow: hidden;
            margin-bottom: 20px;
            transition: all 0.5s linear;
            opacity: 0;
        }

        .messages .message.left .avatar {
            background-color: #f5886e;
            float: left;
        }

        .messages .message.left .text_wrapper {
            background-color: #ffe6cb;
            margin-left: 20px;
        }

        .messages .message.left .text_wrapper::after, .messages .message.left .text_wrapper::before {
            right: 100%;
            border-right-color: #ffe6cb;
        }

        .messages .message.left .text {
            color: #c48843;
        }

        .messages .message.right .avatar {
            background-color: #fdbf68;
            float: right;
        }

        .messages .message.right .text_wrapper {
            background-color: #c7eafc;
            margin-right: 20px;
            float: right;
        }

        .messages .message.right .text_wrapper::after, .messages .message.right .text_wrapper::before {
            left: 100%;
            border-left-color: #c7eafc;
        }

        .messages .message.right .text {
            color: #45829b;
        }

        .messages .message.appeared {
            opacity: 1;
        }

        .messages .message .avatar {
            width: 60px;
            height: 60px;
            border-radius: 50%;
            display: inline-block;
        }

        .messages .message .text_wrapper {
            display: inline-block;
            padding: 20px;
            border-radius: 6px;
            width: calc(100% - 85px);
            min-width: 100px;
            position: relative;
        }

        .messages .message .text_wrapper::after, .messages .message .text_wrapper:before {
            top: 18px;
            border: solid transparent;
            content: " ";
            height: 0;
            width: 0;
            position: absolute;
            pointer-events: none;
        }

        .messages .message .text_wrapper::after {
            border-width: 13px;
            margin-top: 0px;
        }

        .messages .message .text_wrapper::before {
            border-width: 15px;
            margin-top: -2px;
        }

        .messages .message .text_wrapper .text {
            font-size: 18px;
            font-weight: 300;
        }

        .bottom_wrapper {
            position: relative;
            width: 100%;
            background-color: #fff;
            padding: 20px 20px;
            bottom: 0;
            padding-left: 5px;
            padding-right: 5px;
        }

        .bottom_wrapper .message_input_wrapper {
            display: inline-block;
            height: 50px;
            border-radius: 25px;
            border: 1px solid #bcbdc0;
            width: 75%;
            position: relative;
            padding: 0 20px;
        }

        .bottom_wrapper .message_input_wrapper .message_input {
            border: none;
            height: 100%;
            box-sizing: border-box;
            width: calc(100% - 40px);
            position: absolute;
            outline-width: 0;
            color: gray;
        }

        .bottom_wrapper .send_message {
            width: 24%;
            height: 50px;
            display: inline-block;
            border-radius: 50px;
            background-color: #a3d063;
            border: 2px solid #a3d063;
            color: #fff;
            cursor: pointer;
            transition: all 0.2s linear;
            text-align: center;
            float: right;
        }

        .bottom_wrapper .send_message:hover {
            color: #a3d063;
            background-color: #fff;
        }

        .bottom_wrapper .send_message .text {
            font-size: 18px;
            font-weight: 300;
            display: inline-block;
            line-height: 48px;
        }

        .message_template {
            display: none;
        }
    </style>
    <!-- chatting -->

</head>
<body data-aos-easing="slide" data-aos-duration="800" data-aos-delay="0">

<!-- 전체 페이지 START-->
<div id="colorlib-page">
    <!-- START 왼쪽 NANI -->
    <!-- END 왼쪽 NANI -->

    <!-- START 상단 NANI -->
    <div class="padding" style="padding-bottom: 55px;"></div>
    <div class="sidebar-heading"
         style="text-align: center; position: fixed; top: 0; width: 100%; height: 70px; background-color: #fff; z-index: 5; padding-top: 20px; font-weight: 500; color: black;">
        <div class="hh" style="font-size: 23px; height: 32px;">
            <%=user_id%>고객센터
        </div>
        <hr
                style="width: 90%; height: 1.5px; border: none; background-color: #666666;">
    </div>
    <div id="colorlib-main">
        <section class="ftco-section ftco-no-pt ftco-no-pb">
            <div class="container">

                <div class="row d-flex">

                    <div class="col-xl-8 px-md-5" style="background-color: #f7fbff;">
                        <div class="" style="margin-left: 20px">
                            <label
                                    style="font-size: 30px; font-family: 'Do Hyeon', sans-serif; font-family: 'Nanum Pen Script', cursive; margin-top: 18px; margin-bottom: -8px;">
                            </label>
                            <label
                                    style="font-size: 20px; font-family: 'Do Hyeon', sans-serif; font-family: 'Nanum Pen Script', cursive; margin-top: 18px; margin-bottom: -8px; float:right;">
                            </label>
                        </div>
                        <hr>
                        <div class="">
                            <div class="buttons">
                                <button class="btn btn-success" type="button" onclick="pre();" style="font-size: 12px;">
                                    이전 메시지 불러오기
                                </button>
                            </div>
                            <div class="chat_window">
                                <div class="top_menu">
                                    <div class="buttons">
                                        <div class="button close"></div>
                                        <div class="button minimize"></div>
                                        <div class="button maximize"></div>
                                    </div>
                                    <div class="title">1:1 CS</div>
                                </div>
                                <div id="chat"></div>
                                <ul class="messages">
                                </ul>

                                <div class="bottom_wrapper clearfix">
                                    <div class="message_input_wrapper">
                                        <input type="text" id="sender" value="${sessionScope.user_id}"
                                               style="display: none;">
                                        <input type="text" class="message_input"
                                               placeholder="Type your message here..."/>
                                    </div>
                                    <div class="send_message" onclick="send();">
                                        <div class="icon"></div>
                                        <div class="text">Send</div>
                                    </div>
                                </div>
                            </div>
                            <div class="message_template">
                                <li class="message right appeared">
                                    <div class="avatar"></div>
                                    <div class="text_wrapper">
                                        <div class="text"
                                             style="font-size: 20px; font-family: \'Do Hyeon\', sans-serif; font-family: \'Nanum Pen Script\', cursive;  margin-bottom: -8px;">
                                        </div>
                                    </div>
                                </li>

                            </div>
                            <hr style="margin-top: 10px; margin-bottom: 0px;"/>
                        </div>

                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<!-- loader -->


<script src="/andrea-master/js/jquery.min.js"></script>
<script src="/andrea-master/js/jquery-migrate-3.0.1.min.js"></script>
<script src="/andrea-master/js/popper.min.js"></script>
<script src="/andrea-master/js/bootstrap.min.js"></script>
<script src="/andrea-master/js/jquery.easing.1.3.js"></script>
<script src="/andrea-master/js/jquery.waypoints.min.js"></script>
<script src="/andrea-master/js/jquery.stellar.min.js"></script>
<script src="/andrea-master/js/owl.carousel.min.js"></script>
<script src="/andrea-master/js/jquery.magnific-popup.min.js"></script>
<script src="/andrea-master/js/aos.js"></script>
<script src="/andrea-master/js/jquery.animateNumber.min.js"></script>
<script src="/andrea-master/js/scrollax.min.js"></script>
<script src="/andrea-master/js/main.js"></script>
<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>

</body>

<script type="text/javascript">

    function getDatetime() {
        let _date = new Date();
        let _hours = _date.getHours();
        let _min = _date.getMinutes();
        _hours = _hours < 10 ? '0' + _hours : _hours
        _min = _min < 10 ? '0' + _min : _min
        return _hours + ':' + _min;
    }

    let key = "<%=roomkey%>";


    /* ##### websocket js start ##### */
    let ws;
    let cs_name;

    function openSocket() {
        console.log("openSocket");
        if (ws !== undefined && ws.readyState !== WebSocket.CLOSED) {
            console.log("WebSocket is already opened.");
            return;
        }
        //웹소켓 객체 만드는 코드
        //ws = new WebSocket("ws://3.39.127.108:11000/echo.do");
        ws = new WebSocket("ws://localhost:11000/echo.do");

        ws.onopen = function (event) {
            if (event.data === undefined)
                return;

            writeResponse(event.data);
        };

        ws.onmessage = function (event) {
            writeResponse(event.data);
        };
        ws.onclose = function (event) {
            writeResponse("연결이 해제되었습니다.");
        }
    }

    function send() {
        var text = $(".message_input").val() + "," + document.getElementById("sender").value;
        console.log("text : " + text)
        ws.send(text);
        text = "";


        let chat_dt = getDatetime()
        let chat_content = $(".message_input").val();

        let data = {};
        data.chat_id = '<%=(String) session.getAttribute("user_id")%>';
        data.chat_dt = chat_dt;
        data.cs_name = key;
        data.chat_content = chat_content;

        let res_data = JSON.stringify(data);

        console.table(res_data);

        $.ajax({
            url: "/chat/chatConTentRedis",
            type: 'POST',
            data: res_data,
            dataType: "JSON",
            contentType: "application/json;charset=utf-8",
            success: function (result) {
                console.log(result);
            },
            error: function (e) {
                console.log(e);
            }
        })

        $('.message_input').val('');
    }

    function closeSocket() {
        ws.close();
    }

    function writeResponse(text) {
        console.log("text : " + text);
        let _dateTime = getDatetime();
        let res = "";
        var qqq = text.split(",")[1];
        console.log("qqq : " + qqq);
        let res_userid = "<%=user_id%>";
        console.log("res_user", res_userid);

        let user_id = '${sessionScope.user_id}';
        console.log("user_id : ", user_id);
        if (user_id ==qqq) {
            res += '<li class="message right appeared" id="chat_ul">';
            res += '<div class="avatar"></div>';
            res += '<div class="text_wrapper">';
            res += '<div class="text" style="font-size: 20px; font-family: \'Do Hyeon\', sans-serif; font-family: \'Nanum Pen Script\', cursive;  margin-bottom: -8px;">' + _dateTime + '&nbsp;&nbsp;' + text + '</div>';
            res += '</div>';
            res += '</li>';
        } else {
            res += '<li class="message left appeared" id="chat_ul">';
            res += '<div class="avatar"></div>';
            res += '<div class="text_wrapper">';
            res += '<div class="text" style="font-size: 20px; font-family: \'Do Hyeon\', sans-serif; font-family: \'Nanum Pen Script\', cursive;  margin-bottom: -8px;">' + _dateTime + '&nbsp;&nbsp;' + text + '</div>';
            res += '</div>';
            res += '</li>';
        }
        $(".messages").append(res);
        $messages = $('.messages');
        return $messages.animate({scrollTop: $messages.prop('scrollHeight')}, 300);
    }

    openSocket(); // websocket open
    window.onbeforeunload = function (e) {
        closeSocket() // 페이지 벗어나면 websocket close
    };

    /* ##### websocket js end ##### */
    function clearText() { // 웹 소켓 끝나면 자식노드에 있는 메시지 삭제
        console.log(messages.parentNode);
        messages.parentNode.removeChild(messages)
    }

    function pre() {
        console.log("pre start!!");
        let chat_date1;
        let chat_con = "";
        let res = "";

        <%
           if(rList != null){
            for(int i= 0; i<rList.size(); i++){
                if(rList.get(i).getChat_id().equals(user_id)){
        %>
        chat_date1 = "<%=rList.get(i).getChat_dt()%>";
        chat_con = "<%=rList.get(i).getChat_content()%>,<%=rList.get(i).getChat_id()%>";
        res += '<li class="message right appeared" id="chat_ul">';
        res += '<div class="avatar"></div>';
        res += '<div class="text_wrapper">';
        res += '<div class="text" style="font-size: 20px; font-family: \'Do Hyeon\', sans-serif; font-family: \'Nanum Pen Script\', cursive;  margin-bottom: -8px;">' + chat_date1 + '&nbsp;&nbsp;' + chat_con + '</div>';
        res += '</div>';
        res += '</li>';

        <%
                }else{
                %>
        chat_date1 = "<%=rList.get(i).getChat_dt()%>";
        chat_con = "<%=rList.get(i).getChat_content()%>,<%=rList.get(i).getChat_id()%>";
        res += '<li class="message left appeared" id="chat_ul">';
        res += '<div class="avatar"></div>';
        res += '<div class="text_wrapper">';
        res += '<div class="text" style="font-size: 20px; font-family: \'Do Hyeon\', sans-serif; font-family: \'Nanum Pen Script\', cursive;  margin-bottom: -8px;">' + chat_date1 + '&nbsp;&nbsp;' + chat_con + '</div>';
        res += '</div>';
        res += '</li>';
        <%

     }
        }
    }
%>

        $(".messages").html(res);
        $messages = $('.messages');

    }
</script>
</html>