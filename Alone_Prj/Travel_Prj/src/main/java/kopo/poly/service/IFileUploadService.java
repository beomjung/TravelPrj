package kopo.poly.service;

import kopo.poly.dto.FileDetailDTO;
import kopo.poly.dto.FileInfoDTO;

public interface IFileUploadService {


    // 게시판 파일 정보를 DB 저장
    int insertFileInfoDB(FileInfoDTO pDTO) throws Exception;
    // 게시판 파일 상세 정보를 DB 저장
    int insertFileDetailDB(FileDetailDTO fDTO) throws Exception;
}
