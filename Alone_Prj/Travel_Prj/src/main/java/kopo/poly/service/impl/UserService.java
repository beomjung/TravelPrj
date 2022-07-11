package kopo.poly.service.impl;

import kopo.poly.dto.Board_PostDTO;
import kopo.poly.dto.UserDTO;
import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("UserService")
public class UserService implements IUserService {

    private final IUserMapper userMapper;

    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDTO UserLogin(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "유저로그인 서비스 시작!");
        log.info(this.getClass().getName() + "유저로그인 서비스 종료!");

        return userMapper.UserLogin(pDTO);
    }

    @Override
    @Transactional
    public int InsertUserInfo(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "회원가입 서비스 시작!");
        int res = 0;
        int user_seq = 0;

        if (pDTO == null) {
            pDTO = new UserDTO();
        }
        res = userMapper.InsertUserInfo(pDTO);

        if (res == 1) {
            user_seq = pDTO.getUser_seq();
            log.info("회원가입 유저 seq : " + user_seq);
            log.info(this.getClass().getName() + "회원가입 서비스 종료!");
            return user_seq;
        } else {
            return res;
        }
    }

    @Override
    @Transactional
    public UserDTO UserIdCheck(String user_id) throws Exception {
        log.info(this.getClass().getName() + "유저 아이디 중복 체크 서비스 시작!");

        log.info(this.getClass().getName() + "유저 아이디 중복 체크 서비스 종료!");
        return userMapper.UserIdCheck(user_id);
    }

    @Override
    @Transactional
    public UserDTO UserEmailCheck(String user_email) throws Exception {
        log.info(this.getClass().getName() + "유저 이메일 중복 체크 서비스 시작!");
        log.info(this.getClass().getName() + "유저 이메일 중복 체크 서비스 종료!");
        return userMapper.UserEmailCheck(user_email);
    }

    @Override
    @Transactional
    public UserDTO findUserId(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "유저 아이디찾기 서비스 시작!");
        if (pDTO == null) {
            pDTO = new UserDTO();
        }
        UserDTO rDTO = new UserDTO();
        rDTO = userMapper.findUserId(pDTO);

        log.info(this.getClass().getName() + "유저 아이디찾기 서비스 종료!");
        return rDTO;
    }

    @Override
    @Transactional
    public int findUserPwd(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "유저 비밀번호 변경 서비스 시작!");

        int res = 0;
        if (pDTO == null) {
            pDTO = new UserDTO();
        }
        res = userMapper.findUserPwd(pDTO);
        log.info(this.getClass().getName() + "유저 비밀번호 변경 서비스 종료!");

        return res;
    }

    @Override
    @Transactional
    public int DeleteUserInfo(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "회원 탈퇴 서비스 시작!");
        if (pDTO == null) {
            pDTO = new UserDTO();
        }

        int res = 0; //북마크 삭제 결과
        int res1 = 0; // 파일 상세정보 삭제 결과
        int res2 = 0; // 파일정보 삭제 결과
        int res3 = 0; //유저 삭제

        res = userMapper.deleteBookMarkUser(pDTO.getUser_seq());
        res1 = userMapper.deleteFileDetailUser(pDTO.getUser_id());
        res2 = userMapper.deleteFileInfoUser(pDTO.getUser_seq());
        res3 = userMapper.DeleteUserInfo(pDTO.getUser_id());

        log.info(this.getClass().getName() + "회원 탈퇴 서비스 종료!");
        return res3;
    }

    @Override
    @Transactional
    public int insertUserInterest(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "유저 관심정보 입력 서비스 시작!");

        int res = 0;

        if (pDTO == null) {
            pDTO = new UserDTO();
        }

        res = userMapper.insertUserInterest(pDTO);
        log.info(this.getClass().getName() + "유저 관심정보 입력 서비스 종료!");

        return res;
    }

    @Override
    public int insertUserInterestCity(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "유저 관심정보 입력 서비스 시작!");

        int res = 0;

        if (pDTO == null) {
            pDTO = new UserDTO();
        }

        res = userMapper.insertUserInterestCity(pDTO);
        log.info(this.getClass().getName() + "유저 관심정보 입력 서비스 종료!");

        return res;
    }

    @Override
    public List<UserDTO> selectUserInterest(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "유저 관심정보 조회 종료!");

        List<UserDTO> rList = userMapper.selectUserInterest(user_seq);

        log.info(this.getClass().getName() + "유저 관심정보 조회 종료!");
        return rList;
    }

    @Override
    public int updateUserImage(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "유저 이미지 변경 서비스 시작!");

        int res = 0;

        if (pDTO == null) {
            pDTO = new UserDTO();
        }
        res = userMapper.updateUserImage(pDTO);

        log.info(this.getClass().getName() + "유저 이미지 변경 서비스 종료!");
        return res;
    }

    @Override
    public List<UserDTO> userNickImgPath(List<Board_PostDTO> rList) throws Exception {
        log.info(this.getClass().getName() + "유저 이미지 사진 및 닉네임 가져오기 서비스 시작!");

        List<UserDTO> pList = new ArrayList<>();
        UserDTO pDTO = new UserDTO();

        log.info("가져온 게시판 시퀀스 갯수는 : " + rList.size());
        for (int i = 0; i < rList.size(); i++) {
            pDTO = new UserDTO();

            pDTO = userMapper.userNickImgPath(rList.get(i).getBoard_seq());
            log.info("시퀀스 번호를 보자" + rList.get(i).getBoard_seq());
            pList.add(pDTO);
        }
        log.info("pDTO에 담긴 값은 ? " + pList.size());
        log.info(this.getClass().getName() + "유저 이미지 사진 및 닉네임 가져오기 서비스 종료!");
        return pList;
    }

    @Override
    public UserDTO userMyPagePath(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "마이페이지 유저 정보 가져오기 시작!");

        if (pDTO == null) {
            pDTO = new UserDTO();
        }

        UserDTO rDTO = userMapper.userMyPagePath(pDTO);

        log.info("서비스 rDTO 결과" + rDTO.getUser_nickname());

        log.info(this.getClass().getName() + "마이페이지 유저 정보 가져오기 종료!");
        return rDTO;
    }

    @Override
    public int insertFollow(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "팔로우 서비스 시작!");

        int res = 0;

        if (pDTO == null) {
            pDTO = new UserDTO();
        }

        res = userMapper.insertFollow(pDTO);

        log.info(this.getClass().getName() + "팔로우 서비스 종료!");

        return res;
    }

    @Override
    public int deleteFollow(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "팔로우 취소하기 서비스 시작!");

        int res = 0;

        if (pDTO == null) {
            pDTO = new UserDTO();
        }

        res = userMapper.deleteFollow(pDTO);

        log.info(this.getClass().getName() + "팔로우 취소하기 서비스 종료!");
        return res;
    }

    @Override
    public int selectFollower(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "팔로우 조회하기 서비스 시작!");

        int res = 0;
        if (pDTO == null) {
            pDTO = new UserDTO();
        }

        res = userMapper.selectFollower(pDTO);
        log.info(this.getClass().getName() + "팔로우 조회하기 서비스 종료!");
        return res;
    }

    @Override
    public int selectFollowing(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "팔로잉 조회하기 서비스 시작!");

        int res2 = 0;
        if (pDTO == null) {
            pDTO = new UserDTO();
        }
        res2 = userMapper.selectFollowing(pDTO);
        log.info(this.getClass().getName() + "팔로잉 조회하기 서비스 종료!");
        return res2;
    }

    @Override
    public int authFollower(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "팔로잉 확인 서비스 시작!");

        int res = 0;
        if (pDTO == null) {
            pDTO = new UserDTO();
        }

        res = userMapper.authFollower(pDTO);

        log.info(this.getClass().getName() + "팔로잉 확인 서비스 종료!");
        return res;
    }

    @Override
    public String selectUserFilePath(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "마이페이지 유저 이미지 가져오기 서비스 시작!");

        String user_detail_path = userMapper.selectUserFilePath(user_seq);

        log.info(this.getClass().getName() + "마이페이지 유저 이미지 가져오기 서비스 종료!");
        return user_detail_path;
    }

    @Override
    @Transactional
    public UserDTO userEmailCheck(String user_email) throws Exception {
        log.info(this.getClass().getName() + "유저 이메일 중복체크 시작!");

        UserDTO pDTO = userMapper.userEmailCheck(user_email);

        log.info(this.getClass().getName() + "유저 이메일 중복체크 종료!");
        return pDTO;
    }

    @Override
    @Transactional
    public UserDTO boarduserNickImgPath(int user_seq) throws Exception {
        log.info(this.getClass().getName() + "유저 이미지 조회 시작!");

        UserDTO pDTO = userMapper.boarduserNickImgPath(user_seq);
        return pDTO;
    }


}
