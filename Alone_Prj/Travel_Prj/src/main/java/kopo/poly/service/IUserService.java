package kopo.poly.service;

import kopo.poly.dto.Board_PostDTO;
import kopo.poly.dto.UserDTO;

import java.util.List;

public interface IUserService {
    //로그인 체크
    UserDTO UserLogin(UserDTO pDTO) throws Exception;

    //회원가입
    int InsertUserInfo(UserDTO pDTO) throws Exception;

    //회원 ID 중복 체크
    UserDTO UserIdCheck(String user_id) throws Exception;

    //회원 Email 중복 체크
    UserDTO UserEmailCheck(String user_email) throws Exception;

    // 회원 아이디 찾기
    UserDTO findUserId(UserDTO pDTO) throws Exception;

    // 회원 비밀번호 변경
    int findUserPwd(UserDTO pDTO)throws Exception;

    //회원 탈퇴
    int DeleteUserInfo(UserDTO pDTO) throws Exception;

    //회원 관심정보 입력
    int insertUserInterest(UserDTO pDTO) throws Exception;
    //회원 관심도시 정보 입력
    int insertUserInterestCity(UserDTO pDTO) throws Exception;

    //회원 관심정보 조회
    List<UserDTO> selectUserInterest(int user_seq) throws Exception;

    //유저 대표 이미지 변경
    int updateUserImage(UserDTO pDTO) throws Exception;

    //유저 닉네임 이미지 사진 가져오기
    List<UserDTO> userNickImgPath(List<Board_PostDTO> rList) throws Exception;

    //마이페이지 유저 정보 가져오기
    UserDTO userMyPagePath(UserDTO pDTO) throws Exception;

    //팔로우하기
    int insertFollow(UserDTO pDTO) throws Exception;

    //팔로우 취소하기
    int deleteFollow(UserDTO pDTO)throws Exception;

    //팔로우 조회결과
    int selectFollower(UserDTO pDTO) throws Exception;

    //팔로잉 조회결과
    int selectFollowing(UserDTO pDTO) throws Exception;

    //팔로잉 했는지 안했는지 조회 결과
    int authFollower(UserDTO pDTO) throws Exception;

    //유저 이미지 가져오기
    String selectUserFilePath(int user_seq) throws Exception;

    //유저 이메일 중복 체크
    UserDTO userEmailCheck(String user_email) throws Exception;

    UserDTO boarduserNickImgPath(int user_seq) throws Exception;


}
