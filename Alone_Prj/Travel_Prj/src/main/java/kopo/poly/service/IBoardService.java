package kopo.poly.service;

import kopo.poly.dto.Board_PostDTO;
import kopo.poly.dto.BookMarkDTO;
import kopo.poly.dto.Travel_BoardDTO;
import kopo.poly.dto.UserDTO;

import java.util.List;

public interface IBoardService {

    //게시판 정보 저장
    int insertBoardInfo(Travel_BoardDTO bDTO) throws Exception;
    
    //게시판 상세 정보 저장
    int insertBoardDetailInfo(Board_PostDTO dto) throws Exception;

    //게시판 상세보기 가져오기
    List<Board_PostDTO> selectBoardContent(Board_PostDTO pDTO) throws Exception;

    //게시판 리스트 가져오기
    List<Board_PostDTO> selectBoardList()throws Exception;

    //사용자 작성 게시글 CNT
    int selectBoardCnt(UserDTO pDTO) throws Exception;

    //게시글 삭제
    int deleteBoard(Board_PostDTO pDTO) throws Exception;
    //자식 게시글 삭제
    int deleteBoardPost(Board_PostDTO pDTO) throws Exception;

    //북마크 삭제
    int deletebookMark(Board_PostDTO pDTO) throws Exception;

    // 부모 게시판 수정
    int updateTravelBoard(Travel_BoardDTO bDTO) throws Exception;

    //자식 게시판 수정
    int updateBoardPost(Board_PostDTO dDTO) throws Exception;

    //자식 게시판 파일 수정
    int updateBoardFile(Board_PostDTO nDTO) throws Exception;

    //뷱마크 조회
    BookMarkDTO selectBookMarkSeq(BookMarkDTO bDTO) throws Exception;



}
