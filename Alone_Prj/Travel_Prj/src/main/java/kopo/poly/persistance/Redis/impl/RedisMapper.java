package kopo.poly.persistance.Redis.impl;

import kopo.poly.dto.ChatDTO;
import kopo.poly.persistance.Redis.IRedisMapper;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("RedisMapper")
public class RedisMapper implements IRedisMapper {

    public final RedisTemplate<String, Object> redisDB;

    public RedisMapper(RedisTemplate<String, Object> redisDB) {
        this.redisDB = redisDB;
    }

    @Override
    public int InsertChatMessage(ChatDTO pDTO) throws Exception {

        log.info(this.getClass().getName()+"redis 채팅정보 저장 시작!");
        int res = 0;
        String redisKey = pDTO.getCs_name();

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class));

        if(!redisDB.hasKey(redisKey)){
            redisDB.opsForList().rightPush(redisKey,pDTO);
            redisDB.expire(redisKey, 3, TimeUnit.DAYS);
        }else{
            redisDB.opsForList().rightPush(redisKey,pDTO);
            redisDB.expire(redisKey, 3, TimeUnit.DAYS);
        }

        log.info(this.getClass().getName()+"redis 채팅정보 저장 종료!");
        res = 1;
        return res;
    }

    @Override
    public List<ChatDTO> getChatInfo(String roomkey) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getChat Start!");

        // Redis에서 가져온 결과 저장할 객체
        List<ChatDTO> rList = null;

        // 대화방 키 정보
        String roomKey = roomkey;

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class));

        if (redisDB.hasKey(roomKey)) {

            // 저장된 전체 레코드 가져오기
            rList = (List) redisDB.opsForList().range(roomKey, 0, -1);

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getChat End!");

        return rList;
    }

    @Override
    public int getRoomKey(String key) throws Exception {
        int res =0;
        if(redisDB.hasKey(key)){
            res = 1;
            return res;
        }else {
            return res;
        }
    }
}
