<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.BookMarkDTO" %>
<!doctype html>
<html lang="en">
<head>
    <%
        List<BookMarkDTO> pList = (List<BookMarkDTO>) request.getAttribute("rList");
    %>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>북마크</title>
    <script src="/js/jquery-3.6.0.min.js"></script>

    <style>
        @import url('https://fonts.googleapis.com/css?family=Sofia');

        body {
            width: 100%;
            margin: 0 auto;
        }

        .layout {
            display: grid;
            grid-template: 20px 40px auto / auto 300px 300px 300px auto;
            grid-gap: 10px;
        }

        .title {
            grid-column: 2 / span 1;
            grid-row: 2 / span 1;
            font-size: 3em;
            font-family: 'Sofia', cursive;
        }

        .imageGallery {
            display: grid;
            grid-template: repeat(3, 300px) / repeat(3, 300px);
            grid-gap: 10px;
            margin: 50px 50px 50px 50px;
            align-content: center;
            justify-content: center;
            grid-column: 2 / span 3;
            grid-row: 3 / span 1;
        }

        img {
            cursor: pointer;
        }

        .modal {
            position: fixed;
            z-index: 10;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 25px 25px 25px 25px;
            display: grid;
            grid-template: 320px 50px / 320px;
        }

        .modalHolder {
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 100;
            width: 100%;
            height: 100%;
            margin: 0 auto;
            position: fixed;
            top: 0;
            left: 0;
        }

        .boxPhoto {
            width: 320px;
        }

        .closeButton {
            grid-row: 2 / span 1;
            grid-column: 1 / span 1;
            text-align: right;
            font-size: 2em;
            font-family: 'Arial Black';
            cursor: pointer;
        }


        @media (max-width: 950px) {
            .imageGallery {
                grid-template: repeat(5, 300px) / repeat(2, 300px);
                grid-column: 2 / span 2;
            }

            .layout {
                grid-template: 20px 40px auto / auto 300px 300px auto;
            }
        }

        @media (max-width: 500px) {
            .imageGallery {
                grid-template: repeat(9, 300px) / 300px;
                grid-column: 2 / span 1;
            }

            .layout {
                grid-template: 20px 40px auto / auto 300px auto;
            }
        }
    </style>
    <script>
        var modal = {
            modalHolder: document.getElementById('modalHolder'),
            box: document.createElement('div'),
            boxPhoto: document.createElement('img'),
            closeButton: document.createElement('span'),
            openModal: function(source) {

                this.modalHolder.appendChild(this.box);
                this.box.appendChild(this.boxPhoto);
                this.box.appendChild(this.closeButton);

                this.closeButton.textContent = 'x';
                this.boxPhoto.src = source;

                this.box.className = 'modal';
                this.closeButton.className = 'closeButton';
                this.modalHolder.className = 'modalHolder';
                this.boxPhoto.className = 'boxPhoto';

                this.closeButton.addEventListener('click', handlers.close);
            },
            closeModal: function() {
                this.modalHolder.innerHTML = '';
                this.modalHolder.className = '';
            }
        }

        var handlers = {
            open: function(n) {
                var imageSource = document.getElementsByTagName('img')[n].src;
                modal.openModal(imageSource);
            },
            close: function() {
                modal.closeModal();
            }
        }
    </script>
</head>
<body>
<div class="layout">
    <div class="title" style="text-align: center;">BookMark</div>

    <div class="imageGallery">
        <%
            for(int i = 0; i < pList.size(); i++){
        %>
        <img class="image" src="<%=pList.get(i).getFile_detail_path()%>" style="object-fit:cover; width: 300px; height: 300px;"
             onclick="location.href='/board/boardDetailInfo?user_seq=<%=pList.get(i).getBoard_user_seq()%>&board_seq=<%=pList.get(i).getBoard_seq()%>'">
        <%
            }
        %>
    </div>
</div>
<div id="modalHolder">

</div>

</div>
</body>
</html>
