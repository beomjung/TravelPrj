package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDTO {
    private String chat_id;
    private String chat_dt;
    private String cs_name; // 키
    private String chat_content;
}
