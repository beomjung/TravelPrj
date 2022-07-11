package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelReInfoDTO {

    private String msg;

    //---------공통 -----------//
    private String infoname; //타이틀
    private String infotext; // 내용

    //---------여행코스(25)-----------//
    private String subdetailimg; // 코스 이미지
    private String subdetailoverview; //코스 개요
    private String subname; //코스명
    private String subdetailalt; //코스 이미지 설명

    //---------숙박(32)-----------//
    private String roomcode; //객실 코드
    private String roomtitle; //객실 명칭
    private String roomsize1; //객실 크기
    private String roomcount; //객실 수
    private String roombasecount; //기준인원
    private String roommaxcount; // 최대인원
    private String roomoffseasonminfee1; //비수기 주중 최소
    private String roomoffseasonminfee2; //비수기 주말 최소
    private String roompeakseasonminfee1; //성수기 주중 최소
    private String roompeakseasonminfee2; // 성수기 주말 최소
    private String roomintro; //객실 소개
    private String roombathfacility; //목욕시설 여부
    private String roombath; // 욕조여부
    private String roomhometheater; //홈시어터 여부
    private String roomaircondition; // 에어컨 여부
    private String roomtv; // Tv여부
    private String roompc; //PC 여부
    private String roomcable; // 케이블 여부
    private String roominternet; // 인터넷 여부
    private String roomrefrigerator; // 냉자고 여부
    private String roomtoiletries; // 세면돋구 여부
    private String roomsofa; //소파
    private String roomcook; // 취사용품
    private String roomtable; // 테이블
    private String roomhairdryer; // 드라이기
    private String roomsize2; //객실크기 (평방)
    private String roomimg1; // 객실사진 1
    private String roomimg1alt; //객실사진 설명
    private String roomimg2; // 객실사진 2
    private String roomimg2alt; //객실사진 설명
    private String roomimg3;
    private String roomimg3alt;
    private String roomimg4;
    private String roomimg4alt;
    private String roomimg5;
    private String roomimg5alt;


}
