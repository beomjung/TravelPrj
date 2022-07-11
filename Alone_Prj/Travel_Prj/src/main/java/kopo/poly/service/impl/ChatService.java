package kopo.poly.service.impl;

import kopo.poly.dto.ChatDTO;
import kopo.poly.persistance.Redis.IRedisMapper;
import kopo.poly.service.IChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("ChatService")
public class ChatService implements IChatService {

    private final IRedisMapper redisMapper;

    @Autowired
    public ChatService(IRedisMapper redisMapper) {
        this.redisMapper = redisMapper;
    }

    @Override
    @Transactional
    public int InsertChatMessage(ChatDTO pDTO) throws Exception {
        log.info(this.getClass().getName()+"채팅정보 저장 시작!!");

        if(pDTO == null){
            pDTO = new ChatDTO();
        }

        int res = redisMapper.InsertChatMessage(pDTO);

        log.info(this.getClass().getName()+"채팅정보 저장 종료!!");
        return res;
    }

    @Override
    @Transactional
    public List<ChatDTO> getChatInfo(String cs_name) throws Exception {
        log.info(this.getClass().getName()+"채팅 정보 가져오기 시작!");
        List<ChatDTO> rList = redisMapper.getChatInfo(cs_name);
        log.info(this.getClass().getName()+"채팅 정보 가져오기 종료!");
        return rList;
    }
}
