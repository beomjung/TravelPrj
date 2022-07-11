package kopo.poly.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board_PostDTO{
    private int board_seq;
    private int user_seq;
    private int file_seq;
    private int bookmark_seq;
    private int board_post_seq;

    private String areacode;
    private String detail_areacode;
    private String user_age;
    private String user_gender;
    private String board_start_day;
    private String board_end_day;
    private String user_file_path;
    private String user_nickname;
    private String file_detail_path;
    private String board_views;
    private String board_like;
    private String board_post_title;
    private String board_post_content;
    private String board_post_star;
    private String board_post_postion;
    private String board_post_hashTag;
    private String user_id;

}
