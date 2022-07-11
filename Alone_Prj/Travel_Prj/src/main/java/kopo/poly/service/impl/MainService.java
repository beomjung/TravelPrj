package kopo.poly.service.impl;

import kopo.poly.dto.*;
import kopo.poly.persistance.Redis.IRedisMapper;
import kopo.poly.persistance.mapper.IMainMapper;
import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.service.IMainService;
import kopo.poly.util.DateUtil;
import kopo.poly.util.TravelUtil;
import kopo.poly.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.util.*;

@Slf4j
@Service("MainService")
public class MainService implements IMainService {

    public final IMainMapper mainMapper;
    public final IUserMapper userMapper;
    public final IRedisMapper redisMapper;

    @Autowired
    public MainService(IMainMapper mainMapper, IUserMapper userMapper, IRedisMapper redisMapper) {
        this.mainMapper = mainMapper;
        this.userMapper = userMapper;
        this.redisMapper = redisMapper;
    }


    @Override
    @Transactional
    public int updateUserInfo(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "회원정보 수정 서비스 로직 시작!");

        int res = 0;

        if (pDTO == null) {
            pDTO = new UserDTO();
        }
        res = mainMapper.updateUserInfo(pDTO);
        log.info("res : " + res);

        log.info(this.getClass().getName() + "회원정보 수정 서비스 로직 종료!");
        return res;
    }

    @Override
    @Transactional
    public int updateUser(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "이미지파일 없이 업데이트 시작!");
        int res = 0;
        if (pDTO == null) {
            pDTO = new UserDTO();
        }
        res = mainMapper.updateUser(pDTO);
        log.info(this.getClass().getName() + "이미지파일 없이 업데이트 종료!");
        return res;
    }

    @Override
    @Transactional
    public String selectUserImg(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "회원정보 이미지 가져오기 시작!");

        String user_file_path = mainMapper.selectUserImg(user_seq);

        log.info(this.getClass().getName() + "회원정보 이미지 가져오기 종료!");
        return user_file_path;
    }

    @Override
    @Transactional
    public UserDTO selectMyPage(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "마이페이지 정보 가져오기 시작!");

        UserDTO rDTO = mainMapper.selectMyPage(user_seq);

        log.info(this.getClass().getName() + "마이페이지 정보 가져오기 종료!");
        return rDTO;
    }

    @Override
    @Transactional
    public int insertBookMark(BookMarkDTO bDTO) throws Exception {
        log.info(this.getClass().getName() + "북마크 insert 서비스 로직 시작!");
        int res = 0;
        int book_seq = 0;

        if (bDTO == null) {
            bDTO = new BookMarkDTO();
        }
        res = mainMapper.insertBookMark(bDTO);

        if (res == 1) {
            book_seq = bDTO.getBookmark_seq();
            log.info("북마크 SEQ : " + book_seq);
            log.info(this.getClass().getName() + "북마크 insert 서비스 로직 종료!");
            return book_seq;
        } else {
            log.info(this.getClass().getName() + "북마크 insert 서비스 로직 실패!");
            return res;
        }
    }

    @Override
    @Transactional
    public List<BookMarkDTO> selectBookMark(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "북마크 죄회 서비스 시작!");

        List<BookMarkDTO> rList = null;

        rList = mainMapper.selectBookMark(user_seq);

        log.info(this.getClass().getName() + "북마크 죄회 서비스 종료!");
        return rList;
    }

    @Override
    @Transactional
    public List<UserDTO> selectMyPagePost(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "마이페이지 작성 게시물 조회 서비스 시작!");

        List<UserDTO> rList = mainMapper.selectMyPagePost(user_seq);

        log.info(this.getClass().getName() + "마이페이지 작성 게시물 조회 서비스 종료!");
        return rList;
    }

    @Override
    @Transactional
    public List<Board_PostDTO> selectFollowPost(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "팔로잉 유저 게시물 조회 서비스 시작!");

        List<FollowDTO> rList = new ArrayList<>();

        rList = mainMapper.selectFollowee(user_seq);

        List<Board_PostDTO> fList = new ArrayList<>();

        List<Board_PostDTO> nList = new ArrayList<>();

        if (rList != null) {
            for (int i = 0; i < rList.size(); i++) {

                fList = mainMapper.selectFollowPost(rList.get(i).getFollowee());
                nList.addAll(fList);
            }

        }

        log.info(this.getClass().getName() + "팔로잉 유저 게시물 조회 서비스 종료!");
        return nList;
    }

    @Override
    @Transactional
    public List<FollowDTO> selectFollowee(int user_seq) throws Exception {

        log.info(this.getClass().getName() + "팔로잉 유저 조회 서비스 시작!");

        log.info("팔로잉유저 조회 서비스 SEQ" + user_seq);

        List<FollowDTO> rList;
        rList = mainMapper.selectFollowee(user_seq);

        log.info("가져온 팔로잉유저 수는 : " + rList.size());

        FollowDTO pDTO = new FollowDTO();
        List<FollowDTO> nList = new ArrayList<>();
        if (rList != null) {
            for (int i = 0; i < rList.size(); i++) {
                pDTO = new FollowDTO();
                log.info("조회한 팔로잉 유저 : " + rList.get(i).getFollowee());
                user_seq = rList.get(i).getFollowee();
                log.info("조회할 유저 시퀀스 : " + user_seq);
                pDTO = mainMapper.selectFollowUserInfo(user_seq);
                if (pDTO.getUser_introducation() == null) {
                    pDTO.setUser_introducation("소개글이 없습니다");
                }
                if (pDTO != null) {
                    nList.add(pDTO);
                    log.info("팔로잉 조회 결과 : " + nList.size());
                }
            }
        }
        return nList;
    }

    @Override
    @Transactional
    public List<FollowDTO> selectFollower(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "팔로우 유저 조회 서비스 시작!");

        List<FollowDTO> rList;
        rList = mainMapper.selectFollower(user_seq);

        FollowDTO pDTO = new FollowDTO();
        List<FollowDTO> pList = new ArrayList<>();
        if (rList != null) {
            for (int i = 0; i < rList.size(); i++) {
                pDTO = mainMapper.selectFollowUserInfo(rList.get(i).getFollower());

                if (pDTO.getUser_introducation() == null) {
                    pDTO.setUser_introducation("소개글이 없습니다");
                }

                if (pDTO != null) {
                    pList.add(pDTO);
                }
            }
        }
        log.info(this.getClass().getName() + "팔로우 유저 조회 서비스 종료!");
        return pList;
    }

    @Override
    @Transactional
    public List<TravelLocDTO> selectUserInterest(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "회원 관심정보 조회 시작!");
        String cat1 = "";
        String cat2 = "";
        String cat3 = "";
        String fullPath = "";

        List<UserDTO> rList = mainMapper.selectUserInterest(user_seq);

        TravelLocDTO pDTO;

        List<TravelLocDTO> pList = new ArrayList<>();

        for (int i = 0; i < rList.size(); i++) {
            String content = rList.get(i).getUser_interest_content();

            String contenttypeId = TravelUtil.Travel_type(content);
            log.info("관광타입 : " + contenttypeId);

            UrlUtil uu = new UrlUtil();
            String url = "http://13.124.6.224:8000";
            String api = "/keyword_travelInfo";
            String nContenttypeId = "?contenttypeId=" + contenttypeId;
            String nkeyWord = "&keyword=";
            String keyWord = content;

            fullPath = uu.urlReadforString(url + api + nContenttypeId + nkeyWord + URLEncoder.encode(keyWord, "UTF-8"));
            log.info("가져온 키워드 조회 결과는 ? " + fullPath);

            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(fullPath);
            log.info("가져온 데이터 사이즈? : " + jsonArray.size());

            for (int j = 0; j < jsonArray.size(); j++) {

                pDTO = new TravelLocDTO();

                JSONObject object = (JSONObject) jsonArray.get(j);
                pDTO.setDetilaLoc(String.valueOf(object.get("addr1")));
                pDTO.setContentTypeID(String.valueOf(object.get("contenttypeid")));
                pDTO.setContentID(String.valueOf(object.get("contentid")));
                pDTO.setReadCnt(String.valueOf(object.get("readcount")));
                pDTO.setFristimage(String.valueOf(object.get("firstimage")));
                pDTO.setFristimage2(String.valueOf(object.get("firstimage2")));
                pDTO.setTitle(String.valueOf(object.get("title")));

                if (pDTO.getFristimage() != "null") {
                    pList.add(pDTO);
                }
            }
        }

        log.info("최종 담아온 api 호출 결과 사이즈는 ? " + pList.size());

        log.info(this.getClass().getName() + "회원 관심정보 조회 종료!");
        return pList;
    }

    @Override
    @Transactional
    public List<TravelLocDTO> recommendTravel(NlpDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "자연어처리 결과 조회 시작!");

        String fullPath = "";
        List<TravelLocDTO> tList = new ArrayList<>();

        if (pDTO == null) {
            pDTO = new NlpDTO();
        }
        log.info("서비스 까지 잘 들어오니? : " + pDTO.getAreacode());
        log.info("서비스 까지 잘 들어오니? : " + pDTO.getDetail_areacode());
        TravelLocDTO tDTO = null;

        List<NlpDTO> pList = new ArrayList<>();
        List<NlpDTO> rList = mainMapper.recommendTravel(pDTO);
        log.info("시도를 기준으로 조회한 관광지 갯수 : " + rList.size());


        NlpDTO nDTO = null;

        for (int i = 0; i < rList.size(); i++) {
            nDTO = new NlpDTO();
            nDTO = mainMapper.recommendStar(rList.get(i).getNlp_addr());
            pList.add(nDTO);
        }

        log.info("조회된 자연어 처리 결과 갯수 : " + pList.size());

        Collections.sort(pList, new Comparator<NlpDTO>() {
            @Override
            public int compare(NlpDTO o1, NlpDTO o2) {
                return Integer.compare(o2.getNlp_res(), o1.getNlp_res());
            }
        });

        for (int i = 0; i < 4; i++) {
            String areaCode = pList.get(i).getAreacode();
            String areaCode2 = pList.get(i).getDetail_areacode();
            String keyWord = pList.get(i).getNlp_addr();

            UrlUtil uu = new UrlUtil();
            String url = "http://13.124.6.224:8000";
            String api = "/User_Choice2";
            String nareaCode = "?areaCode=" + areaCode;
            String nareaCode2 = "&areaCode2=" + areaCode2;
            String nkeyWord = "&keyword=";

            fullPath = uu.urlReadforString(url + api + nareaCode + nareaCode2 + nkeyWord + URLEncoder.encode(keyWord, "UTF-8"));
            log.info("가져온 키워드 조회 결과는 ? " + fullPath);

            JSONParser parser = new JSONParser();

            tDTO = new TravelLocDTO();

            JSONObject object = (JSONObject) parser.parse(fullPath);
            tDTO.setDetilaLoc(String.valueOf(object.get("addr1")));
            tDTO.setContentTypeID(String.valueOf(object.get("contenttypeid")));
            tDTO.setContentID(String.valueOf(object.get("contentid")));
            tDTO.setReadCnt(String.valueOf(object.get("readcount")));
            tDTO.setFristimage(String.valueOf(object.get("firstimage")));
            tDTO.setFristimage2(String.valueOf(object.get("firstimage2")));
            tDTO.setTitle(String.valueOf(object.get("title")));
            tDTO.setMapx(String.valueOf(object.get("mapx")));
            tDTO.setMapy(String.valueOf(object.get("mapy")));

            tList.add(tDTO);
        }

        log.info(this.getClass().getName() + "자연어처리 결과 조회 종료!");
        return tList;
    }

    @Override
    @Transactional
    public List<NlpDTO> userNlpRes(NlpDTO pDTO) throws Exception {

        if (pDTO == null) {
            pDTO = new NlpDTO();
        }

        log.info("서비스 까지 잘 들어오니? : " + pDTO.getAreacode());
        log.info("서비스 까지 잘 들어오니? : " + pDTO.getDetail_areacode());

        List<NlpDTO> pList = new ArrayList<>();
        List<NlpDTO> rList = mainMapper.recommendTravel(pDTO);
        log.info("시도를 기준으로 조회한 관광지 갯수 : " + rList.size());


        NlpDTO nDTO = new NlpDTO();

        for (int i = 0; i < rList.size(); i++) {
            nDTO = new NlpDTO();
            nDTO = mainMapper.recommendStar(rList.get(i).getNlp_addr());
            pList.add(nDTO);
        }

        log.info("조회된 자연어 처리 결과 갯수 : " + pList.size());

        Collections.sort(pList, new Comparator<NlpDTO>() {
            @Override
            public int compare(NlpDTO o1, NlpDTO o2) {
                return Integer.compare(o2.getNlp_res(), o1.getNlp_res());
            }
        });
        return pList;
    }

    @Override
    @Transactional
    public UserDTO selectUserAddr(int user_seq) throws Exception {
        log.info(this.getClass().getName()+"유저 주소 조회 시작");

        UserDTO pDTO = new UserDTO();
        pDTO = mainMapper.selectUserAddr(user_seq);
        log.info(this.getClass().getName()+"유저 주소 조회 종료");
        return pDTO;
    }

    @Override
    @Transactional
    public UserDTO selectUserRoom(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "사용자 룸키 가져오기 서비스 시작!");

        int res = 0;
        int res2 = 0;
        String roomkey = "cs_"+user_seq;

        UserDTO pDTO = mainMapper.selectUserRoom(user_seq);
        res = redisMapper.getRoomKey(roomkey);

        if (pDTO.getRoomkey().equals("null") && res == 0) {
            pDTO.setUser_seq(user_seq);
            pDTO.setRoomkey(roomkey);
            res2 = mainMapper.updateUserRoom(pDTO);

            if (res2 == 1) {
                log.info(this.getClass().getName() + "사용자 룸키 RDB, REDIS 둘다 없음 서비스 종료!");
                return pDTO;
            }

        } else if (!pDTO.getRoomkey().equals("null") && res == 0) {
            pDTO.setUser_seq(user_seq);
            pDTO.setRoomkey(roomkey);
            res2 = mainMapper.updateUserRoom(pDTO);
            if (res2 == 1) {
                log.info(this.getClass().getName() + "사용자 룸키 RDB는 있고 REDIS는 없음 서비스 종료!");
                return pDTO;
            }
        }
            log.info(this.getClass().getName() + "사용자 룸키 가져오기 서비스 종료!");
            return pDTO;

    }

    @Override
    public List<UserDTO> selectUserRoomList() throws Exception {
        log.info(this.getClass().getName()+"관리자 룸 리스트 조회!");

        List<UserDTO> rList = mainMapper.selectUserRoomList();

        log.info("가져온 유저 RoomList? " + rList.size());

        log.info(this.getClass().getName()+"관리자 룸 리스트 종료!");
        return rList;
    }

    @Override
    public List<ChatDTO> getChatInfo(String roomkey) throws Exception {
        log.info(this.getClass().getName()+"채팅정보 가져오기 시작 !!!");

        List<ChatDTO> pList = redisMapper.getChatInfo(roomkey);

        log.info(this.getClass().getName()+"채팅정보 가져오기 종료 !!!");
        return pList;
    }

}
