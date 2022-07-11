package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMarkDTO {
    private int file_seq;
    private int bookmark_seq;
    private int user_seq;
    private int board_user_seq;
    private int board_seq;
    private int board_cnt;
    private String file_detail_path;
}
