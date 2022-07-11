package kopo.poly.controller;

import java.util.*;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

// ServerEndpoint라는 Annotation에서, 본 서버가 어떤 주소로 바인딩 되는지를 정의한다.
@Controller
@Slf4j
@ServerEndpoint(value="/echo.do")
public class WebsocketController {

    private static Set<Session> clients =
            Collections.synchronizedSet(new HashSet<Session>());

    public WebsocketController() {
        log.info("웹소켓(서버) 객체 생성");
    }

    // 웹소켓 연결이 구성되면, Session(Session 인스턴스는 웹소켓이 닫히기 전까지 유효)이 생성되고
    // @OnOpen 어노테이션이 붙은 endpoint가 호출된다.
    @OnOpen
    public void onOpen(Session s) {
        System.out.println("open session : " + s.toString());
        log.info("Open session id:" + s.getId());
        if (!clients.contains(s)) {
            clients.add(s);
            System.out.println("session open : " + s);
        } else {
            System.out.println("이미 연결된 session 입니다!!!");
        }
    }

    @OnMessage
    public void onMessage(String msg, Session session) throws Exception {
        System.out.println("receive message : " + msg);

        for (Session s : clients) {

            System.out.println("send data : " + msg);

            s.getBasicRemote().sendText(msg);

        }

    }

    @OnError
    public void onError(Throwable e, Session session) {

    }

    @OnClose
    public void onClose(Session s) {
        System.out.println("session close : " + s);
        clients.remove(s);
    }

}
