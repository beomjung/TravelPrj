package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelManyInfoDTO {

    private String msg;
    //--------------관광지(12)-----------------//
    private String accomcount; //수용인원
    private String chkbabycarriage; // 유모차 대여 정보
    private String chkcreditcard; //신용카드 정보
    private String chkpet;// 애완동물 동반가능 여부
    private String expagerange; //체험가능 연령
    private String expguide;// 체험안내
    private String heritage1;// 세계문화유산 여부
    private String heritage2; // 세계자연유산 여부
    private String heritage3;// 세계 기록유산 여부
    private String infocenter; // 문의 및 안내
    private String opendate; // 개장일
    private String parking; // 주차시설
    private String restdate; // 쉬는날
    private String useseason;// 이용시기
    private String usetime; // 이용시간

    //--------------문화시설(14)-----------------//
    private String accomcountculture; // 수용인원
    private String culture; //신용카드 가능 여부
    private String chkpetculture; // 애완동물 가능 여부
    private String discountinfo; // 할인 정보
    private String infocenterculture; // 문의 및 안내
    private String parkingculture; // 주차시설
    private String parkingfee; // 주차요금
    private String restdateculture; //쉬는날
    private String usefee; //이용요금
    private String usetimeculture; //이용시간
    private String scale; //규모
    private String spendtime; // 관람 소요시간

    //--------------행사/축제/공연(15)-----------------//
    private String agelimit; //관람 가능연령
    private String bookingplace; // 예매처
    private String discountinfofestival; //할인정보
    private String eventenddate; // 행사 종료일
    private String eventhomepage; // 행사 홈페이지
    private String eventplace; //행사 장소
    private String eventstartdate; // 행사 시작일
    private String festivalgrade; // 축제 등급
    private String placeinfo; // 행사장 위치안내
    private String playtime; // 공연시간
    private String program; // 행사 프로그램
    private String spendtimefestival; // 관람 소요시간
    private String subevent; //부대행사
    private String usetimefestival; //이용요금

    //--------------여행 코스(25)-----------------//
    private String distance; //코스 총거리
    private String infocentertourcourse; // 문의 및 안내
    private String schedule; // 코스 일정
    private String taketime; // 코스 총 소요시간
    private String theme; // 코스 테마

    //--------------레포츠(28)-----------------//
    private String accomcountleports; //수용인원
    private String chkbabycarriageleports; //유모차 대여정보
    private String chkcreditcardleports; // 신용카드 정보
    private String chkpetleports; // 애완동물 동반 여부
    private String expagerangeleports; // 체험 가능 연령
    private String infocenterleports; // 문의 및 안내
    private String openperiod; //개장시간
    private String parkingfeeleports; // 주차요금
    private String parkingleports; // 주차 시설
    private String reservation; // 예약안내
    private String restdateleports; // 쉬는날
    private String scaleleports; // 규모
    private String usefeeleports; // 입장료
    private String usetimeleports; // 이용시간

    //--------------숙박(32)-----------------//
    private String accomcountlodging; //수용 가능인원
    private String benikia; // 베니키아 여부
    private String checkintime; // 입실시간
    private String checkouttime; //퇴실시간
    private String chkcooking; // 객실내 취사여부
    private String foodplace; // 식음료장
    private String goodstay; // 굿 스테이 여부
    private String hanok; // 한옥여부
    private String infocenterlodging; //문의 및 안내
    private String parkinglodging; // 주차시설
    private String pickup; // 픽업 서비스
    private String roomcount; // 객실 수
    private String reservationlodging; // 예약안내
    private String reservationurl; // 예약안내 홈페이지
    private String roomtype; // 객실유형
    private String scalelodging; // 규모
    private String subfacility;  //부대시설 기타
    private String barbecue; //바베큐장
    private String beauty; // 뷰티시설
    private String beverage; //식음료장
    private String bicycle; //자전거 대여 여부
    private String campfire; // 캠프파이어
    private String fitness; //휘트니스 센터
    private String karaoke; // 노래방
    private String publicbath; //공용 샤워실
    private String publicpc; //공용 PC 여부
    private String sauna; // 사우나닐
    private String seminar; //세미나실
    private String sports; // 스포츠 시설 여부

    //--------------쇼핑(38)-----------------//
    private String chkbabycarriageshopping; // 유모차 대여
    private String chkcreditcardshopping; // 신용카드 여부
    private String chkpetshopping; //애완동물
    private String culturecenter; //문화센터
    private String fairday; // 장서는날
    private String infocentershopping; //문의 안내
    private String opendateshopping; // 개장일
    private String opentime; // 영업시간
    private String parkingshopping; //주차시설
    private String restdateshopping; // 쉬는날
    private String restroom; // 화장실
    private String saleitem; // 판매 품목
    private String saleitemcost; // 품목별 가격
    private String scaleshopping; //규모
    private String shopguide; // 매장안내

    //--------------음식점(39)-----------------//
    private String discountinfofood; // 할인정보
    private String firstmenu; // 대표메뉴
    private String infocenterfood; //문의 및 안내
    private String kidsfacility; // 어린이 놀이방 여부
    private String opentimefood; // 영업시간
    private String packing; // 포장 여부
    private String parkingfood; //주자치설
    private String reservationfood; //예약안내
    private String restdatefood; // 쉬는날
    private String scalefood; // 규모
    private String seat; // 좌석수
    private String smoking; // 금연/흡연 여부
    private String treatmenu; // 추급 메뉴


}
