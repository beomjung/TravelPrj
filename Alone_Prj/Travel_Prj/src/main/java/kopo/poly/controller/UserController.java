package kopo.poly.controller;

import kopo.poly.dto.MailDTO;
import kopo.poly.dto.UserDTO;
import kopo.poly.service.IMailService;
import kopo.poly.service.IMainService;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Slf4j
@Controller
public class UserController {
    /*
     * Resource는 name 값을 기준으로 class를 찾아 class안에 있는 함수를 사용할 수 있음
     * */
    @Resource(name = "UserService")
    private IUserService userService;

    @Resource(name = "MailService")
    private IMailService mailService;

    @Resource(name = "MainService")
    private IMainService mainService;


    @GetMapping(value = "index") //인덱스 화면
    public String index() {
        log.info(this.getClass().getName() + "./index start");

        return "index";
    }

    @GetMapping(value = "user/login")
    public String userLogin(HttpSession session) { // 로그인 화면 
        log.info(this.getClass().getName() + "로그인 페이지 시작!");

        //로그인 페이지에서는 세션을 모두 정리한다
        session.invalidate();

        log.info(this.getClass().getName() + "로그인 페이지 종료!");
        return "user/login";
    }

    @PostMapping(value = "user/loginProc") // 로그인 로직 시작!
    public String UserLoginProc(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "로그인 처리 로직 시작!");

        String msg = "";
        String url = "";

        String user_id = CmmUtil.nvl(request.getParameter("user_id"));
        String user_pwd = CmmUtil.nvl(request.getParameter("user_pwd"));
        log.info("user_id : " + user_id);
        log.info("user_pwd : " + user_pwd);

        if(user_id.equals("admin")){
            msg="관리자 로그인";
            url="/main/chatList";
            session.setAttribute("user_id", user_id);
            model.addAttribute("msg", msg);
            model.addAttribute("url",url);
            return "redirect";
        }

        UserDTO pDTO = new UserDTO();

        pDTO.setUser_id(user_id);
        pDTO.setUser_pwd(EncryptUtil.encHashSHA256(user_pwd));

        // mapper return type UserDTO임
        UserDTO rDTO = userService.UserLogin(pDTO);



        // mapper 조회 결과 rDTO 값이 null이면 제대로 조회되지 않음
        if (rDTO == null) {
            log.info("rDTO : " + (rDTO == null));
            msg = "아이디/비밀번호를 다시 입력해 주세요";
            url = "/user/login";
        } else {
            // return이 null이 아니면 세션에 사용자 정보를 담음(계속 로그인 유지를 위해 세션에 사용자 정보 저장)
            session.setAttribute("user_id", rDTO.getUser_id());
            session.setAttribute("user_seq", rDTO.getUser_seq());
            session.setAttribute("user_name", rDTO.getUser_name());
            session.setAttribute("user_nickName", rDTO.getUser_nickname());
            log.info("로그인시 세션에 담은 유저이름은 : " + session.getAttribute("user_id"));
            msg = "환영합니다 회원님!";
            url = "/main/home";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "로그인 처리 로직 종료!");


        return "redirect";
    }

    @GetMapping(value = "user/userReg") //회원가입 페이지
    public String userReg() throws Exception {
        log.info(this.getClass().getName() + "회원 가입 페이지 시작!");
        log.info(this.getClass().getName() + "회원 가입 페이지 종료!");

        return "user/userReg";
    }


    @PostMapping(value = "user/User_RegProc") //회원가입 로직
    public String InsertUserInfo(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + "회원가입 로직 컨트롤러 시작!");
        /*
         * DB Insert 성공시 결과 값이 0,1로 나옴 res를 초기화 시키고
         * insert 결과를 담음
         * */
        int res = 0;

        String msg = "";
        String url = "";

        //view에서 전달받은 회원 정보
        String user_id = CmmUtil.nvl(request.getParameter("user_id"));
        String user_pwd = CmmUtil.nvl(request.getParameter("user_pwd"));
        String user_email = CmmUtil.nvl(request.getParameter("user_email"));
        String user_name = CmmUtil.nvl(request.getParameter("user_name"));
        String user_nickname = CmmUtil.nvl(request.getParameter("user_nickname"));
        String user_addr = CmmUtil.nvl(request.getParameter("user_addr"));
        String user_age = CmmUtil.nvl(request.getParameter("user_age"));
        String user_gender = CmmUtil.nvl(request.getParameter("user_gender"));
        log.info("user_id : " + user_id);
        log.info("user_pwd : " + user_pwd);
        log.info("user_email : " + user_email);
        log.info("user_name : " + user_name);
        log.info("user_nickname : " + user_nickname);
        log.info("user_age : " + user_age);
        log.info("user_gender : " + user_gender);

        //회원정보를 담을 DTO 변수 선언
        UserDTO pDTO = new UserDTO();

        //회원 정보를 담기
        pDTO.setUser_id(user_id);
        pDTO.setUser_pwd(EncryptUtil.encHashSHA256(user_pwd));
        pDTO.setUser_email(EncryptUtil.encAES128CBC(user_email));
        pDTO.setUser_name(user_name);
        pDTO.setUser_nickname(user_nickname);
        pDTO.setUser_addr(user_addr);
        pDTO.setUser_age(user_age);
        pDTO.setUser_gender(user_gender);
        pDTO.setMember_since(DateUtil.getDateTime("yyyyMMdd"));
        pDTO.setUser_file_seq(1);
        pDTO.setUser_introducation("소개글을 입력해 주세요");

        //싱글톤 패턴을 이욯해 서비스로 회원정보 전달 하고 결과 값을 받음
        res = userService.InsertUserInfo(pDTO);


        //DB에 Insert 되었으면 1, 오류 났으면 0
        if (res >= 1) {
            session.setAttribute("user_id", user_id);
            session.setAttribute("user_nickname", user_nickname);
            session.setAttribute("user_name", user_name);
            session.setAttribute("user_seq", res);
            log.info("회원번호 세션에 올라간 값" + session.getAttribute("user_seq"));
            msg = "회원가입을 환영합니다!";
            url = "/user/interest";
        } else {
            msg = "회원가입에 실패하였습니다 다시 시도해 주세요";
            url = "/user/userReg";
        }


        //redirect 페이지 전달을 위한 주소 및 메시지 전송
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "회원가입 컨트롤러 종료!");

        return "redirect";
    }

    @ResponseBody //JSON type의 결과를 만들어주는 어노테이션
    @PostMapping(value = "user/IdCheckProc") // 회원 ID 중복 체크 여부 확인 로직
    public int UserIdCheck(HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + "회원 아이디 중복 체크 시작!");

        int res = 0;

        String user_id = request.getParameter("user_id");
        log.info("user_id : " + user_id);

        UserDTO rDTO = userService.UserIdCheck(user_id);

        if (rDTO != null) {
            res = 1;
        }
        log.info("조회 결과 res : " + res);

        log.info(this.getClass().getName() + "회원 아이디 중복 체크 종료!");

        return res;
    }


    @GetMapping(value = "user/interest")
    public String userInterest() {
        log.info(this.getClass().getName() + "유저 관심정보 등록 페이지 시작!");

        log.info(this.getClass().getName() + "유저 관심정보 등록 페이지 종료!");
        return "/user/interest";
    }

    @PostMapping(value = "user/interestproc")
    public String userInterestProc(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "유저 관싱정보 등록 시작!");

        int res = 0;

        String msg = "";
        String url = "";

        int user_seq = (int) session.getAttribute("user_seq");
        log.info("관심정보입력 유저 seq : " + user_seq);

        String[] user_interest = request.getParameterValues("interest");
        log.info("관심정보 잘 들어왔나? : " + user_interest.length);

        UserDTO pDTO = new UserDTO();

        for (int i = 0; i < user_interest.length; i++) {
            pDTO.setUser_seq(user_seq);
            pDTO.setUser_interest_content(user_interest[i]);
            res = userService.insertUserInterest(pDTO);
        }

        if (res == 1) {
            msg = "관심정보 등록에 성공했습니다!";
            url = "/main/home";
        } else {
            msg = "관심정보 등록실패!";
            url = "/user/interest";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "유저 관싱정보 등록 종료!");

        return "redirect";
    }

    @GetMapping(value = "user/logOutProc") // user 로그아웃 페이지
    public String userLogOut(HttpSession session, ModelMap model) {
        log.info(this.getClass().getName() + "유저 로그아웃 컨트롤러 시작!");
        session.invalidate();

        String msg = "로그아웃 되었습니다 :)";
        String url = "/user/login";

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "유저 로그아웃 컨트롤러 종료!");

        return "redirect";
    }

    @GetMapping(value = "user/findID")
        // 아이디 찾기 페이지
    String findID() {
        log.info(this.getClass().getName() + "유저 아이디찾기 페이지 시작!");

        log.info(this.getClass().getName() + "유저 아이디찾기 페이지 종료!");

        return "/user/findID";
    }

    @PostMapping(value = "user/UserFindIdProc")
        //아이디 찾기 로직
    String UserFindIdProc(HttpServletRequest request, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "유저 아이디찾기 로직 시작!");
        String msg = "";
        String url = "";

        String user_name = CmmUtil.nvl(request.getParameter("user_name"));
        String user_email = CmmUtil.nvl(request.getParameter("user_email"));

        log.info("user_name : " + user_name);
        log.info("user_email : " + user_email);

        UserDTO pDTO = new UserDTO();

        pDTO.setUser_name(user_name);
        pDTO.setUser_email(EncryptUtil.encAES128CBC(user_email));

        UserDTO rDTO = userService.findUserId(pDTO);

        /*
         * rDTO가 널이 아니면 rDTO에서 id값을 꺼내 사용자에게 보여주기
         * */
        if (rDTO!= null) {
            String user_id = rDTO.getUser_id();

            msg = "회원님의 아이디는" + user_id;
            url = "/user/findID";
        } else {
            msg = "잘못 입력하셨습니다. 아이디와 비밀번호를 확인해 주세요";
            url = "/user/findID";
        }
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);


        log.info(this.getClass().getName() + "유저 아이디찾기 로직 종료!");

        return "redirect";
    }

    @GetMapping(value = "user/findPwd")
        // 비밀번호 찾기 페이지
    String pwdSerach() throws Exception {
        log.info(this.getClass().getName() + "유저 비밀번호 찾기 페이지 시작!");
        log.info(this.getClass().getName() + "유저 비밀번호 찾기 페이지 종료!");
        return "/user/findPwd";
    }


    @GetMapping(value = "user/passwordChg")
    public String passwordChg() throws Exception {
        log.info(this.getClass().getName() + "유저 비밀번호 변경 페이지 시작!");
        log.info(this.getClass().getName() + "유저 비밀번호 변경 페이지 종료!");
        return "/user/passwordChg";
    }

    //비밀번호 찾기 로직!
    @PostMapping(value = "user/passwordChgProc")
    public String PasswordChgProc(HttpSession session, HttpServletRequest request, ModelMap model)throws Exception{
        log.info(this.getClass().getName()+"비밀번호 변경 로직 시작!");

        String msg = "";
        String url = "";

        String user_id = (String) session.getAttribute("user_id");
        String user_pwd = CmmUtil.nvl(request.getParameter("password2"));
        log.info("비밀번호 변경 : " + user_pwd);
        UserDTO pDTO = new UserDTO();

        pDTO.setUser_id(user_id);
        pDTO.setUser_pwd(EncryptUtil.encHashSHA256(user_pwd));

        /*
         * rDTO가 아닌 int로 받은 이유는 비밀번호는 Hash암호화 되어있어 조회가 불가능
         * update문을 통해 새로운 비밀번호로 변경
         * update결과는 0,1
         * */
        int res = userService.findUserPwd(pDTO);

        if (res == 1) {
            msg = "비밀번호 변경을 완료했습니다 다시 로그인해 주세요";
            url = "/user/login";
        } else {
            msg = "비밀번호 변경에 실패하였습니다 다시 시도해 주세요";
            url = "/user/login";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);


        log.info(this.getClass().getName()+"비밀번호 변경 로직 종료!");

        return "redirect";
    }

    //이메일 전송을 위한 로직(회원가입)
    @ResponseBody
    @PostMapping(value = "user/emailSendProc")
    public HashMap<String, Integer> emailSendProc(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + "이메일 인증 로직 시작!");
        int res = 0;
        int res3 = 0;
        String user_email = CmmUtil.nvl(request.getParameter("user_email"));
        log.info("user_email : " + user_email);

        UserDTO pDTO = userService.userEmailCheck(EncryptUtil.encAES128CBC(user_email));

        //인증번호를 위한 난수 발생
        Random random = new Random();
        int Random_Pin = random.nextInt(888888) + 111111;

        log.info("Random_Pin : " + Random_Pin);

        // 메일 전송을 위한 로직
        MailDTO mDTO = new MailDTO();
        mDTO.setToMail(user_email);
        mDTO.setTitle("Travel 인증번호 발송");
        mDTO.setContents("인증번호 : " + Random_Pin);

        HashMap<String, Integer> rMap = new HashMap<>();
        //메일 발송 결과 확인
        if (pDTO != null) {
            rMap.put("emailCheck", 0);

        } else {
            res3 = mailService.doSendmail(mDTO);

            log.info("메일 발송 결과는? : " + res3);
            rMap.put("emailCheck", 1);
            rMap.put("Random_Pin", Random_Pin);
            rMap.put("result", res3);
        }
        log.info(this.getClass().getName() + "이메일 인증 종료 !!");
        return rMap;
    }

    @ResponseBody
    @PostMapping(value = "user/passemailSendProc")
    public HashMap<String, Integer> passemailSendProc(HttpServletRequest request, @RequestParam Map<String, String> map) throws Exception {

        log.info(this.getClass().getName() + "이메일 인증 로직 시작!");
        int res3 = 0;
        int Random_Pin = 0;

        MailDTO mDTO = new MailDTO();


        String user_email = map.get("user_email");
        String user_id = map.get("user_id");

        log.info("user_id : " + user_id);
        log.info("user_email : " + user_email);

        UserDTO pDTO = userService.UserIdCheck(user_id);

        if (pDTO.getUser_email().equals(EncryptUtil.encAES128CBC(user_email))) {


            //인증번호를 위한 난수 발생
            Random random = new Random();
            Random_Pin = random.nextInt(888888) + 111111;

            log.info("Random_Pin : " + Random_Pin);

            // 메일 전송을 위한 로직

            mDTO.setToMail(user_email);
            mDTO.setTitle("Travel 인증번호 발송");
            mDTO.setContents("인증번호 : " + Random_Pin);
        }

        HashMap<String, Integer> rMap = new HashMap<>();
        //메일 발송 결과 확인
        res3 = mailService.doSendmail(mDTO);
        log.info("메일 발송 결과는? : " + res3);

        rMap.put("Random_Pin", Random_Pin);
        rMap.put("result", res3);
        log.info(this.getClass().getName() + "이메일 인증 종료 !!");
        return rMap;
    }

    @PostMapping(value = "user/UserUpdatePwdProc")
        // 비밀번호 변경 로직시작!
    String UserUpdatePwdProc(HttpServletRequest request, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "유저 비밀번호 찾기 로직 시작!");
        String msg = "";
        String url = "";

        String user_id = CmmUtil.nvl(request.getParameter("user_id"));
        String user_pwd = CmmUtil.nvl(request.getParameter("user_pwd2"));
        UserDTO pDTO = new UserDTO();

        pDTO.setUser_id(user_id);
        pDTO.setUser_pwd(EncryptUtil.encHashSHA256(user_pwd));

        /*
         * rDTO가 아닌 int로 받은 이유는 비밀번호는 Hash암호화 되어있어 조회가 불가능
         * update문을 통해 새로운 비밀번호로 변경
         * update결과는 0,1
         * */
        int res = userService.findUserPwd(pDTO);

        if (res == 1) {
            msg = "비밀번호 변경을 완료했습니다 다시 로그인해 주세요";
            url = "/user/login";
        } else {
            msg = "비밀번호 변경에 실패하였습니다 다시 시도해 주세요";
            url = "/user/login";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "유저 비밀번호 찾기 로직 종료!");

        return "redirect";
    }

    @GetMapping(value = "user/userDelete") //회원 탈퇴 페이지
    public String userDelete() {
        log.info(this.getClass().getName() + "회원탈퇴 페이지 시작!");
        log.info(this.getClass().getName() + "회원 탈퇴 페이지 종료!");

        return "user/userDelete";
    }

    @PostMapping(value = "user/UserDeleteProc") //회원탈퇴 로직 시작
    public String UserDeleteProc(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + "회원 탈퇴 로직 시작!");

        String msg = "";
        String url = "";
        int res = 0;

        String user_id = (String) session.getAttribute("user_id");
        int user_seq = (int) session.getAttribute("user_seq");
        String auth_text = CmmUtil.nvl(request.getParameter("auth_text"));

        UserDTO pDTO = new UserDTO();
        pDTO.setUser_id(user_id);
        pDTO.setUser_seq(user_seq);

        if (auth_text.equals("탈퇴하겠습니다")) {
            res = userService.DeleteUserInfo(pDTO);
        }

        if (res == 1) {
            msg = "회원탈퇴가 완료되었습니다";
            url = "/user/login";
        } else {
            msg = "다시 입력해 주세요";
            url = "/user/userDelete";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "회원 탈퇴 로직 종료!");

        return "redirect";

    }


}
