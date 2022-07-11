package kopo.poly.controller;

import com.amazonaws.services.xray.model.Http;
import kopo.poly.dto.*;
import kopo.poly.service.IBoardService;
import kopo.poly.service.IMainService;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MainController {

    @Resource(name = "MainService")
    private IMainService mainService;

    @Resource(name = "UserService")
    private IUserService userService;

    @Resource(name = "BoardService")
    private IBoardService boardService;

    @GetMapping(value = "main/home")
    public String home(HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "메인페이지 시작!");

        int res = 0;
        String user_name = (String) session.getAttribute("user_name");
        int user_seq = (int) session.getAttribute("user_seq");

        List<TravelLocDTO> rList = mainService.selectUserInterest(user_seq);
        log.info("세션에서 꺼낸 유저 이름은 : " + user_name);

        model.addAttribute("rList",rList);
        model.addAttribute("user_name", user_name);
        log.info(this.getClass().getName() + "메인페이지 종료!");
        return "/main/home";
    }

    @GetMapping(value = "main/myPage")
    public String myPage() {
        log.info(this.getClass().getName() + "마이페이지 시작!");

        return "/main/myPage";
    }

    @GetMapping(value = "main/myPage2")
    public String myPage2(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "마이페이지 시작!");

        String user_id = (String) session.getAttribute("user_id");
        int user_seq = (int) session.getAttribute("user_seq");
        log.info("마이페이지 유저 시퀀스번호 : " + user_seq);

        int followCnt = 0;
        int followIng = 0;
        int boardCnt = 0;

        UserDTO cDTO = new UserDTO();
        cDTO.setBoard_user_seq(user_seq);
        cDTO.setUser_seq(user_seq);

        followCnt = userService.selectFollower(cDTO);
        log.info("팔로우 수는?" + followCnt);
        followIng = userService.selectFollowing(cDTO);
        boardCnt = boardService.selectBoardCnt(cDTO);
        log.info("게시글 수는?" + boardCnt);

        UserDTO pDTO;
        pDTO = userService.UserIdCheck(user_id);

        String file_detail_path = userService.selectUserFilePath(user_seq);
        log.info("가져온 유저 이미지 경로는 : " + file_detail_path);

        if(pDTO.getUser_introducation() == null){
            pDTO.setUser_introducation("소개글을 입력해주세요");
        }
        pDTO.setFollower(followCnt);
        pDTO.setFollowing(followIng);
        pDTO.setBoard_cnt(boardCnt);
        pDTO.setFile_detail_path(file_detail_path);

        session.setAttribute("user_file_path", pDTO.getFile_detail_path());
        session.setAttribute("user_introducation", pDTO.getUser_introducation());

        List<UserDTO> rList = mainService.selectMyPagePost(user_seq);

        model.addAttribute("rList", rList);
        model.addAttribute("pDTO", pDTO);

        log.info(this.getClass().getName() + "마이페이지 종료!");
        return "/main/myPage2";
    }

    @GetMapping(value = "/main/bookMark")
    public String bookMark(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + "북마크 페이지 시작!");

        int user_seq = (int) session.getAttribute("user_seq") ;

        List<BookMarkDTO> rList = null;

        rList = mainService.selectBookMark(user_seq);
        log.info("조회한 북마크 결과는? + " + (rList != null));

        model.addAttribute("rList", rList);

        log.info(this.getClass().getName() + "북마크 페이지 종료!");

        return "/main/bookMark";
    }

    @PostMapping(value = "/main/bookMarkProc")
    @ResponseBody
    public int bookMarkProc(HttpServletRequest request, @RequestParam Map<String, Integer> map) throws Exception {

        log.info(this.getClass().getName() + "북마크 Insert 컨트롤러 시작!");

        int res = 0;

        int login_user_seq = Integer.parseInt(String.valueOf(map.get("user_seq")));
        int board_user_seq = Integer.parseInt(String.valueOf(map.get("board_user_seq")));
        int board_seq = Integer.parseInt(String.valueOf(map.get("board_seq")));

        BookMarkDTO pDTO = new BookMarkDTO();
        pDTO.setUser_seq(login_user_seq);
        pDTO.setBoard_user_seq(board_user_seq);
        pDTO.setBoard_seq(board_seq);

        res = mainService.insertBookMark(pDTO);
        log.info("북마크 insert 결과는 ? : " + res);

        log.info(this.getClass().getName() + "북마크 Insert 컨트롤러 종료!");
        return res;
    }

    @PostMapping(value = "/main/bookDeleteProc")
    @ResponseBody
    public int bookDeleteProc(@RequestParam Map<String, Integer> map)throws Exception{
        int res = 0;

        int bookMark_seq = Integer.parseInt(String.valueOf(map.get("bookmark_seq")));
        Board_PostDTO pDTO = new Board_PostDTO();
        pDTO.setBookmark_seq(bookMark_seq);

        res = boardService.deletebookMark(pDTO);

        return res;
    }

    @GetMapping(value = "main/travelChoice")
    public String travelChoice() {
        log.info(this.getClass().getName() + "여행 선택페이지 시작!");
        return "/main/travelChoice";
    }

    @GetMapping(value = "main/userInfoChg")
    public String userInfoChg(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "회원정보 수정 페이지 시작!");

        String user_id = (String) session.getAttribute("user_id");
        int user_seq = (int) session.getAttribute("user_seq");

        UserDTO pDTO;
        pDTO = userService.UserIdCheck(user_id);


        List<UserDTO> rList = userService.selectUserInterest(user_seq);

        String user_file_path = mainService.selectUserImg(user_seq);
        session.setAttribute("user_file_path", user_file_path);

        model.addAttribute("user_file_path", user_file_path);
        model.addAttribute("pDTO", pDTO);
        model.addAttribute("rList", rList);

        log.info(this.getClass().getName() + "회원정보 수정 페이지 종료!");

        return "/main/userInfoChg";
    }

    @PostMapping(value = "main/userInfoChgProc")
    public String userInfoChgProc(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "회원정보 수정 컨트롤러 로직 시작!");
        int res = 0;
        String msg = "";
        String url = "";

        String user_id = (String) session.getAttribute("user_id");
        String user_file_seq = String.valueOf(request.getParameter("user_file_seq"));
        log.info("회원정보 수정에서 넘어온 " + user_file_seq);
        String user_nickname = CmmUtil.nvl(request.getParameter("nick_name"));
        String user_age = CmmUtil.nvl(request.getParameter("user_age"));
        String user_introducation = CmmUtil.nvl(request.getParameter("my_info"));

        log.info("user_id : " + user_id);
        log.info("user_nickname : " + user_nickname);
        log.info("user_age : " + user_age);
        log.info("user_introducatrion : " + user_introducation);


        UserDTO pDTO = new UserDTO();
        pDTO.setUser_id(user_id);
        pDTO.setUser_nickname(user_nickname);
        pDTO.setUser_age(user_age);
        pDTO.setUser_introducation(user_introducation);

        log.info("파일 번호는 ? " + user_file_seq);

        if (user_file_seq == "") {
            res = mainService.updateUser(pDTO);
        }

        if (user_file_seq != "") {
            pDTO.setFile_seq(Integer.parseInt(user_file_seq));
            res = mainService.updateUserInfo(pDTO);
        }

        if (res == 1) {
            msg = "회원정보 수정이 완료되었습니다!";
            url = "/main/myPage2";
        } else {
            msg = "수정중 오류가 발생했습니다 다시한번 확인해주세요";
            url = "/main/userInfoChg";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        log.info(this.getClass().getName() + "회원정보 수정 컨트롤러 로직 종료!");

        return "redirect";
    }

    @GetMapping(value = "main/Follow")
    public String Follow(HttpSession session, ModelMap model) throws Exception{
        log.info(this.getClass().getName()+"팔로우 팔로잉 확인 컨트롤러 시작!");

        String user_id = (String) session.getAttribute("user_id");
        int user_seq = (int) session.getAttribute("user_seq");
        log.info("마이페이지 유저 시퀀스번호 : " + user_seq);

        int followCnt = 0;
        int followIng = 0;
        int boardCnt = 0;

        UserDTO cDTO = new UserDTO();
        cDTO.setBoard_user_seq(user_seq);
        cDTO.setUser_seq(user_seq);

        followCnt = userService.selectFollower(cDTO);
        log.info("팔로우 수는?" + followCnt);
        followIng = userService.selectFollowing(cDTO);
        boardCnt = boardService.selectBoardCnt(cDTO);
        log.info("게시글 수는?" + boardCnt);

        UserDTO pDTO;
        pDTO = userService.UserIdCheck(user_id);

        String file_detail_path = userService.selectUserFilePath(user_seq);
        log.info("가져온 유저 이미지 경로는 : " + file_detail_path);

        if(pDTO.getUser_introducation() == null){
            pDTO.setUser_introducation("소개글을 입력해주세요");
        }
        pDTO.setFollower(followCnt);
        pDTO.setFollowing(followIng);
        pDTO.setBoard_cnt(boardCnt);
        pDTO.setFile_detail_path(file_detail_path);

        //팔로잉 유저 조회
        List<FollowDTO> rList = mainService.selectFollowee(user_seq);
        log.info("팔로잉 유저 조회 결과는 : "+(rList != null));
        log.info("팔로잉 유저 정보 는 몇개? : "+ rList.size());
        
        //팔로워 유저 조회
        List<FollowDTO> pList = mainService.selectFollower(user_seq);
        log.info("팔로워 유저 조회 결과는 : "+(pList != null));
        log.info("팔로워 유저 정보 는 몇개? : "+ pList.size());

        session.setAttribute("user_file_path", pDTO.getFile_detail_path());
        session.setAttribute("user_introducation", pDTO.getUser_introducation());

        model.addAttribute("pDTO", pDTO);
        model.addAttribute("rList", rList);
        model.addAttribute("pList", pList);

        log.info(this.getClass().getName()+"팔로우 팔로잉 확인 컨트롤러 시작!");
        return "/main/Follow";
    }

    @GetMapping(value = "main/FollowPost")
    public String followPost(HttpSession session, ModelMap model)throws Exception{
        log.info(this.getClass().getName()+"팔로잉 유저 게시물 조회 컨트롤러 시작!");
        int user_seq = (int) session.getAttribute("user_seq");
        String user_id = (String) session.getAttribute("user_id");

        int followCnt = 0;
        int followIng = 0;
        int boardCnt = 0;

        UserDTO cDTO = new UserDTO();
        cDTO.setBoard_user_seq(user_seq);
        cDTO.setUser_seq(user_seq);

        followCnt = userService.selectFollower(cDTO);
        log.info("팔로우 수는?" + followCnt);
        followIng = userService.selectFollowing(cDTO);
        boardCnt = boardService.selectBoardCnt(cDTO);
        log.info("게시글 수는?" + boardCnt);

        UserDTO pDTO;
        pDTO = userService.UserIdCheck(user_id);

        String file_detail_path = userService.selectUserFilePath(user_seq);
        log.info("가져온 유저 이미지 경로는 : " + file_detail_path);

        if(pDTO.getUser_introducation() == null){
            pDTO.setUser_introducation("소개글을 입력해주세요");
        }
        pDTO.setFollower(followCnt);
        pDTO.setFollowing(followIng);
        pDTO.setBoard_cnt(boardCnt);
        pDTO.setFile_detail_path(file_detail_path);

        List<Board_PostDTO> rList = mainService.selectFollowPost(user_seq);

        model.addAttribute("pDTO", pDTO);
        model.addAttribute("rList",rList);
        log.info(this.getClass().getName()+"팔로잉 유저 게시물 조회 컨트롤러 종료!");

        return "/main/FollowPost";
    }
    @GetMapping(value = "main/chatList")
    public String chatList(ModelMap model)throws Exception{
        log.info(this.getClass().getName()+"관리자 채팅 리스트 시작!");

        List<UserDTO> rList = mainService.selectUserRoomList();

        log.info(this.getClass().getName()+"관리자 채팅 리스트 종료!");
        model.addAttribute("rList", rList);
        return "/main/chatList";
    }



}
