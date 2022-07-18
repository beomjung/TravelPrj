## 자연어 처리를 활용한 사용자 여행지 추천 서비스
* Alone_Prj/Travel_Prj -> src -> main -> poly (Java Back-End)
* Alone_Prj/Travel_Prj -> src -> main -> webbapp -> WEB-INF  -> views (JSP 화면, 무료 부트 테마를 사용)

## 소개 영상

[![Video Label](http://img.youtube.com/vi/VWqvPEzaoPg/0.jpg)](https://youtu.be/VWqvPEzaoPg)

## 적용기술
```
* 배포서버 : AWS EC2
* 개발언어 : JAVA, Python, JavaScript, HTML5, CSS, Jquery
* 개발 툴 : Intellij, PyCharm
* 개발환경 : SpringBoot, Flask
* 데이터베이스 : MariaDB, redis
* API : 국문관광정보서비스, Tmap API
* 라이브리러리 : KoNLPy, Kereas, TensorFlow
* 프로토콜 : WebSocket
* 형상관리 : Git
* 스토리지 : AWS S3
```

## 기능

```
1. 사용자의 관심정보를 기반으로 추천 여행지를 생성하여 사용자가 별 다른 입력 없이도 여행지를 확인 가능
2. 사용자들이 작성한 게시물을 자연어 처리하여 해당 여행지에 대한 사용자들의 긍정, 부정 지수를 통해 긍정리뷰가 높은 순으로 사용자가 선택한 지역에 대한 여행지를 추천함
3. 국문관광정보서비스 API를 사용하여 국내 여행지를 소개하고 사용자가 직접 여행을 계획하는 경우 도움을 주는 정보를 제공함
4. WebSocket 라이브러리를 활용해 구현한 1대1 채팅 상담을 통해 사용자는 문의사항을 쉽게 질문하고 안내 받을 수 있음
```

## 프로젝트 수행 기간
```
* 2022-04-01 ~ 2022-06-10
```

## 개발 내용
```
* AWS S3 연동을 통한 사용자 이미지 및 여행 이미지 업로드 및 조회
* SpringBoot 기반 회원관리 CRUD 로직 구현
* 데이터베이스 개발 및 쿼리 수정
* TensorFlow를 활용한 게시글 자연어 처리
* 사용자 여행지 추천 알고리즘 구현
* WebSocket 구현 및 CS 채팅 개발
* UI 개발
* REST API 서버 개발
```

