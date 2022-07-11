package kopo.poly.service.impl;

import kopo.poly.dto.FileDetailDTO;

import kopo.poly.dto.FileInfoDTO;
import kopo.poly.persistance.mapper.IMainMapper;
import kopo.poly.service.IFileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service("FileUploadService")
public class FileUploadService implements IFileUploadService {

    public final IMainMapper mainMapper;

    @Autowired
    public FileUploadService(IMainMapper mainMapper) {
        this.mainMapper = mainMapper;
    }

    @Override
    public int insertFileInfoDB(FileInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName()+"파일정보 서비스 저장 시작!!");
        if(pDTO == null){
            pDTO = new FileInfoDTO();
        }

        int res = mainMapper.fileInfoDB(pDTO);

        if(res == 1){
            int fseq = pDTO.getFile_seq();
            log.info("fseq : " + fseq);
            log.info(this.getClass().getName()+"파일정보 서비스 저장 종료!!");
            return fseq;

        }else{
            log.info(this.getClass().getName()+"파일정보 서비스 저장할떄 오류 발생!!");
            return res;
        }

    }

    @Override
    public int insertFileDetailDB(FileDetailDTO fDTO) throws Exception {
        log.info(this.getClass().getName()+"파일 상세정보 서비스 저장 시작!!");
        if(fDTO == null){
            fDTO = new FileDetailDTO();
        }
        int res = mainMapper.fileDetailDB(fDTO);

        log.info(this.getClass().getName()+"파일 상세정보 서비스 저장 종료!!");
        return res;
    }
}
