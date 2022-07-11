package kopo.poly.service;

import kopo.poly.dto.*;

import java.util.List;

public interface IMainService {

    // 회원정보 수정
    int updateUserInfo(UserDTO pDTO) throws Exception;

    //이미지 파일 없이 수정
    int updateUser(UserDTO pDTO) throws Exception;

    // 회원정보 수정 후 이미지 가젹오기
    String selectUserImg(int user_seq) throws Exception;

    //마이페이지 정보 가져오기
    UserDTO selectMyPage(int user_seq) throws Exception;

    //북마크 인서트
    int insertBookMark(BookMarkDTO bDTO) throws Exception;

    //북마크 조회
    List<BookMarkDTO> selectBookMark(int user_seq) throws Exception;

    //마이페이지 작성 게시물 조회
    List<UserDTO> selectMyPagePost(int user_seq) throws Exception;

    //팔로잉 유저 게시물 조회
    List<Board_PostDTO> selectFollowPost(int user_seq) throws Exception;

    //팔로잉 유저 조회
    List<FollowDTO> selectFollowee(int user_seq) throws Exception;

    //팔로우 유저 조회
    List<FollowDTO> selectFollower(int user_seq) throws Exception;

    //유저 관심정보 조회
    List<TravelLocDTO> selectUserInterest(int user_seq) throws Exception;

    //자연어처리 여행지 추천
    List<TravelLocDTO> recommendTravel(NlpDTO pDTO) throws Exception;

    //자연어 처리 결과 가져오기
    List<NlpDTO> userNlpRes(NlpDTO pDTO) throws Exception;

    //유저 주소 가져오기
    UserDTO selectUserAddr(int user_seq) throws Exception;

    //사용자 룸키 가져오기
    UserDTO selectUserRoom(int user_seq) throws Exception;

    //유저 룸 리스트 조회
    List<UserDTO> selectUserRoomList() throws Exception;

    //대화 정보 조회하기
    List<ChatDTO> getChatInfo(String roomkey) throws Exception;
}


