package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileInfoDTO {
    private int file_seq;
    private int user_seq;
    private String user_id;
    private String file_code;
    private String file_reg_id;
    private String file_reg_dt;
    private String file_chg_id;
    private String file_chg_dt;
}
