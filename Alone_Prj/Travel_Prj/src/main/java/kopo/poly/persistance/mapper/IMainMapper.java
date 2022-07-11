package kopo.poly.persistance.mapper;

import kopo.poly.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMainMapper {
    /*
     * 회원정보 수정
     * @param pDTO 회원정보
     * @return 업데이트 결과
     * */
    int updateUserInfo(UserDTO pDTO) throws Exception;

    /*
     * 이미지 파일 없이 없데이트
     * */
    int updateUser(UserDTO pDTO) throws Exception;

    /*
     * 유저 수정 후 이미지 불러오기
     * @param UserDTO
     * @return 조회겨로가
     * */
    String selectUserImg(int user_seq) throws Exception;

    /*
     * 마이 페이지 정보 가져오기
     * @param user_seq
     * @return 조회결과
     * */
    UserDTO selectMyPage(int user_seq) throws Exception;


    /*
     * 파일 정보 DB 저장
     * @param fDTO 파일정보
     * @return 저장결과
     * */
    int fileInfoDB(FileInfoDTO pDTO) throws Exception;

    /*
     * 파일 상세정보 DB 저장
     * @param gDTO 파일 상세정보
     * @return 저장결과
     * */
    int fileDetailDB(FileDetailDTO fDTO) throws Exception;

    /*
     * bookmark insert
     * @parma user_seq, board_seq, board_user_seq
     * @insert 결과
     * */
    int insertBookMark(BookMarkDTO bDTO) throws Exception;

    /*
     * 북마크 조회
     * @return 조회결과
     * */
    List<BookMarkDTO> selectBookMark(int user_seq) throws Exception;

    /*
     * 마이페이지 작성 게시물 조회
     * @param user_seq
     * @return 조회결과
     * */
    List<UserDTO> selectMyPagePost(int user_seq) throws Exception;

    /*
     * 팔로잉 유저 조회
     * @param user_seq
     * @return 조회 결과
     * */
    List<FollowDTO> selectFollowee(int user_seq) throws Exception;

    /*
     * 팔로우 유저 조회
     * @param user_seq
     * @return 조회 결과
     * */
    List<FollowDTO> selectFollower(int user_seq) throws Exception;

    /*
     * 팔로잉 게시물 조회
     * @param user_seq
     * @return 조회 결과
     * */
    List<Board_PostDTO> selectFollowPost(int user_seq) throws Exception;

    /*
     * 팔로우, 팔로잉 유저 정보 조회
     * @param user_Seq
     * @return 조회결과
     * */
    FollowDTO selectFollowUserInfo(int user_seq) throws Exception;

    /*
     * 회원 관심정보 조회
     * @param user_seq
     * @return 조회 결과
     * */
    List<UserDTO> selectUserInterest(int user_seq) throws Exception;

    /*
     * 사용자 nlp결과 여행 추천
     * @param 지역코드
     * @return 결과
     * */
    List<NlpDTO> recommendTravel(NlpDTO pDTO) throws Exception;

    /*
     * 사용자 nlp 결과 조회
     * @param 관광지 이름
     * @return 조회 결과
     * */
    NlpDTO recommendStar(String nlp_addr) throws Exception;

    /*
     * 사용자 주소 가져오기
     * @param 유저 seq
     * @return 조회 결과
     * */
    UserDTO selectUserAddr(int user_seq) throws Exception;

    /*
     * 사용자 RoomKey 가져오기
     * @param 유저 seq
     * @return 조회결과
     * */
    UserDTO selectUserRoom(int user_seq) throws Exception;

    /*
     * 사용자 Roomkey 업데이트
     * @param 유저 seq
     * @return 조회 결과
     * */
    int updateUserRoom(UserDTO pDTO) throws Exception;

    /*
    * 유저 RoomList 조회
    * @return 조회결과
    * */
    List<UserDTO> selectUserRoomList()throws Exception;

}
