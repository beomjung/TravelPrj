package kopo.poly.controller;

import kopo.poly.dto.*;
import kopo.poly.service.IBoardService;
import kopo.poly.service.IMainService;
import kopo.poly.service.ITravelService;
import kopo.poly.service.IUserService;
import kopo.poly.service.impl.TravelService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class TravelController {

    @Resource(name = "MainService")
    private IMainService mainService;

    @Resource(name = "UserService")
    private IUserService userService;

    @Resource(name = "BoardService")
    private IBoardService boardService;

    @Resource(name = "TravelService")
    private ITravelService travelService;


    @GetMapping(value = "travel/travelChoice")
    public String travelChoice() throws Exception {
        log.info(this.getClass().getName() + "여행 선택 컨트롤러 시작!");

        log.info(this.getClass().getName() + "여행 선택 컨트롤러  종료!");

        return "/travel/travelChoice";
    }

    @GetMapping(value = "travel/koreaTravel")
    public String koreaTravel(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "국내 관광지 소개 시작!");

        String loc = CmmUtil.nvl(request.getParameter("location"));
        String loc2 = CmmUtil.nvl(request.getParameter("smallcity"));
        String t_type = CmmUtil.nvl(request.getParameter("travel_type"));
        String b_type = CmmUtil.nvl(request.getParameter("big_label"));
        String m_type = CmmUtil.nvl(request.getParameter("middle_label"));
        String s_type = CmmUtil.nvl(request.getParameter("small_label"));

        log.info("도 : " + loc);
        log.info("시구군 : " + loc2);
        log.info("여행타입 : " + t_type);
        log.info("대분류 : " + b_type);
        log.info("중분류 : " + m_type);
        log.info("소분류 : " + s_type);

        TravelLocDTO pDTO = new TravelLocDTO();
        pDTO.setLoc(loc);
        pDTO.setLoc2(loc2);
        pDTO.setT_type(t_type);
        pDTO.setB_type(b_type);
        pDTO.setM_type(m_type);
        pDTO.setS_type(s_type);

        List<TravelLocDTO> rList = travelService.serachTravel(pDTO);

        model.addAttribute("rList", rList);
        log.info(this.getClass().getName() + "국내 관광지 소개 종료!");
        return "/travel/koreaTravel";
    }

    @GetMapping(value = "travel/koreaTravelDetail")
    public String koreaTravelDetail(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "여행 디테일 페이지 시작!");
        String contentId = CmmUtil.nvl((request.getParameter("contentId")));
        String contenttypeId = CmmUtil.nvl((request.getParameter("contenttypeId")));
        log.info("컨텐츠 아이디 : " + contentId);
        log.info("컨텐츠 타입 아이디 : " + contenttypeId);

        TravelLocDTO pDTO = new TravelLocDTO();
        pDTO.setContentID(contentId);
        pDTO.setContentTypeID(contenttypeId);

        TravelLocDTO cDTO = travelService.travelInfo(pDTO);
        log.info("조회한 공통정보 결과는 ? " + (cDTO != null));
        TravelReInfoDTO rDTO = travelService.travelReInfo(pDTO);
        log.info("조회한 반복정보 결과는 ? " + (rDTO != null));
        TravelManyInfoDTO mDTO = travelService.travelMoney(pDTO);
        log.info("조회한 소개정보 결과는 ? " + (rDTO != null));
        log.info(mDTO.getEventenddate());
        log.info(mDTO.getEventstartdate());

        if (cDTO.getMsg() != "null") {
            model.addAttribute("cDTO", cDTO);
        }
        if (rDTO.getMsg() != "null") {
            model.addAttribute("rDTO", rDTO);
        }
        if (pDTO.getMsg() != "null") {
            model.addAttribute("mDTO", mDTO);
        }
        log.info(this.getClass().getName() + "여행 디테일 페이지 종료!");

        return "/travel/koreaTravelDetail";
    }

    @GetMapping(value = "travel/koreaTravelDetail12")
    public String koreaTravelDetail12(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "여행 디테일 페이지 시작!");
        String contentId = CmmUtil.nvl((request.getParameter("contentId")));
        String contenttypeId = CmmUtil.nvl((request.getParameter("contenttypeId")));
        log.info("컨텐츠 아이디 : " + contentId);
        log.info("컨텐츠 타입 아이디 : " + contenttypeId);

        TravelLocDTO pDTO = new TravelLocDTO();
        pDTO.setContentID(contentId);
        pDTO.setContentTypeID(contenttypeId);

        TravelLocDTO cDTO = travelService.travelInfo(pDTO);
        log.info("조회한 공통정보 결과는 ? " + (cDTO != null));
        TravelReInfoDTO rDTO = travelService.travelReInfo(pDTO);
        log.info("조회한 반복정보 결과는 ? " + (rDTO != null));
        TravelManyInfoDTO mDTO = travelService.travelMoney(pDTO);
        log.info("조회한 소개정보 결과는 ? " + (rDTO != null));
        log.info(mDTO.getEventenddate());
        log.info(mDTO.getEventstartdate());

        if (cDTO.getMsg() != "null") {
            model.addAttribute("cDTO", cDTO);
        }
        if (rDTO.getMsg() != "null") {
            model.addAttribute("rDTO", rDTO);
        }
        if (pDTO.getMsg() != "null") {
            model.addAttribute("mDTO", mDTO);
        }
        log.info(this.getClass().getName() + "여행 디테일 페이지 종료!");

        return "/travel/koreaTravelDetail12";
    }

    @GetMapping(value = "travel/koreaTravelDetail14")
    public String koreaTravelDetail14(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "여행 디테일 페이지 시작!");
        String contentId = CmmUtil.nvl((request.getParameter("contentId")));
        String contenttypeId = CmmUtil.nvl((request.getParameter("contenttypeId")));
        log.info("컨텐츠 아이디 : " + contentId);
        log.info("컨텐츠 타입 아이디 : " + contenttypeId);

        TravelLocDTO pDTO = new TravelLocDTO();
        pDTO.setContentID(contentId);
        pDTO.setContentTypeID(contenttypeId);

        TravelLocDTO cDTO = travelService.travelInfo(pDTO);
        log.info("조회한 공통정보 결과는 ? " + (cDTO != null));
        TravelReInfoDTO rDTO = travelService.travelReInfo(pDTO);
        log.info("조회한 반복정보 결과는 ? " + (rDTO != null));
        TravelManyInfoDTO mDTO = travelService.travelMoney(pDTO);
        log.info("조회한 소개정보 결과는 ? " + (rDTO != null));
        log.info(mDTO.getEventenddate());
        log.info(mDTO.getEventstartdate());

        if (cDTO.getMsg() != "null") {
            model.addAttribute("cDTO", cDTO);
        }
        if (rDTO.getMsg() != "null") {
            model.addAttribute("rDTO", rDTO);
        }
        if (pDTO.getMsg() != "null") {
            model.addAttribute("mDTO", mDTO);
        }
        log.info(this.getClass().getName() + "여행 디테일 페이지 종료!");

        return "/travel/koreaTravelDetail14";
    }

    @GetMapping(value = "travel/koreaTravelDetail25")
    public String koreaTravelDetail25(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "여행 디테일 페이지 시작!");
        String contentId = CmmUtil.nvl((request.getParameter("contentId")));
        String contenttypeId = CmmUtil.nvl((request.getParameter("contenttypeId")));
        log.info("컨텐츠 아이디 : " + contentId);
        log.info("컨텐츠 타입 아이디 : " + contenttypeId);

        TravelLocDTO pDTO = new TravelLocDTO();
        pDTO.setContentID(contentId);
        pDTO.setContentTypeID(contenttypeId);

        TravelLocDTO cDTO = travelService.travelInfo(pDTO);
        log.info("조회한 공통정보 결과는 ? " + (cDTO != null));
        List<TravelReInfoDTO> rList = travelService.listTravelReinfo(pDTO);
        log.info("조회한 반복정보 결과는 ? " + (rList != null));
        TravelManyInfoDTO mDTO = travelService.travelMoney(pDTO);
        log.info("조회한 소개정보 결과는 ? " + (mDTO != null));

        log.info("가져온 결과 여기서 찍히나" + rList.get(0).getSubdetailalt());

        if (cDTO.getMsg() != "null") {
            model.addAttribute("cDTO", cDTO);
        }
        model.addAttribute("rList", rList);
        if (pDTO.getMsg() != "null") {
            model.addAttribute("mDTO", mDTO);
        }
        log.info(this.getClass().getName() + "여행 디테일 페이지 종료!");

        return "/travel/koreaTravelDetail25";
    }

    @GetMapping(value = "travel/koreaTravelDetail28")
    public String koreaTravelDetail28(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "여행 디테일 페이지 시작!");
        String contentId = CmmUtil.nvl((request.getParameter("contentId")));
        String contenttypeId = CmmUtil.nvl((request.getParameter("contenttypeId")));
        log.info("컨텐츠 아이디 : " + contentId);
        log.info("컨텐츠 타입 아이디 : " + contenttypeId);

        TravelLocDTO pDTO = new TravelLocDTO();
        pDTO.setContentID(contentId);
        pDTO.setContentTypeID(contenttypeId);

        TravelLocDTO cDTO = travelService.travelInfo(pDTO);
        log.info("조회한 공통정보 결과는 ? " + (cDTO != null));
        List<TravelReInfoDTO> rList = travelService.listTravelReinfo(pDTO);
        log.info("조회한 반복정보 결과는 ? " + (rList != null));
        TravelManyInfoDTO mDTO = travelService.travelMoney(pDTO);
        log.info("조회한 소개정보 결과는 ? " + (mDTO != null));
        log.info(mDTO.getEventenddate());
        log.info(mDTO.getEventstartdate());

        if (cDTO.getMsg() != "null") {
            model.addAttribute("cDTO", cDTO);
        }
        model.addAttribute("rList", rList);
        if (pDTO.getMsg() != "null") {
            model.addAttribute("mDTO", mDTO);
        }
        log.info(this.getClass().getName() + "여행 디테일 페이지 종료!");
        return "/travel/koreaTravelDetail28";
    }

    @GetMapping(value = "travel/koreaTravelDetail32")
    public String koreaTravelDetail32(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "여행 디테일 페이지 시작!");
        String contentId = CmmUtil.nvl((request.getParameter("contentId")));
        String contenttypeId = CmmUtil.nvl((request.getParameter("contenttypeId")));
        log.info("컨텐츠 아이디 : " + contentId);
        log.info("컨텐츠 타입 아이디 : " + contenttypeId);

        TravelLocDTO pDTO = new TravelLocDTO();
        pDTO.setContentID(contentId);
        pDTO.setContentTypeID(contenttypeId);

        TravelLocDTO cDTO = travelService.travelInfo(pDTO);
        log.info("조회한 공통정보 결과는 ? " + (cDTO != null));
        List<TravelReInfoDTO> rList = travelService.listTravelReinfo(pDTO);
        log.info("조회한 반복정보 결과는 ? " + (rList != null));
        TravelManyInfoDTO mDTO = travelService.travelMoney(pDTO);
        log.info("조회한 소개정보 결과는 ? " + (mDTO != null));
        log.info(mDTO.getEventenddate());
        log.info(mDTO.getEventstartdate());

        if (cDTO.getMsg() != "null") {
            model.addAttribute("cDTO", cDTO);
        }
        if (rList.get(0).getMsg() != "null") {
            model.addAttribute("rList", rList);
        }
        if (pDTO.getMsg() != "null") {
            model.addAttribute("mDTO", mDTO);
        }
        log.info(this.getClass().getName() + "여행 디테일 페이지 종료!");

        return "/travel/koreaTravelDetail32";
    }

    @GetMapping(value = "travel/koreaTravelDetail38")
    public String koreaTravelDetail38(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "여행 디테일 페이지 시작!");
        String contentId = CmmUtil.nvl((request.getParameter("contentId")));
        String contenttypeId = CmmUtil.nvl((request.getParameter("contenttypeId")));
        log.info("컨텐츠 아이디 : " + contentId);
        log.info("컨텐츠 타입 아이디 : " + contenttypeId);

        TravelLocDTO pDTO = new TravelLocDTO();
        pDTO.setContentID(contentId);
        pDTO.setContentTypeID(contenttypeId);

        TravelLocDTO cDTO = travelService.travelInfo(pDTO);
        log.info("조회한 공통정보 결과는 ? " + (cDTO != null));
        TravelReInfoDTO rDTO = travelService.travelReInfo(pDTO);
        log.info("조회한 반복정보 결과는 ? " + (rDTO != null));
        TravelManyInfoDTO mDTO = travelService.travelMoney(pDTO);
        log.info("조회한 소개정보 결과는 ? " + (rDTO != null));
        log.info(mDTO.getEventenddate());
        log.info(mDTO.getEventstartdate());

        if (cDTO.getMsg() != "null") {
            model.addAttribute("cDTO", cDTO);
        }
        if (rDTO.getMsg() != "null") {
            model.addAttribute("rDTO", rDTO);
        }
        if (pDTO.getMsg() != "null") {
            model.addAttribute("mDTO", mDTO);
        }
        log.info(this.getClass().getName() + "여행 디테일 페이지 종료!");

        return "/travel/koreaTravelDetail38";
    }

    @GetMapping(value = "travel/koreaTravelDetail39")
    public String koreaTravelDetail39(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "여행 디테일 페이지 시작!");
        String contentId = CmmUtil.nvl((request.getParameter("contentId")));
        String contenttypeId = CmmUtil.nvl((request.getParameter("contenttypeId")));
        log.info("컨텐츠 아이디 : " + contentId);
        log.info("컨텐츠 타입 아이디 : " + contenttypeId);

        TravelLocDTO pDTO = new TravelLocDTO();
        pDTO.setContentID(contentId);
        pDTO.setContentTypeID(contenttypeId);

        TravelLocDTO cDTO = travelService.travelInfo(pDTO);
        log.info("조회한 공통정보 결과는 ? " + (cDTO != null));
        TravelReInfoDTO rDTO = travelService.travelReInfo(pDTO);
        log.info("조회한 반복정보 결과는 ? " + (rDTO != null));
        TravelManyInfoDTO mDTO = travelService.travelMoney(pDTO);
        log.info("조회한 소개정보 결과는 ? " + (rDTO != null));
        log.info(mDTO.getEventenddate());
        log.info(mDTO.getEventstartdate());

        if (cDTO.getMsg() != "null") {
            model.addAttribute("cDTO", cDTO);
        }
        if (rDTO.getMsg() != "null") {
            model.addAttribute("rDTO", rDTO);
        }
        if (pDTO.getMsg() != "null") {
            model.addAttribute("mDTO", mDTO);
        }
        log.info(this.getClass().getName() + "여행 디테일 페이지 종료!");

        return "/travel/koreaTravelDetail39";
    }


    @GetMapping(value = "travel/TravelRecommend")
    public String TravelRecommend() throws Exception {
        log.info(this.getClass().getName() + "회원 여행 추천 서비스 시작!");


        log.info(this.getClass().getName() + "회원 여행 추천 서비스 종료!");

        return "/travel/TravelRecommend";
    }

    @GetMapping(value = "travel/userTravelChoice")
    public String userTravelChoice(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "사용자 여행 입력 컨트롤러 시작!");


        log.info(this.getClass().getName() + "사용자 여행 입력 컨트롤러 종료!");

        return "/travel/userTravelChoice";
    }

    @GetMapping(value = "travel/TravelResult")
    public String userTravelChoiceProc(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "사용자 여행 입력 처리 컨트롤러 시작!");

        int user_seq = (int) session.getAttribute("user_seq");

        UserDTO uDTO = mainService.selectUserAddr(user_seq);
        String[] location = request.getParameterValues("location");
        String[] contenttypeId = request.getParameterValues("travel_type");
        String[] keyword = request.getParameterValues("keyword");

        TravelLocDTO pDTO = new TravelLocDTO();

        List<NlpDTO> nList = new ArrayList<>();

        List<TravelLocDTO> tList = new ArrayList<>();
        TravelLocDTO rDTO = new TravelLocDTO();

        List<TravelLocDTO> rList = new ArrayList<>();

        log.info("가져온 지역 정보 결과는 ? " + location[0]);
        log.info("가져온 컨텐츠 정보 결과는 ? " + contenttypeId[0]);
        log.info("가져온 키워드 결과는 ? " + keyword[0]);

        for (int i = 0; i < location.length; i++) {
            pDTO = new TravelLocDTO();
            pDTO.setDetilaLoc(location[i]);
            pDTO.setContentTypeID(contenttypeId[i]);
            pDTO.setKeyword(keyword[i]);

            rList.add(pDTO);
        }

        log.info("저장한 유저 관광지 크기는 ? " + rList.size());

        List<TravelLocDTO> pList = new ArrayList<>();
        pList = travelService.userChoiceTravel(rList);

        for(int i=0; i< rList.size(); i++){
            pDTO = new TravelLocDTO();
            rDTO = new TravelLocDTO();
            pDTO.setContentID(pList.get(i).getContentID());
            pDTO.setContentTypeID(pList.get(i).getContentTypeID());
            rDTO = travelService.travelInfo(pDTO);
            tList.add(rDTO);

        }

        log.info("넘어온 공통정보 갯수 : " + tList.size());


        model.addAttribute("uDTO", uDTO);
        model.addAttribute("tList", tList);
        model.addAttribute("pList", pList);

        log.info(this.getClass().getName() + "사용자 여행 입력 처리 컨트롤러 종료!");

        return "/travel/TravelResult";

    }

    @GetMapping(value = "travel/TravelResult2")
    public String nlpResultProc(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "사용자 여행추천 처리 컨트롤러 시작!");

        int user_seq = (int) session.getAttribute("user_seq");
        UserDTO uDTO = mainService.selectUserAddr(user_seq);
        String location = request.getParameter("location");
        String location2 = request.getParameter("smallcity");

        log.info("가져온 지역코드는? " + location);
        log.info("가져온 시구군 코드는? " + location2);

        List<TravelLocDTO> rList = new ArrayList<>();
        List<NlpDTO> nList = new ArrayList<>();
        List<TravelManyInfoDTO> mList = new ArrayList<>(); // 소개정보 조회
        List<TravelReInfoDTO> eList = new ArrayList<>(); // 반복정보 조회
        List<TravelLocDTO> tList = new ArrayList<>();


        TravelLocDTO tDTO = new TravelLocDTO();
        TravelLocDTO cDTO = new TravelLocDTO();

        NlpDTO pDTO = new NlpDTO();

        pDTO.setAreacode(location);
        pDTO.setDetail_areacode(location2);

        nList = mainService. userNlpRes(pDTO);
        log.info("컨트롤러에서 받은 자연어 처리 결과 갯수 "+ nList.size());

        rList = mainService.recommendTravel(pDTO);
        log.info("컨트롤러에서 받은 추천 여행지 "+ rList.size());

        for(int i=0; i< rList.size(); i++){
            cDTO = new TravelLocDTO();
            tDTO = new TravelLocDTO();
            cDTO.setContentID(rList.get(i).getContentID());
            cDTO.setContentTypeID(rList.get(i).getContentTypeID());
            tDTO = travelService.travelInfo(cDTO);
            tList.add(tDTO);

        }

        log.info("넘어온 공통정보 갯수 : " + tList.size());


        model.addAttribute("uDTO", uDTO);
        model.addAttribute("tList", tList);
        model.addAttribute("rList", rList);
        model.addAttribute("nList", nList);
        log.info(this.getClass().getName() + "사용자 여행추천 처리 컨트롤러 종료!");

        return "/travel/TravelResult2";

    }

}
