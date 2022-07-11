package kopo.poly.persistance.mapper;

import kopo.poly.dto.Board_PostDTO;
import kopo.poly.dto.NlpDTO;
import kopo.poly.dto.SentenceDTO;
import kopo.poly.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ITensorMapper {

    List<Board_PostDTO> selectNlpResult() throws Exception;

    int insertNlpResult(NlpDTO nDTO) throws Exception;

    List<SentenceDTO> selectSentenceContent()throws Exception;
}
