package kopo.poly.service;

import kopo.poly.dto.NlpDTO;
import kopo.poly.dto.TravelLocDTO;
import kopo.poly.dto.TravelManyInfoDTO;
import kopo.poly.dto.TravelReInfoDTO;

import java.util.List;

public interface ITravelService {
    // 지역기반 관광데이터 조회
    List<TravelLocDTO> serachTravel(TravelLocDTO pDTO) throws Exception;
   //공통 정보 조회
    TravelLocDTO travelInfo(TravelLocDTO pDTO) throws Exception;
    //반복정보 조회
    TravelReInfoDTO travelReInfo(TravelLocDTO pDTO) throws Exception;
    //소개 정보 조회
    TravelManyInfoDTO travelMoney(TravelLocDTO pDTO) throws Exception;
    //숙박, 여행, 레포츠 반복정보 조회
    List<TravelReInfoDTO> listTravelReinfo(TravelLocDTO pDTO) throws Exception;
    //사용자 여행지 입력 정보 조회
    List<TravelLocDTO> userChoiceTravel(List<TravelLocDTO> rList) throws Exception;



}
