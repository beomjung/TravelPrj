package kopo.poly.service;

import kopo.poly.dto.ChatDTO;

import java.util.List;

public interface IChatService {

    //채팅 정보 저장
    int InsertChatMessage(ChatDTO pDTO) throws Exception;

    //채팅 정보 가져오기
    List<ChatDTO> getChatInfo(String cs_name) throws Exception;


}
