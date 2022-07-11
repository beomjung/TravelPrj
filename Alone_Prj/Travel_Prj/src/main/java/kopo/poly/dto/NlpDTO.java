package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NlpDTO {
    private String nlp_age;
    private String nlp_gender;
    private String nlp_content;
    private String nlp_addr;
    private String nlp_result;
    private String areacode;
    private String detail_areacode;
    private int res;
    private int good;
    private int bad;
    private int nlp_res;
}
