package kopo.poly.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDetailDTO{

    private int file_seq;
    private String file_detail_name;
    private String file_detail_path;
    private String file_detail_orgName;
    private String file_detail_ext;
    private String file_reg_id;
    private String file_reg_dt;
    private String file_chg_id;
    private String file_chg_dt;

}
