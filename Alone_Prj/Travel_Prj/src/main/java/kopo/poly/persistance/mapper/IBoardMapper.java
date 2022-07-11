package kopo.poly.persistance.mapper;


import kopo.poly.dto.Board_PostDTO;
import kopo.poly.dto.BookMarkDTO;
import kopo.poly.dto.Travel_BoardDTO;
import kopo.poly.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IBoardMapper {

    /*
    * 게시판 정보저장
    * @param 유저 아이디, 출발일, 도착일
    * @return 저장 결과
    * */
    int insertBoardInfo(Travel_BoardDTO bDTO) throws Exception;

    /*
    * 게시글 정보 저장
    * @param 제목, 위치, 게시글, 평점
    * @return 저장 결과
    * */
    int insertBoardDetail(Board_PostDTO dDTO) throws Exception;

    /*
    * 게시글 정보 가져오기
    * @param 게시판 SEQ
    * @return 조회결과
    * */
    List<Board_PostDTO> selectBoardContent(Board_PostDTO pDTO) throws Exception;

    /*
    * 게시판 리스트 가져오기
    * @return board_seq, board_post_title, file_detail_path
    * */
    List<Board_PostDTO> selectBoardList()throws Exception;

    /*
    * 사용자 작성 게시물 cnt 조회
    * @param board_user_seq;
    * @return 조회 결과(int)
    * */
    int selectBoardCnt(UserDTO pDTO) throws Exception;

    /*
    * 게시글 삭제
    * @param  board_seq
    * @return 삭제 결과
    * */
    int deleteBoard(Board_PostDTO pDTO) throws Exception;
    /*
     * 자식 게시글 삭제
     * @param  board_seq
     * @return 삭제 결과
     * */
    int deleteBoardPost(Board_PostDTO pDTO) throws Exception;
    /*
    * 파일정보 삭제
    * @Parma file_seq
    * @return 삭제 결과
    * */
    int deleteFileInfo(Board_PostDTO pDTO) throws Exception;

    /*
     * 파일 상세정보 삭제
     * @Parma file_seq
     * @return 삭제 결과
     * */
    int deleteFileDetailInfo(Board_PostDTO pDTO) throws Exception;

    /*
    * 게시판 삭제시 북마크 삭제
    * @param board Board_PostDTO
    * @return 삭제 결과
    * */
    int deletebookMark(Board_PostDTO pDTO) throws Exception;

    /*
    * 부모 게시판 수정
    * @param Travel_BoardDTO
    * @return 수정 결과
    * */
    int updateTravelBoard(Travel_BoardDTO bDTO) throws Exception;

    /*
    * 자식 게시판 수정
    * @param Board_PostDTO
    * @return 수정 결과
    * */
    int updateBoardPost(Board_PostDTO dDTO) throws Exception;

    /*
    * 자식 게시시판 파일 수정
    * @param Board_PostDTO
    * @return 수정 결과
    * */
    int updateBoardFile(Board_PostDTO nDTO) throws Exception;

    /*
    * 북마크 조회
    * @북마크 했는지 조회
    * */
    BookMarkDTO selectBookMarkSeq(BookMarkDTO bDTO) throws Exception;

    int deleteBookMarkBoard(int board_seq) throws Exception;




}
