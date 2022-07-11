package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Travel_BoardDTO {
    private int board_seq;
    private int user_seq;
    private int file_seq;
    private String start_day;
    private String end_day;
    private String notification;
    private String board_views;
    private String board_like;
    private String user_id;
}
