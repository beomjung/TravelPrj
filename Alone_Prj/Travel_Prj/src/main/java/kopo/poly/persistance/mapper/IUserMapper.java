package kopo.poly.persistance.mapper;

import kopo.poly.dto.Board_PostDTO;
import kopo.poly.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserMapper {

    /*
     * 회원 로그인 체크
     * @param pDTO 유저 id, pwd
     * @return 조회 결과
     * */
    UserDTO UserLogin(UserDTO pDTO) throws Exception;

    /*
     * 회원 가입
     * @param pDTO 회원 가입 정보
     * @return insert 결과 0, 1
     * */
    int InsertUserInfo(UserDTO pDTO) throws Exception;

    /*
     * 회원 아이디 중복 체크
     * @param String 회원 아이디
     * @return 조회 결과 null or !null
     * */
    UserDTO UserIdCheck(String user_id) throws Exception;

    /*
     * 회원 이메일 충복 체크
     * @param String 회원 이메일
     * @return 조회 결과 null or !null
     * */
    UserDTO UserEmailCheck(String user_email) throws Exception;

    /*
     * 회원 아이디 찾기
     * @param pDTO 회원 이름, 회원 이메일
     * @return 조회 결과(회원 아이디)
     * */
    UserDTO findUserId(UserDTO pDTO) throws Exception;

    /*
     * 회원 비밀번호 업데이트
     * @param pDTO 회원 아이디, 이메일
     * @return 비밀번호 업데이트 결과
     * */
    int findUserPwd(UserDTO pDTO) throws Exception;

    /*
     * 회원 탈퇴
     * @param String 회원 아이디
     * @return 회원 삭제 결과
     * */
    int DeleteUserInfo(String user_id) throws Exception;

    /*
     * 회원 관심정보 입력
     * @param String 유저 관심정보
     * @return 관심정보 입력 결과
     * */
    int insertUserInterest(UserDTO pDTO) throws Exception;

    /*
    * 회원 관심도시 정보 입력
    * @param String 유저 시퀀스, 관심도시
    * @return 관심정보 입력 결과
    * */
    int insertUserInterestCity(UserDTO pDTO) throws Exception;

    /*
     * 회원 관심정보 조회
     * @parma String user_id
     * @return 조회결과
     * */
    List<UserDTO> selectUserInterest(int user_seq) throws Exception;

    /*
     * 회원 대표사진 변경
     * @param 유저id, file_seq
     * @return 변경 결과
     * */
    int updateUserImage(UserDTO pDTO) throws Exception;

    /*
     * 게시판 리스트 유저 닉네임 소개글 가져오기
     * @return 조회결과
     * */
    UserDTO userNickImgPath(int board_seq) throws Exception;

    /*
     * 마이페이지 유저 소개글 가져오기
     * @param user_seq
     * @return 조회결과
     * */
    UserDTO userMyPagePath(UserDTO pDTO) throws Exception;

    /*
     * 팔로우 하기
     * @param user_seq, board_user_seq
     * @return 결과
     * */
    int insertFollow(UserDTO pDTO) throws Exception;

    /*
     * 팔로우 취소
     * @param user_seq, board_user_seq
     * @return 결과
     * */
    int deleteFollow(UserDTO pDTO) throws Exception;

    /*
     * 팔로우 조회 결과
     * @param board_user_seq
     * @return 조회 결과
     * */
    int selectFollower(UserDTO pDTO) throws Exception;

    /*
     * 팔로잉 조회 결과
     * @param board_user_seq
     * @return 조회 결과
     * */
    int selectFollowing(UserDTO pDTO) throws Exception;

    /*
     * 팔로잉 했는지 안했는지 확인
     * @param user_seq, board_user_seq;
     * @return 조회 결과
     * */
    int authFollower(UserDTO pDTO) throws Exception;

    /*
    * 마이페이지 file path 가져오기
    * @parma user_seq
    * @return 조회 결과
    * */
    String selectUserFilePath(int user_seq) throws Exception;

    /*
    * 이메일 중복확인
    * @parma user_email
    * @return 조회 결과 UserDTO
    * */
    UserDTO userEmailCheck(String user_email) throws Exception;

    UserDTO boarduserNickImgPath(int user_seq) throws Exception;

    /*
    * 회원 탈퇴시 북마크 삭제
    * @param user_seq
    * @return 삭제 결과
    * */
    int deleteBookMarkUser(int user_seq) throws Exception;

    /*
    * 회원 탈퇴시 파일 상세정보 삭제
    * @param user_id
    * @return 삭제 결과
    * */
    int deleteFileDetailUser(String user_id) throws Exception;

    /*
     * 회원 탈퇴시 파일 정보 삭제
     * @param user_seq
     * @return 삭제 결과
     * */
    int deleteFileInfoUser(int user_seq) throws Exception;





}
