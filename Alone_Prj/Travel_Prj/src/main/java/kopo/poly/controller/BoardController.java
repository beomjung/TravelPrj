package kopo.poly.controller;

import com.amazonaws.services.dynamodbv2.xspec.B;
import kopo.poly.dto.*;
import kopo.poly.service.IBoardService;
import kopo.poly.service.IFileUploadService;
import kopo.poly.service.IS3UploadService;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.*;

@Slf4j
@Controller
public class BoardController {

    @Resource(name = "BoardService")
    private IBoardService boardService;

    @Resource(name = "UserService")
    private IUserService userService;

    @Resource(name = "S3UploadService")
    private IS3UploadService s3UploadService;

    @Resource(name = "FileUploadService")
    private IFileUploadService fileUploadService;


    @GetMapping(value = "board/boardWrite")
    public String boardWrite(HttpServletRequest request) {
        log.info(this.getClass().getName() + "게시글 작성 게시판 컨트롤러 시작!");
        log.info(this.getClass().getName() + "게시글 작성 게시판 컨트롤러 종료!");

        return "/board/boardWrite";
    }

    @PostMapping(value = "/board/boardWriteProc")
    @ResponseBody
    public Map<String, String> boardWriteProc(HttpServletRequest request, HttpSession session, ModelMap model, MultipartHttpServletRequest mft) throws Exception {

        log.info(this.getClass().getName() + "게시글 작성 컨트롤러 로직 시작!");

        String msg = "";
        String url = "";

        int res1 = 0;
        int res2 = 0; // 파일 상세정보 저장결과
        int res3 = 0;
        int res4 = 0; // 게시판 상세정보 저장 과
        int i = 0;

        FileInfoDTO pDTO = null;


        String user_id = (String) session.getAttribute("user_id");
        String file_code = user_id + "_board";
        String areacode = request.getParameter("location");
        String deatil_areacode = request.getParameter("smallcity");
        int user_seq = (int) session.getAttribute("user_seq");

        //        String user_id = (String) session.getAttribute("user_id");
        String start_day = CmmUtil.nvl(request.getParameter("start_day"));
        String end_day = CmmUtil.nvl(request.getParameter("end_day"));
        //-------------------------------똑같은 name을 가진 input type이 여러개------------------------------------//
        String[] title = request.getParameterValues("title");
        String[] star = request.getParameterValues("star");
        String[] board_content = request.getParameterValues("board_content");
        String[] hashTag = request.getParameterValues("hashTag");
        String[] addr = request.getParameterValues("addr");
        //----------------------------------------------------------------------------------------------------//

        //1개의 게시판에 여러개의 게시물을 담기위해 게시판을 2개로 나눔
        //----------------------게시판 정보 table로 데이터 전송--------------------//
        Travel_BoardDTO bDTO = new Travel_BoardDTO();
        bDTO.setUser_id(user_id);
        bDTO.setUser_seq(user_seq);
        bDTO.setStart_day(start_day);
        bDTO.setEnd_day(end_day);

        res3 = boardService.insertBoardInfo(bDTO);
        //---------------------------------------------------------------------//


        //------다중파일 받기 List안에 MultipartFile 형태를 생성 후 forEach로 돌림------//
        List<MultipartFile> fileList = mft.getFiles("self_file");
        log.info("넘어온 파일 갯수 " + fileList.size());


        for (MultipartFile mf : fileList) {

            //원본 파일 이름
            String originalFileName = mf.getOriginalFilename();

            // 파일 확장자 가져오기
            String ext = originalFileName
                    .substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

            // 저장되는 파일이름 (영어, 숫자로 파일명 변경)
            String saveFileName = DateUtil.getDateTime("24hhmmss") + "." + ext;

            log.info("원본파일 이름! : " + originalFileName);
            log.info("저장 파일 이름 !" + saveFileName);
            //저장경로
            String saveFilePath = s3UploadService.fileUpload(mf, saveFileName);
            //풀 경로
            String fullFileInfo = saveFilePath + "/" + saveFileName;

            log.info("ext : " + ext);
            log.info("saveFileName : " + saveFileName);
            log.info("saveFilePath : " + saveFilePath);
            log.info("fullFileInfo : " + fullFileInfo);

            pDTO = new FileInfoDTO();
            pDTO.setUser_id(user_id);
            file_code = user_id + "_board";
            pDTO.setUser_seq(user_seq);
            pDTO.setFile_code(file_code);
            log.info("파일 코드 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + pDTO.getFile_code());
            pDTO.setFile_chg_id(user_id);
            pDTO.setFile_reg_dt(user_id);

            res1 = fileUploadService.insertFileInfoDB(pDTO);

            FileDetailDTO fDTO = new FileDetailDTO();

            if (res1 >= 1) {
                /*----------------파일상세정보 저장---------------------*/
                fDTO.setFile_detail_name(saveFileName);
                fDTO.setFile_detail_path(saveFilePath);
                fDTO.setFile_detail_orgName(originalFileName);
                fDTO.setFile_detail_ext(ext);
                fDTO.setFile_chg_id(user_id);
                fDTO.setFile_reg_id(user_id);
                fDTO.setFile_seq(res1);
                /*----------------------------------------------------*/

                //파일 정보 저장


                res2 = fileUploadService.insertFileDetailDB(fDTO);
            }
            log.info("res2 : " + res2);

            if (res2 == 1) {
                //---------------위에서 받은 다중 name 변수를 DB에 저장하기 위해 String 으로 변환하고 List에 담음-----------------//
                // 게시판 작성 게시물 DB로 데이터 전송

                Board_PostDTO dDTO = null;


                dDTO = new Board_PostDTO();

                dDTO.setBoard_seq(res3);
                dDTO.setFile_seq(res1);
                dDTO.setUser_seq(user_seq);
                dDTO.setBoard_post_title(title[i]);
                dDTO.setBoard_post_content(board_content[i]);
                dDTO.setBoard_post_star(star[i]);
                dDTO.setBoard_post_postion(addr[i]);
                dDTO.setBoard_post_hashTag(hashTag[i]);
                dDTO.setAreacode(areacode);
                dDTO.setDetail_areacode(deatil_areacode);

                res4 = boardService.insertBoardDetailInfo(dDTO);
                log.info("게시글 상세정보 저장 결과는 : " + res4);
                //----------------------------------------------------------------------------------------------------//
                i++;

            }
        }
        log.info("res4 : " + res4);

        if (res4 == 1) {
            session.setAttribute("board_seq", res3);
            session.setAttribute("user_seq", user_seq);

            msg = "게시글 작성을 성공했습니다";
            url = "/main/myPage2";
        } else {
            msg = "게시글 작성을 실패했습니다 관리자에게 문의해주세요";
            url = "/board/boardWrite";
        }
        //redirect 페이지 전달을 위한 주소 및 메시지 전송
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "게시글 작성 컨트롤러 로직 종료!!");
        Map<String, String> rMap = new HashMap<>();

        rMap.put("msg", msg);
        rMap.put("url", url);
        rMap.put("moveUrl", "/moveRedirect");
        return rMap;
    }

    @GetMapping(value = "moveRedirect")
    public String moveRedirect(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        String url = request.getParameter("url");
        String msg = request.getParameter("msg");

        model.addAttribute("url", url);
        model.addAttribute("msg", msg);
        return "redirect";
    }

    @GetMapping(value = "board/boardList")
    public String boardList(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "게시판 리스트 컨트롤러 시작!");

        List<Board_PostDTO> pList = new ArrayList<>();
        List<UserDTO> rList = new ArrayList<>();

        pList = boardService.selectBoardList();
        rList = userService.userNickImgPath(pList);
        String user_name = (String) request.getAttribute("user_name");

        if (pList != null) {
            model.addAttribute("pList", pList);
        }
        if (rList != null) {
            model.addAttribute("rList", rList);
        }
        model.addAttribute("user_name", user_name);
        log.info(this.getClass().getName() + "게시판 리스트 컨트롤러 종료!");
        return "/board/boardList";
    }

    @GetMapping(value = "board/boardDetail")
    public String boardDetail(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "게시판 상세보기 컨트롤러 시작!");

        int board_seq = (int) session.getAttribute("board_seq");
        int user_seq = (int) session.getAttribute("user_seq");
        log.info("파라미터로 받은 유저 시퀀스 : " + user_seq);
        String user_id = (String) session.getAttribute("user_id");

        int followCnt = 0;
        int followIng = 0;
        int boardCnt = 0;
        int auth_follow = 0;

        int login_user_seq = (int) session.getAttribute("user_seq");
        log.info("로그인 유저 시퀀스 : " + login_user_seq);

        Board_PostDTO pDTO = new Board_PostDTO();

        pDTO.setBoard_seq(board_seq);

        List<Board_PostDTO> pList = boardService.selectBoardContent(pDTO);

        UserDTO uDTO = new UserDTO();

        uDTO.setUser_seq(user_seq);
        uDTO.setBoard_seq(board_seq);

        String file_detail_path = userService.selectUserFilePath(user_seq);
        UserDTO qDTO = userService.UserIdCheck(user_id);
        qDTO.setFile_detail_path(file_detail_path);

        log.info("가져온 pList 결과는 ? " + (pList != null));
        log.info("가져온 rDTO 결과는  ?" + (qDTO != null));

        log.info("닉네임 : " + qDTO.getUser_nickname());

        if (qDTO.getUser_introducation() == null) {
            String msg = "소개글이 없습니다";
            qDTO.setUser_introducation(msg);
        }

        UserDTO cDTO = new UserDTO();
        cDTO.setBoard_user_seq(pList.get(0).getUser_seq());
        cDTO.setUser_seq(login_user_seq);

        followCnt = userService.selectFollower(cDTO);
        followIng = userService.selectFollowing(cDTO);
        boardCnt = boardService.selectBoardCnt(cDTO);
        auth_follow = userService.authFollower(cDTO);

        log.info(this.getClass().getName() + "리스트에서 선택한 게시판 상세보기 컨트롤러 종료!");
        qDTO.setFollower(followCnt);
        qDTO.setFollowing(followIng);
        qDTO.setBoard_cnt(boardCnt);
        qDTO.setAuth_follow(auth_follow);

        model.addAttribute("pList", pList);
        model.addAttribute("qDTO", qDTO);
        model.addAttribute("login_user_seq", login_user_seq);

        return "/board/boardDetail";
    }

    @GetMapping(value = "board/boardDetailInfo")
    public String boardDetailInfo(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "리스트에서 선택한 게시판 상세보기 컨트롤러 시작!");

        int board_seq = Integer.parseInt(request.getParameter("board_seq"));
        int user_seq = Integer.parseInt(request.getParameter("user_seq"));
        String user_id = (String) session.getAttribute("user_id");
        log.info("파라미터로 받은 유저 시퀀스 : " + user_seq);

        int followCnt = 0;
        int followIng = 0;
        int boardCnt = 0;
        int auth_follow = 0;

        int login_user_seq = (int) session.getAttribute("user_seq");
        log.info("로그인 유저 시퀀스 : " + login_user_seq);

        Board_PostDTO pDTO = new Board_PostDTO();

        pDTO.setBoard_seq(board_seq);

        List<Board_PostDTO> pList = boardService.selectBoardContent(pDTO);

        UserDTO qDTO = userService.boarduserNickImgPath(user_seq);

        BookMarkDTO bDTO = new BookMarkDTO();
        bDTO.setBoard_seq(pList.get(0).getBoard_seq());
        bDTO.setUser_seq(login_user_seq);

        BookMarkDTO rDTO = boardService.selectBookMarkSeq(bDTO);

        log.info("가져온 pList 결과는 ? " + (pList != null));
        log.info("가져온 rDTO 결과는  ?" + (qDTO != null));

        log.info("닉네임 : " + qDTO.getUser_nickname());

        if (qDTO.getUser_introducation() == null) {
            String msg = "소개글이 없습니다";
            qDTO.setUser_introducation(msg);
        }

        UserDTO cDTO = new UserDTO();
        cDTO.setBoard_user_seq(pList.get(0).getUser_seq());
        cDTO.setUser_seq(login_user_seq);

        followCnt = userService.selectFollower(cDTO);
        followIng = userService.selectFollowing(cDTO);
        boardCnt = boardService.selectBoardCnt(cDTO);
        auth_follow = userService.authFollower(cDTO);

        log.info(this.getClass().getName() + "리스트에서 선택한 게시판 상세보기 컨트롤러 종료!");
        qDTO.setFollower(followCnt);
        qDTO.setFollowing(followIng);
        qDTO.setBoard_cnt(boardCnt);
        qDTO.setAuth_follow(auth_follow);

        model.addAttribute("pList", pList);
        model.addAttribute("qDTO", qDTO);
        model.addAttribute("rDTO", rDTO);
        model.addAttribute("login_user_seq", login_user_seq);
        return "/board/boardDetail";
    }

    @GetMapping(value = "board/boardChg")
    public String boardChg(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "게시글 수정 컨트롤러 시작!");
        int board_seq = Integer.parseInt(request.getParameter("board_seq"));
        log.info("게시판 수정 게시글 번호" + board_seq);
        int user_seq = Integer.parseInt(request.getParameter("user_seq"));
        log.info("게시판 수정 유저 시퀀스 : " + user_seq);
        Board_PostDTO pDTO = new Board_PostDTO();

        pDTO.setBoard_seq(board_seq);

        List<Board_PostDTO> pList = boardService.selectBoardContent(pDTO);

        model.addAttribute("pList", pList);

        log.info(this.getClass().getName() + "게시글 수정 컨트롤러 종료!");
        return "/board/boardChg";
    }

    @PostMapping(value = "followProc")
    @ResponseBody
    public int followProc(HttpServletRequest request, @RequestParam Map<String, Integer> map) throws Exception {
        log.info(this.getClass().getName() + "팔로우 컨트롤러 시작!");

        int res = 0;

        int login_user_seq = Integer.parseInt(String.valueOf(map.get("login_user_seq")));
        log.info("팔로잉 값 잘 들어오나요? : " + login_user_seq);

        int board_user_seq = Integer.parseInt(String.valueOf(map.get("board_user_seq")));
        log.info("팔로잉 값 잘 들어오나요? : " + board_user_seq);

        UserDTO pDTO = new UserDTO();
        pDTO.setUser_seq(login_user_seq);
        pDTO.setBoard_user_seq(board_user_seq);

        res = userService.insertFollow(pDTO);

        String res1 = String.valueOf(login_user_seq);

        log.info(this.getClass().getName() + "팔로우 컨트롤러 종료!");

        return res;

    }

    @PostMapping(value = "followDeleteProc")
    @ResponseBody
    public int followDeleteProc(HttpServletRequest request, @RequestParam Map<String, Integer> map) throws Exception {
        log.info(this.getClass().getName() + "팔로우 취소 컨트롤러 시작!");

        int res = 0;

        int login_user_seq = Integer.parseInt(String.valueOf(map.get("login_user_seq")));
        log.info("팔로잉 값 잘 들어오나요? : " + login_user_seq);

        int board_user_seq = Integer.parseInt(String.valueOf(map.get("board_user_seq")));
        log.info("팔로잉 값 잘 들어오나요? : " + board_user_seq);

        UserDTO pDTO = new UserDTO();
        pDTO.setUser_seq(login_user_seq);
        pDTO.setBoard_user_seq(board_user_seq);

        res = userService.deleteFollow(pDTO);

        log.info(this.getClass().getName() + "팔로우 취소 컨트롤러 종료!");

        return res;

    }

    @PostMapping(value = "/board/boardDelete")
    public String boardDelete(HttpServletRequest request, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "게시글 삭제 컨트롤러 시작!");

        int res = 0;
        int res2 = 0;
        String msg = "";
        String url = "";

        int board_seq = Integer.parseInt(request.getParameter("board_seq"));
        log.info("삭제할려고 가져온 결과는 ?" + board_seq);
        int file_seq = Integer.parseInt(request.getParameter("file_seq"));

        Board_PostDTO pDTO = new Board_PostDTO();
        pDTO.setFile_seq(file_seq);
        pDTO.setBoard_seq(board_seq);

        res = boardService.deleteBoard(pDTO);

        if (res == 1) {
            msg = "게시글을 삭제 했습니다";
            url = "/main/myPage2";
        } else {
            msg = "게시글 삭제에 실패했습니다 관리자에게 문의하세요";
            url = "/main/myPage2";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "게시글 삭제 컨트롤러 종료!");

        return "redirect";
    }

    @PostMapping(value = "/board/boardChgProc")
    @ResponseBody
    public Map<String, String> boardChgProc(HttpServletRequest request, HttpSession session, ModelMap model, MultipartHttpServletRequest mft) throws Exception {
        log.info(this.getClass().getName() + "게시판 수정 컨트롤러 시작!!!");

        String msg = "";
        String url = "";

        int res = 0; // 게시판 부모 테이블 수정
        int res1 = 0;
        int res2 = 0;
        int res3 = 0;
        int res4 = 0;
        int i = 0;

        String user_id = (String) session.getAttribute("user_id");
        String file_code = user_id + "_board";

        int user_seq = (int) session.getAttribute("user_seq");
        int board_seq = Integer.parseInt(request.getParameter("board_seq"));
        log.info("게시판 번호는 ? " + board_seq);

        String start_day = CmmUtil.nvl(request.getParameter("start_day"));
        String end_day = CmmUtil.nvl(request.getParameter("end_day"));

        //-------------------------------똑같은 name을 가진 input type이 여러개------------------------------------//
        String[] title = request.getParameterValues("title");
        String[] star = request.getParameterValues("star");
        String[] board_content = request.getParameterValues("board_content");
        String[] hashTag = request.getParameterValues("hashTag");
        String[] addr = request.getParameterValues("addr");
        String[] board_post_seq = request.getParameterValues("board_post_seq");
        String[] auth_board = request.getParameterValues("auth_board");
        //----------------------------------------------------------------------------------------------------//
        log.info("auth_board : " + auth_board[0]);

        String res_auth = auth_board[0];

        String[] auth_res = res_auth.split(",");

        log.info("제발 제대로 나와라 좀 : " + auth_res[0]);
        //1개의 게시판에 여러개의 게시물을 담기위해 게시판을 2개로 나눔
        //----------------------게시판 정보 table로 데이터 전송--------------------//
        Travel_BoardDTO bDTO = new Travel_BoardDTO();
        bDTO.setUser_id(user_id);
        bDTO.setBoard_seq(board_seq);
        bDTO.setUser_seq(user_seq);
        bDTO.setStart_day(start_day);
        bDTO.setEnd_day(end_day);

        res = boardService.updateTravelBoard(bDTO);
        log.info("게시판 부모 테이블 수정 결과는 : " + res);
        //---------------------------------------------------------------------//


        //---------------위에서 받은 다중 name 변수를 DB에 저장하기 위해 String 으로 변환하고 List에 담음-----------------//
        // 게시판 작성 게시물 DB로 데이터 전송
        Board_PostDTO dDTO = null;
        log.info("넘어온 제목 수는 : " + title.length);
        for (int j = 0; j < title.length; j++) {
            dDTO = new Board_PostDTO();

            dDTO.setBoard_seq(board_seq);
            dDTO.setUser_seq(user_seq);
            dDTO.setBoard_post_title(title[j]);
            dDTO.setBoard_post_content(board_content[j]);
            dDTO.setBoard_post_star(star[j]);
            dDTO.setBoard_post_postion(addr[j]);
            dDTO.setBoard_post_hashTag(hashTag[j]);
            dDTO.setBoard_post_seq(Integer.parseInt(board_post_seq[j]));
            res3 = boardService.updateBoardPost(dDTO);
        }
        //------------------자식 게시판 업데이트--------------------------------//


        FileInfoDTO pDTO = null; //파일 업로드용 DTO 선언
        List<MultipartFile> fileList = null;
        if (res3 == 1) {
            //------다중파일 받기 List안에 MultipartFile 형태를 생성 후 forEach로 돌림------//
            fileList = mft.getFiles("self_file");
            log.info("넘어온 파일 갯수 " + fileList.size());
            if (fileList.size() > 0) {
                for (MultipartFile mf : fileList) {

                    //원본 파일 이름
                    String originalFileName = mf.getOriginalFilename();

                    // 파일 확장자 가져오기
                    String ext = originalFileName
                            .substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

                    // 저장되는 파일이름 (영어, 숫자로 파일명 변경)
                    String saveFileName = DateUtil.getDateTime("24hhmmss") + "." + ext;

                    log.info("원본파일 이름! : " + originalFileName);
                    log.info("저장 파일 이름 !" + saveFileName);
                    //저장경로
                    String saveFilePath = s3UploadService.fileUpload(mf, saveFileName);
                    //풀 경로
                    String fullFileInfo = saveFilePath + "/" + saveFileName;

                    log.info("ext : " + ext);
                    log.info("saveFileName : " + saveFileName);
                    log.info("saveFilePath : " + saveFilePath);
                    log.info("fullFileInfo : " + fullFileInfo);

                    pDTO = new FileInfoDTO();
                    pDTO.setUser_id(user_id);
                    file_code = user_id + "_board";
                    pDTO.setUser_seq(user_seq);
                    pDTO.setFile_code(file_code);
                    log.info("파일 코드 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + pDTO.getFile_code());
                    pDTO.setFile_chg_id(user_id);
                    pDTO.setFile_reg_dt(user_id);

                    res1 = fileUploadService.insertFileInfoDB(pDTO);

                    FileDetailDTO fDTO = new FileDetailDTO();

                    if (res1 >= 1) {
                        /*----------------파일상세정보 저장---------------------*/
                        fDTO.setFile_detail_name(saveFileName);
                        fDTO.setFile_detail_path(saveFilePath);
                        fDTO.setFile_detail_orgName(originalFileName);
                        fDTO.setFile_detail_ext(ext);
                        fDTO.setFile_chg_id(user_id);
                        fDTO.setFile_reg_id(user_id);
                        fDTO.setFile_seq(res1);
                        /*----------------------------------------------------*/

                        //파일 정보 저장

                        res2 = fileUploadService.insertFileDetailDB(fDTO);
                    }
                    log.info("res2 : " + res2);
                    if (res2 == 1) {
                        Board_PostDTO nDTO = new Board_PostDTO();
                        nDTO.setBoard_seq(board_seq);
                        nDTO.setBoard_post_seq(Integer.parseInt(auth_res[i]));
                        nDTO.setFile_seq(res1);

                        res4 = boardService.updateBoardFile(nDTO);
                        //----------------------------------------------------------------------------------------------------//
                        i++;
                    }
                }
                if (res4 == 1) {
                    msg = "게시글 수정을 성공했습니다";
                    url = "/main/myPage2";
                } else {
                    msg = "게시글 수정을 실패했습니다 관리자에게 문의해주세요";
                    url = "/main/home";
                }
            } else {
                if (res3 == 1) {
                    msg = "게시글 수정을 성공했습니다";
                    url = "/main/myPage2";
                } else {
                    msg = "게시글 수정을 실패했습니다 관리자에게 문의해주세요";
                    url = "/main/home";
                }
            }

        }

        Map<String, String> rMap = new HashMap<>();
        rMap.put("msg", msg);
        rMap.put("url", url);
        rMap.put("moveUrl", "/moveRedirect");
        log.info(this.getClass().getName() + "게시판 수정 컨트롤러 종료!!!");
        return rMap;
    }

}
