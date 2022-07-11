package kopo.poly.service.impl;

import kopo.poly.dto.Board_PostDTO;
import kopo.poly.dto.NlpDTO;
import kopo.poly.dto.SentenceDTO;
import kopo.poly.persistance.mapper.ITensorMapper;
import kopo.poly.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@EnableScheduling
@Service("TensorService")
public class TensorService {

    public final ITensorMapper tensorMapper;

    @Autowired
    public TensorService(ITensorMapper tensorMapper) {
        this.tensorMapper = tensorMapper;
    }

    @Scheduled(cron = "0 0 1 * * *")	// 매일 00시 정각
//    @Scheduled(cron = "0 41 22 * * *")
    public void nlpStart() throws Exception {
        log.info(this.getClass().getName()+"자연어 처리 6+-시작!");
        int res = 0;
        List<Board_PostDTO> rList = tensorMapper.selectNlpResult();
        log.info("가져온 리스트 크기는 : " + rList.size());

        JSONObject obj = new JSONObject();
        //List<Board_PostDTO> 변환을 위한 arry json 객체 생성
        JSONArray jsonArray = new JSONArray();

        for(int i = 0; i< rList.size(); i++){
            // 배열에 들어갈 json 객체 생성
            JSONObject object = new JSONObject();
            object.put("user_age", rList.get(i).getUser_age());
            object.put("user_gender", rList.get(i).getUser_gender());
            object.put("board_post_content", rList.get(i).getBoard_post_content());
            object.put("board_post_postion", rList.get(i).getBoard_post_postion());
            object.put("areacode", rList.get(i).getAreacode());
            object.put("detail_areacode", rList.get(i).getDetail_areacode());

            //배열에 obj값 담아서 끝내기
            jsonArray.add(object);
        }
        log.info("json 변환 결과는 ? " + jsonArray.toJSONString());
        UrlUtil uu = new UrlUtil();

        String url = "http://13.124.6.224:8000";
        String api = "/nlp_jsonDataInfo";
        String nData = "?data=";
        String data = jsonArray.toJSONString();
        String fullPath = uu.urlReadforString(url + api + nData + URLEncoder.encode(data,"UTF-8"));

        log.info("전달받은 값은?" + fullPath);

        JSONParser parser = new JSONParser();
        JSONArray res_jsonArray = (JSONArray) parser.parse(fullPath);
        log.info(String.valueOf(res_jsonArray.size()));

        log.info("jsonarray로 변환한 결과 값은 ? " + res_jsonArray);
        NlpDTO nDTO;
        for(int i =0; i<res_jsonArray.size(); i++){
            nDTO = new NlpDTO();
            JSONObject object = (JSONObject) res_jsonArray.get(i);
            nDTO.setNlp_age((String) object.get("user_age"));
            nDTO.setNlp_gender((String) object.get("user_gender"));
            nDTO.setNlp_content((String) object.get("board_post_content"));
            nDTO.setNlp_addr((String) object.get("board_post_postion"));
            nDTO.setAreacode((String) object.get("areacode"));
            nDTO.setDetail_areacode((String) object.get("detail_areacode"));
            nDTO.setNlp_result((String) object.get("nlp_res"));

            res = tensorMapper.insertNlpResult(nDTO);
            log.info("처리 결과 입력 확인 : " + res);
        }

        log.info(this.getClass().getName()+"자연어 처리 파이썬 넘겨주기 종료!");
    }

    public List<SentenceDTO> spring_sentence(String study_type) throws Exception{
        log.info(this.getClass().getName()+"문장학습 학습 JSON 데이터 변환 시작");

        List<SentenceDTO> rList = tensorMapper.selectSentenceContent();
        // 가져온 데이터 받아줄 객체
        List<SentenceDTO> pList = new ArrayList<>();
        log.info("가져온 문장학습 데이터 길이 : " + rList.size());
        //array 타입 변화을 위한 객체 생성
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i< rList.size(); i++){
            // 배열에 들어갈 json 객체 생성
            JSONObject object = new JSONObject();
            object.put("spring_sentence", rList.get(i));
            //배열에 object 값 담기
            jsonArray.add(object);
        }
        log.info("json 변환 결과는 ? " + jsonArray.toJSONString());
        log.info(this.getClass().getName()+"문장학습 학습 JSON 데이터 변환 종료");

        log.info(this.getClass().getName()+"flask server 전송 시작!");

        UrlUtil uu = new UrlUtil();
        String url = "http://13.124.6.224:8000";
        String api = "/sentence_nlpRes";
        String nData = "?spring_sentence=";
        String data = jsonArray.toJSONString();
        String studyType = "&study_type=" + study_type;
        String fullPath = uu.urlReadforString(url + api + nData + URLEncoder.encode(data,"UTF-8")+
                studyType);
        log.info("전달받은 값은?" + fullPath);

        // 자연어 처리 결과 파싱
        JSONParser parser = new JSONParser();
        JSONArray res_jsonArray = (JSONArray) parser.parse(fullPath);
        log.info(String.valueOf(res_jsonArray.size()));
        SentenceDTO pDTO = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            pDTO = new SentenceDTO();
            JSONObject object = (JSONObject) jsonArray.get(i);
            pDTO.setSentence_content(String.valueOf(object.get(i)));
            pList.add(pDTO);
        }
        log.info(this.getClass().getName()+"flask server 전송 종료!");

        return pList;
    }

}
