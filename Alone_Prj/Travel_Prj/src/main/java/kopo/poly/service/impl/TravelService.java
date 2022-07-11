package kopo.poly.service.impl;

import kopo.poly.dto.NlpDTO;
import kopo.poly.dto.TravelLocDTO;
import kopo.poly.dto.TravelManyInfoDTO;
import kopo.poly.dto.TravelReInfoDTO;
import kopo.poly.persistance.mapper.IMainMapper;
import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.service.ITravelService;
import kopo.poly.util.TravelUtil;
import kopo.poly.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("TravelService")
public class TravelService implements ITravelService {

    @Override
    public List<TravelLocDTO> serachTravel(TravelLocDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + "지역정보기반 관광데이터 조회 시작!");
        String msg = "";
        if (pDTO == null) {
            pDTO = new TravelLocDTO();
        }

        if (pDTO.getT_type() == "14" && pDTO.getB_type() == "AA02") {
            pDTO.setB_type("A02");
        }

        if (pDTO.getT_type() == "15" && pDTO.getB_type() == "AAA02") {
            pDTO.setB_type("A02");
        }
        List<TravelLocDTO> rList = new ArrayList<>();

        UrlUtil uu = new UrlUtil();

        String url = "http://13.124.6.224:8000";
        String api = "/Travel_main";
        String cat1 = "?cat1=" + pDTO.getB_type();
        String contentTypeId = "&contentTypeId=" + pDTO.getT_type();
        String areaCode = "&areaCode=" + pDTO.getLoc();
        String sigunguCod = "&sigunguCode=" + pDTO.getLoc2();
        String cat2 = "&cat2=" + pDTO.getM_type();
        String cat3 = "&cat3=" + pDTO.getS_type();
        String fullPath = uu.urlReadforString(url + api + cat1 + contentTypeId + areaCode + sigunguCod + cat2 + cat3);
        log.info("fullpath: " + fullPath);
        if (fullPath.length() < 1) {
            pDTO.setMsg("조회결과 없음");
            rList.add(pDTO);
            return rList;
        } else {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(fullPath);

            TravelLocDTO gDTO;
            log.info(String.valueOf(jsonArray.size()));

            log.info("가져온 데이터 사이즈? : " + jsonArray.size());
            for (int i = 0; i < jsonArray.size(); i++) {
                gDTO = new TravelLocDTO();
                JSONObject object = (JSONObject) jsonArray.get(i);
                gDTO.setDetilaLoc(String.valueOf(object.get("addr1")));
                gDTO.setLoc(String.valueOf(object.get("areacode")));
                gDTO.setContentID(String.valueOf(object.get("contentid")));
                gDTO.setContentTypeID(String.valueOf(object.get("contenttypeid")));
                gDTO.setFristimage(String.valueOf(object.get("firstimage")));
                gDTO.setFristimage2(String.valueOf(object.get("firstimage2")));
                gDTO.setMapx(String.valueOf(object.get("mapx")));
                gDTO.setMapy(String.valueOf(object.get("mapy")));
                gDTO.setMapLevel(String.valueOf(object.get("mlevel")));
                gDTO.setReadCnt(String.valueOf(object.get("readcount")));
                gDTO.setTitle(String.valueOf(object.get("title")));

                if (gDTO.getFristimage() != "null") {
                    rList.add(gDTO);
                }
            }
        }
        log.info(this.getClass().getName() + "지역정보기반 관광데이터 조회 종료!");
        return rList;
    }

    @Override
    public TravelLocDTO travelInfo(TravelLocDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "관광데이터 공통정보 조회 시작!!");

        if (pDTO == null) {
            pDTO = new TravelLocDTO();
        }

        List<TravelLocDTO> rList = new ArrayList<>();

        UrlUtil uu = new UrlUtil();

        String url = "http://13.124.6.224:8000";
        String api = "/Travel_info";
        String contentId = "?contentId=" + pDTO.getContentID();
        String contentTypeId = "&contentTypeId=" + pDTO.getContentTypeID();

        TravelLocDTO gDTO = new TravelLocDTO();

        String fullPath = uu.urlReadforString(url + api + contentId + contentTypeId);
        if (fullPath == "") {
            gDTO.setMsg("null");
        } else {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(fullPath);


            gDTO.setHomepage(String.valueOf(object.get("homepage")));
            gDTO.setTel(String.valueOf(object.get("tel")));
            gDTO.setFristimage(String.valueOf(object.get("firstimage")));
            gDTO.setFristimage2(String.valueOf(object.get("firstimage2")));
            gDTO.setTitle(String.valueOf(object.get("title")));
            gDTO.setOverview(String.valueOf(object.get("overview")));
        }

        log.info(this.getClass().getName() + "관광데이터 공통정보 조회 종려!!");
        return gDTO;
    }

    @Override
    public TravelReInfoDTO travelReInfo(TravelLocDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "관광데이터 반복정보 조회 시작!!");

        if (pDTO == null) {
            pDTO = new TravelLocDTO();
        }

        UrlUtil uu = new UrlUtil();

        String url = "http://13.124.6.224:8000";
        String api = "/Travel_Reinfo";
        String contentId = "?contentId=" + pDTO.getContentID();
        String contentTypeId = "&contentTypeId=" + pDTO.getContentTypeID();

        TravelReInfoDTO gDTO = new TravelReInfoDTO();

        String fullPath = uu.urlReadforString(url + api + contentId + contentTypeId);

        log.info("넘어오는 값의 길이 : " + fullPath.length());
        if (fullPath.length() < 1) {
            gDTO.setMsg("null");
            return gDTO;
        } else {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(fullPath);

            if (pDTO.getContentTypeID() == "25") {
                gDTO.setSubname(String.valueOf(object.get("subname")));
                gDTO.setSubdetailoverview(String.valueOf(object.get("subdetailoverview")));
                gDTO.setSubdetailimg(String.valueOf(object.get("subdetailimg")));
                gDTO.setSubdetailalt(String.valueOf(object.get("subdetailalt")));
            } else if (pDTO.getContentTypeID() == "32") {
                gDTO.setRoomtitle(String.valueOf(object.get("roomtitle")));
                gDTO.setRoomsize1(String.valueOf(object.get("roomsize1")));
                gDTO.setRoomcount(String.valueOf(object.get("roomcount")));
                gDTO.setRoombasecount(String.valueOf(object.get("roombasecount")));
                gDTO.setRoommaxcount(String.valueOf(object.get("roommaxcount")));
                gDTO.setRoomoffseasonminfee1(String.valueOf(object.get("roomoffseasonminfee1")));
                gDTO.setRoomoffseasonminfee2(String.valueOf(object.get("roomoffseasonminfee2")));
                gDTO.setRoompeakseasonminfee1(String.valueOf(object.get("roompeakseasonminfee1")));
                gDTO.setRoompeakseasonminfee2(String.valueOf(object.get("roompeakseasonminfee2")));
                gDTO.setRoomintro(String.valueOf(object.get("roomintro")));
                gDTO.setRoombathfacility(String.valueOf(object.get("roombathfacility")));
                gDTO.setRoombath(String.valueOf(object.get("roombath")));
                gDTO.setRoomhometheater(String.valueOf(object.get("roomhometheater")));
                gDTO.setRoomaircondition(String.valueOf(object.get("roomaircondition")));
                gDTO.setRoomtv(String.valueOf(object.get("roomtv")));
                gDTO.setRoompc(String.valueOf(object.get("roompc")));
                gDTO.setRoomcable(String.valueOf(object.get("roomcable")));
                gDTO.setRoominternet(String.valueOf(object.get("roominternet")));
                gDTO.setRoomrefrigerator(String.valueOf(object.get("roomrefrigerator")));
                gDTO.setRoomtoiletries(String.valueOf(object.get("roomtoiletries")));
                gDTO.setRoomsofa(String.valueOf(object.get("roomsofa")));
                gDTO.setRoomcook(String.valueOf(object.get("roomcook")));
                gDTO.setRoomtable(String.valueOf(object.get("roomtable")));
                gDTO.setRoomhairdryer(String.valueOf(object.get("roomhairdryer")));
                gDTO.setRoomimg1(String.valueOf(object.get("roomimg1")));
                gDTO.setRoomimg2(String.valueOf(object.get("roomimg2")));
                gDTO.setRoomimg3(String.valueOf(object.get("roomimg3")));
                gDTO.setRoomimg4(String.valueOf(object.get("roomimg4")));
                gDTO.setRoomimg5(String.valueOf(object.get("roomimg5")));
                gDTO.setRoomimg1alt(String.valueOf(object.get("roomimg1alt")));
                gDTO.setRoomimg2alt(String.valueOf(object.get("roomimg2alt")));
                gDTO.setRoomimg3alt(String.valueOf(object.get("roomimg3alt")));
                gDTO.setRoomimg4alt(String.valueOf(object.get("roomimg4alt")));
                gDTO.setRoomimg5alt(String.valueOf(object.get("roomimg5alt")));
            } else {
                gDTO.setInfoname(String.valueOf(object.get("infoname")));
                gDTO.setInfotext(String.valueOf(object.get("infotext")));
            }
        }
        log.info(this.getClass().getName() + "관광데이터 반복정보 조회 종료!!");
        return gDTO;
    }


    @Override
    public TravelManyInfoDTO travelMoney(TravelLocDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "관광데이터 소개정보 조회 시작!");

        if (pDTO == null) {
            pDTO = new TravelLocDTO();
        }

        UrlUtil uu = new UrlUtil();

        String url = "http://13.124.6.224:8000";
        String api = "/Travel_moneyinfo";
        String contentId = "?contentId=" + pDTO.getContentID();
        String contentTypeId = "&contentTypeId=" + pDTO.getContentTypeID();

        String fullPath = uu.urlReadforString(url + api + contentId + contentTypeId);
        log.info("소개정보 조회 결과" + fullPath);

        TravelManyInfoDTO gDTO = new TravelManyInfoDTO();
        log.info("배열로 넘어오는 값의 길이 : " + fullPath.length());
        if (fullPath.length() < 1) {
            gDTO.setMsg("null");
            return gDTO;
        } else {
            log.info("여길 들어오기는 하나요?");
            log.info("컨텐츠 타입 값이 찍이긴 하나 ? " + pDTO.getContentTypeID());
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(fullPath);
            if (pDTO.getContentTypeID().equals("12")) {
                gDTO.setAccomcount(String.valueOf(object.get("accomcount")));
                gDTO.setChkbabycarriage(String.valueOf(object.get("chkbabycarriage")));
                gDTO.setChkcreditcard(String.valueOf(object.get("chkcreditcard")));
                gDTO.setChkpet(String.valueOf(object.get("chkpet")));
                gDTO.setExpagerange(String.valueOf(object.get("expagerange")));
                gDTO.setExpguide(String.valueOf(object.get("expguide")));
                gDTO.setHeritage1(String.valueOf(object.get("heritage1")));
                gDTO.setHeritage2(String.valueOf(object.get("heritage2")));
                gDTO.setHeritage3(String.valueOf(object.get("heritage3")));
                gDTO.setInfocenter(String.valueOf(object.get("infocenter")));
                gDTO.setOpendate(String.valueOf(object.get("opendate")));
                gDTO.setParking(String.valueOf(object.get("parking")));
                gDTO.setRestdate(String.valueOf(object.get("restdate")));
                gDTO.setUseseason(String.valueOf(object.get("useseason")));
                gDTO.setUsetime(String.valueOf(object.get("usetime")));
            } else if (pDTO.getContentTypeID().equals("14")) {
                gDTO.setAccomcountculture(String.valueOf(object.get("accomcountculture")));
                gDTO.setCulture(String.valueOf(object.get("chkcreditcardculture")));
                gDTO.setChkpetculture(String.valueOf(object.get("chkpetculture")));
                gDTO.setDiscountinfo(String.valueOf(object.get("discountinfo")));
                gDTO.setInfocenterculture(String.valueOf(object.get("infocenterculture")));
                gDTO.setParkingculture(String.valueOf(object.get("parkingculture")));
                gDTO.setParkingfee(String.valueOf(object.get("parkingfee")));
                gDTO.setRestdateculture(String.valueOf(object.get("restdateculture")));
                gDTO.setUsefee(String.valueOf(object.get("usefee")));
                gDTO.setUsetimeculture(String.valueOf(object.get("usetimeculture")));
                gDTO.setScale(String.valueOf(object.get("scale")));
                gDTO.setSpendtime(String.valueOf(object.get("spendtime")));
            } else if (pDTO.getContentTypeID().equals("15")) {
                gDTO.setAgelimit(String.valueOf(object.get("agelimit")));
                gDTO.setBookingplace(String.valueOf(object.get("bookingplace")));
                gDTO.setDiscountinfofestival(String.valueOf(object.get("discountinfofestival")));
                gDTO.setEventstartdate(String.valueOf(object.get("eventstartdate")));
                gDTO.setEventenddate(String.valueOf(object.get("eventenddate")));
                gDTO.setEventhomepage(String.valueOf(object.get("eventhomepage")));
                gDTO.setEventplace(String.valueOf(object.get("eventplace")));
                gDTO.setFestivalgrade(String.valueOf(object.get("festivalgrade")));
                gDTO.setPlaceinfo(String.valueOf(object.get("placeinfo")));
                gDTO.setPlaytime(String.valueOf(object.get("playtime")));
                gDTO.setProgram(String.valueOf(object.get("program")));
                gDTO.setSpendtimefestival(String.valueOf(object.get("spendtimefestival")));
                gDTO.setSubevent(String.valueOf(object.get("subevent")));
                gDTO.setUsetimefestival(String.valueOf(object.get("usetimefestival")));

                log.info("가져온 이벤트 결과는 ? " + gDTO.getEventstartdate());
                log.info("가져온 이벤트 결과는 ? " + gDTO.getEventenddate());
            } else if (pDTO.getContentTypeID().equals("25")) {
                gDTO.setDistance(String.valueOf(object.get("distance")));
                gDTO.setInfocentertourcourse(String.valueOf(object.get("infocentertourcourse")));
                gDTO.setSchedule(String.valueOf(object.get("schedule")));
                gDTO.setTaketime(String.valueOf(object.get("taketime")));
                gDTO.setTheme(String.valueOf(object.get("theme")));
            } else if (pDTO.getContentTypeID().equals("28")) {
                gDTO.setAccomcountleports(String.valueOf(object.get("accomcountleports")));
                gDTO.setChkbabycarriageleports(String.valueOf(object.get("chkbabycarriageleports")));
                gDTO.setChkpetleports(String.valueOf(object.get("chkcreditcardleports")));
                gDTO.setExpagerangeleports(String.valueOf(object.get("expagerangeleports")));
                gDTO.setInfocenterleports(String.valueOf(object.get("infocenterleports")));
                gDTO.setOpenperiod(String.valueOf(object.get("openperiod")));
                gDTO.setParkingfeeleports(String.valueOf(object.get("parkingfeeleports")));
                gDTO.setParkingleports(String.valueOf(object.get("parkingleports")));
                gDTO.setReservation(String.valueOf(object.get("reservation")));
                gDTO.setRestdateleports(String.valueOf(object.get("restdateleports")));
                gDTO.setScaleleports(String.valueOf(object.get("scaleleports")));
                gDTO.setUsefeeleports(String.valueOf(object.get("usefeeleports")));
                gDTO.setUsetimeleports(String.valueOf(object.get("usetimeleports")));
            } else if (pDTO.getContentTypeID().equals("32")) {
                gDTO.setAccomcountlodging(String.valueOf(object.get("accomcountlodging")));
                gDTO.setBenikia(String.valueOf(object.get("benikia")));
                gDTO.setCheckintime(String.valueOf(object.get("checkintime")));
                gDTO.setCheckouttime(String.valueOf(object.get("checkouttime")));
                gDTO.setChkcooking(String.valueOf(object.get("chkcooking")));
                gDTO.setFoodplace(String.valueOf(object.get("foodpce")));
                gDTO.setGoodstay(String.valueOf(object.get("goodstay")));
                gDTO.setHanok(String.valueOf(object.get("hanok")));
                gDTO.setInfocenterlodging(String.valueOf(object.get("infocenterlodging")));
                gDTO.setParkinglodging(String.valueOf(object.get("parkinglodging")));
                gDTO.setPickup(String.valueOf(object.get("pickup")));
                gDTO.setRoomcount(String.valueOf(object.get("roomcount")));
                gDTO.setReservationlodging(String.valueOf(object.get("reservationlodging")));
                gDTO.setReservationurl(String.valueOf(object.get("reservationurl")));
                gDTO.setRoomtype(String.valueOf(object.get("roomtype")));
                gDTO.setScalelodging(String.valueOf(object.get("scalelodging")));
                gDTO.setSubfacility(String.valueOf(object.get("subfacility")));
                gDTO.setBarbecue(String.valueOf(object.get("barbecue")));
                gDTO.setBeauty(String.valueOf(object.get("beauty")));
                gDTO.setBeverage(String.valueOf(object.get("beverage")));
                gDTO.setBicycle(String.valueOf(object.get("bicycle")));
                gDTO.setCampfire(String.valueOf(object.get("campfire")));
                gDTO.setFitness(String.valueOf(object.get("fitness")));
                gDTO.setKaraoke(String.valueOf(object.get("karaoke")));
                gDTO.setPublicbath(String.valueOf(object.get("publicbath")));
                gDTO.setPublicpc(String.valueOf(object.get("publicpc")));
                gDTO.setSauna(String.valueOf(object.get("sauna")));
                gDTO.setSeminar(String.valueOf(object.get("seminar")));
                gDTO.setSports(String.valueOf(object.get("sports")));

            } else if (pDTO.getContentTypeID().equals("38")) {
                gDTO.setChkbabycarriageshopping(String.valueOf(object.get("chkbabycarriageshopping")));
                gDTO.setChkcreditcardshopping(String.valueOf(object.get("chkcreditcardshopping")));
                gDTO.setChkpetshopping(String.valueOf(object.get("chkpetshopping")));
                gDTO.setCulturecenter(String.valueOf(object.get("culturecenter")));
                gDTO.setFairday(String.valueOf(object.get("fairday")));
                gDTO.setInfocentershopping(String.valueOf(object.get("infocentershopping")));
                gDTO.setOpendateshopping(String.valueOf(object.get("opendateshopping")));
                gDTO.setOpentime(String.valueOf(object.get("opentime")));
                gDTO.setParkingshopping(String.valueOf(object.get("parkingshopping")));
                gDTO.setRestdateshopping(String.valueOf(object.get("restdateshopping")));
                gDTO.setRestroom(String.valueOf(object.get("restroom")));
                gDTO.setSaleitem(String.valueOf(object.get("saleitem")));
                gDTO.setSaleitemcost(String.valueOf(object.get("saleitemcost")));
                gDTO.setScaleshopping(String.valueOf(object.get("scaleshopping")));
                gDTO.setShopguide(String.valueOf(object.get("shopguide")));
            } else if (pDTO.getContentTypeID().equals("39")) {
                gDTO.setChkcreditcard(String.valueOf(object.get("chkcreditcardfood")));
                gDTO.setDiscountinfofood(String.valueOf(object.get("discountinfofood")));
                gDTO.setFirstmenu(String.valueOf(object.get("firstmenu")));
                gDTO.setInfocenterfood(String.valueOf(object.get("infocenterfood")));
                gDTO.setKidsfacility(String.valueOf(object.get("kidsfacility")));
                gDTO.setOpentimefood(String.valueOf(object.get("opentimefood")));
                gDTO.setPacking(String.valueOf(object.get("packing")));
                gDTO.setParkingfood(String.valueOf(object.get("parkingfood")));
                gDTO.setRestdatefood(String.valueOf(object.get("restdatefood")));
                gDTO.setReservationfood(String.valueOf(object.get("reservationfood")));
                gDTO.setScalefood(String.valueOf(object.get("scalefood")));
                gDTO.setSeat(String.valueOf(object.get("seat")));
                gDTO.setSmoking(String.valueOf(object.get("smoking")));
                gDTO.setTreatmenu(String.valueOf(object.get("treatmenu")));
            }
        }
        log.info(this.getClass().getName() + "관광데이터 소개정보 조회 종료!");
        return gDTO;
    }

    @Override
    public List<TravelReInfoDTO> listTravelReinfo(TravelLocDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "관광데이터 리스트 반복정보 조회 시작!!");
        if (pDTO == null) {
            pDTO = new TravelLocDTO();
        }

        UrlUtil uu = new UrlUtil();

        String url = "http://13.124.6.224:8000";
        String api = "/Travel_listReinfo";
        String contentId = "?contentId=" + pDTO.getContentID();
        String contentTypeId = "&contentTypeId=" + pDTO.getContentTypeID();

        TravelReInfoDTO gDTO = new TravelReInfoDTO();

        String fullPath = uu.urlReadforString(url + api + contentId + contentTypeId);
        log.info("반복 리스트 조회 결과!!" + fullPath);
        List<TravelReInfoDTO> rList = new ArrayList<>();

        log.info("넘어오는 값의 길이 : " + fullPath.length());
        if (fullPath.length() < 1) {
            pDTO.setMsg("조회결과 없음");
            rList.add(gDTO);
            return rList;
        } else {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(fullPath);
            for (int i = 0; i < jsonArray.size(); i++) {
                gDTO = new TravelReInfoDTO();
                log.info(String.valueOf(jsonArray.size()));
                JSONObject object = (JSONObject) jsonArray.get(i);

                if (pDTO.getContentTypeID().equals("25")) {
                    gDTO.setSubname(String.valueOf(object.get("subname")));
                    gDTO.setSubdetailoverview(String.valueOf(object.get("subdetailoverview")));
                    gDTO.setSubdetailimg(String.valueOf(object.get("subdetailimg")));
                    gDTO.setSubdetailalt(String.valueOf(object.get("subdetailalt")));
                    rList.add(gDTO);
                } else if (pDTO.getContentTypeID().equals("32")) {
                    gDTO.setRoomtitle(String.valueOf(object.get("roomtitle")));
                    gDTO.setRoomsize1(String.valueOf(object.get("roomsize1")));
                    gDTO.setRoomcount(String.valueOf(object.get("roomcount")));
                    gDTO.setRoombasecount(String.valueOf(object.get("roombasecount")));
                    gDTO.setRoommaxcount(String.valueOf(object.get("roommaxcount")));
                    gDTO.setRoomoffseasonminfee1(String.valueOf(object.get("roomoffseasonminfee1")));
                    gDTO.setRoomoffseasonminfee2(String.valueOf(object.get("roomoffseasonminfee2")));
                    gDTO.setRoompeakseasonminfee1(String.valueOf(object.get("roompeakseasonminfee1")));
                    gDTO.setRoompeakseasonminfee2(String.valueOf(object.get("roompeakseasonminfee2")));
                    gDTO.setRoomintro(String.valueOf(object.get("roomintro")));
                    gDTO.setRoombathfacility(String.valueOf(object.get("roombathfacility")));
                    gDTO.setRoombath(String.valueOf(object.get("roombath")));
                    gDTO.setRoomhometheater(String.valueOf(object.get("roomhometheater")));
                    gDTO.setRoomaircondition(String.valueOf(object.get("roomaircondition")));
                    gDTO.setRoomtv(String.valueOf(object.get("roomtv")));
                    gDTO.setRoompc(String.valueOf(object.get("roompc")));
                    gDTO.setRoomcable(String.valueOf(object.get("roomcable")));
                    gDTO.setRoominternet(String.valueOf(object.get("roominternet")));
                    gDTO.setRoomrefrigerator(String.valueOf(object.get("roomrefrigerator")));
                    gDTO.setRoomtoiletries(String.valueOf(object.get("roomtoiletries")));
                    gDTO.setRoomsofa(String.valueOf(object.get("roomsofa")));
                    gDTO.setRoomcook(String.valueOf(object.get("roomcook")));
                    gDTO.setRoomtable(String.valueOf(object.get("roomtable")));
                    gDTO.setRoomhairdryer(String.valueOf(object.get("roomhairdryer")));
                    gDTO.setRoomimg1(String.valueOf(object.get("roomimg1")));
                    gDTO.setRoomimg2(String.valueOf(object.get("roomimg2")));
                    gDTO.setRoomimg3(String.valueOf(object.get("roomimg3")));
                    gDTO.setRoomimg4(String.valueOf(object.get("roomimg4")));
                    gDTO.setRoomimg5(String.valueOf(object.get("roomimg5")));
                    gDTO.setRoomimg1alt(String.valueOf(object.get("roomimg1alt")));
                    gDTO.setRoomimg2alt(String.valueOf(object.get("roomimg2alt")));
                    gDTO.setRoomimg3alt(String.valueOf(object.get("roomimg3alt")));
                    gDTO.setRoomimg4alt(String.valueOf(object.get("roomimg4alt")));
                    gDTO.setRoomimg5alt(String.valueOf(object.get("roomimg5alt")));
                    rList.add(gDTO);
                } else {
                    gDTO.setInfoname(String.valueOf(object.get("infoname")));
                    gDTO.setInfotext(String.valueOf(object.get("infotext")));
                    rList.add(gDTO);
                }
            }
        }
        log.info(this.getClass().getName() + "관광데이터 리스트 반복정보 조회 시작!!");
        return rList;
    }

    @Override
    public List<TravelLocDTO> userChoiceTravel(List<TravelLocDTO> rList) throws Exception {
        log.info(this.getClass().getName() + "유저 선택 여행지 서비스 시작!");

        String fullPath = "";

        if (rList == null) {
            rList = new ArrayList<TravelLocDTO>();
        }

        List<TravelLocDTO> pList = new ArrayList<>();

        for (int i = 0; i < rList.size(); i++) {
            String areaCode = rList.get(i).getDetilaLoc();
            String contenttypeId = rList.get(i).getContentTypeID();
            String keyWord = rList.get(i).getKeyword();

            UrlUtil uu = new UrlUtil();
            String url = "http://13.124.6.224:8000";
            String api = "/UserKeyWord_travelInfo";
            String nareaCode = "?areaCode=" + areaCode;
            String nContenttypeId = "&contenttypeId=" + contenttypeId;
            String nkeyWord = "&keyword=";

            TravelLocDTO pDTO;


            fullPath = uu.urlReadforString(url + api + nareaCode + nContenttypeId + nkeyWord + URLEncoder.encode(keyWord, "UTF-8"));
            log.info("가져온 키워드 조회 결과는 ? " + fullPath);

            JSONParser parser = new JSONParser();

            pDTO = new TravelLocDTO();

            JSONObject object = (JSONObject) parser.parse(fullPath);
            pDTO.setDetilaLoc(String.valueOf(object.get("addr1")));
            pDTO.setContentTypeID(String.valueOf(object.get("contenttypeid")));
            pDTO.setContentID(String.valueOf(object.get("contentid")));
            pDTO.setReadCnt(String.valueOf(object.get("readcount")));
            pDTO.setFristimage(String.valueOf(object.get("firstimage")));
            pDTO.setFristimage2(String.valueOf(object.get("firstimage2")));
            pDTO.setTitle(String.valueOf(object.get("title")));
            pDTO.setMapx(String.valueOf(object.get("mapx")));
            pDTO.setMapy(String.valueOf(object.get("mapy")));

            if (pDTO.getFristimage() != "null") {
                pList.add(pDTO);
            }
        }


        log.info(this.getClass().getName() + "유저 선택 여행지 서비스 종료!");
        return pList;
    }


}
