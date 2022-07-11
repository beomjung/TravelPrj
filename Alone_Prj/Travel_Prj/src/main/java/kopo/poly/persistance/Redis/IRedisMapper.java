package kopo.poly.persistance.Redis;

import kopo.poly.dto.ChatDTO;

import java.util.List;

public interface IRedisMapper {

    int InsertChatMessage(ChatDTO pDTO) throws Exception;

    List<ChatDTO> getChatInfo(String roomkey) throws Exception;

    int getRoomKey(String key) throws Exception;

}
