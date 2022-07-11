package kopo.poly.service.impl;

import kopo.poly.dto.Board_PostDTO;
import kopo.poly.dto.BookMarkDTO;
import kopo.poly.dto.Travel_BoardDTO;
import kopo.poly.dto.UserDTO;
import kopo.poly.persistance.mapper.IBoardMapper;
import kopo.poly.service.IBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("BoardService")
public class BoardService implements IBoardService {

    public final IBoardMapper boardMapper;

    @Autowired
    public BoardService(IBoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    @Transactional
    public int insertBoardInfo(Travel_BoardDTO bDTO) throws Exception {

        if (bDTO == null) {
            bDTO = new Travel_BoardDTO();
        }
        int res = boardMapper.insertBoardInfo(bDTO);

        if (res == 1) {
            int bseq = bDTO.getBoard_seq();
            return bseq;
        } else {
            return res;
        }
    }

    @Override
    @Transactional
    public int insertBoardDetailInfo(Board_PostDTO dDTO) throws Exception {
        if (dDTO == null) {
            dDTO = new Board_PostDTO();
        }

        int res = boardMapper.insertBoardDetail(dDTO);

        return res;
    }

    @Override
    @Transactional
    public List<Board_PostDTO> selectBoardContent(Board_PostDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "게시판 정보 가져오기 서비스 시작!!");

        List<Board_PostDTO> rList = null;

        if (pDTO == null) {
            pDTO = new Board_PostDTO();
        }
        rList = boardMapper.selectBoardContent(pDTO);
        log.info(this.getClass().getName() + "게시판 정보 가져오기 서비스 종료!!");
        return rList;
    }

    @Override
    @Transactional
    public List<Board_PostDTO> selectBoardList() throws Exception {
        log.info(this.getClass().getName() + "게시판 리스트 가져오기 서비스 시작!");

        List<Board_PostDTO> rList = new ArrayList<>();

        rList = boardMapper.selectBoardList();

        log.info(this.getClass().getName() + "게시판 리스트 가져오기 서비스 종료!");
        return rList;
    }

    @Override
    @Transactional
    public int selectBoardCnt(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "사용자 작성게시물 CNT 시작");
        int res = 0;

        if (pDTO == null) {
            pDTO = new UserDTO();
        }

        res = boardMapper.selectBoardCnt(pDTO);

        log.info(this.getClass().getName() + "사용자 작성게시물 CNT 종료");
        return res;
    }

    @Override
    @Transactional
    public int deleteBoard(Board_PostDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "게시글 삭제 서비스 시작!");

        int book_res = 0;
        int res = 0;
        int res2 = 0;
        int res3 = 0;

        if (pDTO == null) {
            pDTO = new Board_PostDTO();
        }

        book_res = boardMapper.deleteBookMarkBoard(pDTO.getBoard_seq());

        res = boardMapper.deleteFileDetailInfo(pDTO);

        res2 = boardMapper.deleteFileInfo(pDTO);

        res3 = boardMapper.deleteBoard(pDTO);

        log.info(this.getClass().getName() + "게시글 삭제 서비스 종료!");
        return res3;
    }

    @Override
    public int deleteBoardPost(Board_PostDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "자식 게시판 삭제!");
        if (pDTO == null) {
            pDTO = new Board_PostDTO();
        }
        int res = boardMapper.deleteBoardPost(pDTO);
        log.info("삭제 결과는 ? " + res);
        log.info(this.getClass().getName() + "자식 게시판 종료!");
        return res;
    }

    @Override
    @Transactional
    public int deletebookMark(Board_PostDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "북마크 삭제 서비스 시작!");
        int res = 0;

        if (pDTO == null) {
            pDTO = new Board_PostDTO();
        }

        res = boardMapper.deletebookMark(pDTO);

        log.info(this.getClass().getName() + "북마크 삭제 서비스 종료!");
        return 0;
    }

    @Override
    @Transactional
    public int updateTravelBoard(Travel_BoardDTO bDTO) throws Exception {
        log.info(this.getClass().getName() + "부모 게시판 수정 서비스 시작!");

        int res = 0;

        if (bDTO == null) {
            bDTO = new Travel_BoardDTO();
        }

        res = boardMapper.updateTravelBoard(bDTO);

        log.info(this.getClass().getName() + "부모 게시판 수정 서비스 종료!");
        return res;
    }

    @Override
    @Transactional
    public int updateBoardPost(Board_PostDTO dDTO) throws Exception {
        log.info(this.getClass().getName() + "자식 게시판 수정 서비스 시작!");

        int res = 0;

        if (dDTO == null) {
            dDTO = new Board_PostDTO();
        }

        res = boardMapper.updateBoardPost(dDTO);

        log.info(this.getClass().getName() + "자식 게시판 수정 서비스 종료!");
        return res;
    }

    @Override
    @Transactional
    public int updateBoardFile(Board_PostDTO nDTO) throws Exception {
        log.info(this.getClass().getName() + "게시판 파일 수정 시작!");
        int res = 0;

        if (nDTO == null) {
            nDTO = new Board_PostDTO();
        }

        res = boardMapper.updateBoardFile(nDTO);

        log.info(this.getClass().getName() + "게시판 파일 수정 종료!");
        return res;
    }

    @Override
    public BookMarkDTO selectBookMarkSeq(BookMarkDTO bDTO) throws Exception {
        log.info(this.getClass().getName() + "북마크 여부 확인 서비스 시작!");
        if (bDTO == null) {
            bDTO = new BookMarkDTO();
        }
        BookMarkDTO rDTO = boardMapper.selectBookMarkSeq(bDTO);

        log.info(this.getClass().getName() + "북마크 여부 확인 서비스 시작!");
        return rDTO;
    }
}
