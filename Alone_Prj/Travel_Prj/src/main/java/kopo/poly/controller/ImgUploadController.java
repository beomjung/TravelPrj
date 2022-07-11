package kopo.poly.controller;

import kopo.poly.dto.FileDetailDTO;
import kopo.poly.dto.FileInfoDTO;
import kopo.poly.dto.UserDTO;
import kopo.poly.service.IFileUploadService;
import kopo.poly.service.IS3UploadService;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ImgUploadController {

    @Resource(name = "S3UploadService")
    private IS3UploadService s3UploadService;

    @Resource(name = "FileUploadService")
    private IFileUploadService fileUploadService;

    @Resource(name = "UserService")
    private IUserService userService;


    @ResponseBody
    @PostMapping(value = "img/imgUpload")
    public Map<String, String> imgUpload(HttpServletRequest request, HttpSession session,
                                         @RequestParam(value = "fileUpload") MultipartFile mf) throws Exception {
        log.info(this.getClass().getName() + "이미지 업로드 컨트롤러 시작!");

        int res = 0;
        int res2 = 0;
        int res3 = 0;


        //누가 등록자, 수정자 파악용

        String user_id = (String) session.getAttribute("user_id");

        int user_seq = 4;
        //파일 원본파일명 가져오기
        String originalFileName = mf.getOriginalFilename();
        //파일 뒤 확장자만 가져오기
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

        HashMap<String, String> rMap = null;

        //위조건에 만족하는 확장자만 실행
        if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {
            // 저장되는 파일이름 (영어, 숫자로 파일명 변경)
            String saveFileName = DateUtil.getDateTime("24hhmmss") + "." + ext;
            //저장경로
            String saveFilePath = s3UploadService.fileUpload(mf, saveFileName);
            //풀 경로
            String fullFileInfo = saveFilePath + "/" + saveFileName;

            String file_code = CmmUtil.nvl(request.getParameter("file_code"));

            log.info("user_id : " + user_id);
            log.info("originalFileName : " + originalFileName);
            log.info("ext : " + ext);
            log.info("saveFileName : " + saveFileName);
            log.info("fullFileInfo : " + fullFileInfo);
            log.info("file_code : " + file_code);

            /*----------------파일정보 저장---------------------*/
            FileInfoDTO fDTO = new FileInfoDTO();

            fDTO.setUser_id(user_id);
            fDTO.setUser_seq(user_seq);
            fDTO.setFile_code(file_code);
            fDTO.setFile_chg_id(user_id);
            fDTO.setFile_reg_dt(user_id);

            res = fileUploadService.insertFileInfoDB(fDTO);
            log.info("리턴된 파일 seq 번호" + res);
            /*------------------------------------------------*/

            if(res >=1) {
                /*----------------파일상세정보 저장---------------------*/
                FileDetailDTO gDTO = new FileDetailDTO();

                gDTO.setFile_detail_name(saveFileName);
                gDTO.setFile_detail_path(saveFilePath);
                gDTO.setFile_detail_orgName(originalFileName);
                gDTO.setFile_detail_ext(ext);
                gDTO.setFile_chg_id(user_id);
                gDTO.setFile_reg_id(user_id);
                gDTO.setFile_seq(res);
                /*----------------------------------------------------*/
                res2 = fileUploadService.insertFileDetailDB(gDTO);
            }

            if(res2==1){
                UserDTO pDTO = new UserDTO();
                log.info("res 결과는? : " + res);
                pDTO.setFile_seq(res);
                pDTO.setUser_id(user_id);

                res3 = userService.updateUserImage(pDTO);
            }

            rMap = new HashMap<>();

            if(res3 == 1) {
                rMap.put("fullFileInfo", fullFileInfo);
            }

            log.info("res3 : " + res3);

        }

        rMap.put("user_file_seq", String.valueOf(res));

        log.info(this.getClass().getName() + "이미지 업로트 컨트롤러 종료!");
        return rMap;
    }
}
